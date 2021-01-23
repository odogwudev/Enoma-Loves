package com.odogwudev.enoma_loves

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import org.apache.commons.lang3.ArrayUtils
import java.util.*

class FlamesActivity : AppCompatActivity() {

    var FLAMES = "FLAMES"
    var arrFLAMES = FLAMES.toCharArray()
    lateinit var outputImg: ImageView

    var yourName: String? = null
    var partnerName: String? = null

    var arryourName: Array<String>? = null
    var arrpartnerName: Array<String>?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flames)

        val intent = intent
        val yourNameDisplay = intent.getStringExtra("your_name")
        val partnerNameDisplay = intent.getStringExtra("your_partner")

        outputImg = findViewById(R.id.output_img)

        val back = findViewById<Button>(R.id.back)

        processName(yourNameDisplay.toString(),partnerNameDisplay.toString())
        eliminateCommonLetters()
        displayOutput()

        back.setOnClickListener {
            updateUi()
        }
    }

    private fun processName(yourNameDisplay: String, partnerNameDisplay: String) {
        yourName = yourNameDisplay.toLowerCase(Locale.ROOT).replace("\\s+".toRegex(), "")
        arryourName = yourName!!.split("".toRegex()).toTypedArray()

        partnerName = partnerNameDisplay.toLowerCase(Locale.ROOT).replace("\\s+".toRegex(), "")
        arrpartnerName = partnerName!!.split("".toRegex()).toTypedArray()
    }

    private fun eliminateCommonLetters() {
        for (i in arryourName!!) {
            for (j in arrpartnerName!!) {
                if (i == j) {
                    arryourName = ArrayUtils.removeElement(arryourName, i)
                    arrpartnerName = ArrayUtils.removeElement(arrpartnerName, j)
                    break
                }
            }
        }
        val arrCompleteName: Array<String> = ArrayUtils.addAll(arryourName, *arrpartnerName!!)
        val stringBuilder = StringBuilder()
        for (string in arrCompleteName) {
            stringBuilder.append(string)
        }

        FlamesTest(stringBuilder.toString())
    }

    private fun FlamesTest(completeName: String) {
        var index: Int
        var flamesLength = 6
        while (FLAMES.length != 1) {
            index = completeName.length % flamesLength
            if (index == 0) {
                FLAMES = FLAMES.replace(arrFLAMES[FLAMES.length - 1].toString(), "")
                arrFLAMES = FLAMES.toCharArray()
            } else {
                FLAMES = FLAMES.replace(arrFLAMES[index - 1].toString(), "")
                FLAMES = FLAMES.substring(index - 1) + FLAMES.substring(0, index - 1)
                arrFLAMES = FLAMES.toCharArray()
            }
            flamesLength--
        }
    }

    private fun displayOutput() {
        when (arrFLAMES[0]) {
            'F' -> outputImg.setImageResource(R.drawable.friends)
            'L' -> outputImg.setImageResource(R.drawable.love)
            'A' -> outputImg.setImageResource(R.drawable.affection)
            'M' -> outputImg.setImageResource(R.drawable.marriage)
            'E' -> outputImg.setImageResource(R.drawable.enemies)
            'S' -> outputImg.setImageResource(R.drawable.sister)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        updateUi()
    }

    private fun updateUi() {
        val intent1 = Intent(this@FlamesActivity, MainActivity::class.java)
        startActivity(intent1)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        finish()
    }
}