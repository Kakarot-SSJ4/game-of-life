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
        add(start);//adding button into frame  
        setSize(1400,900);//frame size 300 width and 300 height  
        setLayout(new GridLayout(limit + 1, limit + 1));//no layout manager  
        setVisible(true);//now frame will be visible, by default not visible  
    }

    public void initializeButtons(JButton[][] buttons) {
        for(int i = 0; i < limit; i++) {
            for(int j = 0; j < limit; j++) {
                int tempI = i;
                int tempJ = j;
                buttons[i][j] = new JButton(" ");
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
            for(int i = 0; i < limit; i++) {
                for(int j = 0; j < limit; j++) {
                    if(grid[i][j] == 1) {
                        System.out.println("green at " + i + "," + j);
                    }
                }
            }
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
        initialState();
        iterateAndDisplay();
    }

    public static void initialState() {

    }

    public static void iterateAndDisplay() {

    }
}
