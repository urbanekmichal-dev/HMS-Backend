package pl.wasko.internships.HotelManagmentSystem.Exceptions;

public class HmsException extends RuntimeException{
    public HmsException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public HmsException(String exMessage) {
        super(exMessage);
    }

}
