package org.byters.bctodo

import android.app.Application
import org.byters.bctodo.controller.AppModule

class ApplicationToDo : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = buildComponent()
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}