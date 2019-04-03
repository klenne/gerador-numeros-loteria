package br.com.klenne.gerarnumero

/*
* Classe que gera os numeros aleatórios
*
* */

class NumerosLoteria {


    fun selecionarJogo(opcao: String): String {

        //Função que verifica qual jogo foi escolhido e direciona para os respectivos métodos

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

            TipoDeJogo.LOTOFACIL.tipoJogo -> return formatarResultado(
                gerarNumeros(
                    QuantidadeDeNumerosApostados.LOTOFACILAPOSTA.quantidadeApostados,
                    NumeroLimiteParaApostar.LOTOFACILLIMITE.numeroLimite
                )
            )
            else -> return "0"
        }


    }

    private fun gerarNumeros(
        quantidadeNumerosAGerar: Int,
        range: Int
    ): ArrayList<Int> {

        //Função que gera a quantidade de numeros aleatórios(dentro do range passado) que for pedida como parâmetro, e os retorna em um vetor

        val arrayNumeros = ArrayList<Int>()
        var numeroRecebido: Int

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

    //Função que concatena todos os numeros gerados em uma única String

    var resultadoFormatado = ""
    var controleTabulacao = 1

    for (i in 0 until array.size) {

        when {

            i < array.size - 1 -> {
                if (array.size > 6 && controleTabulacao >= 5) {
                    resultadoFormatado += formatarUnidades(array[i]) + "\n"
                    controleTabulacao = 0
                } else {
                    resultadoFormatado += formatarUnidades(array[i]) + "-"
                }
                controleTabulacao++

            }

            else -> resultadoFormatado += formatarUnidades(array[i])
        }


    }
    return resultadoFormatado
}

fun verificaNumerosIguais(numeroGerado: Int, array: ArrayList<Int>): Boolean {

    //Função para impedir que existam duplicatas dos números gerados aleatoriamente dentro do vetor

    for (i in 0 until array.size) {

        if (array[i] == numeroGerado) {

            return true
        }
    }

    return false
}

fun formatarUnidades(numero: Int): String {

    return if (numero < 10) {
        "0$numero"
    } else {
        "$numero"
    }
}