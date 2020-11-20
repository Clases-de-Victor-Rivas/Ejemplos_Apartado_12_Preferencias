package es.vrivas.ejemplosapartado12preferencias

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mostrar_preferencias_guardadas.*

class MostrarPreferenciasGuardadas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_preferencias_guardadas)
        val preferencias = getSharedPreferences(MainActivity.PREFERENCES_FILENAME, MODE_PRIVATE)

        val login = preferencias.getString(MainActivity.TOKEN_LOGIN, "VALOR_POR_DEFECTO")
        val password = preferencias.getString(MainActivity.TOKEN_PASSWORD, "VALOR_POR_DEFECTO")
        val recordar = preferencias.getBoolean(MainActivity.TOKEN_RECORDAR, false)

        tv_login.text= String.format( getString(R.string.login_mostrar),
                login!!.toString())
        tv_password.text=String.format( getString(R.string.password_mostrar),
                password!!.toString())
        tv_recordar.text=String.format( getString(R.string.recordar_mostrar),
                if( recordar ) "Sí" else "No")
    }

    override fun onResume() {
        super.onResume()
        recuperar_preferencias_activity()
    }

    override fun onPause() {
        guardar_preferencias_activity()
        super.onPause()
    }
    // Métodos para preferencias_activity
    fun recuperar_preferencias_activity() {
        val preferencias = getPreferences(MODE_PRIVATE)
        val palabras_recuperadas=preferencias.getStringSet(MainActivity.TOKEN_PALABRAS, setOf<String?>()) // Añadir o modificar
        tv_palabras_mostrar.setText( palabras_recuperadas.toString())
    }

    fun guardar_preferencias_activity() {
        val preferencias = getPreferences(MODE_PRIVATE)
        val editorPreferencias = preferencias.edit()
        editorPreferencias.putStringSet(MainActivity.TOKEN_PALABRAS,
                setOf<String?>(
                        "Soy",
                        "la",
                        "actividad",
                        "MostrarPreferenciasGuardadas")
        ) // Añadir o modificar
        editorPreferencias.apply()
    }

}