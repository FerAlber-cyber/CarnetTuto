package mx.tecm.ciudadhidalgo.carnetuto

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Duración del Splash (en milisegundos)
        val splashDuration: Long = 3000

        Handler().postDelayed({
            // Inicia la siguiente actividad después del tiempo especificado
            val intent = Intent(this, LoginA::class.java)
            startActivity(intent)

            // Cierra la actividad actual
            finish()
        }, splashDuration)
    }
}