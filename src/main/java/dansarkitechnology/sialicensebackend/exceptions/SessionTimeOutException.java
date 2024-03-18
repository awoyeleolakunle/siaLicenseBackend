package dansarkitechnology.sialicensebackend.exceptions;

public class SessionTimeOutException extends RuntimeException {

    public SessionTimeOutException(String message){
        super(message);
    }
}
