package com.example.propinasexamen.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.propinasexamen.R

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Obtener el total de propina del intent
        val total = intent.getDoubleExtra("total", 0.0)

        // Mostrar el total de propina en un TextView
        val totalTextView = findViewById<TextView>(R.id.totalTextView)
        totalTextView.text = "Total de propina: $total"

        // Agregar un botón para ir a la actividad de empleados
        val nextActivityButton = findViewById<Button>(R.id.nextActivityButton)
        nextActivityButton.setOnClickListener {
            val intent = Intent(this, EmployeeDistributionActivity::class.java)
            intent.putExtra("total", total)
            startActivity(intent)
        }
    }
}
