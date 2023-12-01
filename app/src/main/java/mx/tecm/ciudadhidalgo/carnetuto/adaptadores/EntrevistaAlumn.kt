package mx.tecm.ciudadhidalgo.carnetuto.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tecm.ciudadhidalgo.carnetuto.R
import mx.tecm.ciudadhidalgo.carnetuto.dataClass.EntrevistaAlumn

class EntrevistaAlumnAdapter(val listaEntrevista: ArrayList<EntrevistaAlumn>)
    : RecyclerView.Adapter<EntrevistaAlumnAdapter.EntrevistaViewHolder>() {

    class EntrevistaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fecha: TextView = itemView.findViewById(R.id.fechaEntre)
        val noControl: TextView = itemView.findViewById(R.id.noControl)
        val temaEntre: TextView = itemView.findViewById(R.id.temaEntre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntrevistaViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_entrevistas, parent, false)
        return EntrevistaViewHolder(vista)
    }

    override fun onBindViewHolder(holder: EntrevistaViewHolder, position: Int) {
        val entrevista = listaEntrevista[position]
        holder.fecha.text = entrevista.fecha.toString()
        holder.noControl.text = entrevista.noControl.toString()
        holder.temaEntre.text = entrevista.temaEntevista.toString()
    }

    override fun getItemCount(): Int {
        return listaEntrevista.size
    }

}