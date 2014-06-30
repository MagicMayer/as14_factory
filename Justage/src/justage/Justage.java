package justage;

public class Justage {

    private Justage() {
    }

    /* Wenn schraeg zur Wand gefahren wird, wird dadurch falsch gemessen. */
    public static double wahrerAbstand(double gemessen, double angle) {
        return gemessen * Math.cos(angle / 180. * Math.PI);
    }

    public static double getAngleToChange(double fahrstrecke, double deltaDistance) {
        return Math.atan(deltaDistance / fahrstrecke) * 180. / Math.PI;
    }
}
