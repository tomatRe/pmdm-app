package com.angel.pmdmfinalapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.angel.pmdmfinalapp.MainActivity.Companion.eventsDBHelper
import kotlinx.android.synthetic.main.activity_add_event.*

class AddEvent : AppCompatActivity() {

    private var color: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        MainActivity.eventsDBHelper = MyDBOpenHelper(this, null)

        et_name.setOnClickListener(){
            bt_save.isEnabled = true
            bt_save.isClickable = true
        }

        bt_save.setOnClickListener(){
            eventsDBHelper.addEvent(
                et_name.text.toString(), sw_allDay.isChecked,
                et_since.text.toString(), et_untill.text.toString(),
                color, et_description.text.toString()
            )
            
        }
    }
}
