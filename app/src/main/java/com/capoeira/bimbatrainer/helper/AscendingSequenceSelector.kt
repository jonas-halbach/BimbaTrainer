package com.capoeira.bimbatrainer.helper

import com.capoeira.bimbatrainer.enums.SequenceTypes

class AscendingSequenceSelector : SequenceSelector {

    var currentIndex : Int = 0

    constructor(sequenceChoice : SequenceTypes) : super(sequenceChoice)
    {
            currentIndex = -1
    }

    override fun getNextSequenceItemIndex(): Int {
        increaseCurrentIndex()
        return currentIndex
    }

    fun increaseCurrentIndex() {
        currentIndex++
        if(currentIndex >= possibleSequences.count()) {
            currentIndex = 0
        }
    }

}