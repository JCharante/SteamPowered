//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import java.awt.Color;

public class Timer extends Actor {
    public long millisElapsed;
    long lastTime;
    public int rtime = 15;
    int tstart = 15;
    public boolean auton = true;
    public boolean ts = true;

    public Timer() {
        this.setImage(new GreenfootImage("15", 26, Color.BLACK, (Color)null));
    }

    public void start() {
        this.millisElapsed = 0L;
        this.lastTime = 0L;
    }

    public void gamePaused() {
        this.lastTime = 0L;
    }

    public void act() {
        long time = System.currentTimeMillis();
        if(this.lastTime != 0L) {
            long diff = time - this.lastTime;
            this.millisElapsed += diff;
        }

        this.lastTime = time;
        this.rtime = (int)((long)this.tstart - this.millisElapsed / 1000L);
        this.setImage(new GreenfootImage("" + this.rtime, 26, Color.BLACK, (Color)null));
        if(this.rtime <= 30 && this.ts && !this.auton) {
            if(Title.sounds) {
                Greenfoot.playSound("Whistle.wav");
            }

            this.ts = false;
        }

        if(this.rtime <= 0) {
            if(this.auton) {
                this.tstart = 135;
                this.millisElapsed = 0L;
                this.rtime = 135;
                this.auton = false;
                TopAirship red = (TopAirship)this.getWorld().getObjects(TopAirship.class).get(0);
                TopAirship blue = (TopAirship)this.getWorld().getObjects(TopAirship.class).get(1);
                ++red.gears;
                ++blue.gears;
                if(Title.sounds) {
                    Greenfoot.playSound("three-bells.wav");
                }
            } else {
                RedScore red = (RedScore)this.getWorld().getObjects(RedScore.class).get(0);
                BlueScore blue = (BlueScore)this.getWorld().getObjects(BlueScore.class).get(0);
                if(Title.sounds) {
                    Greenfoot.playSound("buzzer.wav");
                }

                Greenfoot.setWorld(new End(blue.score, red.score, blue.mobility, blue.fscore, blue.rscore, blue.cscore, blue.bscore, BlueScore.fouls, red.mobility, red.fscore, red.rscore, red.cscore, red.bscore, RedScore.fouls));
            }
        }

    }

    public void abortMatch() {
        Greenfoot.playSound("foghorn.wav");
        MyWorld.music.stop();
        RedScore red = (RedScore)this.getWorld().getObjects(RedScore.class).get(0);
        BlueScore blue = (BlueScore)this.getWorld().getObjects(BlueScore.class).get(0);
        Greenfoot.setWorld(new End(blue.score, red.score, blue.mobility, blue.fscore, blue.rscore, blue.cscore, blue.bscore, BlueScore.fouls, red.mobility, red.fscore, red.rscore, red.cscore, red.bscore, RedScore.fouls));
    }

    public void end() {
        this.millisElapsed = 134000L;
        this.tstart = 135;
        this.auton = false;
    }

    public void climb() {
        this.millisElapsed = 104000L;
        this.tstart = 135;
        this.auton = false;
    }
}
