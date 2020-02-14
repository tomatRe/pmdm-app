package com.angel.pmdmfinalapp

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var eventsDBHelper: MyDBOpenHelper
    }
    //var db: SQLiteDatabase? = null

    private var events = ArrayList<eventObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Instanciamos el objeto MyDBOpenHelper.
        eventsDBHelper = MyDBOpenHelper(this, null)
        downloadData()

        fab.setOnClickListener { view ->
            val intent = Intent(this, AddEvent::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        downloadData()
    }

    private fun downloadData(){

        val db: SQLiteDatabase = eventsDBHelper.readableDatabase

        val cursor = db.rawQuery(
            " SELECT * FROM ${MyDBOpenHelper.TABLE_EVENTS}",
            null
        )

        // Comprobamos que haya al menos un registro.
        if (cursor.moveToFirst()) {

            do {
                // Creamos el adaptador con el resultado del cursor.
                val myRecyclerViewAdapter = MyRecyclerViewAdapter()
                myRecyclerViewAdapter.MyRecyclerViewAdapter(this, cursor)
                // Montamos el RecyclerView.
                myRecycler.setHasFixedSize(true)
                myRecycler.layoutManager = LinearLayoutManager(this)
                myRecycler.adapter = myRecyclerViewAdapter
            } while (cursor.moveToNext())

        } else {
            myToast("No hay datos a mostrar.")
            Log.d("Database", "Database empty")
        }

        db.close()
    }

    private fun myToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
