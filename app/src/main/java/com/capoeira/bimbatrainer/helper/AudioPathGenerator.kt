package com.capoeira.bimbatrainer.helper

import android.content.Context
import com.capoeira.bimbatrainer.R

class AudioPathGenerator {

    var context : Context

    constructor(context: Context) {
        this.context = context
    }

    fun generateNumberAudioPath(number : Int) : String {
        var numberPath = ""

        if(context != null) {
            if(number > 0 && number <= 8) {
                var numberBasePath = context?.getString(R.string.audio_number_basepath)
                numberPath = "${numberBasePath}${number}.mp3"
            }
        }
        return numberPath
    }

    fun generateSequenceTypeAudioPath(type : String) : String{

        return "${getAudioBasePath()}${type.lowercase()}.mp3"
    }

    fun generateSequencePartAudioPath(part : Char) : String {
        return "${getAudioBasePath()}${part.toString().lowercase()}.mp3"
    }

    fun getAudioBasePath() : String? {
        return context?.getString(R.string.audio_basepath)
    }
}