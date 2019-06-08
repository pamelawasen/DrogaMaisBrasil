package com.example.entregasfarmacia

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.example.entregasfarmacia.Rest.FinalizeService
import com.example.entregasfarmacia.Rest.PedidosService
import com.example.entregasfarmacia.Rest.impedidos
import com.example.entregasfarmacia.adapter.AdapterFinalize
import com.example.entregasfarmacia.adapter.AdapterImpedido
import com.example.entregasfarmacia.adapter.AdpterPedidos
import kotlinx.android.synthetic.main.adapterimpedido.*
import kotlinx.android.synthetic.main.adapterpedidos.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context:Context get()=this
    private var pedidos = listOf<ListaPedidos>()
    val op = ListaPedidos()
    var recycler:RecyclerView? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        recycler = recyclerPedidos
        recycler?.layoutManager = LinearLayoutManager(context)
        recycler?.itemAnimator = DefaultItemAnimator()
        recycler?.setHasFixedSize(true)




        btnPend.setOnClickListener {
            taskPedido()
            btnImp.setBackgroundResource(R.drawable.estilounico)
            btnFim.setBackgroundResource(R.drawable.estilounico)
            btnPend.setBackgroundResource(R.drawable.stilo)
        }

        btnFim.setOnClickListener {
           taskfinalize()
            btnImp.setBackgroundResource(R.drawable.estilounico)
            btnPend.setBackgroundResource(R.drawable.estilounico)
            btnFim.setBackgroundResource(R.drawable.stilo)
        }

        btnImp.setOnClickListener {
            taskImpedido()
            btnFim.setBackgroundResource(R.drawable.estilounico)
            btnPend.setBackgroundResource(R.drawable.estilounico)
            btnImp.setBackgroundResource(R.drawable.stilo)
            cardcmt?.text  = intent.getStringExtra("motivo")
        }


    }



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        super.onResume()

        taskPedido()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun taskfinalize() {

        Thread {

            this.pedidos = FinalizeService.getFinalizados(context)
            runOnUiThread {

                recycler?.adapter = AdapterFinalize(this.pedidos) {  }
            }
        }.start()
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun taskImpedido() {

            Thread {

                this.pedidos = impedidos.getImpedidos(context)
                runOnUiThread {

                    recycler?.adapter = AdapterImpedido(this.pedidos) { onClickimpedidos(it) }

                }
            }.start()
        }
    fun onClickimpedidos(pedido:ListaPedidos){
        cardcmt.visibility = View.VISIBLE
        cardcmt.text  = intent.getStringExtra("motivo")


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun taskPedido(){


        Thread {

            this.pedidos = PedidosService.getPedidos(context)
            runOnUiThread {

                recycler?.adapter = AdpterPedidos(this.pedidos) { onClickpedidos(it) }
            }
        }.start()

    }

    fun onClickpedidos(pedido:ListaPedidos){

        intent = Intent(context,ExibirActivity::class.java)
        val params = Bundle()
       // params.putInt("id",pedido.id)
        intent.putExtras(params)
        //intent?.putExtra("id",pedido.id)
        intent.putExtra("nome", pedido.nome)
        intent.putExtra("ped",pedido.pedido)
        intent.putExtra("end",pedido.endereco)
        intent.putExtra("data",pedido.prazoentrega)
        intent.putExtra("comentario",pedido.comentario)

        startActivity(intent)
        cardlistap.visibility = View.INVISIBLE

    }


    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menuselect, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as android.widget.SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
               if (newText != cardNome.toString()){
                   Toast.makeText(this@MainActivity,"$newText",Toast.LENGTH_SHORT).show()
               }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_buscar ->{
                Toast.makeText(this@MainActivity, "Botão de buscar",
                    Toast.LENGTH_LONG).show()
            }

           R.id.action_atu ->{
                progressBar?.visibility = View.VISIBLE
                val handler = Handler()
                handler.postDelayed({progressBar?.visibility = View.GONE},1000)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_sair -> {
                val intent =  Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_cadastro -> {
                val intent =  Intent(this,CadastroActivity::class.java)
                startActivity(intent)
            }

        }
        txtNameent.text = intent.getStringExtra("nomeuser")
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }
}
