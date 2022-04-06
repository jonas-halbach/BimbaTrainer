package com.capoeira.bimbatrainer.renderer

import com.capoeira.bimbatrainer.sequences.SequenceItem

 abstract class Renderer {

    abstract fun render(sequenceItem: SequenceItem)
}