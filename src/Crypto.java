import java.util.Scanner;

/**
 * Created by arthurhasoro on 7/19/17.
 */
public class Crypto {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to text encrypter");
        System.out.print("Enter your text to be encrypted: ");
        String text = input.nextLine();

        System.out.print("Enter the key integer number to encrypt the text: ");
        int shift = input.nextInt();

        System.out.print("Enter the number of letter you want to be on each group: ");
        int groupNumber = input.nextInt();

        String encryptedString = encryptString(text, shift, groupNumber);
        System.out.println("Your encrypted string is: " + encryptedString);

    }

    public static String encryptString(String toEncrypt, int shiftNum, int groupSize){
        String textNormalized = normalizeText(toEncrypt);
        String textObified = obify(textNormalized);
        String textcaesarified = caesarify(textObified, shiftNum);
        String textGrouped = groupify(textcaesarified, groupSize);
        return textGrouped;
    }

    public  static String normalizeText(String convertString){
        convertString = convertString.replace(" ", "");
        convertString = convertString.replace("(", "");
        convertString = convertString.replace(")", "");
        convertString = convertString.replace("\"", "");
        convertString = convertString.replace(".", "");
        convertString = convertString.replace(",", "");
        convertString = convertString.replace("?", "");
        convertString = convertString.replace(":", "");
        convertString = convertString.replace(";", "");
        convertString = convertString.replace("!", "");
        convertString = convertString.replace("'", "");
        convertString = convertString.toUpperCase();

        return convertString;
    }

    public static String obify(String message){
        message = message.replace("O", "OBO");
        message = message.replace("A", "OBA");
        message = message.replace("E", "OBE");
        message = message.replace("I", "OBI");
        message = message.replace("U", "OBU");
        message = message.replace("Y", "OBY");

        return message;
    }

    public static String unobify(String message){
        message = message.replace("OBO", "O");
        message = message.replace("OBA", "A");
        message = message.replace("OBE", "E");
        message = message.replace("OBI", "I");
        message = message.replace("OBU", "U");
        message = message.replace("OBY", "Y");

        return message;
    }

    public static String caesarify(String encryptString, int num){
        String result = "";
        for (int i = 0; i < encryptString.length(); i++) {
            result += (char)(((encryptString.charAt(i) - (int)'A' + num) % 26) + (int)'A');
        }
        return result;
    }

    public static String groupify(String strToGroup, int num){
        String result = "";
        if ((strToGroup.length() % num) == 0){
            for (int i = 0; i < (strToGroup.length()); i += num){
                result += strToGroup.substring(i,(i+num)) + " ";
            }
        }
        else {

            for (int i = 0; i < (strToGroup.length()); i += num){
                if ((strToGroup.length() - i) < num){
                    int addX = num - (strToGroup.length() - i);
                    String xToAdd = xString(addX);
                    result += strToGroup.substring(i) + xToAdd;
                }
                else{
                    result += strToGroup.substring(i,(i+num)) + " ";
                }
            }
        }

        return result;
    }

    public static String xString(int n){
        if (n < 0){
            throw new IllegalArgumentException();
        }
        else if (n == 1){
            return "x";
        }
        else {
            return "x" + xString(n - 1);
        }
    }

}
