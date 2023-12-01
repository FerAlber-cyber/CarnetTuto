package mx.tecm.ciudadhidalgo.carnetuto

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.EntrevistaAlumn

class EntrevistaTutor : AppCompatActivity() {

    //lateinit
    private lateinit var btnRegistrarEntrevis: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var noControl:TextInputLayout
    private lateinit var fecha:TextInputLayout
    private lateinit var tema:TextInputLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrevista_tutor)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val baseDeDatos = Firebase.firestore

        noControl = findViewById(R.id.noControlA)
        fecha = findViewById(R.id.fechaEntre)
        tema = findViewById(R.id.temaEntre)
        btnRegistrarEntrevis = findViewById(R.id.btnRegistrarEntrevista)

        //
        val btnMenuTutor: MaterialButton = findViewById(R.id.btnMenuTutor)
        val btnEntrevista: MaterialButton = findViewById(R.id.btnEntrevistaTutor)
        val btnCanalizacion: MaterialButton = findViewById(R.id.btnCanalizacionTutor)
        val btnAsesoriaAca: MaterialButton = findViewById(R.id.btnAsesoriAcaTutor)

        btnRegistrarEntrevis.setOnClickListener {

            val noControlText = noControl.editText?.text.toString()
            val fechaText = fecha.editText?.text.toString()
            val temaText = tema.editText?.text.toString()

            // Crea un objeto EntrevistaAlumn
            val entrevistaAlumn = EntrevistaAlumn(noControlText, fechaText, temaText)

            // Accede a la colección "entrevistas" y agrega el nuevo documento
            val entrevistasCollection = firestore.collection("entrevistas")
            entrevistasCollection.add(entrevistaAlumn)
                .addOnSuccessListener { documentReference ->
                    // Manejar el éxito, si es necesario
                    Toast.makeText(this, "Entrevista registrada con éxito", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    // Manejar el error, si es necesario
                    Toast.makeText(this, "Error al registrar la entrevista: $e", Toast.LENGTH_SHORT).show()
                }
        }

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
}