package com.capoeira.bimbatrainer.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import com.capoeira.bimbatrainer.R
import com.capoeira.bimbatrainer.databinding.ActivityMainBinding

const val SPEED_MESSAGE = "com.capoeira.bimbatrainer.Activities.SPEED_MESSAGE"
const val PLAYER_COUNT_MESSAGE =  "com.capoeira.bimbatrainer.Activities.PLAYER_COUNT_MESSAGE"
const val SEQUENCE_MESSAGE = "com.capoeira.bimbatrainer.Activities.SEQUENCE_MESSAGE"
const val ORDER_MESSAGE =  "com.capoeira.bimbatrainer.Activities.ORDER_MESSAGE"

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var maxSequenceTimeConfigurationSeekBar : SeekBar
    private lateinit var maxSequenceTimeConfigurationTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        maxSequenceTimeConfigurationSeekBar = findViewById<SeekBar>(R.id.speedSelectionBar)

        maxSequenceTimeConfigurationTextView = findViewById<TextView>(R.id.max_sequenceTime_textView)
        maxSequenceTimeConfigurationTextView.text = "" + maxSequenceTimeConfigurationSeekBar.progress

        maxSequenceTimeConfigurationSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(p0: SeekBar?, currentValue: Int, p2: Boolean) {
                var newProgressValue = currentValue
                if(currentValue <= 0) {
                    newProgressValue = 1
                    p0?.progress = newProgressValue
                }

                maxSequenceTimeConfigurationTextView.text = "" + newProgressValue
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        /*val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()*/
        return super.onSupportNavigateUp()
    }

    fun startExercise(view: View) {
        val speed = getSpeed()
        val playerCountChoice = getPlayerCountChoice()
        val sequenceChoice = getSequenceChoice()
        val orderChoice = getOrderChoice()

        val intent = Intent(this, ExerciseActivity::class.java).apply {
            putExtra(PLAYER_COUNT_MESSAGE, playerCountChoice)
            putExtra(SEQUENCE_MESSAGE, sequenceChoice)
            putExtra(ORDER_MESSAGE, orderChoice)
            putExtra(SPEED_MESSAGE, speed)
        }
        startActivity(intent)
    }

    fun openInstructions(view: View) {
        val intent = Intent(this, InstructionsActivity::class.java)

        startActivity(intent)
    }

    fun getPlayerCountChoice(): Int {
        return getRadioButtonChoice(R.id.playerCountRadioGroup)
    }

    fun getSequenceChoice() : Int {
        return getRadioButtonChoice(R.id.sequenceChoiceRadioGroup)
    }

    fun getOrderChoice() : Int {
        return getRadioButtonChoice(R.id.orderChoiceRadioGroup)
    }

    fun getRadioButtonChoice(radioGroupId : Int) : Int {
        val radioGroup = findViewById<RadioGroup>(radioGroupId)

        return radioGroup.checkedRadioButtonId
    }

    fun getSpeed() : Int {
        return maxSequenceTimeConfigurationSeekBar.progress
    }
}