import greenfoot.GreenfootSound;
import greenfoot.World;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class Title extends World {
    public static String backToTitle = "escape";
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

        // Contributor Text
        this.addObject(new Text("JCharante\nB.E.R.T.'s Biggest Fan\nTeam 2410\nThe Metal Mustangs", 30, Color.ORANGE, (Color)null, Color.BLACK), 1090, 500);
        // Version Text
        this.addObject(new Text("SteamPowered++ v2.2", 30, Color.WHITE, (Color)null, Color.BLACK), 164, 555);


        ptime.put("regolith", 1000);
        ptime.put("fire", 500);
        ptime.put("freeze", 250);
        ptime.put("blindness", 1000);
        ptime.put("watergame", 1000);
        ptime.put("portal", 3);
        ptime.put("fillup", 3);
        ptime.put("speed", 500);
        ptime.put("space", 1000);
        ptime.put("cutrope", 3);
        ptime.put("autoclimb", 3);
        ptime.put("nuke", 3);
        ptime.put("antiboiler", 250);
        ptime.put("biasref", 250);
        ptime.put("recyclerush", 5);
        if(!music.isPlaying() && bgsound) {
            music.playLoop();
        }

    }
}
