package com.example.entregasfarmacia.persistencia

import android.arch.persistence.room.Room

object DatabaseManager {
    // singleton
    private var dbInstance: Dadosdb
    init {
        val appContext = Apliication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            appContext, // contexto global
            Dadosdb::class.java, // Referência da classe do banco
            "lms.sqlite" // nome do arquivo do banco
        ).build()
    }

    fun pedidosDAO(): pedidosDAO {
        return dbInstance.pedidosDAO()
    }
}