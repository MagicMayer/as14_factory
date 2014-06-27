package justage;

import java.lang.Math.*;

public class Justier {

    private Justier() {
    }

    /* Wenn schraeg zur Wand gefahren wird, wird dadurch falsch gemessen. */
    public static double wahrerAbstand(double gemessen, double angle){
        return gemessen * Math.cos(angle / 180. * Math.PI);
    }
    
    public static double getAngleToChange(double fahrstrecke, double abstandsDifferenz) {
        return Math.atan(abstandsDifferenz / fahrstrecke) * 180. / Math.PI;
    }
}
