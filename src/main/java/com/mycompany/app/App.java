package com.mycompany.app;
import java.awt.*;
import javax.swing.*;
// import java.util.Scanner;

/**
 * Cowan's Game of Life
 * Take input on an infinite grid
 * In every cycle, make changes according to the rules of the game of life
 * display the new state on the infinite grid
 * Rules - https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#:~:text=The%20Game%20of%20Life%2C%20also,state%2C%20requiring%20no%20further%20input.
 */
public class App extends Frame
{
    JButton[][] display;
    final static int limit = 30;
    int[][] grid;

    App() {
        display = new JButton[limit][limit];
        grid = new int[limit][limit];
        initializeButtons(display);
        JButton start = startButton();  
        add(start); 
        setSize(1400,900);
        setLayout(new GridLayout(limit + 1, limit + 1)); 
        setVisible(true); 
    }

    public void initializeButtons(JButton[][] buttons) {
        for(int i = 0; i < limit; i++) {
            for(int j = 0; j < limit; j++) {
                int tempI = i;
                int tempJ = j;
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setBackground(Color.BLUE);
                buttons[i][j].addActionListener( e -> {
                    buttons[tempI][tempJ].setBackground( Color.GREEN);
                    grid[tempI][tempJ] = 1;
                    System.out.println("Clicked " + tempI + "," + tempJ);
                }
                );
                add(buttons[i][j]);
            }
        }
    }

    public JButton startButton() {
        JButton start = new JButton("->");
        start.addActionListener( e -> {
            iterateAndDisplay();
        }
        );
        return start;
    }

    public static void main( String[] args )
    {
        initialize();
    }

    public static void initialize() {
        App obj = new App();
    }

    // TODO: edge case
    public void iterateAndDisplay() {
        for(int i = 0; i < limit; i++) {
            for(int j = 0; j < limit; j++) {
                if(toLive(i, j)) {
                    grid[i][j] = 1;
                } else {
                    grid[i][j] = 0;
                }
            }
        }
        changeColors();
    }

    public boolean toLive(int row, int col) {
        int neighbourLifeCount = 0;
        if(alive(row - 1, col)) {
            neighbourLifeCount++;
        }
        if(alive(row - 1, col - 1)) {
            neighbourLifeCount++;
        }
        if(alive(row - 1, col + 1)) {
            neighbourLifeCount++;
        }
        if(alive(row, col - 1)) {
            neighbourLifeCount++;
        }
        if(alive(row, col + 1)) {
            neighbourLifeCount++;
        }
        if(alive(row + 1, col - 1)) {
            neighbourLifeCount++;
        }
        if(alive(row + 1, col)) {
            neighbourLifeCount++;
        }
        if(alive(row + 1, col + 1)) {
            neighbourLifeCount++;
        }
        if((neighbourLifeCount == 2 || neighbourLifeCount == 3) && grid[row][col] == 1) {
            return true;
        } else if(neighbourLifeCount == 3) {
            return true;
        } else {
            return false;
        }

    }

    public void changeColors() {

    }
}
