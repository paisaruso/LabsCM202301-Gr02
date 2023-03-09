package exoesqueletor.co.edu.udea.compumovil.gr02_202301.lab1gr02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

private lateinit var btnDataPersonVari: Button
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDataPersonVari = findViewById(R.id.btnDataPerson)

        btnDataPersonVari.setOnClickListener {
            val intent = Intent(this, ActividadPersonalDatos::class.java)
            startActivity(intent)
        }


    }
}