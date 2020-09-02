package io.github.paulosalvatore.ocean_a2_02_09_2020

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_perfil.*

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val nome = intent.getStringExtra("NOME")
        val idade = intent.getStringExtra("IDADE")

        textView.text = String.format(
            getString(R.string.resultado_nome_idade),
            nome,
            idade
        )
    }
}
