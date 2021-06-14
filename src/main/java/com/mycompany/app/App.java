package com.mycompany.app;
import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Cowan's Game of Life
 * Take input of the initial state on an infinite grid
 * In every cycle, make changes according to the rules of the game of life
 * display the new state on the infinite grid
 * Rules - https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#:~:text=The%20Game%20of%20Life%2C%20also,state%2C%20requiring%20no%20further%20input.
 * Improvements: 
 *  - if initial state is on the edge, shift before iterating (DONE)
 *  - in population growing in different directions, data will be lost
 *  - cannot know about grids other than the one in display
 */
public class App extends Frame
{
    JButton[][] display;
    final static int limit = 20;
    int[][] grid;
    int offsetMagnitude = 7;

    final Color aliveColor = Color.GREEN;
    final Color deadColor = Color.GRAY;

    /**
     * Default constructor for the class
     * creates the components of the frame
     */
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

    /**
     * initialize the gridview of buttons
     * assign an action on click of the button
     * @param buttons
     */
    public void initializeButtons(JButton[][] buttons) {
        for(int i = 0; i < limit; i++) {
            for(int j = 0; j < limit; j++) {
                int tempI = i;
                int tempJ = j;
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setBackground(deadColor);
                buttons[i][j].addActionListener( e -> {
                    buttons[tempI][tempJ].setBackground( aliveColor);
                    grid[tempI][tempJ] = 1;
                    System.out.println("Clicked " + tempI + "," + tempJ);
                }
                );
                add(buttons[i][j]);
            }
        }
    }

    /**
     * button which starts the game of life
     * @return JButton
     */
    public JButton startButton() {
        JButton start = new JButton("->");
        start.addActionListener( e -> {
            startGameOfLife();
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

    /**
     * apply the rules of game of life at an interval of 1 second
     * call function iterateAndDisplay to handle the rules
     */
    public void startGameOfLife() {
        System.out.println("STARTED");
        checkInitialOverflow();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                iterateAndDisplay();
            }
        }, 0, 1000);
    }

    /**
     * check if any alive grid is on the edge
     * shift by offset if there is
     */
    public void checkInitialOverflow() {
        boolean rowOverflow = false;
        boolean columnOverflow = false;
        boolean rowUnderflow = false;
        boolean columnUnderflow = false;
        for(int i = 0; i < limit; i++) {
            for(int j = 0; j < limit; j++) {
                if(alive(i, j)) {
                    if(i == limit - 1) {
                        rowOverflow = true;
                    }
                    if(j == limit - 1) {
                        columnOverflow = true;
                    }
                    if(i == 0) {
                        rowUnderflow = true;
                    }
                    if(j == 0) {
                        columnUnderflow = true;
                    }
                }
            }
        }
        if(rowOverflow) {
            shiftRowByOffset(grid, offsetMagnitude);
        } else if (rowUnderflow) {
            shiftRowByOffset(grid, -1 * offsetMagnitude);
        }

        if(columnOverflow) {
            shiftColByOffset(grid, offsetMagnitude);
        } else if(columnUnderflow) {
            shiftColByOffset(grid, -1 * offsetMagnitude);
        }
    }

    /**
     * iterates through the grid and applies the rules
     * change the color according to the new grids formed
     */
    public void iterateAndDisplay() {
        boolean rowOverflow = false;
        boolean columnOverflow = false;
        boolean rowUnderflow = false;
        boolean columnUnderflow = false;
        int[][] tempGrid = new int[limit][limit];
        for(int i = 0; i < limit; i++) {
            for(int j = 0; j < limit; j++) {
                if(toLive(i, j)) {
                    if(i == limit - 1) {
                        rowOverflow = true;
                    }
                    if(j == limit - 1) {
                        columnOverflow = true;
                    }
                    if(i == 0) {
                        rowUnderflow = true;
                    }
                    if(j == 0) {
                        columnUnderflow = true;
                    }
                    //System.out.println("WILL LIVE " + i + "," + j);
                    tempGrid[i][j] = 1;
                } else {
                    tempGrid[i][j] = 0;
                }
            }
        }

        if(rowOverflow) {
            shiftRowByOffset(tempGrid, offsetMagnitude);
        } else if (rowUnderflow) {
            shiftRowByOffset(tempGrid, -1 * offsetMagnitude);
        }

        if(columnOverflow) {
            shiftColByOffset(tempGrid, offsetMagnitude);
        } else if(columnUnderflow) {
            shiftColByOffset(tempGrid, -1 * offsetMagnitude);
        }

        for(int i = 0; i < limit; i++) {
            for(int j = 0; j < limit; j++) {
                grid[i][j] = tempGrid[i][j];
            }
        }
    
        changeColors();
    }

    /**
     * shift arr vertically by offset units
     * arr[i][j] = arr[i + offset][j];
     * @param arr
     * @param offset
     */
    public void shiftRowByOffset(int[][] arr, int offset) {
        System.out.println("OVERFLOW ROW");
        int[][] tempArr = new int[limit][limit];
        for(int i = 0; i < limit; i++){
            for (int j = 0; j < limit; j++) {
                try {
                    tempArr[i][j] = arr[i + offset][j];
                } catch (Exception e) {
                    tempArr[i][j] = 0;
                }
            }
        }

        for(int i = 0; i < limit; i++){
            for (int j = 0; j < limit; j++) {
                if(tempArr[i][j] == 1) {
        System.out.println("GREEN AT" + i + "," + j);

                }
                arr[i][j] = tempArr[i][j];
            }
        }
    }

    /**
     * shift arr horizontally by offset units
     * arr[i][j] = arr[i][j + offset];
     * @param arr
     * @param offset
     */
    public void shiftColByOffset(int[][] arr, int offset) {
        System.out.println("OVERFLOW COL");
        int[][] tempArr = new int[limit][limit];
        for(int i = 0; i < limit; i++){
            for (int j = 0; j < limit; j++) {
                try {
                    tempArr[i][j] = arr[i][j + offset];
                } catch (Exception e) {
                    tempArr[i][j] = 0;
                }
            }
        }

        for(int i = 0; i < limit; i++){
            for (int j = 0; j < limit; j++) {
                if(tempArr[i][j] == 1) {
                    System.out.println("GREEN AT" + i + "," + j);
                }
                arr[i][j] = tempArr[i][j];
            }
        }
    }

    /**
     * determine if grid[row][col] should be alive in the next iteration
     * @param row
     * @param col
     * @return
     */
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
        // if(row == 3 && col == 1) {
        //  System.out.println("!!!!!!!!!!!!!!!!" + neighbourLifeCount);
        // }
        if(neighbourLifeCount == 2 && grid[row][col] == 1) {
            return true;
        } else if(neighbourLifeCount == 3) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * determine if grid[row][col] is alive in the current iteration
     * @param row
     * @param col
     * @return
     */
    public boolean alive(int row, int col) {
        try {
            return grid[row][col] == 1;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * change the color of the grid of buttons according to the current state
     */
    public void changeColors() {
        for(int i = 0; i < limit; i++) {
            for(int j = 0; j < limit; j++) {
                if(grid[i][j] == 1) {
                    display[i][j].setBackground(aliveColor);
                } else {
                    display[i][j].setBackground(deadColor);
                }
            }
        }
    }
}
