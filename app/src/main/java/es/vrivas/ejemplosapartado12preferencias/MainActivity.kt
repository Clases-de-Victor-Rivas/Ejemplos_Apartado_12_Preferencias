package es.vrivas.ejemplosapartado12preferencias

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mostrar_preferencias_guardadas.*


class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_iniciar_sesion.setOnClickListener(this)

        // getDefaultSharedPreferences está obsoleto
        PREFERENCES_FILENAME=getPackageName() + "_preferences"
    }

    override fun onPostResume() {
        super.onPostResume()
        recuperar_preferencias()
    }
    fun recuperar_preferencias() {
        Log.i( "VICTOR", PREFERENCES_FILENAME)
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
    fun guardar_preferencias() {
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
    override fun onClick(v: View?) {
        guardar_preferencias()
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
        recuperar_preferencias()
    }

    companion object {
        const val TOKEN_LOGIN = "token login"
        const val TOKEN_RECORDAR = "token recordar"
        const val TOKEN_PASSWORD = "token password"
        var PREFERENCES_FILENAME = ""
    }
}