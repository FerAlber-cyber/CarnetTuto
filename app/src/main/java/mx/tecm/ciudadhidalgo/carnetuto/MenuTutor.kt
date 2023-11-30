package mx.tecm.ciudadhidalgo.carnetuto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.tecm.ciudadhidalgo.carnetuto.adaptadores.UsuarioTAdapter
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.UsuarioT

class MenuTutor : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var usuarioTAdapter: UsuarioTAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_tutor)

        auth = Firebase.auth
        recyclerView = findViewById(R.id.recyclerViewUsuariosT)
        recyclerView.layoutManager = LinearLayoutManager(this)

        obtenerDatosUsuarioFirebase()

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

    private fun obtenerDatosUsuarioFirebase() {
        auth.currentUser?.email?.let { email ->
            val baseDeDatosT = Firebase.firestore
            baseDeDatosT.collection("usuariosT").document(email).get().addOnSuccessListener { documento ->
                if (documento.exists()) {
                    val usuarioT = UsuarioT(
                        "${documento.data?.get("correoT")}",
                        "${documento.data?.get("nombreT")}",
                        "${documento.data?.get("apellidoPT")}",
                        "${documento.data?.get("apellidoMT")}",
                        "${documento.data?.get("grupoT")}"
                    )
                    mostrarDatosEnRecyclerView(usuarioT)
                }
            }
        }
    }

    private fun mostrarDatosEnRecyclerView(usuarioT: UsuarioT) {
        val listaUsuarios = listOf(usuarioT)
        usuarioTAdapter = UsuarioTAdapter(listaUsuarios)
        recyclerView.adapter = usuarioTAdapter
    }
}
