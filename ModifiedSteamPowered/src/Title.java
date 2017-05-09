import greenfoot.GreenfootSound;
import greenfoot.World;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class Title extends World {
    public static String blueJump = "i";
    public static String blueLeft = "j";
    public static String blueRight = "l";
    public static String blueGear = "k";
    public static String bluePickup = "u";
    public static String blueShoot = "o";
    public static String blueUse = "m";
    public static String redJump = "w";
    public static String redLeft = "a";
    public static String redRight = "d";
    public static String redGear = "s";
    public static String redPickup = "q";
    public static String redShoot = "e";
    public static String redUse = "x";
    public static String[] powerups = new String[]{"regolith", "fire", "freeze", "blindness", "watergame", "fillup", "portal", "speed", "space", "cutrope", "autoclimb", "nuke", "antiboiler", "biasref", "recyclerush"};
    public static String[] allpowerups = new String[]{"regolith", "fire", "freeze", "blindness", "watergame", "fillup", "portal", "speed", "space", "cutrope", "autoclimb", "nuke", "antiboiler", "biasref", "recyclerush"};
    public static int spawnrate = 1;
    public static boolean autoaim = true;
    public static boolean autopickup = true;
    public static boolean worlds = false;
    public static boolean bgsound = true;
    public static boolean sounds = true;
    public static GreenfootSound music = new GreenfootSound("Steam_Powered.wav");
    public static Map<String, Integer> ptime = new HashMap();

    public Title() {
        super(1256, 570, 1);
        this.addObject(new StartButton(), 628, 325);
        this.addObject(new SettingsButton(), 628, 475);
        this.addObject(new Text("JCharante\nBERT's Biggest Fan\nTeam 2410\nThe Metal Mustangs", 30, Color.MAGENTA, (Color)null, Color.BLACK), 1100, 500);
        ptime.put("regolith", Integer.valueOf(1000));
        ptime.put("fire", Integer.valueOf(500));
        ptime.put("freeze", Integer.valueOf(250));
        ptime.put("blindness", Integer.valueOf(1000));
        ptime.put("watergame", Integer.valueOf(1000));
        ptime.put("portal", Integer.valueOf(3));
        ptime.put("fillup", Integer.valueOf(3));
        ptime.put("speed", Integer.valueOf(500));
        ptime.put("space", Integer.valueOf(1000));
        ptime.put("cutrope", Integer.valueOf(3));
        ptime.put("autoclimb", Integer.valueOf(3));
        ptime.put("nuke", Integer.valueOf(3));
        ptime.put("antiboiler", Integer.valueOf(250));
        ptime.put("biasref", Integer.valueOf(250));
        ptime.put("recyclerush", Integer.valueOf(5));
        if(!music.isPlaying() && bgsound) {
            music.playLoop();
        }

    }
}
