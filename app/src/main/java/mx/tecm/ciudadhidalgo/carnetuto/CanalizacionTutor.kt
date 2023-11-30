package mx.tecm.ciudadhidalgo.carnetuto

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class CanalizacionTutor : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canalizacion_tutor)

        // Configura los clics de los botones del menú
        val btnMenuTutor: MaterialButton = findViewById(R.id.btnMenuTutor)
        val btnEntrevista: MaterialButton = findViewById(R.id.btnEntrevistaTutor)
        val btnCanalizacion: MaterialButton = findViewById(R.id.btnCanalizacionTutor)
        val btnAsesoriaAca: MaterialButton = findViewById(R.id.btnAsesoriAcaTutor)

        btnMenuTutor.setOnClickListener {
            val intent = Intent(this, MenuTutor::class.java)
            startActivity(intent)
        }

        btnEntrevista.setOnClickListener {
            val intent = Intent(this, EntrevistaTutor::class.java)
            startActivity(intent)
        }

        btnCanalizacion.setOnClickListener {
            val intent = Intent(this, CanalizacionTutor::class.java)
            startActivity(intent)
        }

        btnAsesoriaAca.setOnClickListener {
            val intent = Intent(this, AsesoriasTutor::class.java)
            startActivity(intent)
        }
    }
}