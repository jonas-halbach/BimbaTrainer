package com.capoeira.bimbatrainer.renderer

import android.widget.TextView

abstract class TextRenderer : Renderer {

    protected var textView : TextView?

    constructor( textView : TextView) {
        this.textView = textView
    }
}