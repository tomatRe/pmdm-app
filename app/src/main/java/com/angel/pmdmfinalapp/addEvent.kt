package com.angel.pmdmfinalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_event.*

class addEvent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        et_name.setOnClickListener(){
            bt_save.isEnabled = true
            bt_save.isClickable = true
        }

        bt_save.setOnClickListener(){


        }
    }
}
