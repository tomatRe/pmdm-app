package com.angel.pmdmfinalapp

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.angel.pmdmfinalapp.MainActivity.Companion.eventsDBHelper
import kotlinx.android.synthetic.main.activity_add_event.*
import java.text.SimpleDateFormat
import java.util.*

class AddEvent : AppCompatActivity() {

    private var color: Int = 0
    private var dateSince: String = ""
    private var dateTo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        eventsDBHelper = MyDBOpenHelper(this, null)

        bt_since.setOnClickListener(){
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker,
                                                                       i, i2, i3 ->
                cal.set(Calendar.YEAR, i)
                cal.set(Calendar.MONTH, i2)
                cal.set(Calendar.DAY_OF_MONTH, i3)
                dateSince += "${cal.get(Calendar.DAY_OF_MONTH)}" +
                        "/${cal.get(Calendar.MONTH) + 1}" +
                        "/${cal.get(Calendar.YEAR)}"

                val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker,
                                                                           hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)
                    dateSince += " " + SimpleDateFormat("HH:mm").format(cal.time)
                    bt_since.text = dateSince
                    dateSince = ""
                }
                TimePickerDialog(this,
                    timeSetListener,
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    true
                ).show()

            }

            //if(!sw_allDay.isChecked)
                DatePickerDialog(
                    this,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
        }


        bt_untill.setOnClickListener {

            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker,
                                                                       i, i2, i3 ->
                cal.set(Calendar.YEAR, i)
                cal.set(Calendar.MONTH, i2)
                cal.set(Calendar.DAY_OF_MONTH, i3)
                dateTo += "${cal.get(Calendar.DAY_OF_MONTH)}" +
                        "/${cal.get(Calendar.MONTH) + 1}" +
                        "/${cal.get(Calendar.YEAR)}"

                val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker,
                                                                           hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)
                    dateTo += " " + SimpleDateFormat("HH:mm").format(cal.time)
                    bt_untill.text = dateTo
                    dateTo = ""
                }
                TimePickerDialog(this,
                    timeSetListener,
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    true
                ).show()

            }
            //if(!sw_allDay.isChecked)
                DatePickerDialog(
                    this,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
        }


        et_name.setOnClickListener(){
            bt_save.isEnabled = true
            bt_save.isClickable = true
        }

        bt_save.setOnClickListener(){

            if(!et_name.text.isBlank() && dateSince != "" && dateTo != "")
                eventsDBHelper.addEvent(
                    et_name.text.toString(), sw_allDay.isChecked,
                    dateSince, dateTo, color, et_description.text.toString()
                )
            else
                myToast("Debes rellenar al menos el nombre")

            finish()
        }
    }

    private fun myToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
