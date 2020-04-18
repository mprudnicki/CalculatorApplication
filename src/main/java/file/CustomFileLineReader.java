package file;

import org.apache.commons.io.LineIterator;

import java.io.IOException;

/**
 * This interface imposes ways of read file operations on the objects
 * of each class that implements it.
 * Operation of the line-by-line read is bounded with {@code org.apache.commons.io.LineIterator}
 *
 * @author  Maciej Rudnicki
 * @see LineIterator
 */
public interface CustomFileLineReader {
    /**
     * Translates file into the LineIterator object
     * @param   path path to the specified file.
     * @return  a {@code org.apache.commons.io.LineIterator} describing the file contents.
     *
     * @throws IOException if the specified file doesn't exist
     */
    LineIterator readFileAsLineIterator(String path) throws IOException;

    /**
     * Reads a specified line from the file
     * @param   line the number of the line to be read
     * @param   path path to the specified file.
     * @return  a specified line (string) of the file.
     *
     * @throws IllegalArgumentException if the line number is negative
     * or is not available in file (exceeds the number of lines)
     * @throws IOException if the file doesn't exist
     */
    String readLine(int line, String path) throws IOException;
}
