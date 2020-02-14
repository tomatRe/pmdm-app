package com.miguel.tasks

import android.content.Context
import android.database.Cursor
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.angel.pmdmfinalapp.MainActivity
import com.angel.pmdmfinalapp.R
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
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
        holder.textTask.text = cursor.getString(2)
        holder.dateTask.text = cursor.getString(1)
    }
    inner class ViewHolder : RecyclerView.ViewHolder {
        // Creamos las referencias de la UI.
        val textTask: TextView
        val dateTask: TextView

        constructor(view: View1) : super(view) {
            this.textTask = view.findViewById(R.id.tv_Name)
            this.dateTask = view.findViewById(R.id.tv_description)
            view.setOnClickListener {
                Toast.makeText(
                    context,
                    "${this.textTask.text} - ${this.dateTask.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            view.updateLayoutParams {

            }
            view.setOnLongClickListener {

                when (actionMode) {
                    null -> {
                        // Lanzamos el ActionMode.
                        Toast.makeText(context, "Option 1", Toast.LENGTH_LONG).show()
                        actionMode = it.startActionMode(actionModeCallback)
                        it.isSelected = true
                        true
                    }
                    else -> false
                }
            }
        }

        private val actionModeCallback = object : ActionMode.Callback {

            override fun onActionItemClicked(
                mode: ActionMode?, item: MenuItem?): Boolean {
                return /*when (item!!.itemId) {
                    R.id.option01 -> {
                        Toast.makeText(
                            MainActivity().applicationContext,
                            "Option 1",
                            Toast.LENGTH_LONG
                        ).show()
                        return true
                    }
                    R.id.option02 -> {
                        Toast.makeText(
                            MainActivity().applicationContext,
                            "Option 2",
                            Toast.LENGTH_LONG
                        ).show()
                        return true
                    }
                    else -> false
                }*/ false
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