package com.angel.pmdmfinalapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var eventsDBHelper: MyDBOpenHelper
    }

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
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun downloadData(){
        //TODO download data from database

        /*
         tv_database.text = ""
            val db: SQLiteDatabase = peopleDBHelper.readableDatabase

            val cursor = db.rawQuery(
                " SELECT * FROM ${MyDBOpenHelper.TABLE_PEOPLE}",
                null
            )

            // Comprobamos que haya al menos un registro.
            if (cursor.moveToFirst()) {
                do {
                    tv_database.append(cursor.getInt(0).toString() + " - ")
                    tv_database.append(cursor.getString(1).toString() + " ")
                    tv_database.append(cursor.getString(2).toString() + "\n")
                } while (cursor.moveToNext())
            } else {
                myToast("No hay datos a mostrar.")
            }

            db.close()
        */
    }
}
