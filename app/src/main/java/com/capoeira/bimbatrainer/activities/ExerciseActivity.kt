package com.capoeira.bimbatrainer.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.capoeira.bimbatrainer.R
import com.capoeira.bimbatrainer.enums.Order
import com.capoeira.bimbatrainer.enums.PlayerCount
import com.capoeira.bimbatrainer.enums.SequenceTypes
import com.capoeira.bimbatrainer.helper.SequenceSelector
import com.capoeira.bimbatrainer.helper.SequenceSelectorFactory
import com.capoeira.bimbatrainer.helper.TextRendererFactory
import com.capoeira.bimbatrainer.renderer.Renderer
import com.capoeira.bimbatrainer.sequences.SequenceItem
import java.util.*

class ExerciseActivity : AppCompatActivity() {

    var maxCountDownTime : Int = 0

    var sequenceSelectorFactory = SequenceSelectorFactory()

    var sequenceSelector : SequenceSelector? = null

    var renderer : MutableList<Renderer> = mutableListOf<Renderer>()

    var rendererFactory = TextRendererFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        maxCountDownTime = intent.getIntExtra(SPEED_MESSAGE,30)

        val playerCountChoiceMessageData = intent.getIntExtra(PLAYER_COUNT_MESSAGE, 0)
        val sequenceChoiceMessageData = intent.getIntExtra(SEQUENCE_MESSAGE, 0)
        val orderChoiceMessageData = intent.getIntExtra(ORDER_MESSAGE, 0)

        val playerCount = getPlayerCount(playerCountChoiceMessageData)
        val sequenceType = getSequenceType(sequenceChoiceMessageData)

        addSequenceTypeTextRenderer(sequenceType)
        addSequenceNumberTextRenderer()
        addSequencePartTextRenderer(playerCount)

        var countDownText : TextView = findViewById(R.id.countDownText)

        sequenceSelector = sequenceSelectorFactory.createSequenceSelector(getOrder(orderChoiceMessageData), sequenceType)
        startNewSequence(orderChoiceMessageData, sequenceChoiceMessageData)


        var timer = Timer()
        timer.schedule(
            object : TimerTask() {
                var currentCountDownTime : Int = maxCountDownTime

                override fun run() {

                    currentCountDownTime -= 1
                    countDownText.text = currentCountDownTime.toString()
                    if(currentCountDownTime <= 0) {
                        currentCountDownTime = maxCountDownTime
                        startNewSequence(orderChoiceMessageData, sequenceChoiceMessageData)
                    }
                }
            }, 0, 1000)
    }

    fun startNewSequence(orderChoice: Int, sequenceChoice: Int) {

        var sequenceItem = sequenceSelector?.getNextSequenceItem()

        Render(sequenceItem)
    }

    fun Render(sequenceItem : SequenceItem?) {
        if (sequenceItem != null) {
            for (currentRenderer in renderer) {
                currentRenderer.render(sequenceItem)
            }
        }
    }

    fun getPlayerCount(playerCountChoice : Int) : Int {
        var playerCount = 1
        if(playerCountChoice == R.id.twoPlayerRadioBtn) {
            playerCount = 2
        }

        return playerCount
    }

    fun getOrder(orderChoice : Int) : Order {
        var order = Order.ASCENDING
        if(orderChoice == R.id.ascendingSequenceRadioBtn) {
            order = Order.ASCENDING
        } else if(orderChoice == R.id.descendingSequenceRadioBtn) {
            order = Order.DESCENDING
        } else {
            order = Order.RANDOM
        }
        return order
    }

    fun getSequenceType(sequenceChoice: Int) : SequenceTypes {
        var sequenceType = SequenceTypes.BIMBA

        if (sequenceChoice == R.id.bimbaSequenceRadioBtn) {
            sequenceType = SequenceTypes.BIMBA
        } else if (sequenceChoice == R.id.miudinhoSequenceRadioBtn) {
            sequenceType = SequenceTypes.MIUDINHO
        } else {
            sequenceType = SequenceTypes.ALL
        }

        return sequenceType
    }

    fun addSequenceTypeTextRenderer(sequenceTypes: SequenceTypes) {
        var sequenceTypeTextView = findViewById<TextView>(R.id.sequenceTypeText)
        var sequenceTypeTextRenderer = rendererFactory.createSequenceTypeRenderer(sequenceTypeTextView, sequenceTypes)
        renderer.add(sequenceTypeTextRenderer)
    }

    fun addSequenceNumberTextRenderer() {
        var sequenceNumberTextView = findViewById<TextView>(R.id.sequenceNumberText)
        var sequenceNumberRenderer = rendererFactory.createSequenceNumberRenderer(sequenceNumberTextView)
        renderer.add(sequenceNumberRenderer)
    }

    fun addSequencePartTextRenderer(playerCount: Int) {
        var sequencePartTextView = findViewById<TextView>(R.id.sequencePartText)
        var sequencePartRenderer = rendererFactory.createSequencePartRenderer(sequencePartTextView, playerCount)
        renderer.add(sequencePartRenderer)
    }
}