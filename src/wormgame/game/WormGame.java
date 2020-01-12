package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Piece;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;

    public WormGame(int width, int height) {
        super(1000, null);
        
        Random random = new Random();
        this.width = width;
        this.height = height;
        this.continues = true;
        this.worm = new Worm(width/2, height/2,Direction.DOWN);
        this.apple = new Apple(random.nextInt(width), random.nextInt(height));
        if (apple.getX() == worm.getPieces().get(0).getX() && apple.getY() == worm.getPieces().get(0).getY()) {
            apple = new Apple(random.nextInt(width), random.nextInt(height));
        }

        addActionListener(this);
        setInitialDelay(2000);

    }
    
    public Worm getWorm() {
        return worm;
    }
    
    public void setWorm(Worm worm) {
        this.worm = worm;
    }
    
    public Apple getApple() {
        return apple;
    }
    
    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Random random = new Random();
        if (!continues) {
            JOptionPane.showMessageDialog(null, "Awwww, you lost! Rerun the game to try again.");
            exit(0);;
        }
        worm.move();
        if (worm.runsInto(apple)) {
            worm.grow();
            setApple(new Apple(random.nextInt(width), random.nextInt(height)));
        }
        if (worm.runsIntoItself()) {
            continues = false;
        }
        for (Piece piece : worm.getPieces()) {
            if (piece.getX() < 0 || piece.getX() >= width || piece.getY() < 0 || piece.getY() >= height) {
                continues = false;
            }
        }
        updatable.update();
        setDelay(1000 / worm.getLength());
    }

}
