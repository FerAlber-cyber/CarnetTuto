package mx.tecm.ciudadhidalgo.carnetuto

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.tecm.ciudadhidalgo.carnetuto.adaptadores.EntrevistaAlumnAdapter
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.EntrevistaAlumn
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.UsuarioA

var entrevistas:ArrayList<EntrevistaAlumn> = ArrayList()

class EntrevistaAlumno : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var entrevistaAdapter: EntrevistaAlumnAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrevista_alumno)

        auth = Firebase.auth
        recyclerView = findViewById(R.id.rvEntrevista)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtén el número de control del usuario logueado
        val noControlU = intent.getStringExtra("noControl")

        // Realiza la consulta a Firestore para obtener las entrevistas del alumno
        if (noControlU != null) {
            obtenerEntrevistasAlumno(noControlU)
        }

        val btnMenuAlum: Button = findViewById(R.id.btnMenuAlum)
        btnMenuAlum.setOnClickListener {
            val intent = Intent(this, MenuAlumno::class.java)
            startActivity(intent)
        }

        // Configura los clics de los botones del menú
        val btnEntrevista: Button = findViewById(R.id.btnEntrevista)
        val btnCanalizacion: Button = findViewById(R.id.btnCanalizacion)
        val btnAsesoriaAca: Button = findViewById(R.id.btnAsesoriaAca)


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
    private fun obtenerEntrevistasAlumno(noControl: String) {
        val baseDeDatos = Firebase.firestore
        baseDeDatos.collection("entrevistas")
            .whereEqualTo("noControl", noControl)
            .get()
            .addOnSuccessListener { documentos ->
                val listaEntrevistas = mutableListOf<EntrevistaAlumn>()
                for (documento in documentos) {
                    val entrevista = EntrevistaAlumn(
                        "${documento.data?.get("fecha")}",
                        "${documento.data?.get("noControl")}",
                        "${documento.data?.get("temaEntevista")}"
                    )
                    listaEntrevistas.add(entrevista)
                }
                //mostrarEntrevistas(listaEntrevistas)
                entrevistaAdapter = EntrevistaAlumnAdapter(entrevistas)
                recyclerView.adapter = entrevistaAdapter
            }
    }

    /*private fun mostrarEntrevistas(entrevistas: List<EntrevistaAlumn>) {

    }*/

}