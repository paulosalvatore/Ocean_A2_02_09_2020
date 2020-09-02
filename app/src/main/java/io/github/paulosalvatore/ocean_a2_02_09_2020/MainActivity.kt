package io.github.paulosalvatore.ocean_a2_02_09_2020

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Recuperando dados salvos anteriormente, pelo savedInstanceState, que é um Bundle
        savedInstanceState?.let {
            val nome = it.getString("NOME")
            val idade = it.getString("IDADE")

            tvResultado.text = String.format(
                getString(R.string.resultado_nome_idade),
                nome,
                idade
            )
        }

        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // Resolução do desafio

        btAtualizar.setOnClickListener {
            val nome = etNome.text
            val idade = etIdade.text

            val nomeVazio = nome.isBlank()
            val idadeVaziaOuNula = idade?.isBlank() ?: true

            // Checagem de blank (com isBlank()
            // Validação se não é nulo (Com operador '?')
            // Elvis operator (?:), que é ativado caso a operação anterior seja nula

            if (nomeVazio || idadeVaziaOuNula) {
                // Algum dos elementos não passou na validação, pois está vazio ou nulo
                if (nomeVazio) {
                    etNome.error = getString(R.string.erro_nome)
                }

                if (idadeVaziaOuNula) {
                    etIdade.error = getString(R.string.erro_idade)
                }
            } else {
                // Todos os elementos passaram na validação
                tvResultado.text = String.format(
                    getString(R.string.resultado_nome_idade),
                    etNome.text,
                    etIdade.text
                )
//                tvResultado.text = "Olá ${etNome.text}, você tem ${etIdade.text} anos."
            }
        }

        btLimpar.setOnClickListener {
            tvResultado.text = getString(R.string.resultado)
            etNome.text.clear()
            etIdade.text?.clear()
        }

        btAbrirPerfil.setOnClickListener {
            val abrirPerfilIntent = Intent(this, PerfilActivity::class.java)
            abrirPerfilIntent.putExtra("NOME", etNome.text.toString())
            abrirPerfilIntent.putExtra("IDADE", etIdade.text.toString())
            startActivity(abrirPerfilIntent)

            // Exemplo de Intent para enviar SMS
//            val telefone = "12346556"
//            startActivity(Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", telefone, null)))

            // Exemplo para abrir página da web
//            val url = "https://oceanbrasil.com"
//            val i = Intent(Intent.ACTION_VIEW)
//            i.data = Uri.parse(url)
//            startActivity(i)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("NOME", etNome.text.toString())
        outState.putString("IDADE", etIdade.text.toString())

        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
