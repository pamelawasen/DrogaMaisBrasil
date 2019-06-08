package com.example.entregasfarmacia

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.entregasfarmacia.Rest.PedidosService
import kotlinx.android.synthetic.main.activity_exibir.*
import kotlinx.android.synthetic.main.adapterfinalize.*
import kotlinx.android.synthetic.main.adapterimpedido.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.toolbar.toolbar

class ExibirActivity : AppCompatActivity() {

    private val context: Context get()=this
    private var pedidos:ListaPedidos? = null
    var recycler: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibir)

        recycler = recyclerPedidos
        recycler?.layoutManager = LinearLayoutManager(context)
        recycler?.itemAnimator = DefaultItemAnimator()
        recycler?.setHasFixedSize(true)

        val Toolbar = toolbar
        setSupportActionBar(Toolbar)
        supportActionBar!!.title = "Detalhes do pedido"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)



        btnImpedido.setOnClickListener {
            btnImp()
        }

        btnSalva.setOnClickListener {
            btnSalve()
        }

        btnFinalizar.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        super.onResume()
        preencher()
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun preencher() {

        Log.e("abrir a activity","abrindo activiy do cardview")

        if (intent.hasExtra("nome")&& intent.hasExtra("ped") && intent.hasExtra("end"))


                txtNome.text =  intent.getStringExtra("nome")
                txtPedido.text = intent.getStringExtra("ped")
                txtData.text = intent.getStringExtra("data")
                txtEndereco.text = intent.getStringExtra("end")

    }
    fun btnImp(){
        editCMT?.visibility = View.VISIBLE
        btnSalva?.visibility = View.VISIBLE

    }

    fun btnSalve(){
        val args:Bundle = intent.extras
        var id = args?.getInt("id")
        var cmt1 = editCMT.text.toString()
        cardNomecli1?.text = intent.getStringExtra("nome")
        cardbodyped1?.text = intent.getStringExtra("ped")
        Log.e("mensagem de variavel","${id}")
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("motivo",cmt1)
        startActivity(intent)
        taskExcluir()


    }
    private fun taskExcluir() {
        if (this.pedidos != null && this.pedidos is ListaPedidos) {
            // Thread para remover
            Thread {
                PedidosService.delete(this.pedidos as ListaPedidos)
                runOnUiThread {
                    // após remover, voltar para activity anterior
                    finish()
                }
            }.start()
        }
    }


}
