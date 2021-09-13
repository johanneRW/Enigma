package company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //velkomst- spørg om encode/decode
        System.out.println("""
        Velkommen til Enigma
        Vil du enkode eller dekode en tekst?
        Skriv e for enkode
        eller d for dekode""");

        // bruger tager et valg mellem e og d
        String answer = scanner.nextLine();

        if (answer.equals("e")) {
            System.out.println("""
            Du har valgt at enkode en tekst
            Indtast din tekst""");
            String text = scanner.nextLine();
            //Lav teksten om til store bogstaver, så der ikke er codepoints
            //der falder uden for if-sætningerne.
            String upperCaseLetters = text.toUpperCase();
            //Lav teksten om til array af krypterede cifre
            int[] encryptedText = getEncodedtext(upperCaseLetters);
            //lav det array, metoden har lavet, om til en string, der kan udskrives
            String encryptedToString = Arrays.toString(encryptedText);
            System.out.println(encryptedToString);
        }

        if (answer.equals("d")) {
            System.out.println("""
            Du har valgt at dekode en tekst
            Indtast dine tal""");
            String numbers = scanner.nextLine();
            //Lav talrækken om til array af dekrypterede bogstaver
            String[] decryptedNumbers = getDecodedNumbers(numbers);
            //lav det array, metoden har lavet, om til en string, der kan udskrives
            String decodedSToString = Arrays.toString(decryptedNumbers);
            System.out.println(decodedSToString);
            //TODO: find en måde at udskrive String uden komma.
        }
    }

    public static int[] getEncodedtext(String text) {
        // vi omregner bogstavernes codepoint til de ønskede talværdier, og laver et array med tallene
        int textLength = text.length();
        int[] resultNumbers = new int[textLength];
        for (int i = 0; i < textLength; i++) {
            int codepoint = text.codePointAt(i);
            //bogstaver fra A-Z (vi trækker 64 fra bogstavernes codepoints for at give bogstaverne tal fra 1-26)
            if ((codepoint >= 65) && (codepoint <= 90)) {
                int encryptedCodepoint = codepoint - 64;
                resultNumbers[i] = encryptedCodepoint;
            }
            //mellemrum (codepoint er 32 og skal erstattes af 0)
            if (codepoint == 32) {
                resultNumbers[i] = 0;
            }
            //'Æ' Codepoint er 198
            if (codepoint == 198) {
                resultNumbers[i] = 27;
            }
            //'Ø' Codepoint er 216
            if (codepoint == 216) {
                resultNumbers[i] = 28;
            }
            //'Å' Codepoint er 197
            if (codepoint == 197) {
                resultNumbers[i] = 29;
            }
        }
        return resultNumbers;
    }

    public static String[] getDecodedNumbers(String numbersAsString) {
        //inspiration til at omsætte codepoint til bogstaver fundet på:
        //https://stackoverflow.com/questions/18380901/how-do-i-convert-unicode-codepoints-to-their-character-representation

        //bearbejdet kode til at splitte strings op i enkelte bidder fundet på:
        //https://stackoverflow.com/questions/35764996/how-to-convert-the-numeric-comma-separated-string-into-int-array
        String[] numbers = numbersAsString.split(",");
        String[] resultLetters = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            int numberAsCodepoint = Integer.parseInt(numbers[i]);

            //bogstaver fra A-Z (vi lægger 64 til for at få deres codepoint)
            if ((numberAsCodepoint >= 1) && (numberAsCodepoint <= 26)) {
                numberAsCodepoint = numberAsCodepoint + 64;
                resultLetters[i] = Character.toString(numberAsCodepoint);
            }
            //mellemrum er 0 (codepoint er 32)
            if (numberAsCodepoint == 0) {
                numberAsCodepoint = 32;
                resultLetters[i] = Character.toString(numberAsCodepoint);
            }
            //'Æ' er 27 (codepoint er 198)
            if (numberAsCodepoint == 27) {
                numberAsCodepoint = 198;
                resultLetters[i] = Character.toString(numberAsCodepoint);
            }
            //'Ø' er 28 (codepoint er 216)
            if (numberAsCodepoint == 28) {
                numberAsCodepoint = 216;
                resultLetters[i] = Character.toString(numberAsCodepoint);
            }
            //'Å' er 29 (codepoint er 197)
            if (numberAsCodepoint == 29) {
                numberAsCodepoint = 197;
                resultLetters[i] = Character.toString(numberAsCodepoint);
            }
        }
        return resultLetters;
    }
}