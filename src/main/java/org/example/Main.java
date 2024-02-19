package org.example;

import java.util.Scanner;

public class Main {

    private static final char[] alphabet = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            System.out.println("Pasirinkite veiksma: 1 - Šifruoti, 2 - Dešifruoti, 3 - Šifruoti (ASCII), 4 - Dešifruoti (ASCII), 0 - Baigti");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    encryptData(scanner);
                    break;
                case 2:
                    decryptOperation(scanner);
                    break;
                case 3:
                    encryptASCII(scanner);
                    break;
                case 4:
                    decryptASCII(scanner);
                    break;
                case 0:
                    continueRunning = false;
                    break;
                default:
                    System.out.println("Netinkamas pasirinkimas.");
                    break;
            }
        }

        scanner.close();
    }

    public static void encryptData(Scanner scanner) {
        System.out.println("Įveskite pradinį tekstą: ");
        String inputText = scanner.nextLine();
        System.out.println("Įveskite poslinkį: ");
        int posl = scanner.nextInt();
        scanner.nextLine();

        String encryptedText = encryptCaesar(inputText, posl);
        System.out.println("Užšifruotas tekstas: " + encryptedText);
    }

    public static void decryptOperation(Scanner scanner) {
        System.out.println("Įveskite užšifruotą tekstą: ");
        String encryptedText = scanner.nextLine();
        System.out.println("Įveskite poslinkį: ");
        int posl = scanner.nextInt();
        scanner.nextLine();

        String decryptedText = decryptCaesar(encryptedText, posl);
        System.out.println("Atšifruotas tekstas: " + decryptedText);
    }

    public static void encryptASCII(Scanner scanner) {
        System.out.println("Įveskite pradinį tekstą: ");
        String inputText = scanner.nextLine();
        System.out.println("Įveskite poslinkį: ");
        int shift = scanner.nextInt();
        scanner.nextLine();

        String encryptedText = encryptWithASCII(inputText, shift);
        System.out.println("Užšifruotas tekstas (ASCII): " + encryptedText);
    }

    public static void decryptASCII(Scanner scanner) {
        System.out.println("Įveskite užšifruotą tekstą: ");
        String encryptedText = scanner.nextLine();
        System.out.println("Įveskite poslinkį: ");
        int shift = scanner.nextInt();
        scanner.nextLine();

        String decryptedText = decryptWithASCII(encryptedText, shift);
        System.out.println("Atšifruotas tekstas (ASCII): " + decryptedText);
    }

    public static String encryptCaesar(String inputText, int posl) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < inputText.length(); i++) {
            char currentChar = inputText.charAt(i);
            int index = indexOf(currentChar);

            if (index != -1) {
                encryptedText.append(alphabet[(index + posl) % alphabet.length]);
            } else {
                encryptedText.append(currentChar);
            }
        }

        return encryptedText.toString();
    }

    public static String decryptCaesar(String encryptedText, int posl) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char currentChar = encryptedText.charAt(i);
            int index = indexOf(currentChar);

            if (index != -1) {
                int newPos = (index - posl) % alphabet.length;
                if (newPos < 0) {
                    newPos += alphabet.length;
                }
                decryptedText.append(alphabet[newPos]);
            } else {
                decryptedText.append(currentChar);
            }
        }

        return decryptedText.toString();
    }

    private static int indexOf(char c) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public static String encryptWithASCII(String inputText, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < inputText.length(); i++) {
            char currentChar = inputText.charAt(i);
            int encryptedChar = currentChar + shift;

            if (encryptedChar > 126) {
                encryptedChar = 32 + (encryptedChar % 127);
            } else if (encryptedChar < 32) {
                encryptedChar = 127 - (32 % encryptedChar);
            }

            encryptedText.append((char) encryptedChar);
        }

        return encryptedText.toString();
    }

    public static String decryptWithASCII(String encryptedText, int shift) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char currentChar = encryptedText.charAt(i);
            int decryptedChar = currentChar - shift;

            if (decryptedChar > 126) {
                decryptedChar = 32 + (decryptedChar % 127);
            } else if (decryptedChar < 32) {
                decryptedChar = 127 - (32 % decryptedChar);
            }

            decryptedText.append((char) decryptedChar);
        }

        return decryptedText.toString();
    }
}
