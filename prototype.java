package company;

import java.util.Arrays;
import java.util.Scanner;

public class prototype {

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
            int[] encryptedText = textToListOfNumbers(upperCaseLetters);
            //lav det array, metoden har lavet, om til en string, der kan udskrives
            String encryptedToString = Arrays.toString(encryptedText);
            System.out.println(encryptedToString);
        }

        if (answer.equals("d")) {
            System.out.println("""
                    Du har valgt at dekode en tekst
                    Indtast dine tal""");
            String numbers = scanner.nextLine();
            String decryptedNumbers = listOfNumbersToText(numbers);
            System.out.println(decryptedNumbers);
        }
    }

    public static int[] textToListOfNumbers(String text) {
        // vi omregner bogstavernes codepoint til de ønskede talværdier, og laver et array med tallene
        int[] resultNumbers = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            //vi beder om codepointet på hvert tegn i arrayet
            int codepoint = text.codePointAt(i);
            //Vi sender hvert tegn til metoden for at få codepoint udtrykt som et tal fra 0-29, og gemmer det i et nyt array
            int encryptedCodepoint = CodepointToNumber(codepoint);
            resultNumbers[i] = encryptedCodepoint;
        }
        return resultNumbers;
    }

    public static String listOfNumbersToText(String numbersAsString) {
        StringBuilder text = new StringBuilder();
        //vi splitter string op i et array, ved at dele ved komma.
        String[] numbers = numbersAsString.split(",");
        //vi fodre resultatet af opsplitningen ind i et array, for at gemme vores resultat
        for (int i = 0; i < numbers.length; i++) {
            //vi laver samtidig tegnene om til int
            int numberAsCodepoint = Integer.parseInt(numbers[i]);
            //vi sender tallet tio metoden der laver dem til codepoint igen, og retunere bogstavet.
            String letter = numberToLetter(numberAsCodepoint);
            //vi benytter StringBulideren til at lave en samlet String af af vores bogstaver.
            text.append(letter);
        }
        return text.toString();
    }

    public static String numberToLetter(int numberAsCodepoint) {
        //vi giver resultLetter en startværdi, der ikke forstyrre den endelige tekst
        String resultLetters = " ";
        //bogstaver fra A-Z (vi lægger 64 til for at få deres codepoint)
        if ((numberAsCodepoint >= 1) && (numberAsCodepoint <= 26)) {
            numberAsCodepoint = numberAsCodepoint + 64;
            //vi laver codepointet om til det tilsvarende bogstav.
            resultLetters = Character.toString(numberAsCodepoint);
        } else {
            //mellemrum er 0 (codepoint er 32)
            if (numberAsCodepoint == 0) {
                numberAsCodepoint = 32;
                resultLetters = Character.toString(numberAsCodepoint);
            }
            //'Æ' er 27 (codepoint er 198)
            if (numberAsCodepoint == 27) {
                numberAsCodepoint = 198;
                resultLetters = Character.toString(numberAsCodepoint);
            }
            //'Ø' er 28 (codepoint er 216)
            if (numberAsCodepoint == 28) {
                numberAsCodepoint = 216;
                resultLetters = Character.toString(numberAsCodepoint);
            }
            //'Å' er 29 (codepoint er 197)
            if (numberAsCodepoint == 29) {
                numberAsCodepoint = 197;
                resultLetters = Character.toString(numberAsCodepoint);
            }
        }
        return resultLetters;
    }

    public static int CodepointToNumber(int codepoint) {
        //vi giver emcryptetCodepoint en start værdi, som koden ikke selv rammer, så vi ved at det er en værdi vi har tildelt
        int encryptedCodepoint = -1;
        //bogstaver fra A-Z (vi trækker 64 fra bogstavernes codepoints for at give bogstaverne tal fra 1-26)
        if ((codepoint >= 65) && (codepoint <= 90)) {
            encryptedCodepoint = codepoint - 64;
        } else {
            //mellemrum (codepoint er 32 og skal erstattes af 0)
            if (codepoint == 32) {
                encryptedCodepoint = 0;
            }
            //'Æ' Codepoint er 198
            if (codepoint == 198) {
                encryptedCodepoint = 27;
            }
            //'Ø' Codepoint er 216
            if (codepoint == 216) {
                encryptedCodepoint = 28;
            }
            //'Å' Codepoint er 197
            if (codepoint == 197) {
                encryptedCodepoint = 29;
            }
        }
        return encryptedCodepoint;
    }
}