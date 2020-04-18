import calculation.Calculator;
import calculation.CalculatorImpl;
import file.CalculationFileLineReader;
import file.CustomFileLineReader;
import org.apache.commons.io.LineIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        if(args.length != 1) {
            logger.error("Please provide parameters: file path.");
            return;
        }
        final String filePath = args[0];
        if(!fileExists(filePath)) {
            logger.error("File does not exist.");
            return;
        }
        final CustomFileLineReader calculationFileReader = new CalculationFileLineReader();
        final Calculator calculator = new CalculatorImpl();
        int lineIndex = 1;
        try {
            BigDecimal baseValue = new BigDecimal(calculationFileReader.readLine(CalculationFileLineReader.LINE_LAST, filePath).split(" ")[1]);
            final LineIterator lineIterator = calculationFileReader.readFileAsLineIterator(filePath);
            while(lineIterator.hasNext()) {
                String operation = lineIterator.next();
                baseValue = calculator.performOperation(baseValue, operation);
                lineIndex++;
            }
            logger.info(String.format("Calculation outcome: %s", baseValue.toString()));
        } catch (IllegalArgumentException | ArithmeticException | ArrayIndexOutOfBoundsException exception) {
            logger.error(String.format("Execution stopped. Reason: %s at line: %s", exception.getMessage(), lineIndex));
        } catch (IOException ioException) {
            logger.error(String.format("Execution stopped. Reason: %s", ioException.getMessage()));
        }
    }

    public static boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }
}
