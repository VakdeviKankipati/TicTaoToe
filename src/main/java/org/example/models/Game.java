package org.example.models;

import org.example.exception.InvalidBotCountException;
import org.example.exception.InvalidMoveException;
import org.example.exception.InvalidPlayerCountException;
import org.example.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Game {
    private List<Player> playersList;
    private List<Move> moves;
    private Board board;
    private Player winner;
    private GameState gameState;
    private int nextPlayerMoveIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<Player> playersList, List<WinningStrategy> winningStrategies){
        this.playersList=playersList;
        this.moves = new ArrayList<>();
        this.board = new Board(dimension);
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerMoveIndex = 0;
        this.winningStrategies=winningStrategies;
    }

    public void printBoard() {
        board.print();
    }

    private boolean validateMove(Move move) {
        Player player = move.getPlayer();
        Cell cell = move.getCell();
        int row = cell.getRow();
        int col = cell.getCol();

        if (row < 0 || row >= board.getDimension() || col < 0
                || col >= board.getDimension() ||
                !board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
            return false;
        }

        return true;
    }

    public void makeMove() throws InvalidMoveException{
        Player currentPlayer = playersList.get(nextPlayerMoveIndex);

        System.out.println("it is "+currentPlayer.getName()+"'s move");

        Move move = currentPlayer.makeMove(board);

        if(!validateMove(move)){
            throw new InvalidMoveException("Invalid move");
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        Move finalMove = new Move(currentPlayer,cell);
        moves.add(finalMove);

        nextPlayerMoveIndex = (nextPlayerMoveIndex + 1) % playersList.size();

        if (checkWinner(finalMove)) {
            winner = currentPlayer;
            gameState = GameState.ENDED;
        } else if (moves.size() == board.getDimension() * board.getDimension()) {
            //Game has DRAWN.
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Move move) {
        for (WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }

    public static Builder getBuilder(){
        return new Builder();
    }


    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public static class Builder{
        private List<Player> playerList;
        private List<WinningStrategy> winningStrategies;
        private int dimension;


        public Builder setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public void validateBotCount() throws InvalidBotCountException{
            int botCount = 0;
            for(Player player : playerList){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount +=1;
                }
            }
            if(botCount>0){
                throw new InvalidBotCountException("only 1 bot should be present");
            }
        }

        public void validatePlayerCount() throws InvalidPlayerCountException {
            if(playerList.size() != dimension-1){
                throw new InvalidPlayerCountException("Number of players should be 1 less than the dimension");
            }
        }

        public void validateUniquePlayerSymbols() {

        }

        public void validate() throws InvalidPlayerCountException,InvalidBotCountException{
            validateBotCount();
            validatePlayerCount();
            validateUniquePlayerSymbols();
        }

        public Game build() throws InvalidBotCountException,InvalidPlayerCountException{
            validate();
            return new Game(dimension,playerList,winningStrategies);
        }
    }
}
