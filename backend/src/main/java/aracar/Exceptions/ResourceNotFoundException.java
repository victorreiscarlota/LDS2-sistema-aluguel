package aracar.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("Recurso %s com ID %d não foi encontrado.", resourceName, id));
    }

    public ResourceNotFoundException(String resourceName, String attributeName, String attributeValue) {
        super(String.format("Recurso %s com %s = '%s' não foi encontrado.", resourceName, attributeName, attributeValue));
    }
}
