package com.example.entregasfarmacia.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.entregasfarmacia.ListaPedidos
import com.example.entregasfarmacia.R
import kotlinx.android.synthetic.main.adapterimpedido.view.*


class AdapterImpedido(
    val listaimpedidos:List<ListaPedidos>,
    val onClick:(ListaPedidos) -> Unit): RecyclerView.Adapter<AdapterImpedido.ImpedidoViewHolder>() {

    class ImpedidoViewHolder(view: View):RecyclerView.ViewHolder(view){
        val cardNomecli1: TextView
        val cardbodyped1: TextView
        val cardcmt:TextView
        val cardlistap2:CardView
        val status:TextView

        init {
            cardNomecli1 = view.cardNomecli1
            cardbodyped1 = view.cardbodyped1
            cardcmt = view.cardcmt
            cardlistap2 = view.cardlistap2
            status = view.status

        }
    }
    override fun getItemCount() = this.listaimpedidos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ImpedidoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapterimpedido,parent,false)
        val holder = ImpedidoViewHolder(view)
        return holder
    }


    override fun onBindViewHolder(holder: ImpedidoViewHolder, position: Int) {
        val context = holder.itemView.context
        val pedidos = this.listaimpedidos[position]

        holder.cardNomecli1.text = pedidos.nome
        holder.cardbodyped1.text = pedidos.pedido
        holder.cardcmt.text = pedidos.comentario
        holder.itemView.setOnClickListener{onClick(pedidos)}
    }
}