package com.capoeira.bimbatrainer.renderer

import android.widget.TextView
import com.capoeira.bimbatrainer.sequences.SequenceItem

class SequenceNumberRenderer(textView : TextView) : TextRenderer(textView) {

    override fun render(sequenceItem: SequenceItem) {
        textView?.setText(sequenceItem.nr.toString())
    }

}