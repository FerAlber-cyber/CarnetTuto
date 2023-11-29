package mx.tecm.ciudadhidalgo.carnetuto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.tecm.ciudadhidalgo.carnetuto.adaptadores.UsuarioTAdapter
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.UsuarioT

class MenuTutor : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var usuarioT: UsuarioT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_tutor)

        auth = Firebase.auth
        val baseDeDatosT = Firebase.firestore
        recyclerView = findViewById(R.id.recyclerViewUsuariosT)
        recyclerView.layoutManager = LinearLayoutManager(this)

        auth.currentUser?.email?.let { email ->
            baseDeDatosT.collection("usuariosT").document(email).get().addOnSuccessListener { documento ->
                if (documento.exists()) {
                    usuarioT = UsuarioT(
                        "${documento.data?.get("correoT")}",
                        "${documento.data?.get("nombreT")}",
                        "${documento.data?.get("apellidoPT")}",
                        "${documento.data?.get("apellidoMT")}",
                        "${documento.data?.get("grupoT")}"
                    )
                    mostrarDatosEnRecyclerView()
                }
            }
        }
    }

    private fun mostrarDatosEnRecyclerView() {
        val listaUsuarios = listOf(usuarioT)
        val adapter = UsuarioTAdapter(listaUsuarios)
        recyclerView.adapter = adapter
    }
}
