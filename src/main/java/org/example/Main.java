package org.example;

import org.example.controllers.GameController;
import org.example.exception.InvalidBotCountException;
import org.example.exception.InvalidPlayerCountException;
import org.example.models.*;
import org.example.strategies.winningstrategies.ColWinningStrategy;
import org.example.strategies.winningstrategies.DiagonalWinningStrategy;
import org.example.strategies.winningstrategies.RowWinningStrategy;
import org.example.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int dimension = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Player("mahesh", new Symbol('X'), PlayerType.HUMAN));
        players.add(new Player("vakya", new Symbol('O'), PlayerType.HUMAN));

        List<WinningStrategy> winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColWinningStrategy(),
                new DiagonalWinningStrategy()
        );

        try {
            GameController gameController = new GameController();
            Game game = gameController.startGame(
                    dimension,
                    players,
                    winningStrategies
            );

            while(game.getGameState().equals(GameState.IN_PROGRESS)){
                gameController.printBoard(game);
                gameController.makeMove(game);

            }

        }catch (Exception e){

        }



        //Game game = Game.getBuilder().setWinningStrategies(new ArrayList<>()).setPlayerList(null).setDimension(3).build();

    }
}