package exoesqueletor.co.edu.udea.compumovil.gr02_202301.lab1gr02

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.util.*


class ActividadPersonalDatos : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var btnCambiar: Button
    private lateinit var tvFechaNacimiento: TextView
    private val calendar: Calendar = Calendar.getInstance()
    private lateinit var spnEscolaridad: Spinner
    private lateinit var btnSiguiente: Button
    private lateinit var edtNombres: EditText
    private lateinit var edtApellidos: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_personal_data)

        btnCambiar = findViewById(R.id.btn_cambiar)
        tvFechaNacimiento = findViewById(R.id.tv_fechaNacimiento)
        spnEscolaridad = findViewById(R.id.spn_escolaridad)
        btnSiguiente = findViewById(R.id.btn_siguiente)
        edtNombres = findViewById<EditText>(R.id.edt_nombres)
        edtApellidos = findViewById<EditText>(R.id.edt_apellidos)


        btnCambiar.setOnClickListener {
            showDatePickerDialog()
        }
        // Definir la función de clic
        btnSiguiente.setOnClickListener {
            validarSpinner(spnEscolaridad, edtNombres, edtApellidos, tvFechaNacimiento)
        }
        }
        private fun validarSpinner(spinner: Spinner, adtNombres: EditText, adtApellidos:EditText, tvFechaNacimiento: TextView) {
            val seleccion = spinner.selectedItem.toString()
            val nombre = edtNombres.text.toString().trim()
            val apellido = edtApellidos.text.toString().trim()
            val FechaNacimiento = tvFechaNacimiento.text.toString().trim()
            val msmNombre= getString(R.string.toas_nombre)
            val msmApellido = getString(R.string.toas_apellido)
            val msmFechaNacimiento = getString(R.string.toas_fechaNacimiento)
            val msmEscolaridad = getString(R.string.toas_escolaridad)
            val msmSiguiente = getString(R.string.toas_siguiente)



            if (seleccion.toString()=="Seleccione") {
                Toast.makeText(this, msmEscolaridad, Toast.LENGTH_SHORT).show()
            } else if (nombre.isEmpty()) {
                Toast.makeText(this, msmNombre, Toast.LENGTH_SHORT).show()
            }else if(apellido.isEmpty()){
                Toast.makeText(this, msmApellido, Toast.LENGTH_SHORT).show()
            }else if(FechaNacimiento.isEmpty()){
                Toast.makeText(this, msmFechaNacimiento, Toast.LENGTH_SHORT).show()
            }else{
                //llamremos la tercera activity
<<<<<<< HEAD
                Toast.makeText(this, msmSiguiente, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ContactDataActivity ::class.java)
                startActivity(intent)

=======
                Toast.makeText(this, "4 campos validados", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ContactDataActivity ::class.java)
                startActivity(intent)
>>>>>>> 606a5c262b1ba73752667e0d772a22d3faf87289
            }
        }

    private fun showDatePickerDialog() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this, this, year, month, day)
        datePickerDialog.show()
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.time)
        tvFechaNacimiento.text = " $currentDateString"
    }

}

