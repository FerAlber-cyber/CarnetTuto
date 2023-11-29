package mx.tecm.ciudadhidalgo.carnetuto.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tecm.ciudadhidalgo.carnetuto.R
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.UsuarioT

class UsuarioTAdapter(private val listaUsuarios: List<UsuarioT>) :
    RecyclerView.Adapter<UsuarioTAdapter.UsuarioTViewHolder>() {

    class UsuarioTViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewCorreoT: TextView = itemView.findViewById(R.id.textViewCorreoT)
        val textViewNombreT: TextView = itemView.findViewById(R.id.textViewNombreT)
        val textViewApellidoPT: TextView = itemView.findViewById(R.id.textViewApellidoPT)
        val textViewApellidoMT: TextView = itemView.findViewById(R.id.textViewApellidoMT)
        val textViewGrupoT: TextView = itemView.findViewById(R.id.textViewGrupoT)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioTViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario_tutor, parent, false)
        return UsuarioTViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsuarioTViewHolder, position: Int) {
        val usuario = listaUsuarios[position]

        with(holder) {
            textViewCorreoT.text = "Correo: ${usuario.correoT}"
            textViewNombreT.text = "Nombre: ${usuario.nombreT}"
            textViewApellidoPT.text = "Apellido Paterno: ${usuario.apellidoPT}"
            textViewApellidoMT.text = "Apellido Materno: ${usuario.apellidoMT}"
            textViewGrupoT.text = "Grupo: ${usuario.grupoT}"
        }
    }

    override fun getItemCount(): Int = listaUsuarios.size
}
