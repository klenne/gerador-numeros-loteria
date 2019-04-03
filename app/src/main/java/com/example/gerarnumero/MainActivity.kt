package com.example.gerarnumero

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        val id = v!!.id

        if (id == R.id.btn_gerar) {
            val numerosGerados = NumerosLoteria()
            txv_numeroGerado.text = numerosGerados.selecionarJogo(spn_tiposDeJogos.selectedItem.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_gerar.setOnClickListener(this)
        var tiposDeJogo = arrayListOf<String>(TipoDeJogo.MEGASENA.tipoJogo, TipoDeJogo.QUINA.tipoJogo)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tiposDeJogo)
        spn_tiposDeJogos.adapter = adapter
        spn_tiposDeJogos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //.
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                when (selectedItem) {
                    TipoDeJogo.MEGASENA.tipoJogo -> {
                        txv_titulo.text = "Gerar Números Mega Sena"
                        imv_logo.setImageResource(R.drawable.megasena)
                    }
                    TipoDeJogo.QUINA.tipoJogo -> {
                        txv_titulo.text = "Gerar Números Quina"
                        imv_logo.setImageResource(R.drawable.quina)
                    }

                }
            }

        }
    }
}
