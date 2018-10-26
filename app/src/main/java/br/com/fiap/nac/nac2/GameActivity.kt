package br.com.fiap.nac.nac2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*
import java.util.Arrays.asList



const val FIRE = 1
const val GRASS = 2
const val WATER = 3

const val PLAYER_WIN = 1
const val PLAYER_LOSE = 2
const val PLAYER_TIE = 3


class GameActivity : AppCompatActivity() {
    private var currentPlayerChoice: Int? = null
    private var score: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        btWater.setOnClickListener{
            if(btPlay.isEnabled) {
                onSelect(WATER, btWater)
            }
        }

        btFire.setOnClickListener{
            if(btPlay.isEnabled) {
                onSelect(FIRE, btFire)
            }
        }

        btGrass.setOnClickListener{
            if(btPlay.isEnabled) {
                onSelect(GRASS, btGrass)
            }
        }

        btPlay.setOnClickListener {
            onPlay()
        }
    }

    private fun getGameStatus(player1: Int, player2:Int): Int {
        if(player1 == player2) {
            return PLAYER_TIE
        }

        if(player1 == WATER && player2 == FIRE
        || player1 == FIRE && player2 == GRASS
        || player1 == GRASS && player2 == WATER) {
            return PLAYER_WIN
        }

        return PLAYER_LOSE
    }

    private fun getRandomChoice():Int {
        val choices = Arrays.asList(FIRE, GRASS, WATER)
        return choices[Random().nextInt(choices.count())]
    }

    private fun getImageByChoice(choice: Int): Int {
        var imageIndex: Int? = null

        when(choice) {
            FIRE -> imageIndex= R.drawable.charmander
            GRASS -> imageIndex = R.drawable.bulbasaur
            WATER -> imageIndex = R.drawable.squirtle
        }

        return imageIndex!!
    }

    private fun onSelect(playerChoice: Int, button: View) {
        ivPlayerChoice.setImageResource(getImageByChoice(playerChoice))
        ivMachineChoice.setImageResource(0)
        currentPlayerChoice = playerChoice
    }

    private fun reset() {
        Handler().postDelayed({
            ivMachineChoice.setImageResource(0)
            ivPlayerChoice.setImageResource(0)
            currentPlayerChoice = null
            btPlay.isEnabled = true
        }, 2400L)
    }

    private fun showMessage(message: String) {
        val toast = Toast.makeText(this,  message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    private fun onPlay() = if(currentPlayerChoice != null) {
        val choiceOfMachine: Int = getRandomChoice()
        val gameStatus: Int = getGameStatus(currentPlayerChoice!!, choiceOfMachine)

        btPlay.isEnabled  = false;
        ivMachineChoice.setImageResource(getImageByChoice(choiceOfMachine))

        when(gameStatus) {
            PLAYER_WIN -> {
                score += 2
                showMessage("GANHOU")
            }

            PLAYER_TIE -> {
                score += 1
                showMessage("EMPATOU")
            }

            PLAYER_LOSE -> {
                if(score != 0) {
                    goToGameOver(score)
                } else {
                    showMessage("PERDEU")
                }
            }
        }

        tvScore.text = "Score: " + score.toString()
        reset()
    } else {
        showMessage("Escolha uma opção")
    }

    private fun goToGameOver(score: Int) {
        val intent = Intent(
                this@GameActivity,
                GameOverActivity::class.java
        )

        intent.putExtra(
                "score",
                score
        )

        startActivity(intent)
    }
}
