package com.capoeira.bimbatrainer.helper

import com.capoeira.bimbatrainer.enums.Order
import com.capoeira.bimbatrainer.enums.SequenceTypes

class SequenceSelectorFactory {

    fun createSequenceSelector(order : Order, sequenceTypes: SequenceTypes) : SequenceSelector {
        var sequenceSelector : SequenceSelector = AscendingSequenceSelector(sequenceTypes)
        if(order == Order.DESCENDING) {
            sequenceSelector = DescendingSequenceSelector(sequenceTypes)
        } else if(order == Order.RANDOM) {
            sequenceSelector = RandomSequenceSelector(sequenceTypes)
        }
        return sequenceSelector
    }
}