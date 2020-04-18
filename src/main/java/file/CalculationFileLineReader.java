package file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

public class CalculationFileLineReader implements CustomFileLineReader {
    public static final int LINE_LAST = Integer.MAX_VALUE;

    @Override
    public String readLine(int line, String stringPath) throws IOException {
        if(line < 0) throw new IllegalArgumentException("Line number cannot be negative.");
        final LineIterator lineIterator = readFileAsLineIterator(stringPath);
        String readLine = "";
        int lineIndex = 0;
        while (lineIterator.hasNext() && lineIndex <= line) {
            readLine = lineIterator.nextLine();
            lineIndex++;
        }
        if(line != LINE_LAST && lineIndex != line + 1) throw new IllegalArgumentException(String.format("No line number %d available in file.", line));
        return readLine;
    }

    @Override
    public LineIterator readFileAsLineIterator(String path) throws IOException {
        final File file = new File(path);
        return FileUtils.lineIterator(file,"UTF-8");
    }
}
