package de.four.in.a.row;

/*
 * Illegal value exception class
 *
 * @author Martin Moors
 */
public class MyIllegalValueException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MyIllegalValueException(String err) {
        super(err);
    }
}
