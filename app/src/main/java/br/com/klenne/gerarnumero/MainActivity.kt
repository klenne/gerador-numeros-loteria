package br.com.klenne.gerarnumero

import android.app.AlertDialog
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
            if (spn_tiposDeJogos.selectedItem == TipoDeJogo.LOTOMANIA.tipoJogo) {

                val alerta = AlertDialog.Builder(this)

                val numeros = numerosGerados.formatarResultado(
                    numerosGerados.gerarNumeros(
                        QuantidadeDeNumerosApostados.LOTOMANIAAPOSTA.quantidadeApostados,
                        NumeroLimiteParaApostar.LOTOMANIALIMITE.numeroLimite
                    )
                )

                alerta.setMessage(numeros)
                //Adicionando titulo
                alerta.setTitle("Numeros Gerados")

                //Adicionando botão neutro
                alerta.setNeutralButton("Voltar", null)

                //Forçando usuario não cancelar alerta
                alerta.setCancelable(false)

                //Executa o alerta
                alerta.create().show()

            }
            txv_numeroGerado.text = numerosGerados.selecionarJogo(spn_tiposDeJogos.selectedItem.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_gerar.setOnClickListener(this)

        val tiposDeJogo = arrayListOf(
            TipoDeJogo.MEGASENA.tipoJogo,
            TipoDeJogo.QUINA.tipoJogo,
            TipoDeJogo.LOTOFACIL.tipoJogo,
            TipoDeJogo.DUPLASENA.tipoJogo,
            TipoDeJogo.DIADESORTE.tipoJogo,
            TipoDeJogo.LOTOMANIA.tipoJogo
        )

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

                        txv_titulo.text = getString(R.string.gerar_megasena)
                        imv_logo.setImageResource(R.drawable.megasena)
                        txv_numeroGerado.text = ""


                    }
                    TipoDeJogo.QUINA.tipoJogo -> {

                        txv_titulo.text = getString(R.string.gerar_quina)
                        imv_logo.setImageResource(R.drawable.quina)
                        txv_numeroGerado.text = ""


                    }
                    TipoDeJogo.LOTOFACIL.tipoJogo -> {

                        txv_titulo.text = getString(R.string.gerar_lotoFacil)
                        imv_logo.setImageResource(R.drawable.lotofacil)
                        txv_numeroGerado.text = ""


                    }
                    TipoDeJogo.DUPLASENA.tipoJogo -> {

                        txv_titulo.text = getString(R.string.gerar_duplasena)
                        imv_logo.setImageResource(R.drawable.duplasena)
                        txv_numeroGerado.text = ""


                    }
                    TipoDeJogo.DIADESORTE.tipoJogo -> {

                        txv_titulo.text = getString(R.string.gerar_dia_de_sorte)
                        imv_logo.setImageResource(R.drawable.diadesorte)
                        txv_numeroGerado.text = ""


                    }
                    TipoDeJogo.LOTOMANIA.tipoJogo -> {

                        txv_titulo.text = getString(R.string.gerar_lotomania)
                        imv_logo.setImageResource(R.drawable.lotomania)
                        txv_numeroGerado.text = ""


                    }

                }
            }

        }
    }
}
