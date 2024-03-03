package dansarkitechnology.sialicensebackend.exceptions;

public class CacheNotFoundException extends RuntimeException {
    public CacheNotFoundException(String message) {
        super(message);
    }
}
