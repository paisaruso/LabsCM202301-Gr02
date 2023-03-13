package exoesqueletor.co.edu.udea.compumovil.gr02_202301.lab1gr02

import android.app.DatePickerDialog
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

            if (seleccion.isEmpty()) {
                Toast.makeText(this, "Debe seleccionar un valor en el spinner", Toast.LENGTH_SHORT).show()
            } else if (nombre.isEmpty()) {
                Toast.makeText(this, "Debe ingresar un nombre en el campo", Toast.LENGTH_SHORT).show()
            }else if(apellido.isEmpty()){
                Toast.makeText(this, "Debe ingresar un apellido en el campo", Toast.LENGTH_SHORT).show()
            }else if(FechaNacimiento.isEmpty()){
                Toast.makeText(this, "Debe ingresar una fecha en el campo", Toast.LENGTH_SHORT).show()
            }else{
                //llamremos la tercera activity
                Toast.makeText(this, "4 campos validados", Toast.LENGTH_SHORT).show()
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

