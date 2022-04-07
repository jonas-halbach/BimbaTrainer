package com.capoeira.bimbatrainer.renderer

import com.capoeira.bimbatrainer.sequences.SequenceItem

class ExerciseRenderItem {

    var sequenceTypeName : String

    var sequenceNumber : Int

    var sequencePart : Char

    public constructor(sequenceItem: SequenceItem, part: Char) {

        this.sequenceTypeName = sequenceItem.sequence
        this.sequenceNumber = sequenceItem.nr
        this.sequencePart = part
    }
}