import greenfoot.Actor;

public class Feeder extends Actor {
    private int load;
    private int l;
    private long lastHopperRefill;

    public Feeder() {
        l = 2;
        load = 50;
        lastHopperRefill = System.currentTimeMillis();
    }

    public void act() {
        updateDrawing();

        if ( (System.currentTimeMillis() - lastHopperRefill) > 13500) {
            load = 50;  // - this.getWorld().getObjects(Ball.class).toArray().length;  // Used to "limit" number of balls to 50 on the field for I'm guessing performance reasons.
            lastHopperRefill = System.currentTimeMillis();
        }

    }

    private void updateDrawing() {
        // Updates Drawing that shows how full the Feeder is
        if (load > 35) {
            l = 2;
        } else if (load > 0) {
            l = 1;
        } else {
            l = 0;
        }

        this.setImage("FuelContainer" + l + ".png");
    }

    public void dump() {
        while(load > 0) {
            --load;
            int x = this.getX() + (int)Math.floor(Math.random() * 50.0D) - 25;
            int y = this.getY() - (int)Math.floor(Math.random() * 50.0D);
            this.getWorld().addObject(new Ball(0.0D, 0.0D, x, y, false), x, y);
        }

    }
}
