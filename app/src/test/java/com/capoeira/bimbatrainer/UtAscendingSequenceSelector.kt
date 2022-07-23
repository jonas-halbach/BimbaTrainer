package com.capoeira.bimbatrainer

import com.capoeira.bimbatrainer.enums.SequenceTypes
import com.capoeira.bimbatrainer.helper.AscendingSequenceSelector
import org.junit.Test
import org.junit.Assert.*

class UtAscendingSequenceSelector {

    @Test
    fun bla() {
        // Arrange
        var sequenceSelector : AscendingSequenceSelector = AscendingSequenceSelector(SequenceTypes.BIMBA)

        // Action
        var sequenceItem = sequenceSelector.getNextSequenceItem()

        // Assert
        assertEquals(1, sequenceItem.nr)
    }
}