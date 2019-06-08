package com.example.entregasfarmacia.persistencia

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.entregasfarmacia.ListaPedidos

// anotação define a lista de entidades e a versão do banco
@Database(entities = arrayOf(ListaPedidos::class), version = 2)
abstract class Dadosdb : RoomDatabase() {
    abstract fun pedidosDAO(): pedidosDAO
}