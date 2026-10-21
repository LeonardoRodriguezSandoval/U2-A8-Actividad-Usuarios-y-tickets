package mx.edu.utez.tickets.exception.customExceptions;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }
}
