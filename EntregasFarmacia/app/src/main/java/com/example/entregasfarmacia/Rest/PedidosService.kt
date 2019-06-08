@file:Suppress("UNREACHABLE_CODE")

package com.example.entregasfarmacia.Rest

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.widget.EditText
import com.example.entregasfarmacia.ListaPedidos
import com.example.entregasfarmacia.Response
import com.example.entregasfarmacia.persistencia.DatabaseManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_exibir.*

object PedidosService {
    val dados1 = mutableListOf<ListaPedidos>()

    var nomes = arrayOf(
        "Maria Vicente",
        "Ana Rosa",
        "Fabio Teixeira",
        "Claudio Franca",
        "Aline Medeiros",
        "Luiza Sampaio"
    )
    val remedios = arrayOf(
        "Amoxilina, Resfenol, Vitacid",
        "Loratadina, Parecetamol, Dipirona",
        "Soro Fisiologio, Dipirona, Betaxol",
        "Loratadina, Parecetamol, Dipirona",
        "Resfenol, Ziagen, Betametasona",
        "Amoxilina, Loratadina, Parecetamol"
    )
    var dtprazo = arrayOf(
        "23/06/2019",
        "25/06/2019",
        "10/06/2019",
        "23/06/2019",
        "18/06/2019",
        "16/06/2019")
    var adress = arrayOf(
        "Alameda Santos 158",
        "Av casa verde 328",
        "Av João Marcelino Branco, 95",
        "Av João Costa 98",
        "Av Paulo Garcia Aquiline 358",
        "R Barão Verde 510"
    )

    var coments = arrayOf(
        "Endereço não localizado",
        "Nenhum responsável para receber a mercadoria",
        "Endereço não localizado",
        "Nenhum responsável para recebr mercadoria",
        "Endereço não localizado",
        "Endereço não localizado"

    )

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getPedidos(context: Context): List<ListaPedidos> {
        val pedi = mutableListOf<ListaPedidos>()
        for (d in pedi)
            saveOffline(d)
        val dao = DatabaseManager.pedidosDAO()
        val dados = dao.findAll()


        for (i in 0..5) {
            val d = ListaPedidos()
            d.id = i.toLong()
            d.nome = nomes[i]
            d.pedido = remedios[i]
            d.prazoentrega = dtprazo[i]
            d.endereco = adress[i]
            d.status = "pendente"
            d.comentario = coments[i]

            pedi.add(d)


        }
        return dados


    }


        fun existePedido(pedido: ListaPedidos): Boolean {
            val dao = DatabaseManager.pedidosDAO()
            return dao.getById(pedido.id) == null
        }

        fun saveOffline(pedido: ListaPedidos): Boolean {
            val dao = DatabaseManager.pedidosDAO()

            if (existePedido(pedido)) {
                dao.insert(pedido)
            }

            return true
        }

        fun delete(dados: ListaPedidos) {
            val dao = DatabaseManager.pedidosDAO()
                dao.delete(dados)

        }

    /*fun delete1(num:Int,num1:Int,num2:Int,num3:Int,num4:Int,num5:Int) {
        dados1.removeAt(num)
    }*/



    }
