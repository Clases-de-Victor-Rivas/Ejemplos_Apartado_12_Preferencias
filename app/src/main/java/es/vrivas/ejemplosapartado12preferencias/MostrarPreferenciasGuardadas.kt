package es.vrivas.ejemplosapartado12preferencias

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mostrar_preferencias_guardadas.*

class MostrarPreferenciasGuardadas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_preferencias_guardadas)
        val preferencias = getSharedPreferences(MainActivity.PREFERENCES_FILENAME, MODE_PRIVATE)

        val login = preferencias.getString(MainActivity.TOKEN_LOGIN, "VALOR_POR_DEFECTO")
        val password = preferencias.getString(MainActivity.TOKEN_PASSWORD, "VALOR_POR_DEFECTO")
        val recordar = preferencias.getBoolean(MainActivity.TOKEN_RECORDAR, false)

        tv_login.text= "Login: "+login!!.toString()
        tv_password.text="Password: "+password!!.toString()
        tv_recordar.text="Recordar: "+(if( recordar ) "Sí" else "No")
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)


    }
}