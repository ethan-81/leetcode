package com.leetcode.matrix;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/valid-sudoku/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class ValidSudokuTest {

    @Test
    void test1() {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        //boolean result = this.isValidSudoku(board);
        boolean result = this.solution(board);

        assertTrue(result);
    }

    @Test
    void test2() {
        char[][] board = {
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        //boolean result = this.isValidSudoku(board);
        boolean result = this.solution(board);

        assertFalse(result);
    }

    private boolean isValidSudoku(char[][] board) {
        if (!this.isValidX(board)) {
            return false;
        }

        if (!this.isValidY(board)) {
            return false;
        }

        if (!this.isValidSubBoard(board)) {
            return false;
        }

        return true;
    }

    private boolean isValidX(char[][] board) {
        for(int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char base = board[row][col];
                if (base == '.') {
                    continue;
                }

                for(int idx = col + 1; idx < 9; idx++) {
                    if (base == '.') {
                        continue;
                    }

                    if (base == board[row][idx]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean isValidY(char[][] board) {
        for(int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                char base = board[row][col];
                if (base == '.') {
                    continue;
                }

                for(int idx = row + 1; idx < 9; idx++) {
                    if (base == '.') {
                        continue;
                    }

                    if (base == board[idx][col]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean isValidSubBoard(char[][] board) {
        for(int row = 0; row < 9; row = row + 3) {
            for (int col = 0; col < 9; col = col + 3) {
                char[] subBoard = new char[9];
                int x = col;
                int y = row;
                for (int i = 0; i < 9; i++) {
                    subBoard[i] = board[y][x];
                    x++;
                    if (x % 3 == 0) {
                        x = col;
                        y++;
                    }
                }

                for(int i = 0; i < 9; i++) {
                    char base = subBoard[i];
                    if (base == '.') {
                        continue;
                    }

                    for(int idx = i + 1; idx < 9; idx++) {
                        if (base == '.') {
                            continue;
                        }

                        if (base == subBoard[idx]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean solution(char[][] board) {
        if (!this.isValidRows(board)) {
            return false;
        }

        if (!this.isValidColumns(board)) {
            return false;
        }

        if (!this.isValidSubBoards(board)) {
            return false;
        }

        return true;
    }

    private boolean isValidRows(char[][] board) {
        for(int row = 0; row < 9; row++) {
            if(!this.isValidSet(board[row])) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidColumns(char[][] board) {
        for (int col = 0; col < 9; col++) {
            char[] set = new char[9];
            for (int row = 0; row < 9; row++) {
                set[row] = board[row][col];
            }

            if (!this.isValidSet(set)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidSubBoards(char[][] board) {
        for(int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                char[] subBoard = new char[9];
                int x = col;
                int y = row;
                for (int i = 0; i < 9; i++) {
                    subBoard[i] = board[y][x];
                    x++;
                    if (x % 3 == 0) {
                        x = col;
                        y++;
                    }
                }

                if (!this.isValidSet(subBoard)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValidSet(char[] set) {
        HashSet<Character> seens = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            char base = set[i];
            if (base != '.') {
                if(seens.contains(base)) {
                    return false;
                }
                seens.add(base);
            }
        }
        return true;
    }
}
