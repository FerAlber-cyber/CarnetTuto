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
import mx.tecm.ciudadhidalgo.carnetuto.adaptadores.UsuarioAAdapter
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.UsuarioA

class MenuAlumno : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var usuarioAAdapter: UsuarioAAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_alumno)

        auth = Firebase.auth
        recyclerView = findViewById(R.id.recyclerViewUsuariosA)
        recyclerView.layoutManager = LinearLayoutManager(this)

        obtenerDatosUsuarioFirebase()

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

    private fun obtenerDatosUsuarioFirebase() {
        auth.currentUser?.email?.let { email ->
            val baseDeDatosA = Firebase.firestore
            baseDeDatosA.collection("usuariosA").document(email).get()
                .addOnSuccessListener { documento ->
                    if (documento.exists()) {
                        val usuarioA = UsuarioA(
                            "${documento.data?.get("correoInst")}",
                            "${documento.data?.get("nombreA")}",
                            "${documento.data?.get("aPaternoA")}",
                            "${documento.data?.get("aMaternoA")}",
                            "${documento.data?.get("noControl")}",
                            "${documento.data?.get("grupoA")}"
                        )
                        mostrarDatosEnRecyclerView(usuarioA)
                    }
                }
        }
    }

    private fun mostrarDatosEnRecyclerView(usuarioA: UsuarioA) {
        val listaUsuariosA = listOf(usuarioA)
        usuarioAAdapter = UsuarioAAdapter(listaUsuariosA)
        recyclerView.adapter = usuarioAAdapter
    }
}