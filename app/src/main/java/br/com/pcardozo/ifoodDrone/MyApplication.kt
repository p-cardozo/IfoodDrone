package br.com.pcardozo.ifoodDrone

import android.app.Application
import br.com.pcardozo.ifoodDrone.di.AppModule
import br.com.pcardozo.ifoodDrone.di.NetworkModule
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(AppModule, NetworkModule)
        }

    }

}