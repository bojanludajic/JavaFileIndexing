package test;

import main.FileIndexProcessor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FileIndexProcessorTest {

    private FileIndexProcessor processor;
    private File testFile;

    @BeforeEach
    void setUp() throws IOException {
        processor = new FileIndexProcessor();
        testFile = Files.createTempFile("testfile", ".txt").toFile();
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("Example text for testing purposes.");
        }
    }

    @AfterEach
    void clearFile() {
        testFile.delete();
    }

    @Test
    void testFileIndexing() throws IOException {
        processor.indexFile(testFile);
        Set<File> files = processor.getIndex().get("text");
        assertTrue(files.contains(testFile));
    }

    @Test
    void testDirectoryIndexing() throws IOException {
        File testDir = Files.createTempDirectory("testdir").toFile();
        File file1 = new File(testDir, "file1.txt");
        File file2 = new File(testDir, "file2.txt");

        try(FileWriter writer = new FileWriter(file1)) {
            writer.write("Example text for testing purposes.");
        }
        try(FileWriter writer = new FileWriter(file2)) {
            writer.write("Second example text for testing purposes.");
        }
        try {
            processor.indexFile(testDir);
            Set<File> files = processor.getIndex().get("text");

            assertTrue(files.contains(file1));
            assertTrue(files.contains(file2));
        } finally {
            file2.delete();
            file1.delete();
            testDir.delete();
        }
    }

    @Test
    void testQuerying() throws IOException {
        processor.indexFile(testFile);
        Set<File> queryResult = processor.query("text");
        assertTrue(queryResult.contains(testFile));
    }

    @Test
    void testQueryingNothingFound() throws IOException {
        processor.indexFile(testFile);
        Set<File> queryResult = processor.query("java");
        assertTrue(queryResult.isEmpty());
    }

    @Test
    void testQueryingCaseInsensivity() throws IOException {
        processor.indexFile(testFile);
        Set<File> queryResult1 = processor.query("TEXT");
        Set<File> queryResult2 = processor.query("text");
        assertEquals(queryResult1, queryResult2);
    }

}