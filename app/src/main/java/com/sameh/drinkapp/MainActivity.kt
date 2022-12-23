package com.sameh.drinkapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    private lateinit var drank_choose: AutoCompleteTextView
    private lateinit var result: TextView
    private lateinit var order_button: Button

    private val OrangeJuice = "OrangeJuice"
    private val AppleJuice = "AppleJuice"
    private val MangoJuice = "MangoJuice"
    private val KewiJuice = "KewiJuice"

    private val mymap = mapOf(
        OrangeJuice to 10.0,
        AppleJuice to 15.0,
        MangoJuice to 25.0,
        KewiJuice to 30.0
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initilization()

        val myList = listOf(OrangeJuice, AppleJuice, MangoJuice, KewiJuice)
        val myAdapter = ArrayAdapter(this,R.layout.text_view_for_menu, myList)
        drank_choose.setAdapter(myAdapter)

        drank_choose.setOnItemClickListener { adapterView, view, i, l ->
            val drankNmae = drank_choose.text.toString()
            val res = mymap[drankNmae]
            result.setText(res.toString())
        }

        order_button.setOnClickListener {

            val message = "I want to order: ${drank_choose.text.toString()}"
            val MyInt = Intent(Intent.ACTION_SENDTO)
            MyInt.data = Uri.parse("mailto:")
            MyInt.putExtra(Intent.EXTRA_EMAIL, arrayOf("example@gmail.com"))
            MyInt.putExtra(Intent.EXTRA_SUBJECT, "Order")
            MyInt.putExtra(Intent.EXTRA_TEXT, message)

            if(MyInt.resolveActivity(packageManager) != null) {
                startActivity(MyInt)
            }
            else {
                Toast.makeText(this, "App not found", Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun initilization(){
        drank_choose = findViewById(R.id.drank_choose)
        result = findViewById(R.id.result)
        order_button = findViewById(R.id.order_button)
    }
}