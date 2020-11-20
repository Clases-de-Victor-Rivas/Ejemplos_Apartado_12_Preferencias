package es.vrivas.ejemplosapartado12preferencias

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    // Creación de la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Establecew evento click para el botón
        bt_iniciar_sesion.setOnClickListener(this)

        // getDefaultSharedPreferences está obsoleto. Usamos packageName como "alternativa"
        PREFERENCES_FILENAME=getPackageName() + "_preferences"
    }

    // Recuperamos las preferencias que haya almacenadas
    override fun onPostResume() {
        super.onPostResume()
        recuperar_preferencias_compartidas()
        recuperar_preferencias_activity()
    }

    // Métodos para preferencias compartidas
    fun recuperar_preferencias_compartidas() {
        val preferencias = getSharedPreferences(PREFERENCES_FILENAME, MODE_PRIVATE)
        var login: String?=""
        var password: String?=""
        val recordar = preferencias.getBoolean(TOKEN_RECORDAR, false)

        // La preferencia "RECORDAR" siempre se recupera
        sw_recordar.isChecked = recordar

        if(sw_recordar.isChecked ) {
            login=preferencias.getString(TOKEN_LOGIN, "")
            password = preferencias.getString(TOKEN_PASSWORD, "")
        }
        te_login.setText(login!!.toString())
        te_password.setText(password!!.toString())
    }
    fun guardar_preferencias_compartidas() {
        val preferencias = getSharedPreferences(PREFERENCES_FILENAME, MODE_PRIVATE)
        val editorPreferencias = preferencias.edit()
        // La preferencia "RECORDAR" siempre se almacena
        editorPreferencias.putBoolean(TOKEN_RECORDAR, sw_recordar.isChecked)

        if( sw_recordar.isChecked ) {
            editorPreferencias.putString(TOKEN_LOGIN, te_login.text.toString()) // Añadir o modificar
            editorPreferencias.putString(TOKEN_PASSWORD, te_password.text.toString()) // Añadir o modificar
        }
        editorPreferencias.apply()
    }


    // Métodos para preferencias_activity
    fun recuperar_preferencias_activity() {
        val preferencias = getPreferences(MODE_PRIVATE)
        val palabras_recuperadas=preferencias.getStringSet(TOKEN_PALABRAS, setOf<String?>()) // Añadir o modificar
        tv_palabras_main.setText( palabras_recuperadas.toString())
    }

    fun guardar_preferencias_activity() {
        val preferencias = getPreferences(MODE_PRIVATE)
        val editorPreferencias = preferencias.edit()
        editorPreferencias.putStringSet(TOKEN_PALABRAS, setOf<String?>(
                "Yo",
                "soy",
                "la",
                "actividad",
                "principal")
        ) // Añadir o modificar
        editorPreferencias.apply()
    }


    // Evento click del botón.
    override fun onClick(v: View?) {
        guardar_preferencias_compartidas()
        guardar_preferencias_activity()
        try {
            val intent= Intent(this, MostrarPreferenciasGuardadas::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(
                    this,
                    "No se pudo abrir actividad: ${e.localizedMessage}",
                    Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        recuperar_preferencias_compartidas()
    }

    companion object {
        // Tokens para preferencias compartidas
        const val TOKEN_LOGIN = "token login"
        const val TOKEN_RECORDAR = "token recordar"
        const val TOKEN_PASSWORD = "token password"
        var PREFERENCES_FILENAME = ""

        // Tokens para preferencias actividad
        const val TOKEN_PALABRAS = "token para listado de palabras"
    }
}