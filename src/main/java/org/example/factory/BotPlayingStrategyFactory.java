package org.example.factory;

import org.example.models.BotDifficultyLevel;
import org.example.strategies.botplayingstrategies.BotPlayingStrategy;
import org.example.strategies.botplayingstrategies.EasyBotPlayingStrategy;
import org.example.strategies.botplayingstrategies.HardBotPlayingStrategy;
import org.example.strategies.botplayingstrategies.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel difficultyLevel) {
        if (difficultyLevel.equals(BotDifficultyLevel.EASY)) {
            return new EasyBotPlayingStrategy();
        } else if (difficultyLevel.equals(BotDifficultyLevel.MEDIUM)) {
            return new MediumBotPlayingStrategy();
        } else if (difficultyLevel.equals(BotDifficultyLevel.HARD)) {
            return new HardBotPlayingStrategy();
        }

        return null;
    }
}
