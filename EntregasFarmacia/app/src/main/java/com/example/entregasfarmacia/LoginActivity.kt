package com.example.entregasfarmacia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.nav_header_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnEntrar.setOnClickListener {
            loginuser(editID.text.toString(),editPassword.text.toString())
        }

    }
    fun loginuser(id:String,password:String){
        if (id == "203040" && password == "drogamais"){
            var nome = "João Mauricio"
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("nomeuser",nome)
            startActivity(intent)
       }else{
            Toast.makeText(this@LoginActivity,"Usuário ou senha incorretos",Toast.LENGTH_SHORT).show()
        }
    }
}
