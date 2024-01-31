package com.xenia.playwithnumbers.domain.repository

import com.xenia.playwithnumbers.domain.entity.GameSettings
import com.xenia.playwithnumbers.domain.entity.Level
import com.xenia.playwithnumbers.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings

}