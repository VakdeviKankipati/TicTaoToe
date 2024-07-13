package org.example.controllers;

import org.example.exception.InvalidBotCountException;
import org.example.exception.InvalidPlayerCountException;
import org.example.models.Game;
import org.example.models.GameState;
import org.example.models.Player;
import org.example.strategies.winningstrategies.WinningStrategy;

import java.util.List;
import java.util.WeakHashMap;

public class GameController {

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) throws InvalidBotCountException, InvalidPlayerCountException {
        return  Game.getBuilder()
                .setDimension(dimension)
                .setPlayerList(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void makeMove(Game game){
        game.getMoves();
    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public void printBoard(Game game){
        game.printBoard();
    }

    public void getWinner(Game game) {
        game.getWinner();
    }
}
