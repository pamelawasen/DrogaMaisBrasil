package com.example.entregasfarmacia.persistencia

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.entregasfarmacia.ListaPedidos

@Dao
interface pedidosDAO {
    @Query("SELECT * FROM pedidos where id = :id")
    fun getById(id: Long) : ListaPedidos?

    @Query("SELECT * FROM pedidos")
    fun findAll(): List<ListaPedidos>

    @Insert
    fun insert(pedido: ListaPedidos)

    @Delete
    fun delete(pedido: ListaPedidos)

}