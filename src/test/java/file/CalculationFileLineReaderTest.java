package file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationFileLineReaderTest {
    private static final String FILE_INVALID_PATH = "wrong-path.txt";
    private static final URL FILE_NO_OPERATIONS_URL = CalculationFileLineReaderTest.class.getClassLoader().getResource("file_no-operations.txt");
    private static final URL FILE_VALID_OPERATIONS_URL = CalculationFileLineReaderTest.class.getClassLoader().getResource("file_multiple-operations.txt");

    @Test
    public void readLine_NoFileExists_ThrowsIOException() throws IOException {
        final CustomFileLineReader lineReader = new CalculationFileLineReader();
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            lineReader.readLine(0, FILE_INVALID_PATH);
        });
    }

    @Test
    public void readLine_InvalidLineNumber_ThrowsIllegalArgumentException() throws IOException {
        final CustomFileLineReader lineReader = new CalculationFileLineReader();
        assert FILE_NO_OPERATIONS_URL != null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            lineReader.readLine(-1, FILE_NO_OPERATIONS_URL.getPath());
        });
    }

    @Test
    public void readLine_NoOperationPresent_Success() throws IOException {
        final CustomFileLineReader lineReader = new CalculationFileLineReader();
        assert FILE_NO_OPERATIONS_URL != null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            lineReader.readLine(5, FILE_NO_OPERATIONS_URL.getPath());
        });
    }

    @Test
    public void readLine_LastLineProvided_Success() throws IOException {
        final CustomFileLineReader lineReader = new CalculationFileLineReader();
        assert FILE_VALID_OPERATIONS_URL != null;
        final FileReader file = new FileReader(FILE_VALID_OPERATIONS_URL.getFile());
        final BufferedReader input = new BufferedReader(file);
        String last = "", line;
        while ((line = input.readLine()) != null) last = line;
        assertEquals(
                last, lineReader.readLine(CalculationFileLineReader.LINE_LAST, FILE_VALID_OPERATIONS_URL.getPath())
        );

    }

    @Test
    public void readLine_ValidOperations_Success() throws IOException {
        final CustomFileLineReader lineReader = new CalculationFileLineReader();
        int lineIndex = 0;
        assert FILE_VALID_OPERATIONS_URL != null;
        final FileReader file = new FileReader(FILE_VALID_OPERATIONS_URL.getFile());
        final BufferedReader read = new BufferedReader(file);
        String line = null;
        while ((line = read.readLine()) != null) {
            assertEquals(line, lineReader.readLine(lineIndex, FILE_VALID_OPERATIONS_URL.getPath()));
            lineIndex++;
        }
    }
}
