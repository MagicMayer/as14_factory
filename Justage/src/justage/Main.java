package justage;

public class Main {

    public static void main(String[] args) {
        double abstandsDifferenz = -2;
        double fahrstrecke = 20.;

        for (int i = 0; i < 5; i++) {
            System.out.println("Fahrstrecke: " + fahrstrecke + "\tAbstandsDiff: " + abstandsDifferenz + "\t" + Justage.getAngleToChange(fahrstrecke, abstandsDifferenz));
            abstandsDifferenz += 1.;
        }
        System.out.println("");

        abstandsDifferenz = -2.;
        fahrstrecke = 10.;
        for (int i = 0; i < 5; i++) {
            System.out.println("Fahrstrecke: " + fahrstrecke + "\tAbstandsDiff: " + abstandsDifferenz + "\t" + Justage.getAngleToChange(fahrstrecke, abstandsDifferenz));
            abstandsDifferenz += 1.;
        }
        System.out.println("");

        abstandsDifferenz = -2.;
        fahrstrecke = 5.;
        for (int i = 0; i < 5; i++) {
            System.out.println("Fahrstrecke: " + fahrstrecke + "\tAbstandsDiff: " + abstandsDifferenz + "\t" + Justage.getAngleToChange(fahrstrecke, abstandsDifferenz));
            abstandsDifferenz += 1.;
        }
        System.out.println("");
        
        for (int i = 0; i <= 45; i += 5) {
            System.out.println("Gemessener Abstand: 10\tWinkel:" + i + "\tWahrer Abstand: " + Justage.wahrerAbstand(10., i));
        }
    }
}
