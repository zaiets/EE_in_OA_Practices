package Practice01.exceptions;

public class DatabaseException extends Exception {
    public DatabaseException() {    }

    public DatabaseException(String message) {
        super(message);
    }
}
