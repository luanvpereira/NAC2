package br.com.fiap.nac.nac2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.fiap.nac.nac2.adapter.RankingAdapter
import br.com.fiap.nac.nac2.api.getPokemonApi
import br.com.fiap.nac.nac2.model.Score
import kotlinx.android.synthetic.main.activity_game_over.*
import kotlinx.android.synthetic.main.activity_ranking.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        val context = this

        btBackToMenu2.setOnClickListener {
            startActivity(Intent(this@RankingActivity, MenuActivity::class.java))
            this@RankingActivity.finish()
        }


        pbLoadScore.visibility = View.VISIBLE

        getPokemonApi().loadScores().enqueue(object: Callback<List<Score>> {
            override fun onResponse(call: Call<List<Score>>?, response: Response<List<Score>>?) {
                val scores = response?.body()

                rvRanking.adapter = RankingAdapter(
                        scores!!,
                        context
                ) {
                    Log.i("TAG", "MEU_ITEM")
                }

                pbLoadScore.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<List<Score>>?, t: Throwable?) {
                Toast.makeText(context, "Error ao carregar lista.", Toast.LENGTH_SHORT).show()
                pbLoadScore.visibility = View.INVISIBLE
            }

        })

        val layoutManager = LinearLayoutManager(this)

        rvRanking.layoutManager = layoutManager
    }


    private fun scores(): List<Score> {
        return listOf(
            Score(
                    "Luan",
                    500
            ),
            Score(
                    "Caio",
                    1000
            )
        )
    }
}
