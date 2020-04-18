package calculation;

import java.math.BigDecimal;

/**
 * This interface imposes a validation and execution of operation on the objects
 * of each class that implements it. Operations are based on the {@code java.math.BigDecimal}
 * due to the precision issues.
 *
 * @author  Maciej Rudnicki
 * @see BigDecimal
 */
public interface Calculator {
    /**
     * Performs a specified operation on the BigDecimal object
     * @param   baseValue the object to be operated on.
     * @param   operation the object specifying the operation.
     * @return  a new BigDecimal object, the result of the executed operation.
     *
     * @throws NullPointerException if the specified objects are null
     * @throws IllegalArgumentException if the specified operation is not valid
     */
    BigDecimal performOperation(BigDecimal baseValue, String operation);

    /**
     * Checks if the operation is valid with specified rules
     * @param   operation the object specifying the operation.
     * @return  a boolean value determining the validity of the operation
     */
    boolean isOperationValid(String operation);
}
