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
        val preferencias = getSharedPreferences("nombre_fichero", MODE_PRIVATE)

        Log.i( "VICTOR", "onPostCreate")
        val login = preferencias.getString("token_login", "VALOR_POR_DEFECTO")
        val password = preferencias.getString("token_password", "VALOR_POR_DEFECTO")
        val recordar = preferencias.getBoolean("token_recordar", false)

        tv_login.text= "Login: "+login!!.toString()
        tv_password.text="Password: "+password!!.toString()
        tv_recordar.text="Recordar: "+(if( recordar ) "Sí" else "No")
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)


    }
}