package com.d20.rpgdice

import android.app.Application

class RpgDiceApplication: Application() {

    companion object{
        lateinit var prefs:Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext
        )
    }
}