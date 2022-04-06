package com.capoeira.bimbatrainer.helper

import com.capoeira.bimbatrainer.enums.SequenceTypes
import com.capoeira.bimbatrainer.sequences.SequenceCollectionFactory
import com.capoeira.bimbatrainer.sequences.SequenceItem

abstract class SequenceSelector(sequenceChoice : SequenceTypes) {

    var possibleSequences : MutableList<SequenceItem> = mutableListOf<SequenceItem>()

    init {
        var sequenceCollectionFactory = SequenceCollectionFactory()

        if(sequenceChoice == SequenceTypes.BIMBA) {
            possibleSequences.addAll(getBimbaSequenceItems(sequenceCollectionFactory))
        } else if(sequenceChoice == SequenceTypes.MIUDINHO) {
            possibleSequences.addAll(getMiudinhoSequenceItems(sequenceCollectionFactory))
        } else {
            possibleSequences.addAll(getBimbaSequenceItems(sequenceCollectionFactory))
            possibleSequences.addAll(getMiudinhoSequenceItems(sequenceCollectionFactory))
        }
    }

    fun getBimbaSequenceItems(sequenceCollectionFactory : SequenceCollectionFactory) :  MutableList<SequenceItem> {
        return sequenceCollectionFactory.createBimbaSequence().sequenceItems
    }

    fun getMiudinhoSequenceItems(sequenceCollectionFactory : SequenceCollectionFactory) :  MutableList<SequenceItem> {
        return sequenceCollectionFactory.createMiudinhoSequence().sequenceItems
    }

    fun getNextSequenceItem() : SequenceItem {

        var nextSequenceItem = SequenceItem("No Sequence Item found", -1)

        var nextSequenceItemIndex = getNextSequenceItemIndex()
        if(nextSequenceItemIndex > 0 && nextSequenceItemIndex < possibleSequences.count()) {
            nextSequenceItem = possibleSequences[nextSequenceItemIndex]
        }
        return nextSequenceItem
    }

    abstract fun getNextSequenceItemIndex() : Int
}