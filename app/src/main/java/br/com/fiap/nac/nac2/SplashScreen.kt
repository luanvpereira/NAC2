package br.com.fiap.nac.nac2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

private const val DISPLAY_TIME = 2000L

class SplashScreen : AppCompatActivity() {
    lateinit var ivLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        ivLogo = findViewById(R.id.ivLogo)
        load()
    }

    private fun load() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_splash)
        anim.reset()

        ivLogo!!.clearAnimation()
        ivLogo!!.startAnimation(anim)

        Handler().postDelayed({
            val intent = Intent(
                    this@SplashScreen,
                    MenuActivity::class.java
            )

            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

            startActivity(intent)
            this@SplashScreen.finish()
        }, DISPLAY_TIME)
    }
}
