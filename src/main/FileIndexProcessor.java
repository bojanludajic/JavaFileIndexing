package main;

import java.io.*;
import java.util.*;

public class FileIndexProcessor {

    private Map<String, Set<File>> index = new HashMap<>();
    private final Tokenizer tokenizer = (line) -> Arrays.asList(line.split("\\W+"));

    public void indexFile(File file) throws IOException {
        if(file.isDirectory()) {
            indexDirectory(file);
            return;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                List<String> words = tokenizer.tokenize(line);
                for(String word: words) {
                    index.computeIfAbsent(word.toLowerCase(), f -> new HashSet<>()).add(file);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void indexDirectory(File dir) throws IOException {
        File[] files = dir.listFiles();
        if(files != null) {
            for (File file : files) {
                if(file.isDirectory()) {
                    indexDirectory(file);
                } else {
                    indexFile(file);
                }
            }
        }
    }

    public Set<File> query(String search) {
        return index.getOrDefault(search.toLowerCase(), Collections.emptySet());
    }

    public Map<String, Set<File>> getIndex() {
        return index;
    }
}
