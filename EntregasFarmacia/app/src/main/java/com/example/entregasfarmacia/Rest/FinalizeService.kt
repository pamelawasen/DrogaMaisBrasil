package com.example.entregasfarmacia.Rest

import android.content.Context
import com.example.entregasfarmacia.ListaPedidos

object FinalizeService {
    fun getFinalizados(context: Context): List<ListaPedidos> {

        val dados = mutableListOf<ListaPedidos>()
        var nomes = arrayOf(
             "Thaiana Borges",
            "Luiza Sampaio",
            "Fabio Teixeira",
            "Claudio Franca",
            "Aline Medeiros",
            "Luiza Sampaio"
        )
        val remedios = arrayOf(
            "Amoxilina, Parecetamol, Resfenol, Vitacid",
            "Loratadina, Parecetamol, Dipirona",
            "Soro Fisiologio, Ziagen, Betaxol",
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

        //Criar 5 cards
        for (i in 0..5) {
            val d = ListaPedidos()
            d.nome = nomes[i]
            d.pedido = remedios[i]
            d.prazoentrega = dtprazo[i]
            d.endereco = adress[i]
            d.comentario = ""
            dados.add(d)


        }
        return dados
    }
}