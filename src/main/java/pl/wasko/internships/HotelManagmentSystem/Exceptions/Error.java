package pl.wasko.internships.HotelManagmentSystem.Exceptions;

import java.util.List;

public class Error {
    private String field;
    private List<String> message;

    public Error(String field, List<String> message) {
        this.field = field;
        this.message = message;
    }
}
