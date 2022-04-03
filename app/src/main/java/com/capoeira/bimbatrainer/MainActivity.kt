package com.capoeira.bimbatrainer

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioGroup
import android.widget.SeekBar
import androidx.core.view.get
import com.capoeira.bimbatrainer.databinding.ActivityMainBinding

const val SPEED_MESSAGE = "com.capoeira.bimbatrainer.SPEED_MESSAGE"
const val PLAYER_COUNT_MESSAGE =  "com.capoeira.bimbatrainer.PLAYER_COUNT_MESSAGE"
const val SEQUENCE_MESSAGE = "com.capoeira.bimbatrainer.SEQUENCE_MESSAGE"
const val ORDER_MESSAGE =  "com.capoeira.bimbatrainer.ORDER_MESSAGE"

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setSupportActionBar(binding.toolbar)

        //val navController = findNavController(R.id.nav_host_fragment_content_main)
        //appBarConfiguration = AppBarConfiguration(navController.graph)
        //setupActionBarWithNavController(navController, appBarConfiguration)

        /*binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
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
        return findViewById<SeekBar>(R.id.speedSelectionBar).progress
    }
}