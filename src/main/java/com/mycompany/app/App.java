package com.mycompany.app;
// import java.util.Scanner;

/**
 * Cowan's Game of Life
 * Take input on an infinite grid
 * In every cycle, make changes according to the rules of the game of life
 * display the new state on the infinite grid
 * Rules - https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#:~:text=The%20Game%20of%20Life%2C%20also,state%2C%20requiring%20no%20further%20input.
 */
public class App 
{
    final static int limit = Integer.MAX_VALUE;
    public static void main( String[] args )
    {
        initialize();
    }

    public static void initialize() {
        int[][] grid = new int[limit][limit];
        initialState(grid);
        iterateAndDisplay(grid);
    }

    public static void initialState(int[][] grid) {

    }

    public static void iterateAndDisplay(int[][] grid) {

    }
}
