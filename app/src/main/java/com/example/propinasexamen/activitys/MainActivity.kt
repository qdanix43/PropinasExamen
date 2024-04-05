package com.example.propinasexamen.activitys



import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propinasexamen.R
import com.example.propinasexamen.adapters.TipAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var tipsRecyclerView: RecyclerView
    private lateinit var addTipButton: Button
    private lateinit var calculateTipButton: Button
    private lateinit var tipAdapter: TipAdapter

    private val tips = mutableListOf<Tip>()

    companion object {
        private const val PREFS_NAME = "TipPrefs"
        private const val KEY_TOTAL_TIPS = "totalTips"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tipsRecyclerView = findViewById(R.id.tipsRecyclerView)
        addTipButton = findViewById(R.id.addTipButton)
        calculateTipButton = findViewById(R.id.calculateTipButton)

        tipAdapter = TipAdapter(tips)
        tipsRecyclerView.adapter = tipAdapter
        tipsRecyclerView.layoutManager = LinearLayoutManager(this)

        addTipButton.setOnClickListener {
            showAddTipDialog()
        }

        calculateTipButton.setOnClickListener {
            val total = calculateTotal()
            saveTotalTips(total)
            navigateToResult(total)
        }

        // Cargar datos guardados al iniciar la actividad
        loadTotalTips()
    }

    private fun showAddTipDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_tip, null)
        val editText = dialogView.findViewById<EditText>(R.id.tipEditText)

        AlertDialog.Builder(this)
            .setTitle("Añadir Propina")
            .setView(dialogView)
            .setPositiveButton("Añadir") { dialog, _ ->
                val amountText = editText.text.toString()
                if (amountText.isNotEmpty()) {
                    try {
                        val amount = amountText.toDouble()
                        addTip(amount)
                    } catch (e: NumberFormatException) {
                        // Manejar la excepción si el texto no puede convertirse a Double
                    }
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun addTip(amount: Double) {
        val newTip = Tip(amount)
        tips.add(newTip)
        tipAdapter.notifyDataSetChanged()
    }

    private fun calculateTotal(): Double {
        var total = 0.0
        for (tip in tips) {
            total += tip.amount
        }
        return total
    }

    private fun saveTotalTips(total: Double) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putFloat(KEY_TOTAL_TIPS, total.toFloat())
        editor.apply()
    }

    private fun loadTotalTips() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val total = prefs.getFloat(KEY_TOTAL_TIPS, 0f).toDouble()
        // Hacer algo con el valor cargado, como mostrarlo en una vista
    }

    private fun navigateToResult(total: Double) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("total", total)
        startActivity(intent)
    }
}
