package com.capoeira.bimbatrainer.helper

import android.widget.TextView
import com.capoeira.bimbatrainer.enums.PlayerCount
import com.capoeira.bimbatrainer.enums.SequenceTypes
import com.capoeira.bimbatrainer.renderer.*

class TextRendererFactory {

    fun createSequenceNumberRenderer(textView : TextView) : Renderer {
        return SequenceNumberRenderer(textView)
    }

    fun createSequencePartRenderer(textView : TextView, playerCount : Int) : Renderer {
        var textRenderer : TextRenderer? = null
        if(playerCount > 1) {
            textRenderer = SequencePartTextRenderer(textView)
        }
        else {
            textRenderer = EmptyTextRenderer(textView)
        }
        return SequencePartTextRenderer(textView)
    }

    fun createSequenceTypeRenderer(textView: TextView, sequenceTypes: SequenceTypes) : Renderer {
        var textRenderer : TextRenderer? = null
        if(sequenceTypes == SequenceTypes.ALL) {
            textRenderer = SequenceTypeTextRenderer(textView)
        } else {
            textRenderer = EmptyTextRenderer(textView)
        }
        return textRenderer
    }
}