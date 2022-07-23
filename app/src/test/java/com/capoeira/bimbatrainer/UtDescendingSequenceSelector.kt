package com.capoeira.bimbatrainer

import com.capoeira.bimbatrainer.enums.SequenceTypes
import com.capoeira.bimbatrainer.helper.DescendingSequenceSelector
import org.junit.Assert
import org.junit.Test

class UtDescendingSequenceSelector {

    @Test
    fun bla() {
        // Arrange
        var sequenceSelector : DescendingSequenceSelector = DescendingSequenceSelector(SequenceTypes.BIMBA)

        // Action
        var sequenceItem = sequenceSelector.getNextSequenceItem()

        // Assert
        Assert.assertEquals(8, sequenceItem.nr)
    }
}