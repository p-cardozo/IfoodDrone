package br.com.pcardozo.ifoodDrone.ui.cep

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.pcardozo.ifoodDrone.R
import br.com.pcardozo.ifoodDrone.model.CepModel
import br.com.pcardozo.ifoodDrone.ui.products.ProductsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.max
import kotlin.math.min

private const val STREET = "STREET_KEY"


class CepActivity : AppCompatActivity() {

    private val edtCep: EditText by lazy { findViewById(R.id.cep_edt) }

    private val cepViewModel: CepViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupsEditTextCep()
        initViewStates()
        goToProducts()


    }

    private fun initViewStates() {
        cepViewModel.viewStates.observe(this) { viewStates ->
            viewStates.let {
                when (it) {
                    is CepDataStates.CallSucess -> {
                        getCep(it.cep)
                    }

                    is CepDataStates.CallError -> {
                        buildError()
                    }
                }
            }

        }
    }

    private fun buildError() {
        Toast.makeText(this, "Digite um cep válido", Toast.LENGTH_LONG).show()
    }

    private fun getCep(cep: CepModel) {
        val pref: SharedPreferences = getSharedPreferences("PREF", MODE_PRIVATE)
        pref.edit().putString(STREET, cep.endereco).apply()
        val intent = ProductsActivity.newInstance(this)
        startActivity(intent)
    }

    private fun goToProducts() {
        findViewById<Button>(R.id.cep_btn_search).setOnClickListener {
            val cep = edtCep.text.toString()
                .replace("[.]".toRegex(), "")
                .replace("[-]".toRegex(), "")
            cepViewModel.interpret(CepInteractor.GetCep(cep))
        }
    }

    private fun setupsEditTextCep() {
        edtCep.addTextChangedListener(object : TextWatcher {
            var isUpdating = false
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, after: Int
            ) {

                // Quando o texto é alterado o onTextChange é chamado
                // Essa flag evita a chamada infinita desse método
                if (isUpdating) {
                    isUpdating = false
                    return
                }

                // Ao apagar o texto, a máscara é removida,
                // então o posicionamento do cursor precisa
                // saber se o texto atual tinha ou não, máscara
                val hasMask = s.toString().indexOf('.') > -1 ||
                        s.toString().indexOf('-') > -1

                // Remove o '.' e '-' da String
                var str = s.toString()
                    .replace("[.]".toRegex(), "")
                    .replace("[-]".toRegex(), "")

                // as variáveis before e after dizem o tamanho
                // anterior e atual da String, se after > before
                // é pq está apagando
                if (after > before) {
                    // Se tem mais de 5 caracteres (sem máscara)
                    // coloca o '.' e o '-'
                    if (str.length > 5) {
                        str = str.substring(0, 2) + '.' +
                                str.substring(2, 5) + '-' +
                                str.substring(5)

                        // Se tem mais de 2, coloca só o ponto
                    } else if (str.length > 2) {
                        str = str.substring(0, 2) + '.' +
                                str.substring(2)
                    }
                    // Seta a flag pra evitar chamada infinita
                    isUpdating = true
                    // seta o novo texto
                    edtCep.setText(str)
                    // seta a posição do cursor
                    edtCep.setSelection(edtCep.text.length)
                } else {
                    isUpdating = true
                    edtCep.setText(str)
                    // Se estiver apagando posiciona o cursor
                    // no local correto. Isso trata a deleção dos
                    // caracteres da máscara.
                    edtCep.setSelection(
                        max(
                            0, min(
                                if (hasMask) start - before else start,
                                str.length
                            )
                        )
                    )
                }
            }

            override fun afterTextChanged(s: Editable) {
                if (s.length == 10) {
                    findViewById<Button>(R.id.cep_btn_search).isEnabled = true
                }
            }
        })
    }
}