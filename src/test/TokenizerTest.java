package test;

import main.Tokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

    private Tokenizer tokenizer;

    @BeforeEach
    void setTokenizer() {
        tokenizer = (line) -> Arrays.asList(line.split("\\W+"));
    }

    @Test
    void testTokenization() {
        assertEquals(Arrays.asList("Kotlin", "Java"), tokenizer.tokenize("Kotlin Java"));
    }

    @Test
    void testkTokenizationWithSpecialCharacters() {
        assertEquals(Arrays.asList("Kotlin", "Java"), tokenizer.tokenize("Kotlin, Java!"));
    }

}