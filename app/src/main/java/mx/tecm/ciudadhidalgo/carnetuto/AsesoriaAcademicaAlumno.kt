package mx.tecm.ciudadhidalgo.carnetuto

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AsesoriaAcademicaAlumno : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asesoria_academica)



        // Configura los clics de los botones del menú
        val btnMenuAlum: Button = findViewById(R.id.btnMenuAlum)
        val btnEntrevista: Button = findViewById(R.id.btnEntrevista)
        val btnCanalizacion: Button = findViewById(R.id.btnCanalizacion)
        val btnAsesoriaAca: Button = findViewById(R.id.btnAsesoriaAca)


        btnMenuAlum.setOnClickListener {
            val intent = Intent(this,MenuAlumno::class.java)
            startActivity(intent)
        }

        btnEntrevista.setOnClickListener {
            val intent = Intent(this,EntrevistaAlumno::class.java)
            startActivity(intent)
        }

        btnCanalizacion.setOnClickListener {
            val intent = Intent(this,CanalizacionAlumnos::class.java)
            startActivity(intent)
        }

        btnAsesoriaAca.setOnClickListener {
            val intent = Intent(this,AsesoriaAcademicaAlumno::class.java)
            startActivity(intent)
        }
    }
}