package exoesqueletor.co.edu.udea.compumovil.gr02_202301.lab1gr02

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import android.util.Log


class ContactDataActivity : AppCompatActivity() {

    private lateinit var countrySpinner: Spinner
    private lateinit var citySpinner: Spinner
    private lateinit var countryNames: ArrayList<String>
    private lateinit var cityNames: ArrayList<String>
    private val countriesUrl = "https://restcountries.com/v3.1/all"

    private lateinit var btnValidarDatos: Button
    private lateinit var tvTodoDatos: EditText

    data class CiudadPais(val ciudad: String, val pais: String)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)

        countrySpinner = findViewById(R.id.spn_paises)
        citySpinner = findViewById(R.id.spn_ciudades)
        //Validar el correo
        val correoEditText = findViewById<EditText>(R.id.edt_correo)

        // Cargar archivo de texto y crear lista de ciudades y países
        val listaCiudadesPaises = mutableListOf<CiudadPais>()
        val inputStream: InputStream = resources.openRawResource(R.raw.country_cities)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var linea = reader.readLine()
        while (linea != null) {
            val registro = linea.split(",")
            listaCiudadesPaises.add(CiudadPais(registro[0], registro[1]))
            linea = reader.readLine()
        }

        // Initialize country spinner with data from REST Countries API
        GlobalScope.launch(Dispatchers.IO) {
            // Make HTTP request to REST Countries API
            val response = URL(countriesUrl).readText()

            // Analizar respuesta JSON
            val jsonArray = JSONArray(response)
            countryNames = ArrayList<String>()

            // Agregar elemento vacío al comienzo de la lista
            countryNames.add("")

            // Add Colombia as second item
            countryNames.add("Colombia")

            for (i in 0 until jsonArray.length()) {
                val countryName =
                    jsonArray.getJSONObject(i).getJSONObject("name").getString("common")
                countryNames.add(countryName)
            }

            withContext(Dispatchers.Main) {
                // Actualizar la interfaz de usuario con los datos del país
                val adapter = ArrayAdapter(this@ContactDataActivity,android.R.layout.simple_spinner_item,
                    countryNames
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                countrySpinner.adapter = adapter

                // Set initial selection to Colombia
                countrySpinner.setSelection(1)
            }
        }
        // Configurar adaptador del primer spinner
        val paises = listaCiudadesPaises.map { it.pais }.distinct()
        val adaptadorPaises = ArrayAdapter(this, android.R.layout.simple_spinner_item, paises)
        countrySpinner.adapter = adaptadorPaises

        // Configurar adaptador del segundo spinner
        countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val paisSeleccionado = parent.getItemAtPosition(position) as String
                val ciudadesPorPais = listaCiudadesPaises.filter { it.pais == paisSeleccionado }
                    .map { it.ciudad }
                    .distinct()
                val adaptadorCiudades = ArrayAdapter(this@ContactDataActivity, android.R.layout.simple_spinner_item, ciudadesPorPais)
                citySpinner.adapter = adaptadorCiudades
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // No se hace nada
            }
        }

        btnValidarDatos = findViewById(R.id.btn_validarDatos)
        btnValidarDatos.setOnClickListener {
            // Obtener los datos pasados desde la activity_personal_data.xml
            val nombre = intent.getStringExtra("nombre")
            val apellidos = intent.getStringExtra("apellidos")
            val genero = intent.getStringExtra("genero")
            val fechaNacimiento = intent.getStringExtra("fechaNacimiento")
            val escolaridad = intent.getStringExtra("escolaridad")
            //datos de la activity actual
            val telefono = findViewById<TextView>(R.id.edt_telefono)
            val correo = findViewById<TextView>(R.id.edt_correo)
            val pais = findViewById<Spinner>(R.id.spn_paises)
            val ciudad = findViewById<Spinner>(R.id.spn_ciudades)
            val direccion = findViewById<TextView>(R.id.edt_direccion)


            // Asignar los datos al TextView
            val tv_todoDatos = findViewById<TextView>(R.id.tv_todoDatos)
            tv_todoDatos.text = "Nombre: $nombre\nApellidos: $apellidos\nGénero: $genero\n" +
                    "Fecha de nacimiento: $fechaNacimiento\nEscolaridad: $escolaridad\n"+
                    "Telefono: "+telefono.text.toString()+"\n"+"correo: "+correo.text.toString()+"\n"+
                    "País: "+pais.selectedItem.toString()+"\n"+"Ciudad: "+ciudad.selectedItem.toString()+"\n"+
                    "Dirección: "+direccion.text.toString()

        }
        val btn_imprimir = findViewById<Button>(R.id.btn_imprimir)
        val tv_todoDatos = findViewById<TextView>(R.id.tv_todoDatos)

        btn_imprimir.setOnClickListener {
            val texto = tv_todoDatos.text.toString()
            Log.d("TAG", "Contenido del TextView: $texto")
        }
    }
}





