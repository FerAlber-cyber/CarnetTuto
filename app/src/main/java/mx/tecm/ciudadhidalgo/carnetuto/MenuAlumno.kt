package mx.tecm.ciudadhidalgo.carnetuto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_alumno)

        auth = Firebase.auth
        recyclerView = findViewById(R.id.recyclerViewUsuariosA)
        recyclerView.layoutManager = LinearLayoutManager(this)

        obtenerDatosUsuarioFirebase()
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