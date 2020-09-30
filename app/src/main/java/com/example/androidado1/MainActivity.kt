package com.example.androidado1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sh = getSharedPreferences("produto", Context.MODE_PRIVATE)

        btLimpar.setOnClickListener { v: View? ->
            txtProduto.text.clear()
            txtPrecoCusto.text.clear()
            txtPrecoVenda.text.clear()
            txtResultado.text.clear()
        }


        btSalvar.setOnClickListener { v: View? ->
            if (txtProduto.text.isNotEmpty()) {
                val valor = txtPrecoCusto.text.toString() + ";" + txtPrecoVenda.text.toString()
                sh.edit().putString(txtProduto.text.toString(), valor).apply()
                Toast.makeText(this, "Anotação Salva", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "nome inexistente", Toast.LENGTH_LONG).show()
            }

        }

        btresultado.setOnClickListener { v: View? ->
            if (txtProduto.text.isNotEmpty()) {

                var texto = sh.getString(txtProduto.text.toString(), "")
                if (texto.isNullOrEmpty()) {
                    Toast.makeText(this, "Anotação Vazia ou Inexistente", Toast.LENGTH_SHORT).show()
                } else {
                    val splitt = texto.split(";")
                    txtPrecoCusto.setText(splitt[0])
                    txtPrecoVenda.setText(splitt[1])
                    val custo = txtPrecoCusto.text.toString();
                    val venda = txtPrecoVenda.text.toString();
                    val calculo = venda.toFloat() - custo.toFloat()

                    if (calculo > 0) {
                        txtResultado.setText("Lucro: " + calculo.toString())
                    }else if (calculo < 0){
                        txtResultado.setText("Prejuízo: " + calculo.toString())
                    } else {
                        txtResultado.setText(calculo.toString())
                    }

                    Toast.makeText(this, "Anotação Carregada com Sucesso", Toast.LENGTH_SHORT)
                        .show()
                }

            } else {
                Toast.makeText(this, "Nome da anotação inexistente", Toast.LENGTH_SHORT).show()
            }

        }

    }
}