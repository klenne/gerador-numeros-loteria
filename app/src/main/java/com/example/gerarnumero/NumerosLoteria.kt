package com.example.gerarnumero

class NumerosLoteria {


    fun selecionarJogo(opcao: String): String {//função que verifica qual jogo foi escolhido e direciona para os respectivos métodos
        when (opcao) {
            TipoDeJogo.MEGASENA.tipoJogo -> return formatarResultado(
                gerarNumeros(
                    QuantidadeDeNumerosApostados.MEGASENAAPOSTA.quantidadeApostados,
                    NumeroLimiteParaApostar.MEGASENALIMITE.numeroLimite
                )
            )
            TipoDeJogo.QUINA.tipoJogo -> return formatarResultado(
                gerarNumeros(
                    QuantidadeDeNumerosApostados.QUINAAPOSTA.quantidadeApostados,
                    NumeroLimiteParaApostar.QUINALIMITE.numeroLimite
                )
            )
            else -> return "0"
        }


    }

    fun gerarNumeros(
        quantidadeNumerosAGerar: Int,
        range: Int
    ): ArrayList<Int> {//função que gera a quantidade de numeros aleatórios(dentro do range passado) que for pedida como parâmetro, e os retorna em um vetor
        var arrayNumeros = ArrayList<Int>()
        var numeroRecebido = 0;
        for (i in 0 until quantidadeNumerosAGerar) {

            numeroRecebido = (1..range).random()

            while (verificaNumerosIguais(numeroRecebido, arrayNumeros)) {
                numeroRecebido = (1..range).random()
            }

            arrayNumeros.add(numeroRecebido)
        }

        return arrayNumeros
    }


}

fun formatarResultado(array: ArrayList<Int>): String {
    var resultadoFormatado = ""
    for (i in 0 until array.size) {
        when {
            i < array.size - 1 -> resultadoFormatado += array[i].toString() + "-"
            else -> resultadoFormatado += array[i].toString()
        }

    }
    return resultadoFormatado
}

fun verificaNumerosIguais(numeroGerado: Int, array: ArrayList<Int>): Boolean {
    for (i in 0 until array.size) {
        if (array[i] == numeroGerado) {
            return true
        }
    }

    return false
}
