package com.odogwudev.enoma_loves

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(){
    var FLAMES = "FLAMES"
    var arr_FLAMES = FLAMES.toCharArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edit_yourName = findViewById<EditText>(R.id.input_your_name)
        val edit_partnerName = findViewById<EditText>(R.id.input_partner_name)
        val btn_test = findViewById<Button>(R.id.flame_test)

        btn_test.setOnClickListener {

            if (!TextUtils.isEmpty(edit_yourName.text.toString())) {
                if (!TextUtils.isEmpty(edit_partnerName.text.toString())) {
                    val inputMethodManager = (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
                    inputMethodManager.hideSoftInputFromWindow(if (null == currentFocus) null else currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                    resetValues()
                    start(edit_yourName,edit_partnerName)
                }
            }
        }
    }

    private fun start(edit_yourName: EditText, edit_partnerName: EditText) {
        val intent = Intent(this@MainActivity, FlamesActivity::class.java)
        intent.putExtra("your_name", edit_yourName.text.toString())
        intent.putExtra("your_partner", edit_partnerName.text.toString())
        startActivity(intent)
    }

    fun resetValues() {
        FLAMES = getString(R.string.FLAMES)
        arr_FLAMES = FLAMES.toCharArray()
    }
}