package mx.tecm.ciudadhidalgo.carnetuto.adaptadores
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tecm.ciudadhidalgo.carnetuto.R
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.UsuarioA

class UsuarioAAdapter(private val listaUsuariosA: List<UsuarioA>) :
    RecyclerView.Adapter<UsuarioAAdapter.UsuarioAViewHolder>() {

    class UsuarioAViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewCorreoA: TextView = itemView.findViewById(R.id.textViewCorreoA)
        val textViewNombreA: TextView = itemView.findViewById(R.id.textViewNombreA)
        val textViewApellidoPA: TextView = itemView.findViewById(R.id.textViewApellidoPA)
        val textViewApellidoMA: TextView = itemView.findViewById(R.id.textViewApellidoMA)
        val textViewNoControl: TextView = itemView.findViewById(R.id.textViewNoControl)
        val textViewGrupoA: TextView = itemView.findViewById(R.id.textViewGrupoA)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioAViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario_alumno, parent, false)
        return UsuarioAViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsuarioAViewHolder, position: Int) {
        val usuarioA = listaUsuariosA[position]

        with(holder) {
            textViewCorreoA.text = "${usuarioA.correoInst}"
            textViewNombreA.text = "${usuarioA.nombreA}"
            textViewApellidoPA.text = "${usuarioA.aPaternoA}"
            textViewApellidoMA.text = "${usuarioA.aMaternoA}"
            textViewNoControl.text = "${usuarioA.noControl}"
            textViewGrupoA.text = "${usuarioA.grupoA}"
        }
    }

    override fun getItemCount(): Int = listaUsuariosA.size
}