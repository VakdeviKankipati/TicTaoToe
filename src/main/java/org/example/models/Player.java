package org.example.models;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private static Scanner sc = new Scanner(System.in);

    public Player(String name,Symbol symbol,PlayerType playerType){
        this.name = name;
        this.symbol = symbol;
        this.playerType =playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public  Move makeMove(Board board){
        System.out.println("Please enter the row index where you want to make a move");
        int row = sc.nextInt();

        System.out.println("Please enter the col index where you want to make a move");
        int col = sc.nextInt();

        return  new Move(this, new Cell(row,col));

    }
}
