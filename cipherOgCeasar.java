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
            String lowerCaseAnswer=answerEOrD.toLowerCase();
            if (lowerCaseAnswer.equals("e")){
                System.out.println("""
                        Du har valgt at enkode en tekst.
                        Skriv den tekst her:""");
                String plainText = scanner.nextLine();
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
            String lowerCaseAnswer=answerEOrD.toLowerCase();
            if (lowerCaseAnswer.equals("e")) {
                System.out.println("""
                        Du har valgt at enkode en tekst.
                        Skriv din tekst her:""");
                String plainText = scanner.nextLine();
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
                String upperCaseCipherText=cipherText.toUpperCase();
                System.out.println("Hvilket tal er koden forskudt med? (vælg tal mellem 1-29)");
                int shift = scanner.nextInt();
                shift = 29 - shift;
                String decryptedText = caesarDecrypt(upperCaseCipherText, shift);
                System.out.println("Din dekrypterede tekst er " + decryptedText);
            }
        }
        if (answer.equals("0")) {
            System.out.println("Tak for denne gang!");
        }
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

    public static String caesarEncrypt(String plainText, int shift) {
        int[] numbers = textToListOfNumbers(plainText);
        int[] shiftetNumbers = shiftListOfNumbers(numbers, shift);
        String cipherText = listOfNumbersToText(shiftetNumbers);
        return cipherText;
    }

    public static String caesarDecrypt(String cipherText, int shift) {
        int[] numbers = textToListOfNumbers(cipherText);
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
        //number = ((number + (shift-1)) % 29)+1;
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










