package id.interconnect.projectlifecyclemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import id.interconnect.projectlifecyclemanagement.uicomponent.DatePickerFragment
import kotlinx.android.synthetic.main.activity_make_task.*
import kotlinx.android.synthetic.main.toolbar_blank.*
import java.text.SimpleDateFormat
import java.util.*

class MakeTaskActivity : AppCompatActivity(),View.OnClickListener, DatePickerFragment.DialogDateListener {

    lateinit var calendar_dateStart:Calendar
    lateinit var calendar_dateEnd:Calendar
    lateinit var calendar_dateDue: Calendar

    companion object{
        private const val DATE_START = "START"
        private const val DATE_END = "END"
        private const val DATE_DUE = "DUE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_task)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        maketask_startDate_input.setOnClickListener(this)
        maketask_endDate_input.setOnClickListener(this)
        maketask_dueDate_input.setOnClickListener(this)
        calendar_dateStart = Calendar.getInstance()
        calendar_dateEnd = Calendar.getInstance()
        calendar_dateDue = Calendar.getInstance()
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        //inisialisasi nilai ketika dipilih inputannya
        if(tag==DATE_START){
            maketask_startDate_input.setText(dateFormat.format(calendar.time))
            calendar_dateStart = calendar
        }else if(tag== DATE_END){
            maketask_endDate_input.setText(dateFormat.format(calendar.time))
            calendar_dateEnd = calendar
        }else{
            maketask_dueDate_input.setText(dateFormat.format(calendar.time))
            calendar_dateDue = calendar
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.maketask_startDate_input -> {
                val datePickerDialog =
                    DatePickerFragment()
                datePickerDialog.setDateChoosen(calendar_dateStart)
                datePickerDialog.show(supportFragmentManager, DATE_START)
            }
            R.id.maketask_endDate_input->{
                val datePickerDialog =
                    DatePickerFragment()
                datePickerDialog.setDateChoosen(calendar_dateEnd)
                datePickerDialog.show(supportFragmentManager, DATE_END)
            }
            R.id.maketask_dueDate_input->{
                val datePickerDialog =
                    DatePickerFragment()
                datePickerDialog.setDateChoosen(calendar_dateDue)
                datePickerDialog.show(supportFragmentManager, DATE_DUE)
            }
        }
    }

    //    val date1 = DatePickerDialog.OnDateSetListener { datePicker, year, month, day->
//        val calendar = Calendar.getInstance()
//        calendar.set(year, month, day)
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//        maketask_startDate_input.setText(dateFormat.format(calendar.time))
//    }
}