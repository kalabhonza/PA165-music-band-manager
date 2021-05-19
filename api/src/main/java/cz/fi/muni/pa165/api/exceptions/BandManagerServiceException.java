package cz.fi.muni.pa165.api.exceptions;

/**
 * Exception thrown by service layer
 */
public class BandManagerServiceException extends RuntimeException {

    private ErrorStatus errorStatus;

    public BandManagerServiceException(String message) {
        super(message);
    }

    public BandManagerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BandManagerServiceException(Throwable ex, ErrorStatus status){
        super(ex);
        this.errorStatus = status;
    }

    public BandManagerServiceException(String message, ErrorStatus status){
        super(message);
        this.errorStatus = status;
    }
    public ErrorStatus getErrorStatus(){
        return errorStatus;
    }

}
