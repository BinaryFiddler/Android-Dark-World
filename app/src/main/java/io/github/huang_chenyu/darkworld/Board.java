package io.github.huang_chenyu.darkworld;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * Created by chenyu on 7/29/17.
 */

public class Board {
    int[][] board;
    public int row;
    public int col;
    int x;
    int y;

    int avatar;

    private final int numOfTrophy = 1;
    private int numOfDragons;
    private int numOfSwords;
    private Random random;

    public Board(int row, int col){
        this.row = row;
        this.col = col;

        x = 0;
        y = 0;

        random = new Random();
        board = new int[row][col];

        avatar = DarkWorldConstants.AVATAR_ALIVE;

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                board[i][j] = DarkWorldConstants.EMPTY_CELL;
            }
        }
        setTrophyCells();
        setDragonCells();
        setSwordCells();
    }

    public Board(String string) {
        JSONObject puzzleParams = null;
        try {
            puzzleParams = new JSONObject(string);
            int size = puzzleParams.getInt("sizeOfPuzzle");
            numOfDragons = puzzleParams.getInt("numOfDragons");
            numOfSwords = puzzleParams.getInt("numOfSwords");

            this.row = size;
            this.col = size;

        } catch (JSONException e) {
            e.printStackTrace();
            numOfDragons = 1;
            numOfSwords = 1;
            this.row = 3;
            this.col = 3;
        }
        finally {
            x = 0;
            y = 0;

            random = new Random();
            board = new int[row][col];

            avatar = DarkWorldConstants.AVATAR_ALIVE;

            for (int i = 0; i < row; i++){
                for (int j = 0; j < col; j++){
                    board[i][j] = DarkWorldConstants.EMPTY_CELL;
                }
            }
            setTrophyCells();
            setDragonCells();
            setSwordCells();
        }
    }

    private void setTrophyCells() {
        int counter = 0;
        while(counter < numOfTrophy){
            int r = random.nextInt(row);
            int c = random.nextInt(col);
            if (board[r][c] != DarkWorldConstants.EMPTY_CELL || r == 0 && c == 0){
               continue;
            }else {
                board[r][c] = DarkWorldConstants.TROPHY_CELL;
                counter++;
            }
        }
        return;
    }

    private void setDragonCells() {
        int counter = 0;
        while(counter < numOfDragons){
            int r = random.nextInt(row);
            int c = random.nextInt(col);
            if (board[r][c] != DarkWorldConstants.EMPTY_CELL || r == 0 && c == 0){
                continue;
            }else {
                board[r][c] = DarkWorldConstants.DRAGON_CELL;
                counter++;
            }
        }
        return;
    }

    private void setSwordCells() {
        int counter = 0;
        while(counter < numOfSwords){
            int r = random.nextInt(row);
            int c = random.nextInt(col);
            if (board[r][c] != DarkWorldConstants.EMPTY_CELL || r == 0 && c == 0){
                continue;
            }else {
                board[r][c] = DarkWorldConstants.SWORD_CELL;
                counter++;
            }
        }
        return;
    }

    public int move(int movement){
        int h = 0;
        int v = 0;

        switch (movement){
            case MovementOptions.LEFT:
                v--;
                break;
            case MovementOptions.DOWN:
                h++;
                break;
            case MovementOptions.RIGHT:
                v++;
                break;
            case MovementOptions.UP:
                h--;
                break;
        }

        if (x + v < 0 || x + v >= row || y + h < 0 || y + h >= col){
            return DarkWorldConstants.INVALID_CELL;
        }
        x = x + v;
        y = y + h;

        switch (board[x][y]) {
            case DarkWorldConstants.EMPTY_CELL:
                board[x][y] = DarkWorldConstants.VISITED_CELL;
                break;
            case DarkWorldConstants.DRAGON_CELL:
                if (avatar == DarkWorldConstants.AVATAR_ALIVE_SWORD) {
                    board[x][y] = DarkWorldConstants.VISITED_CELL;
                    return DarkWorldConstants.AVATAR_SLAY_DRAGON;
                }else {
                    avatar = DarkWorldConstants.AVATAR_DEAD;
                }
                break;
            case DarkWorldConstants.SWORD_CELL:
                board[x][y] = DarkWorldConstants.VISITED_CELL;
                avatar = DarkWorldConstants.AVATAR_ALIVE_SWORD;
                break;
            case DarkWorldConstants.TROPHY_CELL:
                avatar = DarkWorldConstants.AVATAR_TRIUMPH;
                board[x][y] = DarkWorldConstants.VISITED_CELL;
                break;
            default:
                break;
        }
        return avatar;
    }
    
}
