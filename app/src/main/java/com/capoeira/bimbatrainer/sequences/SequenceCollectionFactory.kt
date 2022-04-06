package com.capoeira.bimbatrainer.sequences

class SequenceCollectionFactory {

    fun createBimbaSequence() : SequenceCollection {
        return createSequenceCollection("Bimba",8)
    }

    fun createMiudinhoSequence() : SequenceCollection {
        return createSequenceCollection("Miudinho",2)
    }

    fun createSequenceCollection(sequenceName : String, sequenceItemCount : Int) : SequenceCollection {
        var sequenceCollection = SequenceCollection(sequenceName)

        for (i in 1 .. sequenceItemCount) {
            var sequenceItem = SequenceItem(sequenceName, i)
            sequenceCollection.addSequenceItem(sequenceItem)
        }

        return sequenceCollection
    }
}