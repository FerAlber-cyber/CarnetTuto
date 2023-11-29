package mx.tecm.ciudadhidalgo.carnetuto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.UsuarioA

lateinit var usuarioA: UsuarioA
class LoginA : AppCompatActivity() {

    private lateinit var btnRegistrar: MaterialButton
    private lateinit var btnIngresar: MaterialButton
    private lateinit var btnLTutores: MaterialButton
    private lateinit var auth: FirebaseAuth

    private lateinit var nombre: TextInputLayout
    private lateinit var aPaterno: TextInputLayout
    private lateinit var aMaterno: TextInputLayout
    private lateinit var correo: TextInputLayout
    private lateinit var pass: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        val baseDeDatosA = Firebase.firestore

        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnLTutores = findViewById(R.id.btnTutores)
        correo = findViewById(R.id.email)
        pass = findViewById(R.id.password)

        btnIngresar.setOnClickListener {
            val emailA =correo.editText?.text
            val pswA = pass.editText?.text

            if(emailA.toString().isNotEmpty()&&pswA.toString().isNotEmpty()){
                auth.signInWithEmailAndPassword(emailA.toString(), pswA.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        baseDeDatosA.collection("usuarios").whereEqualTo("correo", emailA.toString()).get().addOnSuccessListener {documentos->
                            for (documento in documentos){
                                usuarioA = UsuarioA(
                                    "${documento.data.get("correoInst")}",
                                    "${documento.data.get("nombreA")}",
                                    "${documento.data.get("aPaternoA")}",
                                    "${documento.data.get("aMaternoA")}",
                                    "${documento.data.get("noControl")}",
                                    "${documento.data.get("grupoA")}")
                            }
                            val intent = Intent(this,MenuAlumno::class.java)
                            startActivity(intent)
                        }
                    }else{
                        notificacion()
                    }
                }
            }
        }

        btnLTutores.setOnClickListener {
            val intent = Intent(this,LoginT::class.java)
            startActivity(intent)
        }

        btnRegistrar.setOnClickListener {
            val intent = Intent(this,RegistroAlumnos::class.java)
            startActivity(intent)
        }

    }
    private fun notificacion(){
        val notiDialogo = androidx.appcompat.app.AlertDialog.Builder(this)
        notiDialogo.setTitle("Error")
        notiDialogo.setMessage("Se ha producido un ERROR en la AUTENTICACIÓN")
        notiDialogo.setPositiveButton("Aceptar",null)
        notiDialogo.show()
    }
}