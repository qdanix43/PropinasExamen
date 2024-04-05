package com.example.propinasexamen.activitys

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.propinasexamen.R

class EmployeeDistributionActivity : AppCompatActivity() {

    private var employeeCount = 0
    private var totalTips = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_distribution)

        val employeeCountTextView = findViewById<TextView>(R.id.employeeCountTextView)
        val increaseButton = findViewById<Button>(R.id.increaseButton)
        val decreaseButton = findViewById<Button>(R.id.decreaseButton)
        val tipPerEmployeeTextView = findViewById<TextView>(R.id.tipPerEmployeeTextView)

        increaseButton.setOnClickListener {
            employeeCount++
            employeeCountTextView.text = employeeCount.toString()
            calculateTipPerEmployee()
        }

        decreaseButton.setOnClickListener {
            if (employeeCount > 0) {
                employeeCount--
                employeeCountTextView.text = employeeCount.toString()
                calculateTipPerEmployee()
            }
        }

        // Aquí puedes obtener el total de propinas de tu base de datos
        // Por ahora, lo estableceremos en un valor fijo para propósitos de ejemplo
        totalTips = 555.0 // Ejemplo de propinas totales

        calculateTipPerEmployee()
    }

    private fun calculateTipPerEmployee() {
        val tipPerEmployeeTextView = findViewById<TextView>(R.id.tipPerEmployeeTextView)
        val tipPerEmployee = if (employeeCount > 0) totalTips / employeeCount else 0.0
        tipPerEmployeeTextView.text = "Cantidad por empleado: $$tipPerEmployee"
    }
}
