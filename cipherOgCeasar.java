package company;

import java.util.Arrays;
import java.util.Scanner;

public class cipherOgCeasar {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Velkommen til Enigma
                Her kan du enkode eller dekode en tekst.
                Hvilken kode ønsker du at bruge?
                Skriv 1 for number cipher
                Skriv 2 for Caeser cipher
                Skriv 0 For exit""");
        String answer = scanner.nextLine();
        if (answer.equals("1")) {
            System.out.println("""
                    Du har valgt at benytte number cipher
                    Ønsker du at enkode eller dekode?
                    Skriv e for at enkode
                    Skriv d for at dekode""");
            String answerEOrD = scanner.nextLine();
            //lav om til små bogstaver så der tages højde for at brugeren måske skriver store bogstaver, hvilket stopper programmet.
            String lowerCaseAnswer = answerEOrD.toLowerCase();
            if (lowerCaseAnswer.equals("e")) {
                System.out.println("""
                        Du har valgt at enkode en tekst.
                        Skriv den tekst her:""");
                String plainText = scanner.nextLine();
                //Lav string om til store bogstaver, da programmet stopper hvis brugeren kommer til at skrive små bogstaver
                String upperCasePlainText = plainText.toUpperCase();
                int[] planTextAsArray = textToListOfNumbers(upperCasePlainText);
                String encryptedText = Arrays.toString(planTextAsArray);
                System.out.println("Din krypterede tekst er: " + encryptedText);
            }
            if (lowerCaseAnswer.equals("d")) {
                System.out.println("""
                        Du har valgt at en dekode en tekst.
                        Skriv din talrække her:""");
                String cipherText = scanner.nextLine();
                int[] cipherTextAsArray = splitNumbersIntoArray(cipherText);
                String decryptedText = listOfNumbersToText(cipherTextAsArray);
                System.out.println("Din tekst er: " + decryptedText);
            }
        }
        if (answer.equals("2")) {
            System.out.println("""
                    Du har valgt at benytte Ceaser cipher
                    Ønsker du at enkode eller dekode?
                    Skriv e for at enkode
                    Skriv d for at dekode""");
            String answerEOrD = scanner.nextLine();
            //lav om til små bogstaver så der tages højde for at brugeren måske skriver store bogstaver.
            String lowerCaseAnswer = answerEOrD.toLowerCase();
            if (lowerCaseAnswer.equals("e")) {
                System.out.println("""
                        Du har valgt at enkode en tekst.
                        Skriv din tekst her:""");
                String plainText = scanner.nextLine();
                //Lav string om til store bogstaver, for at gøre programmet mere robust, i forhold til bruger input
                String upperCasePlainText = plainText.toUpperCase();
                System.out.println("Hvilket tal skal koden forskydes med? (vælg tal mellem 1-29)");
                int shift = scanner.nextInt();
                String encryptedText = caesarEncrypt(upperCasePlainText, shift);
                System.out.println("Din krypterede tekst er " + encryptedText);

            }
            if (lowerCaseAnswer.equals("d")) {
                System.out.println("""
                        Du har valgt at en dekode en tekst.
                        Skriv din krypterede tekst her:""");
                String cipherText = scanner.nextLine();
                //Lav string om til store bogstaver, for at gøre programmet mere robust,
                String upperCaseCipherText = cipherText.toUpperCase();
                System.out.println("Hvilket tal er koden forskudt med? (vælg tal mellem 1-29)");
                int shift = scanner.nextInt();
                // træk shift fra 29 da shiftmetoden arbejder bedst med positive tal.
                // Det at ligge (29-shift) til er der samme som at trække shift fra en tal række
                shift = 29 - shift;
                String decryptedText = caesarDecrypt(upperCaseCipherText, shift);
                System.out.println("Din dekrypterede tekst er " + decryptedText);
            }
        }
        if (answer.equals("3")) {
            System.out.println("""
                    Du har valgt at benytte Vigenére cipher
                    Ønsker du at enkode eller dekode?
                    Skriv e for at enkode
                    Skriv d for at dekode""");
            String answerEOrD = scanner.nextLine();
            //lav om til små bogstaver så der tages højde for at brugeren måske skriver store bogstaver.
            String lowerCaseAnswer = answerEOrD.toLowerCase();
            if (lowerCaseAnswer.equals("e")) {
                System.out.println("""
                        Du har valgt at enkode en tekst.
                        Skriv din tekst her:""");
                String plainText = scanner.nextLine();
                //Lav string om til store bogstaver, for at gøre programmet mere robust, i forhold til bruger input
                String upperCasePlainText = plainText.toUpperCase();
                System.out.println("Hvad ønsker du som kodeord?");
                String codeWord = scanner.nextLine();
                String encryptedText = vigenèreEncrypt(upperCasePlainText, codeWord);
                System.out.println("Din krypterede tekst er " + encryptedText);

            }
            if (lowerCaseAnswer.equals("d")) {
                System.out.println("""
                        Du har valgt at en dekode en tekst.
                        Skriv din krypterede tekst her:""");
                String cipherText = scanner.nextLine();
                //Lav string om til store bogstaver, for at gøre programmet mere robust,
                String upperCaseCipherText = cipherText.toUpperCase();
                System.out.println("Hvad er kodeordet?");
                String codeWord = scanner.nextLine();
                String decryptedText = vigenèreDecrypt(upperCaseCipherText, codeWord);
                System.out.println("Din dekrypterede tekst er " + decryptedText);
            }
        }
        if (answer.equals("0")) {
            System.out.println("Tak for denne gang!");
        }
    }

    public static String vigenèreDecrypt(String upperCaseCipherText, String codeWord) {
        return "plainText";
    }

    public static String vigenèreEncrypt(String upperCasePlainText, String codeWord) {
            int[] numbers = textToListOfNumbers(upperCasePlainText);
            int[] codeWordNumbers  = textToListOfNumbers(codeWord);
            int []arraysCombind=new int[numbers.length];
            int codeWordPos = 0;
            for (int i = 0; i < numbers.length; i++) {
                int shift = codeWordNumbers[codeWordPos];
                codeWordPos++;
                if (codeWordPos >= codeWord.length()) {
                    codeWordPos = 0;
                }
                arraysCombind[i]=shiftNumber(numbers[i],shift);
            }
            String cipherText=listOfNumbersToText(arraysCombind);
            return cipherText;
    }

    public static int[] splitNumbersIntoArray(String numbersAsString) {
        String[] numbers = numbersAsString.split(",");
        int[] numberArray = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            int number = Integer.parseInt(numbers[i]);
            numberArray[i] = number;
        }
        return numberArray;
    }

    public static int[] textToListOfNumbers(String text) {
        int[] liste = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            int number = letterToNumber(letter);
            liste[i] = number;
        }
        return liste;
    }

    public static String listOfNumbersToText(int[] numbers) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            char letter = numberToLetter(number);
            text.append(letter);
        }
        return text.toString();
    }

    public static char numberToLetter(int number) {
        String alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        char letter = alphabet.charAt(number);
        return letter;
    }

    public static int letterToNumber(char letter) {
        String alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        int number = alphabet.indexOf(letter);
        return number;
    }

    public static String caesarEncrypt(String upperCasePlainText, int shift) {
        int[] numbers = textToListOfNumbers(upperCasePlainText);
        int[] shiftetNumbers = shiftListOfNumbers(numbers, shift);
        String cipherText = listOfNumbersToText(shiftetNumbers);
        return cipherText;
    }

    public static String caesarDecrypt(String upperCasePlainText, int shift) {
        int[] numbers = textToListOfNumbers(upperCasePlainText);
        int[] shiftetNumbers = shiftListOfNumbers(numbers, shift);
        String plainText = listOfNumbersToText(shiftetNumbers);
        return plainText;
    }

    public static int[] shiftListOfNumbers(int[] numbers, int shift) {
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            numbers[i] = shiftNumber(number, shift);
        }
        return numbers;
    }

    public static int shiftNumber(int number, int shift) {
        //number = ((number +shift) % 30);
        if (number == 0) {
            number = 0;
        } else {
            number = (number + shift);
            if (number < 0) {
                number = number + 29;
            }
            if (number > 29) {
                number = number - 29;
            }
        }
        return number;
    }
}












