import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Choose operation:" + "\n" +
                    "1) Generate new table" + "\n" +
                    "2) View table" + "\n" +
                    "3) Cipher string");
            int operation = scanner.nextInt();
            // Object for reading or writing json
            ObjectMapper mapper = new ObjectMapper();
            // table
            HashMap<Character, Character> cipher_table;
            switch (operation) {
                case 1:{
                    table_generation(mapper);
                    break;
                }

                case 2: {
                    cipher_table = mapper.readValue(new File("src/main/resources/JSON/table.json"), HashMap.class);
                    cipher_table.forEach((k,v) -> System.out.println("Symbol: " + k + " | " + " Cipher: " + v));
                    break;
                }

                case 3: {
                    cipher_table = mapper.readValue(new File("src/main/resources/JSON/table.json"), HashMap.class);
                    System.out.println(cipher_table.get('A'));
                    System.out.println(cipher_table.get('e'));
                    System.out.println("Add input string");
                    String input = scanner.next();
                    System.out.println(input + "- input string");
                    System.out.println("Cipher string is: " + cipher(input, cipher_table));
                    break;
                }

                default: {
                    System.err.println("Incorrect command");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String cipher(String value, HashMap<Character, Character> cipher_table) throws IOException {
        char[] input = value.toCharArray();
        for (int i = 0; i < input.length; i++) {
            if (input[i] == ' '){
                continue;
            } else {
                if (cipher_table.get(input[i]) != null){
                    input[i] = (char) cipher_table.get(input[i]);
                } else {
                    System.out.println("Я не понимать");
                }
            }
        }
        return String.valueOf(input);
    }

    public static void table_generation(ObjectMapper mapper) throws IOException {
        // Object for choose random symbol
        Random random = new Random();
        // Collection == table
        HashMap<Character, Character> cipher_table = new HashMap<>();
        // all ABC
        char[] dictionary = new char[52];
        // free indexes for shuffling
        ArrayList<Integer> indexes = new ArrayList<>(52);
        int count = 0;
        // add abc
        for (char i = 'A'; i <= 'Z'; i++) {
            dictionary[count] = i;
            dictionary[count + 26] = (char) (i + 32);
            indexes.add(count);
            indexes.add(count + 26);
            count++;
        }

        int index;
        int maxIndex = 26;

        for (char i = 'A'; i <= 'Z'; i++) {
            index = random.nextInt(maxIndex);

            if (index == maxIndex) maxIndex--;

            if (indexes.contains(index)) {
                cipher_table.put(i, dictionary[index]);
                cipher_table.put((char) (i + 32), dictionary[index + 26]);
                indexes.remove((Integer) index);
                indexes.remove((Integer) (index + 26));
            } else {
                i--;
            }
        }

        // write table in file
        mapper.writeValue(new File("src/main/resources/JSON/table.json"), cipher_table);
        // output in console
        for (Map.Entry<Character, Character> entry : cipher_table.entrySet()){
            System.out.println("Symbol: " + entry.getKey() + " | " + " Cipher: " + entry.getValue());
        }
    }
}
