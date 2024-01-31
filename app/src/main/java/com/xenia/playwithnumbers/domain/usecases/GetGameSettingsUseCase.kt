package com.xenia.playwithnumbers.domain.usecases

import com.xenia.playwithnumbers.domain.entity.GameSettings
import com.xenia.playwithnumbers.domain.entity.Level
import com.xenia.playwithnumbers.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level) : GameSettings {
        return repository.getGameSettings(level)
    }
}