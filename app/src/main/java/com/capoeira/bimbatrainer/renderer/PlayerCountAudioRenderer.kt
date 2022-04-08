package com.capoeira.bimbatrainer.renderer

import android.content.Context
import android.content.res.AssetManager
import com.capoeira.bimbatrainer.helper.AudioPathGenerator

class PlayerCountAudioRenderer (audioPathGenerator : AudioPathGenerator?, assetManager: AssetManager) : AudioRenderer(assetManager) {

    var audioPathGenerator: AudioPathGenerator? = null

    var context: Context? = null

    init {
        this.audioPathGenerator = audioPathGenerator
    }

    override fun render(exerciseRenderItem: ExerciseRenderItem) {
        var sequencePartFilePath = audioPathGenerator?.generateSequencePartAudioPath(exerciseRenderItem.sequencePart)
        if (!sequencePartFilePath.isNullOrBlank()) {
            playAudio(sequencePartFilePath)
        }
    }
}