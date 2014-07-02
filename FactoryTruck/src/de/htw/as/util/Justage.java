package de.htw.as.util;

public class Justage {

	/* private constructor to prevent from instanziation */
    private Justage() {
    }

    /* Wenn schraeg zur Wand gefahren wird, wird dadurch falsch gemessen. */
    public static double wahrerAbstand(double gemessen, double angle){
        return gemessen * Math.cos(angle / 180. * Math.PI);
    }
    
    /* Winkelberechnung anhand der Fahrstrecke und der Abstandsaenderung */
    public static double getAngleToChange(double fahrstrecke, double deltaDistance) {
        return Math.atan(deltaDistance / fahrstrecke) * 180. / Math.PI;
    }
}
