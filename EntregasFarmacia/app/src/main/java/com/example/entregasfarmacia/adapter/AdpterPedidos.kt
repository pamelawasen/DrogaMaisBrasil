package com.example.entregasfarmacia.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.entregasfarmacia.ListaPedidos
import com.example.entregasfarmacia.R
import kotlinx.android.synthetic.main.adapterpedidos.view.*

class AdpterPedidos(
        val listaPedidos:List<ListaPedidos>,
        val onClick:(ListaPedidos) -> Unit): RecyclerView.Adapter<AdpterPedidos.PedidosViewHolder>() {

    class PedidosViewHolder(view: View):RecyclerView.ViewHolder(view){
        val cardNome: TextView
        val cardbody: TextView
        var cardView: CardView
        var cardentrega:TextView



        init {
            cardNome = view.cardNome
            cardbody = view.cardbody
            cardView = view.cardlistap
            cardentrega = view.cardentrega
        }
    }
    override fun getItemCount() = this.listaPedidos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): PedidosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapterpedidos,parent,false)
        val holder = PedidosViewHolder(view)
        return holder
    }


    override fun onBindViewHolder(holder: PedidosViewHolder, position: Int) {
        val context = holder.itemView.context
        val pedidos = this.listaPedidos[position]
        holder.cardNome.text = pedidos.nome
        holder.cardbody.text = pedidos.pedido
        holder.cardentrega.text = pedidos.prazoentrega
        holder.itemView.setOnClickListener{onClick(pedidos)}
    }
}