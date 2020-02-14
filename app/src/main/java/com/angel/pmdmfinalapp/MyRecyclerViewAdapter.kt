package com.angel.pmdmfinalapp

import android.content.Context
import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import android.view.View as View1

class MyRecyclerViewAdapter :
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>(){
    lateinit var context: Context
    private lateinit var cursor: Cursor
    private var actionMode: ActionMode? = null

    fun MyRecyclerViewAdapter(context: Context, cursor: Cursor) {
        this.context = context
        this.cursor = cursor
    }
    /**
     * Inflamos la vista de los items.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("RECYCLERVIEW", "onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.card_layout,
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return if (cursor != null)
            cursor.count
        else 0
    }
    /**
     * Completamos los datos de cada vista mediante ViewHolder.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Importante para recorrer el cursor.
        cursor.moveToPosition(position)
        Log.d("RECYCLERVIEW", "onBindViewHolder")

        // Asignamos los valores a los elementos de la UI.
        Log.d("String del cursor", cursor.getString(0))
        holder.taskName.text = cursor.getString(1)
        holder.taskDescription.text = cursor.getString(6)
        holder.dateSince.text = cursor.getString(3)
        holder.dateTo.text = cursor.getString(4)

        holder.color = cursor.getInt(5)
        holder.itemView.setBackgroundColor(holder.color)
    }

    inner class ViewHolder : RecyclerView.ViewHolder {
        // Creamos las referencias de la UI.
        val taskName: TextView
        val taskDescription: TextView
        val dateSince: TextView
        val dateTo: TextView
        var color: Int

        constructor(view: View1) : super(view) {
            this.taskName = view.findViewById(R.id.tv_Name)
            this.taskDescription = view.findViewById(R.id.tv_description)
            this.dateSince = view.findViewById(R.id.tv_since)
            this.dateTo = view.findViewById(R.id.tv_to)
            this.color = 0

            view.setOnClickListener {
                Toast.makeText(
                    context,
                    "${this.taskName.text} - ${this.dateSince.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }


            view.setOnLongClickListener(){
                myAlertDialog("Estás seguro de que quieres borrar esta tarea?")
            }

            view.updateLayoutParams {
            }
        }

        private val actionButton = { dialog: DialogInterface, which: Int ->
            Delete()
        }

        private fun Delete(){
            val db: SQLiteDatabase = MainActivity.eventsDBHelper.readableDatabase
            //TODO
        }


        private fun myAlertDialog(message: String): Boolean {
            val builder = AlertDialog.Builder(context)
            // Creación del AlertDialog.
            builder.apply {
                // Asignamos un título.
                setTitle("Borrar")
                // Asignamos el cuerpo del mensaje.
                setMessage(message)
                // Definimos el comportamiento de los botones.
                setPositiveButton(android.R.string.ok,
                    DialogInterface.OnClickListener (function = actionButton))
                setNegativeButton(android.R.string.no) { _, _ ->
                    //nothing
                }
            }
            // Se muestra el AlertDialog
            builder.show()
            return true
        }

        private val actionModeCallback = object : ActionMode.Callback {

            override fun onActionItemClicked(
                mode: ActionMode?, item: MenuItem?): Boolean {
                return true
            }

            override fun onCreateActionMode(
                mode: ActionMode?, menu: Menu?
            ): Boolean {
                val inflater = AppCompatActivity().menuInflater
                inflater.inflate(R.menu.menu_main, menu)
                return true
            }

            override fun onPrepareActionMode(
                mode: ActionMode?, menu: Menu?
            ): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                actionMode = null
            }
        }
    }
}