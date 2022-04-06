package com.capoeira.bimbatrainer.sequences

class SequenceCollection(var name : String) {


    var sequenceItems : MutableList<SequenceItem> = mutableListOf<SequenceItem>()

    fun addSequenceItem(sequenceItem: SequenceItem) {
        sequenceItems.add(sequenceItem)
    }
}