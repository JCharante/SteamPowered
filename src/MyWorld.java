import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import greenfoot.World;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class MyWorld extends World {
    int rap = 425;
    int bap;
    int spawntime;
    int spr;
    public Map<String, Integer> redpowerups;
    public Map<String, Integer> bluepowerups;
    public Timer timer;
    int preg;
    int pink;
    int rrcan;
    boolean rfsound;
    boolean bfsound;
    boolean isound;
    public static GreenfootSound music = new GreenfootSound("cheer.wav");

    public MyWorld() {
        super(1256, 570, 1);
        timer = new Timer();
        this.bap = 1256 - this.rap;
        this.spawntime = 0;
        this.redpowerups = new HashMap();
        this.bluepowerups = new HashMap();
        this.rrcan = 0;
        this.rfsound = false;
        this.bfsound = false;
        this.isound = false;
        this.setPaintOrder(new Class[]{Obscurer.class, Water.class, Actor.class});
        this.addObject(new ScoreBoard(), 628, 485);
        this.addObject(timer, 628, 468);
        this.addObject(new RedFuel(), 334, 538);
        this.addObject(new BlueFuel(), 922, 538);
        this.addObject(new RedRotors(), 216, 535);
        this.addObject(new BlueRotors(), 1040, 535);
        this.addObject(new RedClimber(), 108, 535);
        this.addObject(new BlueClimber(), 1148, 535);
        this.addObject(new RedScore(), 555, 524);
        this.addObject(new BlueScore(), 701, 524);
        this.addObject(new BottomAirship(0), this.rap, 362);
        this.addObject(new BottomAirship(1), this.bap, 362);
        this.addObject(new Peg(0), 341, 360);
        this.addObject(new Peg(1), 914, 360);
        this.addObject(new TopAirship(0), this.rap - 1, 286);
        this.addObject(new TopAirship(1), this.bap - 1, 286);
        this.addObject(new CenterRotor(1), this.bap + 2, 195);
        this.addObject(new CenterRotor(0), this.rap + 2, 195);
        this.addObject(new BoilerBottom(0), 38, 299);
        this.addObject(new BoilerTop(0), 38, 191);
        this.addObject(new BoilerScore(1), 41, 154);
        this.addObject(new BoilerBottom(1), 1218, 299);
        this.addObject(new BoilerTop(1), 1219, 191);
        this.addObject(new BoilerScore(0), 1216, 154);
        this.addObject(new BlueFeeder(), 38, 339);
        this.addObject(new RedFeeder(), 1218, 339);
        this.addObject(new BlueDavit(), 705, 274);
        this.addObject(new RedDavit(), 550, 274);
        this.addObject(new Feeder(), 628, 350);
        this.addObject(new RedRobot(), 155, 370);
        this.addObject(new BlueRobot(), 1101, 370);
        this.addObject(new BlueRotor(), this.bap + 2, 216);
        this.addObject(new BlueRotor(), this.bap + 93, 216);
        this.addObject(new BlueRotor(), this.bap - 94, 216);
        this.addObject(new RedRotor(), this.rap + 2, 216);
        this.addObject(new RedRotor(), this.rap - 93, 216);
        this.addObject(new RedRotor(), this.rap + 92, 216);
        this.addObject(new Text("Qualification 133 of 140", 24, Color.BLACK, (Color)null, (Color)null), 400, 425);
        this.addObject(new Text("Beantown Blitz", 24, Color.BLACK, (Color)null, (Color)null), 880, 425);


        for(int i = 0; i < Title.allpowerups.length; ++i) {
            this.redpowerups.put(Title.allpowerups[i], Integer.valueOf(0));
            this.bluepowerups.put(Title.allpowerups[i], Integer.valueOf(0));
        }

        this.spr = (3 - Title.spawnrate) * 500;
        this.spr = (int)((double)this.spr + (Math.floor(Math.random() * (double)(200 * (3 - Title.spawnrate))) - (double)(100 * (3 - Title.spawnrate))));
        if(Title.sounds) {
            Greenfoot.playSound("charge-1.wav");
        }

        if(Title.bgsound) {
            music.playLoop();
        }

    }

    public void act() {
        if(Title.spawnrate != 0) {
            ++this.spawntime;
            if(this.spawntime >= this.spr) {
                this.addObject(new Powerup(Title.powerups[(int)Math.floor(Math.random() * (double)Title.powerups.length)]), (int)Math.floor(Math.random() * 1008.0D) + 124, (int)Math.floor(Math.random() * 400.0D));
                this.spr = (3 - Title.spawnrate) * 500;
                this.spr = (int)((double)this.spr + (Math.floor(Math.random() * (double)(200 * (3 - Title.spawnrate))) - (double)(100 * (3 - Title.spawnrate))));
                this.spawntime = 0;
            }
        }

        for(int i = 0; i < Title.powerups.length; ++i) {
            if(((Integer)this.redpowerups.get(Title.powerups[i])).intValue() > 0) {
                this.redpowerups.put(Title.powerups[i], Integer.valueOf(((Integer)this.redpowerups.get(Title.powerups[i])).intValue() - 1));
            }

            if(((Integer)this.bluepowerups.get(Title.powerups[i])).intValue() > 0) {
                this.bluepowerups.put(Title.powerups[i], Integer.valueOf(((Integer)this.bluepowerups.get(Title.powerups[i])).intValue() - 1));
            }
        }

        int reg = 0;
        int space = 0;
        if(((Integer)this.redpowerups.get("regolith")).intValue() > 0 || ((Integer)this.bluepowerups.get("regolith")).intValue() > 0) {
            reg = 1;
        }

        if(((Integer)this.redpowerups.get("space")).intValue() > 0 || ((Integer)this.bluepowerups.get("space")).intValue() > 0) {
            space = 1;
        }

        this.setBackground(new GreenfootImage("Background" + reg + "" + space + ".png"));
        if(!this.getObjects(Obscurer.class).isEmpty()) {
            this.removeObject((Actor)this.getObjects(Obscurer.class).get(0));
        }

        if(((Integer)this.redpowerups.get("blindness")).intValue() <= 0 && ((Integer)this.bluepowerups.get("blindness")).intValue() <= 0) {
            this.isound = true;
        } else {
            this.addObject(new Obscurer(), 628, 200);
            if(this.isound && Title.sounds) {
                Greenfoot.playSound("splat.wav");
                this.isound = false;
            }
        }

        if(!this.getObjects(Water.class).isEmpty()) {
            this.removeObject((Actor)this.getObjects(Water.class).get(0));
        }

        if(((Integer)this.redpowerups.get("watergame")).intValue() > 0 || ((Integer)this.bluepowerups.get("watergame")).intValue() > 0) {
            this.addObject(new Water(), 628, 350);
        }

        if(((Integer)this.redpowerups.get("portal")).intValue() > 0) {
            RedRobot r = (RedRobot)this.getObjects(RedRobot.class).get(0);
            this.addObject(new Teleportal(((Integer)this.redpowerups.get("portal")).intValue()), r.getX(), r.getY());
        }

        if(((Integer)this.bluepowerups.get("portal")).intValue() > 0) {
            BlueRobot b = (BlueRobot)this.getObjects(BlueRobot.class).get(0);
            this.addObject(new Teleportal(((Integer)this.bluepowerups.get("portal")).intValue()), b.getX(), b.getY());
        }

        while(!this.getObjects(Ice.class).isEmpty()) {
            this.removeObject((Actor)this.getObjects(Ice.class).get(0));
        }

        if(((Integer)this.redpowerups.get("nuke")).intValue() > 1) {
            this.addObject(new Nukesplosion(), ((TopAirship)this.getObjects(TopAirship.class).get(1)).getX(), 210);
            if(Title.sounds) {
                Greenfoot.playSound("explosion.wav");
            }
        }

        if(((Integer)this.bluepowerups.get("nuke")).intValue() > 1) {
            this.addObject(new Nukesplosion(), ((TopAirship)this.getObjects(TopAirship.class).get(0)).getX(), 210);
            if(Title.sounds) {
                Greenfoot.playSound("explosion.wav");
            }
        }

        if(((Integer)this.redpowerups.get("freeze")).intValue() > 0) {
            this.addObject(new Ice(), ((BlueRobot)this.getObjects(BlueRobot.class).get(0)).getX(), ((BlueRobot)this.getObjects(BlueRobot.class).get(0)).getY());
            if(this.rfsound && Title.sounds) {
                Greenfoot.playSound("Freeze.wav");
                this.rfsound = false;
            }
        } else {
            this.rfsound = true;
        }

        if(((Integer)this.bluepowerups.get("freeze")).intValue() > 0) {
            this.addObject(new Ice(), ((RedRobot)this.getObjects(RedRobot.class).get(0)).getX(), ((RedRobot)this.getObjects(RedRobot.class).get(0)).getY());
            if(this.bfsound && Title.sounds) {
                Greenfoot.playSound("Freeze.wav");
                this.bfsound = false;
            }
        } else {
            this.bfsound = true;
        }

        if(((Integer)this.redpowerups.get("recyclerush")).intValue() <= 0 && ((Integer)this.bluepowerups.get("recyclerush")).intValue() <= 0) {
            this.rrcan = 0;
        } else {
            this.addObject(new RecycleCrate(((Integer)this.redpowerups.get("recyclerush")).intValue() == 1 || ((Integer)this.bluepowerups.get("recyclerush")).intValue() == 1), 628, 370 - this.rrcan * 60);
            ++this.rrcan;
        }

    }

    public void stopped() {
        ((Timer)this.getObjects(Timer.class).get(0)).gamePaused();
    }
}
