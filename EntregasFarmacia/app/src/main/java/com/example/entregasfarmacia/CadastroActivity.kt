package com.example.entregasfarmacia

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.example.entregasfarmacia.Rest.PedidosService

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        btnCadastro.setOnClickListener {
            val pedidos = ListaPedidos()
            pedidos.nome = editNomes.text.toString()
            pedidos.pedido = editPedidos.text.toString()
            pedidos.prazoentrega = editData.text.toString()
            pedidos.endereco = editEndereco.text.toString()
            pedidos.comentario = ""

            taskAtualizar(pedidos)
        }
    }

    private fun taskAtualizar(dados: ListaPedidos) {
        // Thread para salvar
        Thread {
            PedidosService.saveOffline(dados)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }

    }

