package com.d20.rpgdice

import android.content.Context

class Prefs(private val context:Context) {

    private val storage = context.getSharedPreferences(Constants.SHARED_PREFERENCES_SETTINGS,0)

    fun setDiceSoundInd(diceSoundInd:Boolean){
        storage.edit().putBoolean(Constants.DICE_SOUND_IND,diceSoundInd).apply()
    }

    fun getDiceSoundInd():Boolean{
        return storage.getBoolean(Constants.DICE_SOUND_IND, true)
    }

    fun setCriticalSoundInd(criticalSoundInd:Boolean){
        storage.edit().putBoolean(Constants.CRITICAL_SOUND_IND,criticalSoundInd).apply()
    }

    fun getCriticalSoundInd():Boolean{
        return storage.getBoolean(Constants.CRITICAL_SOUND_IND, true)
    }

    fun setFailSoundInd(failSoundInd:Boolean){
        storage.edit().putBoolean(Constants.FAIL_SOUND_IND,failSoundInd).apply()
    }

    fun getFailSoundInd():Boolean{
        return storage.getBoolean(Constants.FAIL_SOUND_IND, true)
    }



}