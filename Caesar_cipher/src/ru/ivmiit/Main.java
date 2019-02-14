package ru.ivmiit;

import java.util.Scanner;

/**
 * SPECIFICATION: https://en.wikipedia.org/wiki/Caesar_cipher
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose operation:" + "\n" + "1) Encrypt" + "\n" + "2) Decrypt");
        int operation = scanner.nextInt();
        switch (operation) {
            case 1: {
                System.out.println("add string");
                String input = scanner.next();
                System.out.println("add delta");
                int delta = scanner.nextInt();
                System.out.println("Result: " + encrypt(input, delta));
                break;
            }

            case 2: {
                System.out.println("add string");
                String input = scanner.next();
                System.out.println("add delta");
                int delta = scanner.nextInt();
                System.out.println("Result: " + decrypt(input, delta));
                break;
            }

            default: {
                System.out.println("incorrect command");
            }
        }
    }

    public static String encrypt(String word, int delta) {
        char[] letters = word.toCharArray();
        // check delta on correct
        if (delta > 25 || delta < -25) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < letters.length; i++) {
            // check letters on correct
            if (letters[i] > 122 || letters[i] < 65 || (letters[i] > 90 && letters[i] < 97)) {
                throw new IllegalArgumentException();
            }
            // encrypting with register save
            if (letters[i] <= 'Z') {
                letters[i] += delta;
                if (letters[i] >= 'Z') {
                    letters[i] -= 26;
                } else if (letters[i] < 'A') {
                    letters[i] += 26;
                }
            } else if (letters[i] >= 'a') {
                letters[i] += delta;
                if (letters[i] >= 'z') {
                    letters[i] -= 26;
                } else if (letters[i] < 'a') {
                    letters[i] += 26;
                }
            }
        }
        return String.valueOf(letters);
    }

    public static String decrypt(String secretWord, int delta) {
        char[] letters = secretWord.toCharArray();

        if (delta > 25 || delta < -25) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > 122 || letters[i] < 65) {
                throw new IllegalArgumentException();
            }

            if (delta > 0) {
                if (letters[i] <= 'Z') {
                    letters[i] -= delta;
                    if (letters[i] < 'A') {
                        letters[i] += 26;
                    } else if (letters[i] > 'Z') {
                        letters[i] -= 26;
                    }
                } else if (letters[i] >= 'a') {
                    letters[i] -= delta;
                    if (letters[i] < 'a') {
                        letters[i] += 26;
                    } else if (letters[i] > 'z') {
                        letters[i] -= 26;
                    }
                }
            }
        }
        return String.valueOf(letters);
    }

}
