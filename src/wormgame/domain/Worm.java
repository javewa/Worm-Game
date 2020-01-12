/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

import java.util.ArrayList;
import java.util.Iterator;
import wormgame.Direction;
import java.util.List;

/**
 *
 * @author nacht
 */
public class Worm {
    
    private int originalX;
    private int originalY;
    private Direction originalDirection;
    private ArrayList<Piece> pieces;
    
    public Worm(int originalX, int originalY, Direction originalDirection) {
        this.originalX = originalX;
        this.originalY = originalY;
        this.originalDirection = originalDirection;
        this.pieces = new ArrayList<Piece>();
        pieces.add(new Piece(originalX, originalY));
    }
    
    public Direction getDirection() {
        return originalDirection;
    }
    
    public void setDirection(Direction dir) {
        originalDirection = dir;
    }
    
    public int getLength() {
        return pieces.size();
    }
    
    public List<Piece> getPieces() {
        return pieces;
    }
    
    public void move() {
        if (getLength() < 3) {
            this.grow();
            pieces.add(new Piece(originalX, originalY));
        } else {
            if (pieces.get(pieces.size() - 1).getX() != originalX || pieces.get(pieces.size() - 1).getY() != originalY) {
                pieces.add(new Piece(originalX, originalY));
            } else {
                this.grow();
                pieces.add(new Piece(originalX, originalY));
                pieces.remove(0);
            }
        }
    }
    
    public void grow() {
        originalX = pieces.get(pieces.size() - 1).getX();
        originalY = pieces.get(pieces.size() - 1).getY();
        if (this.originalDirection == Direction.DOWN) {
            originalY+= 1;
        } else if (this.originalDirection == Direction.UP) {
            originalY -= 1;
        } else if (this.originalDirection == Direction.RIGHT) {
            originalX += 1;
        } else if (this.originalDirection == Direction.LEFT) {
            originalX -= 1;
        }
    }
    
    public boolean runsInto(Piece piece) {
        boolean c = false;
        for (Piece myPiece : pieces) {
            if (myPiece.runsInto(piece)) {
                c = true;
            }
        }
        return c;
    }
    
    public boolean runsIntoItself() {
        boolean c = false;
        for (Piece piece : pieces) {
            for (Piece myPiece : pieces) {
                if (myPiece.equals(piece)) {
                    continue;
                }
                if (piece.runsInto(myPiece)) {
                    c = true;
                }
            }
        }
        return c;
    }
    
}
