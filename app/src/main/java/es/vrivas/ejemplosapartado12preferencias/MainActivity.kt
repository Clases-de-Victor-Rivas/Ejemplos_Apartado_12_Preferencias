package es.vrivas.ejemplosapartado12preferencias

import android.content.Intent
import android.os.Bundle
import android.text.Editable
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
    }

    override fun onPostResume() {
        super.onPostResume()
        recuperar_preferencias()
    }
    fun recuperar_preferencias() {
        val preferencias = getSharedPreferences("nombre_fichero", MODE_PRIVATE)
        var login: String?=""
        var password: String?=""
        val recordar = preferencias.getBoolean("token_recordar", false)

        // La preferencia "RECORDAR" siempre se recupera
        sw_recordar.isChecked = recordar

        if(sw_recordar.isChecked ) {
            login=preferencias.getString("token_login", "")
            password = preferencias.getString("token_password", "")
        }
        te_login.setText(login!!.toString())
        te_password.setText(password!!.toString())
    }
    fun guardar_preferencias() {
        val preferencias = getSharedPreferences("nombre_fichero", MODE_PRIVATE)
        val editorPreferencias = preferencias.edit()
        // La preferencia "RECORDAR" siempre se almacena
        editorPreferencias.putBoolean("token_recordar", sw_recordar.isChecked)

        if( sw_recordar.isChecked ) {
            editorPreferencias.putString("token_login", te_login.text.toString()) // Añadir o modificar
            editorPreferencias.putString("token_password", te_password.text.toString()) // Añadir o modificar
        }
        editorPreferencias.commit()
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
}