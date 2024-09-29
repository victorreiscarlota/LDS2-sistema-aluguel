package aracar.Exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(String field, String issue) {
        super(String.format("Requisição inválida: O campo '%s' está incorreto. Motivo: %s", field, issue));
    }
}
