package br.com.fiap.nac.nac2

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*
import kotlin.reflect.KClass
import kotlin.system.exitProcess

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btPlay.setOnClickListener{
            goTo(GameActivity::class.java)
        }

        btRanking.setOnClickListener {
            goTo(RankingActivity::class.java)
        }

        btAbout.setOnClickListener {
            goTo(AboutActivity::class.java)
        }

        btExit.setOnClickListener {
            moveTaskToBack(true);
            exitProcess(-1)
            finishAffinity()
            finish()
        }
    }

    private fun goTo(toActivity:Class<out AppCompatActivity>) {
        val newIntent = Intent(
                this@MenuActivity,
                toActivity
        )

        startActivity(newIntent)
    }
}
