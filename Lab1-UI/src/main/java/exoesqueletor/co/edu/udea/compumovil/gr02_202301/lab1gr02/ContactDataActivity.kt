package exoesqueletor.co.edu.udea.compumovil.gr02_202301.lab1gr02

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class ContactDataActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)

        spinner = findViewById(R.id.spn_paises)

        GlobalScope.launch(Dispatchers.IO) {
            // Make HTTP request
            val response = URL("https://restcountries.com/v3.1/all").readText()

            // Parse JSON response
            val jsonArray = JSONArray(response)
            val countryNames = ArrayList<String>()

            // Add empty item at the start of the list
            countryNames.add("")

            // Add Colombia as second item
            countryNames.add("Colombia")

            for (i in 0 until jsonArray.length()) {
                val countryName = jsonArray.getJSONObject(i).getJSONObject("name").getString("common")
                countryNames.add(countryName)
            }

            withContext(Dispatchers.Main) {
                // Update UI
                val adapter = ArrayAdapter(this@ContactDataActivity, android.R.layout.simple_spinner_item, countryNames)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter

                // Set initial selection to Colombia
                spinner.setSelection(1)
            }
        }
    }
}


