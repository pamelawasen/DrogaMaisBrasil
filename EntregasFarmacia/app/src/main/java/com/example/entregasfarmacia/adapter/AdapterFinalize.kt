package com.example.entregasfarmacia.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.entregasfarmacia.ListaPedidos
import com.example.entregasfarmacia.R
import kotlinx.android.synthetic.main.adapterfinalize.view.*


class AdapterFinalize(
    val listaPedidos:List<ListaPedidos>,
    val onClick:(ListaPedidos) -> Unit): RecyclerView.Adapter<AdapterFinalize.AdapterFinalizeViewHolder>() {

    class AdapterFinalizeViewHolder(view: View): RecyclerView.ViewHolder(view){
        val cardNomecli: TextView
        val cardbodyped: TextView
        var cardView: CardView


        init {
            cardNomecli = view.cardNomecli
            cardbodyped = view.cardbodyped
            cardView = view.cardlistap1


        }
    }

    override fun getItemCount() = this.listaPedidos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): AdapterFinalizeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapterfinalize,parent,false)
        val holder = AdapterFinalizeViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: AdapterFinalizeViewHolder, position: Int) {
        val context = holder.itemView.context
        val pedidos = this.listaPedidos[position]

        holder.cardNomecli.text = pedidos.nome
        holder.cardbodyped.text = pedidos.pedido
        holder.itemView.setOnClickListener{onClick(pedidos)}
    }

}