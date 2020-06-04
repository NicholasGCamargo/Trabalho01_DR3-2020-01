package com.example.tp03dr3_firebaseauthefirestore.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp03dr3_firebaseauthefirestore.DeletarEditarActivity
import com.example.tp03dr3_firebaseauthefirestore.R
import com.example.tp03dr3_firebaseauthefirestore.classes.FirebaseClass
import kotlinx.android.synthetic.main.rcy_vw_layout.view.*

class MeuAdapter(val lista: List<FirebaseClass>): RecyclerView.Adapter<MeuAdapter.DadosViewHolder>() {
    class DadosViewHolder(v: View):RecyclerView.ViewHolder(v){
        val campoTexto = v.txtVwRcy
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: DadosViewHolder, position: Int) {
        val dado = lista[position]
        holder.campoTexto.text = "Nome: ${dado.nome}\nEmail: ${dado.email}\nNumero: ${dado.numero}"
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DeletarEditarActivity::class.java)
            intent.putExtra("dado", dado)
            it.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DadosViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.rcy_vw_layout,
                parent, false
            )

        return DadosViewHolder(v)
    }

}