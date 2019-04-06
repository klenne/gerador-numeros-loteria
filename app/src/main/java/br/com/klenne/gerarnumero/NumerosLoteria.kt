package br.com.klenne.gerarnumero



/*
* Classe que gera os numeros aleatórios
*
* */

class NumerosLoteria {


    fun selecionarJogo(opcao: String): String {

        //Função que verifica qual jogo foi escolhido e direciona para os respectivos métodos

        return when (opcao) {

            TipoDeJogo.MEGASENA.tipoJogo -> formatarResultado(
                gerarNumeros(
                    QuantidadeDeNumerosApostados.MEGASENAAPOSTA.quantidadeApostados,
                    NumeroLimiteParaApostar.MEGASENALIMITE.numeroLimite
                )
            )

            TipoDeJogo.QUINA.tipoJogo -> formatarResultado(
                gerarNumeros(
                    QuantidadeDeNumerosApostados.QUINAAPOSTA.quantidadeApostados,
                    NumeroLimiteParaApostar.QUINALIMITE.numeroLimite
                )
            )

            TipoDeJogo.LOTOFACIL.tipoJogo -> formatarResultado(
                gerarNumeros(
                    QuantidadeDeNumerosApostados.LOTOFACILAPOSTA.quantidadeApostados,
                    NumeroLimiteParaApostar.LOTOFACILLIMITE.numeroLimite
                )
            )

            TipoDeJogo.DUPLASENA.tipoJogo -> formatarResultado(
                gerarNumeros(
                    QuantidadeDeNumerosApostados.DUPLASENAAPOSTA.quantidadeApostados,
                    NumeroLimiteParaApostar.DUPLASENALIMITE.numeroLimite
                )
            )

            TipoDeJogo.DIADESORTE.tipoJogo -> formatarResultado(
                gerarNumeros(
                    QuantidadeDeNumerosApostados.DIADESORTEAPOSTA.quantidadeApostados,
                    NumeroLimiteParaApostar.DIADESORTELIMITE.numeroLimite
                )
            ) + "\n" + gerarMes()


            else -> ""
        }


    }

    fun gerarNumeros(
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


    fun formatarResultado(arrayList: ArrayList<Int>): String {

        //Função que concatena todos os numeros gerados em uma única String

        var resultadoFormatado = ""
        var controleTabulacao = 1
        val sort = QuickSort()
        val array = sort.ordenar(arrayList.toIntArray())

        for (i in 0 until array.size) {

            when {

                i < array.size - 1 -> {

                    if (array.size > 7 && controleTabulacao >= 5) {

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

    private fun verificaNumerosIguais(numeroGerado: Int, array: ArrayList<Int>): Boolean {

        //Função para impedir que existam duplicatas dos números gerados aleatoriamente dentro do vetor

        for (i in 0 until array.size) {

            if (array[i] == numeroGerado) {

                return true
            }
        }

        return false
    }

    private fun formatarUnidades(numero: Int): String {

        return when {
            numero < 10 -> "0$numero"
            numero == 100 ->"00"
            else -> "$numero"
        }
    }

    private fun gerarMes(): String {
        val mes = (1..12).random()

        return when (mes) {
            1 -> "Janeiro"
            2 -> "Fevereiro"
            3 -> "Março"
            4 -> "Abril"
            5 -> "Maio"
            6 -> "Junho"
            7 -> "Julho"
            8 -> "Agosto"
            9 -> "Setembro"
            10 -> "Outubro"
            11 -> "Novembro"
            12 -> "Dezembro"
            else -> ""
        }

    }


}