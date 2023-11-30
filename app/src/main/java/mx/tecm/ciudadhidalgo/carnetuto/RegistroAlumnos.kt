package mx.tecm.ciudadhidalgo.carnetuto

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.UsuarioA

class RegistroAlumnos : AppCompatActivity() {

    private lateinit var btnRegistrarmeA: Button
    private lateinit var btnRegresarA: Button

    private lateinit var noControl: TextInputLayout
    private lateinit var nombreA: TextInputLayout
    private lateinit var apellidoMA: TextInputLayout
    private lateinit var apellidoPA: TextInputLayout
    private lateinit var correoA: TextInputLayout
    private lateinit var grupoA: TextInputLayout
    private lateinit var passA: TextInputLayout

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_alumnos)

        //Acceso a la base de datos
        val baseDeDatosT = Firebase.firestore

        btnRegistrarmeA = findViewById(R.id.btnRegistrarmeA)
        btnRegresarA = findViewById(R.id.btnYaEstoyRegistradoA)
        noControl = findViewById(R.id.noControlA)
        nombreA = findViewById(R.id.nombreA)
        apellidoMA = findViewById(R.id.apellidoMA)
        apellidoPA = findViewById(R.id.apellidoPA)
        correoA = findViewById(R.id.email_registroA)
        grupoA = findViewById(R.id.grupoA)
        passA = findViewById(R.id.password_registroA)

        btnRegistrarmeA.setOnClickListener {

            val emailA = correoA.editText?.text
            val pswA = passA.editText?.text//

            val usuarioA = UsuarioA(
                emailA.toString(),
                nombreA.editText?.text.toString(),
                apellidoPA.editText?.text.toString(),
                apellidoMA.editText?.text.toString(),
                noControl.editText?.text.toString(),
                grupoA.editText?.text.toString())

            val confirmaDialogo = AlertDialog.Builder(it.context)
            confirmaDialogo.setTitle("Confirmar Usuario")
            confirmaDialogo.setMessage("""
                Usuario: ${nombreA.editText?.text} ${apellidoPA.editText?.text} ${apellidoMA.editText?.text}
                Correo: ${correoA.editText?.text}
                Contraseña: ${passA.editText?.text}
                """.trimIndent())
            confirmaDialogo.setPositiveButton("confirmar"){confirmaDialogo,which->

                if (emailA.toString().isNotEmpty() && pswA.toString().isNotEmpty()){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        emailA.toString(),pswA.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this,LoginA::class.java).apply {
                                baseDeDatosT.collection("usuariosA").document(emailA.toString())
                                    .set(usuarioA)
                            }//detalle
                            startActivity(intent)
                        }else{
                            notificacion()
                        }
                    }
                }
            }

            confirmaDialogo.setNegativeButton("Editar Datos"){confirmaDialogo,which->
                confirmaDialogo.cancel()
            }
            confirmaDialogo.show()
        }


        btnRegresarA.setOnClickListener {
            val intent = Intent(this, LoginA::class.java)
            startActivity(intent)
        }
    }
    private fun notificacion(){
        val notiDialogo = AlertDialog.Builder(this)
        notiDialogo.setTitle("Error")
        notiDialogo.setMessage("Se ha producido un Error en la AUTENTICACION")
        notiDialogo.setPositiveButton("Aceptar", null)
        notiDialogo.show()
    }
}