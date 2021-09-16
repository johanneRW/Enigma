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
            String nextAnswer = scanner.nextLine();
            if (nextAnswer.equals("e")) {
                System.out.println("""
                        Du har valgt at en kode en tekst.
                        Skriv den tekst her:""");
                String plaintext = scanner.nextLine();
                int[] planTextAsArray = textToListOfNumbers(plaintext);
                String encryptetText = Arrays.toString(planTextAsArray);
                System.out.println("Din talrække er: " + encryptetText);
            }
            if (nextAnswer.equals("d")) {
                System.out.println("""
                        Du har valgt at en dekode en tekst.
                        Skriv din tal række her:""");
                String ciphertext = scanner.nextLine();
                int[] ciphertextAsArray = splitNumbersIntoArray(ciphertext);
                String decryptetText = listOfNumbersToText(ciphertextAsArray);
                System.out.println("Din tekst er: " + decryptetText);
            }
        }
        if (answer.equals("2")) {
            System.out.println("""
                    Du har valgt at benytte Ceaser cipher
                    Ønsker du at enkode eller dekode?
                    Skriv e for at enkode
                    Skriv d for at dekode""");
            String nextAnswer = scanner.nextLine();
            if (nextAnswer.equals("e")) {
                System.out.println("""
                        Du har valgt at enkode en tekst.
                        Skriv din tekst her:""");
                String plaintext = scanner.nextLine();
                System.out.println("Hvilket tal skal koden forskydes med? (vælg tal mellem 1-29)");
                int shift = scanner.nextInt();
                String encryptettext = caesarEncrypt(plaintext, shift);
                System.out.println("Din krypterede tekst er " + encryptettext);

            }
            if (nextAnswer.equals("d")) {
                System.out.println("""
                        Du har valgt at en dekode en tekst.
                        Skriv din krypterede tekst her:""");
                String ciphertext = scanner.nextLine();
                System.out.println("Hvilket tal er koden forskudt med? (vælg tal mellem 1-29)");
                int shift = scanner.nextInt();
                shift = 29 - shift;
                String decryptetText = caesarDecrypt(ciphertext, shift);
                System.out.println("Din dekrypterede tekst er " + decryptetText);
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
        String alfabetet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        char letter = alfabetet.charAt(number);
        return letter;
    }

    public static int letterToNumber(char letter) {
        String alfabetet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        int number = alfabetet.indexOf(letter);
        return number;

    }

    public static String caesarEncrypt(String plaintext, int shift) {
        int[] numbers = textToListOfNumbers(plaintext);
        int[] shiftetNumbers = shiftListOfNumbers(numbers, shift);
        String ciphertext = listOfNumbersToText(shiftetNumbers);
        return ciphertext;
    }

    public static String caesarDecrypt(String ciphertext, int shift) {
        int[] numbers = textToListOfNumbers(ciphertext);
        int[] shiftetNumbers = shiftListOfNumbers(numbers, shift);
        String plaintext = listOfNumbersToText(shiftetNumbers);
        return plaintext;

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








