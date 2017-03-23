package tictactoe;

import java.util.*;

public abstract class GameSearch {

    public static boolean PROGRAM = false;
    public static boolean HUMAN = true;

    // Abstract methods:  
    public abstract boolean tiedPosition(Position p);
    public abstract boolean wonPosition(Position p, boolean player);
    public abstract float positionEvaluation(Position p, boolean player);
    public abstract void printPosition(Position p);
    public abstract Position [] possibleMoves(Position p, boolean player);
    public abstract Position makeMove(Position p, boolean player);
    public abstract boolean reachedMaxDepth(Position p, int depth);

    //Search methods:
    protected Vector alphaBeta(int depth, Position p, boolean player) {
        Vector v = alphaBetaHelper(depth, p, player, 1000000.0f, -1000000.0f);
        return v;
    }

    protected Vector alphaBetaHelper(int depth, Position p,
                                     boolean player, float alpha, float beta) {
        if (reachedMaxDepth(p, depth)) {
            Vector v = new Vector(2);
            float value = positionEvaluation(p, player);
            v.addElement(new Float(value));
            v.addElement(null);
            return v;
        }
        Vector best = new Vector();
        Position [] moves = possibleMoves(p, player);
        for (int i=0; i<moves.length; i++) {
            Vector v2 = alphaBetaHelper(depth + 1, moves[i], !player, -beta, -alpha);
            float value = -((Float)v2.elementAt(0)).floatValue();
            if (value > beta) {
                beta = value;
                best = new Vector();
                best.addElement(moves[i]);
                Enumeration enum2 = v2.elements();
                enum2.nextElement(); // skip previous value
                while (enum2.hasMoreElements()) {
                    Object o = enum2.nextElement();
                    if (o != null) best.addElement(o);
                }
            }
            // alpha-beta cutoff test is used to abort search
            if (beta >= alpha) {
                break;
            }
        }
        Vector v3 = new Vector();
        v3.addElement(new Float(beta));
        Enumeration enum2 = best.elements();
        while (enum2.hasMoreElements()) {
            v3.addElement(enum2.nextElement());
        }
        return v3;
    }
    
    public void playGame(Position startingPosition) {
        while (true) {            
            System.out.println("Human move:");
            startingPosition = makeMove(startingPosition, HUMAN);
            printPosition(startingPosition);
            if (wonPosition(startingPosition, HUMAN)) {
                System.out.println("Human won");
                break;
            }
            if (tiedPosition(startingPosition)) {
                System.out.println("Tied game");
                break;
            }                 
            Vector v = alphaBeta(0, startingPosition, PROGRAM);
            for (Enumeration enum2 = v.elements(); enum2.hasMoreElements();)
                System.out.println(" next element: " + enum2.nextElement());
            startingPosition = (Position)v.elementAt(1);
            printPosition(startingPosition);
            if (wonPosition(startingPosition, PROGRAM)) {
                System.out.println("Program won");
                break;
            }  
          }
        }
    
    }

