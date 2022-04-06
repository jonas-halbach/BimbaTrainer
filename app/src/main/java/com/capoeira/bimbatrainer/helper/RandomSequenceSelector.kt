package com.capoeira.bimbatrainer.helper

import com.capoeira.bimbatrainer.enums.SequenceTypes
import kotlin.random.Random

class RandomSequenceSelector(sequenceChoice : SequenceTypes) : SequenceSelector(sequenceChoice) {

    override fun getNextSequenceItemIndex(): Int {
        return Random.nextInt(0, possibleSequences.count() - 1)
    }


}