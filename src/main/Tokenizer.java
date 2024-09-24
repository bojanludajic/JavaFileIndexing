package main;

import java.util.List;

@FunctionalInterface
public interface Tokenizer {
    List<String> tokenize(String line);
}
