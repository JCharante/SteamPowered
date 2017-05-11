import greenfoot.Greenfoot;
import greenfoot.World;
import java.awt.Color;

public class End extends World {
    public End(int blue, int red, int blueMobility, int bluePressure, int blueRotor, int blueClimbing, int blueBonus, int blueFoul, int redMobility, int redPressure, int redRotor, int redClimbing, int redBonus, int redFoul) {
        super(1256, 570, 1);
        this.addObject(new Text("" + red, 50, Color.WHITE, (Color)null, Color.BLACK), 535, 496);
        this.addObject(new Text("" + blue, 50, Color.WHITE, (Color)null, Color.BLACK), 716, 496);
        this.addObject(new Text("" + redBonus, 30), 535, 222);
        this.addObject(new Text("" + redMobility, 30), 535, 286);
        this.addObject(new Text("" + redPressure, 30), 535, 318);
        this.addObject(new Text("" + redRotor, 30), 535, 350);
        this.addObject(new Text("" + redClimbing, 30), 535, 382);
        this.addObject(new Text("" + redFoul, 30), 535, 414);
        this.addObject(new Text("" + blueBonus, 30), 980, 222);
        this.addObject(new Text("" + blueMobility, 30), 980, 286);
        this.addObject(new Text("" + bluePressure, 30), 980, 318);
        this.addObject(new Text("" + blueRotor, 30), 980, 350);
        this.addObject(new Text("" + blueClimbing, 30), 980, 382);
        this.addObject(new Text("" + blueFoul, 30), 980, 414);

        this.addObject(new Text("Qualification 133 of 140", 23, Color.BLACK, (Color)null, (Color)null), 628, 35);
        this.addObject(new Text("Beantown Blitz", 17, Color.BLACK, (Color)null, (Color)null), 628, 55);


        if(blue > red) {
            this.addObject(new BlueWins(), 868, 496);
            if(Title.sounds) {
                Greenfoot.playSound("bv" + (int)Math.floor(Math.random() * 2.0D) + ".wav");
            }
        } else if(red > blue) {
            this.addObject(new RedWins(), 386, 496);
            if(Title.sounds) {
                Greenfoot.playSound("rv" + (int)Math.floor(Math.random() * 2.0D) + ".wav");
            }
        } else {
            this.addObject(new Tie("Blue"), 868, 496);
            this.addObject(new Tie("Red"), 386, 496);
        }

        if(blueBonus == 20 || blueBonus == 120) {
            this.addObject(new BonusIndicator(3), 736, 217);
        }

        if(blueBonus == 100 || blueBonus == 120) {
            this.addObject(new BonusIndicator(4), 842, 217);
        }

        if(redBonus == 20 || redBonus == 120) {
            this.addObject(new BonusIndicator(1), 297, 217);
        }

        if(redBonus == 100 || redBonus == 120) {
            this.addObject(new BonusIndicator(2), 402, 217);
        }

        this.addObject(new BackButton(), 1100, 516);
    }
}
