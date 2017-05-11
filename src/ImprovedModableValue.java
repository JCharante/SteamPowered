import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import java.awt.Color;

public class ImprovedModableValue extends Actor {
    public String value = "";
    private Color textColor;
    private Color backgroundColor;
    private Color outlineColor;
    boolean mouseDown;

    public ImprovedModableValue(String val) {
        this.value = val;
        textColor = Color.BLACK;
        backgroundColor = (Color)null;
        outlineColor = (Color)null;
        this.setImage(new GreenfootImage("\"" + val + "\"", 30, textColor, backgroundColor, outlineColor));
    }

    public ImprovedModableValue(String val, Color textColor, Color backgroundColor, Color outlineColor) {
        value = val;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.outlineColor = outlineColor;
        this.setImage(new GreenfootImage("\"" + val + "\"", 30, textColor, backgroundColor, outlineColor));
    }

    public void act() {
        if(Greenfoot.mousePressed(this)) {
            this.mouseDown = true;
        } else if(Greenfoot.mouseClicked((Object)null)) {
            this.mouseDown = false;
        }

        if(this.mouseDown && ((Settings)((Settings)this.getWorld())).k != null) {
            this.value = ((Settings)((Settings)this.getWorld())).k;
        }

        this.setImage(new GreenfootImage("\"" + this.value + "\"", 30, textColor, backgroundColor, outlineColor));
    }
}
