package com.capoeira.bimbatrainer.helper

import com.capoeira.bimbatrainer.enums.SequenceTypes

class DescendingSequenceSelector : SequenceSelector {

    var currentIndex : Int = 0

    constructor(sequenceChoice : SequenceTypes) : super(sequenceChoice)
    {
        currentIndex =  possibleSequences.count()
    }

    override fun getNextSequenceItemIndex(): Int {
        increaseCurrentIndex()
        return currentIndex
    }

    fun increaseCurrentIndex() {
        currentIndex--
        if(currentIndex <= 0) {
            currentIndex = possibleSequences.count() -1
        }
    }

}