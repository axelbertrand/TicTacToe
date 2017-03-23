/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author p1503940
 */
public class TicTacToePosition extends Position {

    public static final int BLANK = 0;
    public static final int HUMAN = 1;
    public static final int PROGRAM = -1;
    
    public int board[];
    
    TicTacToePosition()
    {
        board = new int[9];
    }
    
    @Override
    public String toString()
    {
        String str = "";
        
        for(int i = 0; i < board.length; i++)
        {
            str += board[i] + (((i + 1)%3 == 0) ? "\n" : " ");
        }
        
        return str;
    }
}
