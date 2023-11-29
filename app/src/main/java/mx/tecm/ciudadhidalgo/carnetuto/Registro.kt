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
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.UsuarioT
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.UsuarioA


class Registro : AppCompatActivity() {
    private lateinit var btnRegistrarme:Button
    private lateinit var btnRegresar:Button

    private lateinit var nombreT:TextInputLayout
    private lateinit var apellidoMT:TextInputLayout
    private lateinit var apellidoPT:TextInputLayout
    private lateinit var correoT:TextInputLayout
    private lateinit var grupoT:TextInputLayout
    private lateinit var passT:TextInputLayout

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        //Acceso a la base de datos
        val baseDeDatosT = Firebase.firestore

        btnRegistrarme = findViewById(R.id.btnRegistrarme)
        btnRegresar = findViewById(R.id.btnYaEstoyRegistrado)
        nombreT = findViewById(R.id.nombreT)
        apellidoMT = findViewById(R.id.apellidoMT)
        apellidoPT = findViewById(R.id.apellidoPT)
        correoT = findViewById(R.id.email_registroT)
        grupoT = findViewById(R.id.grupoT)
        passT = findViewById(R.id.password_registroT)

        btnRegistrarme.setOnClickListener {

            val emailT = correoT.editText?.text
            val pswT = passT.editText?.text

            val usuarioT = UsuarioT(
                emailT.toString(),
                nombreT.editText?.text.toString(),
                apellidoPT.editText?.text.toString(),
                apellidoMT.editText?.text.toString(),
                grupoT.editText?.text.toString())

            val confirmaDialogo = AlertDialog.Builder(it.context)
            confirmaDialogo.setTitle("Confirmar Usuario")
            confirmaDialogo.setMessage("""
                Usuario: ${nombreT.editText?.text} ${apellidoPT.editText?.text} ${apellidoMT.editText?.text}
                Correo: ${correoT.editText?.text}
                Contraseña: ${passT.editText?.text}
                """.trimIndent())
            confirmaDialogo.setPositiveButton("confirmar"){confirmaDialogo,which->

                if (emailT.toString().isNotEmpty() && pswT.toString().isNotEmpty()){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    emailT.toString(),pswT.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this,LoginA::class.java).apply {
                                baseDeDatosT.collection("usuariosT").document(emailT.toString())
                                    .set(usuarioT)
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


        btnRegresar.setOnClickListener {
            val intent = Intent(this, LoginT::class.java)
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

