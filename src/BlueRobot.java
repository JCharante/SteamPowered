import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class BlueRobot extends Actor {
    public double[] vel = new double[]{0.0D, 0.0D};
    public boolean onGround = true;
    public boolean onShip = false;
    public boolean onRobot = false;
    public boolean onRope = false;
    boolean onCrate = false;
    int[] terminal = new int[]{10, 20};
    public boolean[] moveLock = new boolean[]{false, false, false};
    TopAirship a;
    RedRobot r;
    Rope rop;
    public int[] load = new int[]{1, 0};
    int fuel = 10;
    boolean gbl = true;
    int geartime = 0;
    int facing = 0;
    private long lastShotFuel;
    String backToTitleKey;
    String jumpKey;
    String moveLeftKey;
    String moveRightKey;
    String slideGearKey;
    String pickupFuelKey;
    String shootKey;
    boolean autoaim;
    boolean autocollect;
    String modif = "";
    double accel = 1.0D;
    long pstart;
    boolean pbl = true;
    public boolean disabled = false;
    int pintime = 0;

    public BlueRobot() {
        lastShotFuel = System.currentTimeMillis();

        this.backToTitleKey = Title.backToTitle;
        this.jumpKey = Title.blueJump;
        this.moveLeftKey = Title.blueLeft;
        this.moveRightKey = Title.blueRight;
        this.slideGearKey = Title.blueGear;
        this.pickupFuelKey = Title.bluePickup;
        this.shootKey = Title.blueShoot;
        this.autoaim = Title.autoaim;
        this.autocollect = Title.autopickup;
    }

    private boolean enoughTimeSurpassedToShoot() {
        return (System.currentTimeMillis() - lastShotFuel) > PlusPlusSettings.millisecondsInBetweenShots;
    }

    private void backToTitleScreen() {
        Greenfoot.playSound("foghorn.wav");
        Settings.music.stop();
        MyWorld.music.stop();
        Greenfoot.setWorld(new Title());
    }

    private void shoot(MyWorld w) {
        if(this.fuel > 0 && enoughTimeSurpassedToShoot()) {
            --this.fuel;
            lastShotFuel = System.currentTimeMillis();

            double vx;
            double vy;
            if(this.autoaim) {
                BoilerScore TB = (BoilerScore)this.getWorld().getObjects(BoilerScore.class).get(this.facing);
                double t1 = Math.sqrt((double)(4 * (this.getY() - 55)));
                double t2 = Math.sqrt((double)(4 * (TB.getY() - 10)));
                vx = (double)(TB.getX() - this.getX()) / (t1 + t2);
                vy = t1 / 2.0D;
                vx += Math.random() * (vx / 4.0D) - vx / 8.0D;
            } else {
                if(this.facing == 1) {
                    vx = 8.0D;
                } else {
                    vx = -8.0D;
                }

                vy = 15.0D;
                vx += Math.random() * 2.0D - 1.0D;
            }

            this.getWorld().addObject(new Ball(vx, vy, this.getX(), this.getY() - 45, ((Integer)w.bluepowerups.get("fire")).intValue() > 0), this.getX(), this.getY() - 45);
            if(Title.sounds && ((Integer)w.bluepowerups.get("fire")).intValue() > 0) {
                Greenfoot.playSound("whoosh.wav");
            }
        }
    }

    public void act() {
        MyWorld w = (MyWorld)this.getWorld();

        if(Greenfoot.isKeyDown(this.backToTitleKey)) {
            backToTitleScreen();
        }

        if(((Integer)w.redpowerups.get("freeze")).intValue() == 0 && !this.disabled) {
            this.r = (RedRobot)this.getOneIntersectingObject(RedRobot.class);
            if(this.r != null && this.pintime == 0 && ((Integer)w.redpowerups.get("biasref")).intValue() > 0) {
                this.getWorld().addObject(new BlueFlag(), this.getX(), this.getY() - 100);
                RedScore.fouls += 5;
                this.pintime = 25;
            }

            if(this.pintime > 0) {
                --this.pintime;
            }

            if(((Integer)w.bluepowerups.get("fillup")).intValue() > 0) {
                this.fuel = 40;
                this.load[0] = 1;
            }

            if(((Integer)w.bluepowerups.get("portal")).intValue() > 0) {
                this.vel[0] = 0.0D;
                this.vel[1] = 0.0D;
                this.onGround = true;
                this.onShip = false;
                this.onRobot = false;
                this.onRope = false;
                this.onCrate = false;
                this.facing = 0;
                if(this.load[0] == 1) {
                    this.setLocation(949, 370);
                } else {
                    this.setLocation(110, 370);
                }

                if(Title.sounds) {
                    Greenfoot.playSound("portal.wav");
                }
            }

            if(this.fuel < 10) {
                this.load[1] = 0;
            } else if(this.fuel > 30) {
                this.load[1] = 2;
            } else {
                this.load[1] = 1;
            }

            this.setImage(new GreenfootImage("Blue" + this.load[0] + "" + this.load[1] + "" + this.facing + this.modif + ".png"));
            Water wa = (Water)this.getOneObjectAtOffset(0, 0, Water.class);
            if(wa == null) {
                this.setLocation(this.getX() + (int)this.vel[0], this.getY() + (int)this.vel[1]);
            } else {
                this.setLocation(this.getX() + (int)(2.0D * (this.vel[0] / 3.0D)), this.getY() + (int)(2.0D * (this.vel[1] / 3.0D)));
            }

            w = (MyWorld)this.getWorld();
            if(((Integer)w.redpowerups.get("regolith")).intValue() <= 0 && ((Integer)w.bluepowerups.get("regolith")).intValue() <= 0) {
                this.accel = 1.0D;
            } else {
                this.accel = 0.25D;
            }

            if(this.getX() < 124) {
                this.vel[0] = 0.0D;
                this.setLocation(124, this.getY());
                this.moveLock[0] = true;
            } else {
                this.moveLock[0] = false;
            }

            if(this.getX() > 1132) {
                this.vel[0] = 0.0D;
                this.setLocation(1132, this.getY());
                this.moveLock[1] = true;
            } else {
                this.moveLock[1] = false;
            }

            Gear g = (Gear)this.getOneIntersectingObject(Gear.class);
            if(g != null && (g.getX() < this.getX() && this.facing == 0 || g.getX() > this.getX() && this.facing == 1)) {
                this.load[0] = 1;
                this.getWorld().removeObject(g);
            }

            if(Greenfoot.isKeyDown(this.slideGearKey)) {
                if(this.gbl) {
                    this.getWorld().addObject(new Gear(26), 22, 282);
                }

                this.gbl = false;
            } else {
                this.gbl = true;
            }

            RecycleCrate c;
            if(!this.onGround) {
                if(this.getY() >= 370) {
                    this.setLocation(this.getX(), 370);
                    this.onGround = true;
                    this.vel[1] = 0.0D;
                } else if(this.getY() <= 30) {
                    this.vel[1] = Math.abs(this.vel[1]);
                } else if(((Integer)w.bluepowerups.get("space")).intValue() <= 0 && ((Integer)w.redpowerups.get("space")).intValue() <= 0) {
                    if(this.vel[1] < (double)this.terminal[1]) {
                        ++this.vel[1];
                    }
                } else {
                    this.vel[1] += 0.05D;
                }
            } else {
                if(this.onShip) {
                    this.a = (TopAirship)this.getOneIntersectingObject(TopAirship.class);
                    if(this.a == null) {
                        this.onGround = false;
                        this.onShip = false;
                        this.onRobot = false;
                        this.onRope = false;
                        this.onCrate = false;
                    } else if(this.getY() > this.a.getY() - 45) {
                        this.onGround = false;
                        this.onShip = false;
                        this.onRobot = false;
                        this.onRope = false;
                        this.onCrate = false;
                    }
                }

                if(this.onCrate) {
                    c = (RecycleCrate)this.getOneIntersectingObject(RecycleCrate.class);
                    if(c == null) {
                        this.onGround = false;
                        this.onShip = false;
                        this.onRobot = false;
                        this.onRope = false;
                        this.onCrate = false;
                    }
                }

                if(this.onRope) {
                    this.rop = (Rope)this.getOneIntersectingObject(Rope.class);
                    if(this.rop == null) {
                        this.onGround = false;
                        this.onShip = false;
                        this.onRobot = false;
                        this.onRope = false;
                        this.onCrate = false;
                    }
                }

                this.r = (RedRobot)this.getOneIntersectingObject(RedRobot.class);
                if(this.r != null) {
                    if(this.r.onRobot) {
                        this.moveLock[2] = true;
                    }

                    if(this.getY() < this.r.getY() + 58 && this.getY() > this.r.getY() - 58) {
                        if(this.getX() < this.r.getX() - 74) {
                            this.vel[0] = 0.0D;
                            this.setLocation(this.r.getX() - 94, this.getY());
                            this.moveLock[0] = true;
                        } else {
                            this.moveLock[0] = false;
                        }

                        if(this.getX() > this.r.getX() + 74) {
                            this.vel[0] = 0.0D;
                            this.setLocation(this.r.getX() + 94, this.getY());
                            this.moveLock[1] = true;
                        } else {
                            this.moveLock[1] = false;
                        }
                    }
                } else {
                    c = (RecycleCrate)this.getOneIntersectingObject(RecycleCrate.class);
                    if(c == null) {
                        this.moveLock[0] = this.moveLock[1] = this.moveLock[2] = false;
                    }

                    if(this.onRobot) {
                        this.onGround = false;
                        this.onShip = false;
                        this.onRobot = false;
                        this.onRope = false;
                        this.onCrate = false;
                    }
                }

                if(Greenfoot.isKeyDown(this.moveRightKey) && (this.vel[0] < (double)this.terminal[0] || ((Integer)w.bluepowerups.get("speed")).intValue() > 0) && !this.moveLock[0]) {
                    this.vel[0] += this.accel;
                    this.facing = 1;
                }

                if(Greenfoot.isKeyDown(this.moveLeftKey) && (this.vel[0] > (double)(-this.terminal[0]) || ((Integer)w.bluepowerups.get("speed")).intValue() > 0) && !this.moveLock[1]) {
                    this.vel[0] -= this.accel;
                    this.facing = 0;
                }

                if(!Greenfoot.isKeyDown(this.moveLeftKey) && !Greenfoot.isKeyDown(this.moveRightKey)) {
                    if(this.vel[0] < 0.0D) {
                        this.vel[0] += this.accel;
                    }

                    if(this.vel[0] > 0.0D) {
                        this.vel[0] -= this.accel;
                    }
                }

                if(Greenfoot.isKeyDown(this.jumpKey) && !this.moveLock[2]) {
                    this.rop = (Rope)this.getOneIntersectingObject(Rope.class);
                    if(this.rop != null) {
                        this.onRope = true;
                        BlueDavit bdavit = (BlueDavit)this.getOneIntersectingObject(BlueDavit.class);
                        RedDavit rdavit = (RedDavit)this.getOneIntersectingObject(RedDavit.class);
                        if(bdavit == null && rdavit == null) {
                            this.setLocation(this.getX(), this.getY() - 2);
                        }
                    } else {
                        Feeder f = (Feeder)this.getOneIntersectingObject(Feeder.class);
                        if(f != null) {
                            f.dump();
                        }

                        if(((Integer)w.bluepowerups.get("space")).intValue() <= 0 && ((Integer)w.redpowerups.get("space")).intValue() <= 0) {
                            this.vel[1] = (double)(-this.terminal[1]);
                        } else {
                            this.vel[1] = (double)(-this.terminal[1] / 3);
                        }

                        this.onGround = false;
                        this.onShip = false;
                        this.onRobot = false;
                        this.onRope = false;
                        this.onCrate = false;
                    }
                }
            }

            if(!this.onGround) {
                this.r = (RedRobot)this.getOneIntersectingObject(RedRobot.class);
                if(this.r != null) {
                    int tvel;
                    if(this.getY() < this.r.getY() + 58 && this.getY() > this.r.getY() - 58 && (this.getX() < this.r.getX() - 74 || this.getX() > this.r.getX() + 74)) {
                        tvel = (int)this.vel[0];
                        this.vel[0] = this.r.vel[0];
                        this.r.vel[0] = (double)tvel;
                    }

                    if(this.getX() < this.r.getX() + 92 && this.getX() > this.r.getX() - 92 && (!this.r.onGround || this.r.onShip) && (this.getY() < this.r.getY() - 26 || this.getY() > this.r.getY() + 26)) {
                        tvel = (int)this.vel[1];
                        this.vel[1] = this.r.vel[1];
                        this.r.vel[1] = (double)tvel;
                    }

                    if(this.vel[1] > 0.0D && this.getY() <= this.r.getY() - 40 && this.r.onGround) {
                        this.setLocation(this.getX(), this.r.getY() - 59);
                        this.onGround = true;
                        this.onRobot = true;
                        this.vel[1] = 0.0D;
                    }
                }

                this.a = (TopAirship)this.getOneIntersectingObject(TopAirship.class);
                if(this.a != null && this.vel[1] > 0.0D && this.getY() <= this.a.getY() - 45) {
                    this.setLocation(this.getX(), this.a.getY() - 67);
                    this.onGround = true;
                    this.onShip = true;
                    this.vel[1] = 0.0D;
                }

                c = (RecycleCrate)this.getOneIntersectingObject(RecycleCrate.class);
                if(c != null && this.vel[1] > 0.0D && this.getY() <= c.getY() - 35) {
                    this.setLocation(this.getX(), c.getY() - 54);
                    this.onGround = true;
                    this.onCrate = true;
                    this.vel[1] = 0.0D;
                }
            }

            Peg p = (Peg)this.getOneIntersectingObject(Peg.class);
            if(p != null && this.facing == 0) {
                if(p.getX() < this.getX() - 20 && p.d == 1) {
                    ++this.geartime;
                } else {
                    this.geartime = 0;
                }
            } else {
                this.geartime = 0;
            }

            if(p != null) {
                if(p.d == 0) {
                    Timer tim = (Timer)this.getWorld().getObjects(Timer.class).get(0);
                    if(!tim.auton) {
                        if(this.pbl) {
                            this.pstart = tim.millisElapsed;
                        }

                        if(tim.millisElapsed - this.pstart >= 5000L) {
                            this.pstart = tim.millisElapsed;
                            if(tim.ts) {
                                RedScore.fouls += 5;
                            } else {
                                RedScore.fouls += 25;
                            }

                            this.getWorld().addObject(new BlueFlag(), this.getX(), this.getY() - 100);
                        }

                        this.pbl = false;
                    } else {
                        this.pbl = true;
                    }
                } else {
                    this.pbl = true;
                }
            } else {
                this.pbl = true;
            }

            if(this.geartime > 40 && this.load[0] == 1) {
                this.getWorld().addObject(new DudGear(), 900, 365);
                this.load[0] = 0;
            }

            Ball b = (Ball)this.getOneIntersectingObject(Ball.class);
            if(b != null && this.fuel < 40 && (Greenfoot.isKeyDown(this.pickupFuelKey) || this.autocollect)) {
                ++this.fuel;
                this.getWorld().removeObject(b);
            }

            if(Greenfoot.isKeyDown(this.shootKey)) {
                shoot(w);
            }
        }

        if(this.disabled) {
            if(this.getY() < 370) {
                this.setLocation(this.getX(), this.getY() + (int)this.vel[1]);
                ++this.vel[1];
            } else {
                this.setLocation(this.getX(), 370);
            }

            this.setRotation(180);
            this.setImage(new GreenfootImage("Blue" + this.load[0] + "" + this.load[1] + "" + (1 - this.facing) + this.modif + ".png"));
        }

    }
}
