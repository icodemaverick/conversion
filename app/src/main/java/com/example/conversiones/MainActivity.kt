package com.example.conversiones

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val btnConvert: Button = findViewById(R.id.BtnConvert)
        val txtQtyKilograms: TextView = findViewById(R.id.TxtQtyKilograms)
        val txtQtyPunds: TextView = findViewById(R.id.TxtQtyPounds)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnConvert.setOnClickListener {
            val strQtyKilograms: String = txtQtyKilograms.getText().toString()

            if(isValidInput(strQtyKilograms)){
                val qtyKilograms: Float = strQtyKilograms.toFloat()
                val qtyPounds: Float = convertKgToLb(qtyKilograms)
                txtQtyPunds.text = String.format(Locale.getDefault(), "%f", qtyPounds)
            }
        }
    }

    private fun isValidInput(inputText: String?): Boolean {

        if(inputText.isNullOrEmpty()){
            val toast = Toast.makeText(
                this,
                "Cantidad de kilogramos incorrecta",
                Toast.LENGTH_SHORT)
            toast.show()

            return false
        }

        if(inputText.toFloat() < 0F){
            val toast = Toast.makeText(
                this,
                "Cantidad de kilogramos no puede ser menor a CERO",
                Toast.LENGTH_SHORT)
            toast.show()

            return false
        }

        return true
    }

    private fun convertKgToLb(kilograms: Float): Float {
        val pounds: Float = kilograms * 2.20462F
        return pounds
    }
}