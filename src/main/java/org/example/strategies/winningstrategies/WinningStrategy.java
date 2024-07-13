package org.example.strategies.winningstrategies;

import org.example.models.Board;
import org.example.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board,Move move);
}
