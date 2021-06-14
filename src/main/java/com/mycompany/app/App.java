package com.mycompany.app;
import java.awt.*;
import java.swing.*;
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
    Button[][] display;
    final static int limit = 100;

    App() {
        display = new Button[limit][limit];
        initializeButtons(display);
        // Button b=new Button("click me");  
        // b.setBounds(30,100,80,30);// setting button position  
        // add(b);//adding button into frame  
        setSize(300,300);//frame size 300 width and 300 height  
        setLayout(null);//no layout manager  
        setVisible(true);//now frame will be visible, by default not visible  
    }

    public void initializeButtons(Button[][] buttons) {
        for(int i = 0; i < limit; i++) {
            for(int j = 0; j < limit; j++) {
                buttons[i][j] = new Button(" ");
            }
        }
    }

    public static void main( String[] args )
    {
        initialize();
    }

    public static void initialize() {
        App obj = new App();
        int[][] grid = new int[limit][limit];
        initialState(grid);
        iterateAndDisplay(grid);
    }

    public static void initialState(int[][] grid) {

    }

    public static void iterateAndDisplay(int[][] grid) {

    }
}
