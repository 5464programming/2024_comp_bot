package frc.robot.OI;

public final class UserPolicy {
    public static boolean twistable = false;
    public static boolean xLocked = false;

    public static boolean ampShoot = false;
    public static boolean speakerShoot = false;
    public static boolean disableShoot = false;
    public static boolean shootReverse = false;
    public static boolean shootUptoSpeed = false;

    public static boolean intaking = false;
    public static boolean feeding = false; 
    public static boolean intakeReverse = false;

    public static boolean leftUp = false;
    public static boolean leftDown = false;
    public static boolean rightUp = false;
    public static boolean rightDown = false;
    public static boolean autoUp = false;
    public static boolean autoDown = false;

    public static String LEDselected;

    public static boolean snapAprilSpeaker = false;
    // TODO: Make snapping to the amp a possiblity

    public static boolean closetospeaker = false;
    public static boolean closetoamp = false;

    private UserPolicy() {
    }
}
