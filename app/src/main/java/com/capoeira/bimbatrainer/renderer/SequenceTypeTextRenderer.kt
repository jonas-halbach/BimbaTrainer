package com.capoeira.bimbatrainer.renderer

import android.widget.TextView
import com.capoeira.bimbatrainer.sequences.SequenceItem

class SequenceTypeTextRenderer(textView : TextView) : TextRenderer(textView) {

    override fun render(exerciseRenderItem: ExerciseRenderItem) {
        textView?.text = exerciseRenderItem.sequenceTypeName
    }
}