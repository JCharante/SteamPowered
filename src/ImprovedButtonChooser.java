import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.awt.*;
import java.util.ArrayList;

public class ImprovedButtonChooser extends Actor {
    private ArrayList<String> options;
    private int currentIndex = 0;

    public ImprovedButtonChooser(ArrayList<String> options, String startingValue) {
        this.options = options;
        currentIndex = options.indexOf(startingValue);
    }

    private void incrementIndex() {
        if (options.size() > currentIndex + 1) {
            currentIndex++;
        } else {
            currentIndex = 0;
        }
    }

    public String value() {
        return options.get(currentIndex);
    }

    public void act() {
        if(Greenfoot.mousePressed(this)) {
            incrementIndex();
        }

        this.setImage( new GreenfootImage(value(), 30, Color.WHITE, null, Color.BLACK) );
    }
}
