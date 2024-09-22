import java.io.*;
import java.util.*;

public class FileIndexProcessor {

    public Map<String, Set<File>> index = new HashMap<>();
    private final Tokenizer tokenizer = (line) -> Arrays.asList(line.split("\\W+"));

    public void indexFile(File file) throws IOException {
        if(file.isDirectory()) {
            indexDirectory(file);
            return;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                List<String> lineContent = tokenizer.tokenize(line);
                for(String word: lineContent) {
                    index.computeIfAbsent(word.toLowerCase(), f -> new HashSet<>()).add(file);
                }
            }
        }
    }

    private void indexDirectory(File dir) throws IOException {
        File[] files = dir.listFiles();
        if(files != null) {
            String line;
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
        return index.getOrDefault(search.toLowerCase(), new HashSet<>());
    }
}
