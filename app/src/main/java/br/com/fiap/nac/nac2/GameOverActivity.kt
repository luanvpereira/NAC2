package br.com.fiap.nac.nac2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.fiap.nac.nac2.api.getPokemonApi
import br.com.fiap.nac.nac2.model.Score
import kotlinx.android.synthetic.main.activity_game_over.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val score = intent.getIntExtra("score", 0)
        val context = this

        tvScoreText.text = score.toString()

        btBackToMenu.setOnClickListener {
            goToMenu()
        }

        btTryAgain.setOnClickListener {
            val nickname: String = etName.text.toString()

            if(nickname != "") {
                pbSaveScore.visibility = View.VISIBLE
                saveScore(score)
            } else {
                Toast.makeText(context, "Insira seu apelido.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToGame() {
        startActivity(Intent(this@GameOverActivity, GameActivity::class.java))
        this@GameOverActivity.finish()
    }

    private fun goToMenu() {
        startActivity(Intent(this@GameOverActivity, MenuActivity::class.java))
        this@GameOverActivity.finish()
    }

    private fun saveScore(score: Int) {
        val context = this

        getPokemonApi().sendScore(Score(
                etName.text.toString(),
                score
        )).enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                pbSaveScore.visibility = View.INVISIBLE
                Toast.makeText(context, "Gravado com sucesso.", Toast.LENGTH_SHORT).show()
                context.goToGame()
            }

            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                pbSaveScore.visibility = View.INVISIBLE
                Toast.makeText(context, "Erro ao gravar pontuação. Tente novamente.", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
