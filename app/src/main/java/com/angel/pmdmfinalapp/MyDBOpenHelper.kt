package com.angel.pmdmfinalapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.os.Build
import android.util.Log
import java.time.LocalDateTime
import java.util.*

class MyDBOpenHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
        SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

        companion object {
            val DATABASE_VERSION = 1
            val DATABASE_NAME = "MyDatabase.db"
            val TABLE_EVENTS = "events"
            val COLUMN_NAME = "name"
            val COLUMN_ALLDAY = "allDay"
            val COLUMN_SINCE = "since"
            val COLUMN_TO = "to"
            val COLUMN_COLOR = "color"
            var COLUMN_DESCRIPTION = "description"
    }

    /**
     * Método llamado cuando se crea la base por primera
     * vez. Debe producirse la creación de todas las tablas
     * que formen la base de datos.
     */

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val createTableEvents = "CREATE TABLE "+ TABLE_EVENTS +
                    "(_id INTEGER PRIMARY KEY, name TEXT, allDay BOOLEAN, since TEXT," +
                    " to TEXT, color INTEGER, description TEXT)"
            db!!.execSQL(createTableEvents)
        } catch (e: SQLiteException) {
            Log.e("SQLite(onCreate)", e.message.toString())
        }
    }

    /**
     * Este método se invocará cuando la base de datos necesite
     * ser actualizada. Se utiliza para hacer DROPs, añadir tablas
     * o cualquier acción que actualice el esquema de la BD.
     */

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        try {
            val dropTableEvents = "DROP TABLE IF EXISTS events"
            db!!.execSQL(dropTableEvents)
            onCreate(db)
        } catch (e: SQLiteException) {
            Log.e("SQLite(onUpgrade)", e.message.toString())
        }
    }
    /**
     * Método opcional.
     * Se llamará a este método después de abrir la base de datos,
     * antes de ello, comprobará si está en modo lectura. Se llama
     * justo después de establecer la conexión y crear el esquema.
     */
    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        Log.d("onOpen","Database opened!!")
    }

    /**
     * Método para añadir un Evento a la tabla.
     */

    fun addEvent(name: String, allDay: Boolean, since: String, to: String, color:Int, description: String) {
        // Creamos un ArrayMap<>().
        val data = ContentValues()

        data.put(COLUMN_NAME, name)
        data.put(COLUMN_ALLDAY, allDay)

        if (allDay){
            data.put(COLUMN_SINCE, "All Day")
            data.put(COLUMN_TO, "All Day")
        }
        else{
            data.put(COLUMN_SINCE, since)
            data.put(COLUMN_TO, to)
        }
        data.put(COLUMN_COLOR, color)
        data.put(COLUMN_DESCRIPTION, description)

        // Abrimos la BD en modo escritura.
        val db = this.writableDatabase
        db.insert(TABLE_EVENTS, null, data)
        db.close()
    }

    /**
     * Método para eliminar un Evento de la tabla
     * por el nombre.
     */
    fun delEvent(name: String) {
        val args = arrayOf(name)
        // Abrimos la BD en modo escritura.
        val db = this.writableDatabase
        // Se puede elegir un sistema u otro.
         db.delete(TABLE_EVENTS, "$COLUMN_NAME=?", args)
        //db.execSQL("DELETE FROM $TABLE_PEOPLE WHERE $COLUMN_NAME=?", args)
        db.close()
    }


    /**
     * Método para actualizar el nombre de un Evento
     * de la tabla por el nombre.
     */
    fun updateEvent(oldName: String, newName: String) {
        val args = arrayOf(oldName)
        // Creamos un ArrayMap<>().
        val data = ContentValues()
        data.put(COLUMN_NAME, newName)
        val db = this.writableDatabase
        db.update(TABLE_EVENTS, data, "$COLUMN_NAME=?", args)
        db.close()
    }
}