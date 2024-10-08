package main;

import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileIndexProcessor processor = new FileIndexProcessor();

        while(true) {
            System.out.println("Insert command (index, query, exit)");
            String input = scanner.nextLine();

            if(input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting application.");
                break;
            }
            switch(input) {
                case "index":
                    System.out.println("Enter absolute path of file/directory to index: ");
                    String path = scanner.nextLine();
                    File file = new File(path);
                    if(file.exists()) {
                        try {
                            processor.indexFile(file);
                            System.out.println("File successfully indexed.");
                        } catch(Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    } else {
                        System.out.println("File not found.");
                    }
                    break;

                case "query":
                    System.out.println("Enter word for search (not case sensitive): ");
                    String search = scanner.nextLine();
                    Set<File> res = processor.query(search);
                    if(res.isEmpty()) {
                        System.out.println("No files containing the specified word were found.");
                    } else {
                        System.out.println("Files containing the word \"" + search + "\": ");
                        for(File found : res) {
                            System.out.println(found.getName());
                        }
                    }
                    break;

                default:
                    System.out.println("Unknown command.");
                    break;
            }
        }
        scanner.close();
    }
}