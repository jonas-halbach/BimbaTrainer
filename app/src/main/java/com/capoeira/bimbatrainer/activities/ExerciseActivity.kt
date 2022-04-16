package com.capoeira.bimbatrainer.activities

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.TextView
import com.capoeira.bimbatrainer.R
import com.capoeira.bimbatrainer.enums.Order
import com.capoeira.bimbatrainer.enums.PlayerCount
import com.capoeira.bimbatrainer.enums.SequenceTypes
import com.capoeira.bimbatrainer.helper.*
import com.capoeira.bimbatrainer.renderer.AudioRenderer
import com.capoeira.bimbatrainer.renderer.ExerciseRenderItem
import com.capoeira.bimbatrainer.renderer.NumberAudioRenderer
import com.capoeira.bimbatrainer.renderer.Renderer
import com.capoeira.bimbatrainer.sequences.SequenceItem
import java.util.*

class ExerciseActivity : AppCompatActivity() {

    var maxCountDownTime : Int = 0

    var sequenceSelectorFactory = SequenceSelectorFactory()

    var sequenceSelector : SequenceSelector? = null

    var renderer : MutableList<Renderer> = mutableListOf<Renderer>()

    var rendererFactory = TextRendererFactory()

    var randomCharacterGenerator = RandomCharacterGenerator(arrayOf<Char>('A', 'B'))

    var timer : Timer? = null

    var audioPathGenerator : AudioPathGenerator? = null

    var audioRendererFactory : AudioRendererFactory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        audioPathGenerator = AudioPathGenerator(this)

        var audioPlayer = AudioPlayer(assets)
        audioRendererFactory = AudioRendererFactory(audioPathGenerator, audioPlayer)

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
        addSequenceTypeAudioRenderer(sequenceType)
        addNumberAudioRenderer()
        addPlayerCountAudioRenderer(playerCount)

        var countDownText : TextView = findViewById(R.id.countDownText)

        sequenceSelector = sequenceSelectorFactory.createSequenceSelector(getOrder(orderChoiceMessageData), sequenceType)
        startNewSequence(orderChoiceMessageData, sequenceChoiceMessageData)

        timer = Timer()
        timer?.schedule(
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

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }

    fun startNewSequence(orderChoice: Int, sequenceChoice: Int) {

        var sequenceItem = sequenceSelector?.getNextSequenceItem()
        var sequencePart = randomCharacterGenerator.getRandomChar()

        if(sequenceItem != null) {
            var exerciseRenderItem = ExerciseRenderItem(sequenceItem, sequencePart)
            Render(exerciseRenderItem)
        }
    }

    fun Render(exerciseRenderItem: ExerciseRenderItem) {
        if (exerciseRenderItem != null) {
            for (currentRenderer in renderer) {
                currentRenderer.render(exerciseRenderItem)
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

    fun addSequenceTypeAudioRenderer(sequenceType: SequenceTypes) {
        var audioRenderer = audioRendererFactory?.createSequenceTypeAudioRenderer(sequenceType)
        if(audioRenderer  != null) {
            renderer.add(audioRenderer)
        }
    }

    fun addNumberAudioRenderer() {
        var audioRenderer = audioRendererFactory?.createNumberAudioRenderer()
        if(audioRenderer != null) {
            renderer.add(audioRenderer)
        }
    }

    fun addPlayerCountAudioRenderer(playerCount : Int) {
        var audioRenderer = audioRendererFactory?.createSequencePartAudioRenderer(playerCount)
        if(audioRenderer != null) {
            renderer.add(audioRenderer)
        }
    }
}