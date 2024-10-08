package com.exemple.crossswords

import androidx.annotation.ColorRes

data class Samurai(
    val samuraiName: String,
    var victoryRecord: Int, // Mantenha como Int para o registro de vitórias
    var status: Boolean,
    @ColorRes var hakama: Int = R.color.verde_escuro, // Definindo um valor padrão
    @ColorRes var kendogi: Int = R.color.azul_celeste // Definindo um valor padrão
)

