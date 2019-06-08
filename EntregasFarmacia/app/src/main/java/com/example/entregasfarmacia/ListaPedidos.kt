package com.example.entregasfarmacia


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "Pedidos")
class ListaPedidos:Serializable {

        @PrimaryKey
        var id:Long = 0
        var nome = ""
        var pedido = ""
        var prazoentrega = ""
        var endereco = ""
        var comentario = ""
        var status = ""



    override fun toString(): String {
        return "ListaPedidos(nome do cliente = $nome)"
    }

}