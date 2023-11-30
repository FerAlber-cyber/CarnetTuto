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
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.UsuarioT

lateinit var usuarioT: UsuarioT
class LoginT : AppCompatActivity() {

    private lateinit var btnRegistrar: MaterialButton
    private lateinit var btnIngresar: MaterialButton
    private lateinit var btnLAlumnos: MaterialButton
    private lateinit var auth: FirebaseAuth

    private lateinit var nombre: TextInputLayout
    private lateinit var aPaterno: TextInputLayout
    private lateinit var aMaterno: TextInputLayout
    private lateinit var correo: TextInputLayout
    private lateinit var pass: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_t)

        auth = Firebase.auth
        val baseDeDatosT = Firebase.firestore

        btnRegistrar = findViewById(R.id.btnRegistrarT)
        btnIngresar = findViewById(R.id.btnIngresarT)
        btnLAlumnos = findViewById(R.id.btnAlumnos)
        correo = findViewById(R.id.emailT)
        pass = findViewById(R.id.passwordT)

        btnIngresar.setOnClickListener {
            val emailT =correo.editText?.text
            val pswT = pass.editText?.text

            if(emailT.toString().isNotEmpty()&&pswT.toString().isNotEmpty()){
                auth.signInWithEmailAndPassword(emailT.toString(), pswT.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        baseDeDatosT.collection("usuarios").whereEqualTo("correo", emailT.toString()).get().addOnSuccessListener {documentos->
                            for (documento in documentos){
                                usuarioT = UsuarioT(
                                    "${documento.data.get("correoT")}",
                                    "${documento.data.get("nombreT")}",
                                    "${documento.data.get("apellidoPT")}",
                                    "${documento.data.get("apellidoMT")}",
                                    "${documento.data.get("grupoT")}")
                            }
                            val intent = Intent(this,MenuTutor::class.java)
                            startActivity(intent)
                            //
                        }
                    }else{
                        notificacion()
                    }
                }
            }
        }

        btnLAlumnos.setOnClickListener {
            val intent = Intent(this,LoginA::class.java)
            startActivity(intent)
        }

        btnRegistrar.setOnClickListener {
            val intent = Intent(this,Registro::class.java)
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