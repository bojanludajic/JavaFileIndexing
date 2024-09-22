import java.util.List;

@FunctionalInterface
public interface Tokenizer {
    public List<String> tokenize(String line);
}
