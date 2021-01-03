import java.util.Scanner;

public class Enigma_Decryptor {
    public static final char NOTCH = '_';

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String message = in.next();
        System.out.println(enigmaEncryptor(message, 'A', 'B', 'C'));
    }

    public static String enigmaEncryptor(String message, char settingF, char settingM, char settingS) {
        String label     = "_.ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //28 characters

        String labelF    = "_.ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String rotorF    = "QWERTYUIOPAS_.DFGHJKLZXCVBNM";

        String labelM    = "_.ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String rotorM    = "QAZWSXE_.DCRFVTGBYHNUJMIKOLP";

        String labelS    = "_.ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String rotorS    = "MNBVCXZLKJHGFDSAPOIU_.YTREWQ";

        String reflector = "._QTNUVXRSZWYPOCMLAGHBDEJFKI";
        String plugBoard = "_.OZUPRQXSVWNTYKADFEHLCIJGMB";


        int i = 0;
        String encrypted = "";
        int position = -1;
        // code to setup rotors to initial settings: fast, middle, slow
        position = labelF.indexOf(settingF);
        labelF = labelF.substring(position) + labelF.substring(0, position);
        rotorF = rotorF.substring(position) + rotorF.substring(0, position);

        position = labelM.indexOf(settingM);
        labelM = labelM.substring(position) + labelM.substring(0, position);
        rotorM = rotorM.substring(position) + rotorM.substring(0, position);

        position = labelS.indexOf(settingS);
        labelS = labelS.substring(position) + labelS.substring(0, position);
        rotorS = rotorS.substring(position) + rotorS.substring(0, position);

        do {
            do {
                do {
                    System.out.println("Rotor F: " + rotorF);
                    System.out.println("Rotor M: " + rotorM);
                    System.out.println("Rotor S: " + rotorS);

                    char letter = message.charAt(i);
                    i++;

                    position = label.indexOf(letter);
                    letter = plugBoard.charAt(position);

                    position = label.indexOf(letter); // determine physical connection to left side of rotorF
                    System.out.println(letter);
                    System.out.println(position);

                    letter = rotorF.charAt(position);   // encryption through rotorF
                    position = labelF.indexOf(letter);  // determine physical connection to left side of rotorM
                    System.out.println("Rotor F: " + rotorF.charAt(position));

                    letter = rotorM.charAt(position);   // encryption through rotorM
                    position = labelM.indexOf(letter);  // determine physical connection to left side of rotorS
                    System.out.println("Rotor M: " + rotorM.charAt(position));

                    letter = rotorS.charAt(position);   // encryption through rotorS
                    position = labelS.indexOf(letter);  // determine physical connection to left side of reflector
                    System.out.println("Rotor S: " + rotorS.charAt(position));

                    letter = reflector.charAt(position);    // encryption through reflector
                    System.out.println("Reflector: " + reflector.charAt(position));

                    // Backward encryption from reflector to light bulb
                    //Slow Rotor - Encryption5
                    position = label.indexOf(letter);   // determine physical connection to right side of rotorS
                    letter = labelS.charAt(position);   // back encryption through rotorS
                    position = rotorS.indexOf(letter);
                    letter = labelS.charAt(position);
                    System.out.println("Rotor S: " + labelS.charAt(position));

                    //Middle Rotor - Encryption6
                    position = labelS.indexOf(letter); // determine physical connection to right side of rotorM
                    letter = labelM.charAt(position);   // back encryption through rotorM
                    System.out.println(letter);
                    position = rotorM.indexOf(letter);
                    System.out.println(position);
                    letter = labelM.charAt(position);
                    System.out.println("Rotor M: " + labelM.charAt(position));

                    //Fast Rotor - Encryption7
                    position = labelM.indexOf(letter);   // determine physical connection to right side of rotorF
                    letter = labelF.charAt(position);   // back encryption through rotorF
                    position = rotorF.indexOf(letter);
                    letter = labelF.charAt(position);
                    System.out.println("Rotor F: " + labelF.charAt(position));


                    //Label Rotor - Encryption8
                    position = labelF.indexOf(letter);  // determine physical connection to right side of bulb
                    letter = plugBoard.charAt(position); //Plug board comparision
                    System.out.println("Output: " + label.charAt(position));

                    position = label.indexOf(letter);
                    letter = label.charAt(position);

                    encrypted = encrypted + letter;

                    labelF = labelF.substring(1) + labelF.charAt(0);
                    rotorF = rotorF.substring(1) + rotorF.charAt(0);

                } while (labelF.charAt(0) != NOTCH && i < message.length());

                labelM = labelM.substring(1) + labelM.charAt(0);
                rotorM = rotorM.substring(1) + rotorM.charAt(0);
            } while (labelM.charAt(0) != NOTCH && i < message.length());

            labelS = labelS.substring(1) + labelS.charAt(0);
            rotorS = rotorS.substring(1) + rotorS.charAt(0);
        } while (i < message.length());

        return encrypted;

    }
}
