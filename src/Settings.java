import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.World;
import java.awt.Color;

public class Settings extends World {
    public String k;
    public static GreenfootSound music = new GreenfootSound("Elevator_Music.wav");
    private ImprovedButtonChooser shootingAssistanceChooser;

    public Settings() {
        super(1256, 570, 1);

        /* Left Column */
        this.addObject(new Text("Red Robot", 40, Color.RED, (Color)null, Color.BLACK), 209, 30);
        this.addObject(new Text("Jump", 30, Color.WHITE, (Color)null, Color.BLACK), 104, 75);
        this.addObject(new ImprovedModableValue(Title.redJump, Color.WHITE, (Color)null, Color.BLACK), 314, 75);
        this.addObject(new Text("Left", 30, Color.WHITE, (Color)null, Color.BLACK), 104, 120);
        this.addObject(new ImprovedModableValue(Title.redLeft, Color.WHITE, (Color)null, Color.BLACK), 314, 120);
        this.addObject(new Text("Right", 30, Color.WHITE, (Color)null, Color.BLACK), 104, 165);
        this.addObject(new ImprovedModableValue(Title.redRight, Color.WHITE, (Color)null, Color.BLACK), 314, 165);
        this.addObject(new Text("Gear", 30, Color.WHITE, (Color)null, Color.BLACK), 104, 210);
        this.addObject(new ImprovedModableValue(Title.redGear, Color.WHITE, (Color)null, Color.BLACK), 314, 210);
        this.addObject(new Text("Pickup", 30, Color.WHITE, (Color)null, Color.BLACK), 104, 255);
        this.addObject(new ImprovedModableValue(Title.redPickup, Color.WHITE, (Color)null, Color.BLACK), 314, 255);
        this.addObject(new Text("Shoot", 30, Color.WHITE, (Color)null, Color.BLACK), 104, 300);
        this.addObject(new ImprovedModableValue(Title.redShoot, Color.WHITE, (Color)null, Color.BLACK), 314, 300);

        /* Middle Column*/
        this.addObject(new Text("General", 40, Color.WHITE, (Color)null, Color.BLACK), 628, 30);

        int switchX = 732;
        int textX = 523;
        int startingY = 75;
        int rowGap = 45;

        this.addObject(new Text("Shooter: ", 30, Color.WHITE, (Color)null, Color.BLACK), textX, (startingY + rowGap * 0));
        shootingAssistanceChooser = new ImprovedButtonChooser(Title.shootingAssistanceTypes, Title.shootingAssistance);
        this.addObject(shootingAssistanceChooser, switchX, (startingY + rowGap * 0));

        this.addObject(new Text("AutoCollect", 30, Color.WHITE, (Color)null, Color.BLACK), textX, (startingY + rowGap * 1));
        this.addObject(new Switch(Title.autopickup), switchX, (startingY + rowGap * 1));

        this.addObject(new Text("Worlds Mode", 30, Color.WHITE, (Color)null, Color.BLACK), textX, (startingY + rowGap * 2));
        this.addObject(new Switch(Title.worlds), switchX, (startingY + rowGap * 2));

        this.addObject(new Text("Music", 30, Color.WHITE, (Color)null, Color.BLACK), textX, (startingY + rowGap * 3));
        this.addObject(new Switch(Title.bgsound), switchX, (startingY + rowGap * 3));

        this.addObject(new Text("Sounds", 30, Color.WHITE, (Color)null, Color.BLACK), textX, (startingY + rowGap * 4));
        this.addObject(new Switch(Title.sounds), switchX, (startingY + rowGap * 4));

        /* Right Column*/
        this.addObject(new Text("Blue Robot", 40, Color.BLUE, (Color)null, Color.BLACK), 1046, 30);
        this.addObject(new Text("Jump", 30, Color.WHITE, (Color)null, Color.BLACK), 942, 75);
        this.addObject(new ImprovedModableValue(Title.blueJump, Color.WHITE, (Color)null, Color.BLACK), 1151, 75);
        this.addObject(new Text("Left", 30, Color.WHITE, (Color)null, Color.BLACK), 942, 120);
        this.addObject(new ImprovedModableValue(Title.blueLeft, Color.WHITE, (Color)null, Color.BLACK), 1151, 120);
        this.addObject(new Text("Right", 30, Color.WHITE, (Color)null, Color.BLACK), 942, 165);
        this.addObject(new ImprovedModableValue(Title.blueRight, Color.WHITE, (Color)null, Color.BLACK), 1151, 165);
        this.addObject(new Text("Gear", 30, Color.WHITE, (Color)null, Color.BLACK), 942, 210);
        this.addObject(new ImprovedModableValue(Title.blueGear, Color.WHITE, (Color)null, Color.BLACK), 1151, 210);
        this.addObject(new Text("Pickup", 30, Color.WHITE, (Color)null, Color.BLACK), 942, 255);
        this.addObject(new ImprovedModableValue(Title.bluePickup, Color.WHITE, (Color)null, Color.BLACK), 1151, 255);
        this.addObject(new Text("Shoot", 30, Color.WHITE, (Color)null, Color.BLACK), 942, 300);
        this.addObject(new ImprovedModableValue(Title.blueShoot, Color.WHITE, (Color)null, Color.BLACK), 1151, 300);

        this.addObject(new Text("Tip:", 30, Color.WHITE, (Color)null, Color.BLACK), 204, 450);
        this.addObject(new Text("Hold down LMB over a keybind &\npress a key to reassign keybind", 25, Color.WHITE, (Color)null, Color.BLACK), 204, 500);
        this.addObject(new BackButton(), 628, 525);
        this.addObject(new PowerupsButton(), 628, 400);

        if(!music.isPlaying() && Title.bgsound) {
            music.playLoop();
        }

    }

    public void act() {
        Title.shootingAssistance = shootingAssistanceChooser.value();

        // Other Stuff
        ImprovedModableValue[] c = new ImprovedModableValue[12];
        Switch[] s = new Switch[4];

        int i;
        for(i = 0; i < 12; ++i) {
            c[i] = (ImprovedModableValue)this.getObjects(ImprovedModableValue.class).get(i);
        }

        for(i = 0; i < 4; ++i) {
            s[i] = (Switch)this.getObjects(Switch.class).get(i);
        }
        // TODO: Fix all of this
        this.k = Greenfoot.getKey();
        Title.redJump = c[0].value;
        Title.redLeft = c[1].value;
        Title.redRight = c[2].value;
        Title.redGear = c[3].value;
        Title.redPickup = c[4].value;
        Title.redShoot = c[5].value;
        Title.blueJump = c[6].value;
        Title.blueLeft = c[7].value;
        Title.blueRight = c[8].value;
        Title.blueGear = c[9].value;
        Title.bluePickup = c[10].value;
        Title.blueShoot = c[11].value;
        Title.autopickup = s[0].on;
        Title.worlds = s[1].on;
        Title.bgsound = s[2].on;
        Title.sounds = s[3].on;
    }
}
