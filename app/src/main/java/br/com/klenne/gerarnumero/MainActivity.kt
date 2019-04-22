package br.com.klenne.gerarnumero

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent


class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_gerar.setOnClickListener(this)
        configureSpinner()
    }


    override fun onClick(v: View?) {

        val id = v!!.id

        if (id == R.id.btn_gerar) {

            //chamando função para mostrar numeros gerados no txv
            setNumerosGerados()
        }
    }


    fun setNumerosGerados() {
        val numerosGerados = NumerosLoteria()
        txv_numeroGerado.text = numerosGerados.selecionarJogo(spn_tiposDeJogos.selectedItem.toString())
    }


    private fun configureSpinner() {
        //preenchendo spinner
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

        //configurando o Spinner
        spn_tiposDeJogos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {


            override fun onNothingSelected(parent: AdapterView<*>?) {
                //.
            }


            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //mudando a imagem e strings de acordo com jogo selecionado

                when (parent.getItemAtPosition(position).toString()) {

                    TipoDeJogo.MEGASENA.tipoJogo -> {

                        txv_titulo.text = getString(R.string.gerar_megasena)
                        imv_logo.setImageResource(R.drawable.megasena)
                        setColor(R.color.MegaSena)
                        setNumerosGerados()

                    }
                    TipoDeJogo.QUINA.tipoJogo -> {

                        txv_titulo.text = getString(R.string.gerar_quina)
                        imv_logo.setImageResource(R.drawable.quina)
                        setColor(R.color.Quina)
                        setNumerosGerados()

                    }
                    TipoDeJogo.LOTOFACIL.tipoJogo -> {

                        txv_titulo.text = getString(R.string.gerar_lotoFacil)
                        imv_logo.setImageResource(R.drawable.lotofacil)
                        setColor(R.color.Facil)
                        setNumerosGerados()

                    }
                    TipoDeJogo.DUPLASENA.tipoJogo -> {

                        txv_titulo.text = getString(R.string.gerar_duplasena)
                        imv_logo.setImageResource(R.drawable.duplasena)
                        setColor(R.color.DuplaSena)
                        setNumerosGerados()
                    }
                    TipoDeJogo.DIADESORTE.tipoJogo -> {

                        txv_titulo.text = getString(R.string.gerar_dia_de_sorte)
                        imv_logo.setImageResource(R.drawable.diadesorte)
                        setColor(R.color.DiaDeSorte)
                        setNumerosGerados()


                    }
                    TipoDeJogo.LOTOMANIA.tipoJogo -> {

                        txv_titulo.text = getString(R.string.gerar_lotomania)
                        imv_logo.setImageResource(R.drawable.lotomania)
                        setColor(R.color.Mania)
                        setNumerosGerados()


                    }

                }
            }


        }
    }

    //função para mudar a cor de spinner e de botão
    fun setColor(cor: Int) {
        btn_gerar.setBackgroundResource(cor)
        spn_tiposDeJogos.setPopupBackgroundResource(cor)

    }

    //para deixar em tela cheia
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        hideSystemUI()

    }

    //deixando em modo tela cheia
    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    //evento que quando arrasta para baixo deixa em tela cheia
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> hideSystemUI()
        }
        return super.onTouchEvent(event)
    }

}
