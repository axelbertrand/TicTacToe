package tictactoe;

public class TicTacToe extends GameSearch {

    @Override
    public boolean tiedPosition(Position p) {
        TicTacToePosition pos = (TicTacToePosition) p;
        boolean tie = true;
        
        for(int i = 0; i < pos.board.length; i++)
        {
            if(pos.board[i] == TicTacToePosition.BLANK)
            {
                tie = false;
            }
        }
        
        return tie;
    }
    
    @Override
    public boolean wonPosition(Position p, boolean player) {
       TicTacToePosition pos = (TicTacToePosition) p;
       boolean won = false;
       
       int playerValue = player ? 1 : -1;
       
       for(int i = 0; i < 3; i++)
       {
           won = ((pos.board[i] == playerValue) && (pos.board[i + 3] == playerValue) && (pos.board[i + 6] == playerValue)) ||
                 ((pos.board[3*i] == playerValue) && (pos.board[1 + 3*i] == playerValue) && (pos.board[2 + 3*i] == playerValue)) ||
                 ((pos.board[0] == playerValue) && (pos.board[4] == playerValue) && (pos.board[8] == playerValue)) ||
                 ((pos.board[2] == playerValue) && (pos.board[4] == playerValue) && (pos.board[6] == playerValue));
           
       }
        
       return won;
    }

    @Override
    public float positionEvaluation(Position p, boolean player) {
        
        return 0.f;
    }
    
    @Override
    public void printPosition(Position p) {
        System.out.println(p);
    }
    
    @Override
    public boolean reachedMaxDepth(Position p, int depth) {
        
        return false;
    }
        
    @Override
    public Position [] possibleMoves(Position p, boolean player) {
        TicTacToePosition pos = (TicTacToePosition)p;
        int count = 0;
        for (int i=0; i<9; i++) if (pos.board[i] == 0) count++;
        if (count == 0) return null;
        Position [] ret = new Position[count];
        count = 0;
        for (int i=0; i<9; i++) {
            if (pos.board[i] == 0) {
                TicTacToePosition pos2 = new  TicTacToePosition();
                for (int j=0; j<9; j++) pos2.board[j] = pos.board[j];
                if (player) pos2.board[i] = 1; else pos2.board[i] = -1;
                ret[count++] = pos2;
            }
        }
        return ret;
    }
    
    @Override
    public Position makeMove(Position p, boolean player) {
        int i = 0;
        try {
            int ch = System.in.read();
            i = ch - 48;
            System.in.read();
        } catch (Exception e) { }
        TicTacToeMove m = new TicTacToeMove();
        m.moveIndex = i;
        TicTacToePosition pos = (TicTacToePosition)p;
        TicTacToePosition pos2 = new  TicTacToePosition();
        for (int j=0; j<9; j++) pos2.board[j] = pos.board[j];
        int pp;
        if (player) pp =  1;
        else        pp = -1;
        pos2.board[m.moveIndex] = pp;
        return pos2;
    }
    
    static public void main(String [] args) {
        TicTacToePosition p = new TicTacToePosition();
        TicTacToe ttt = new TicTacToe();
        ttt.playGame(p);
    }
}
