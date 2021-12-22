package pl.wasko.internships.HotelManagmentSystem.Exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Getter
@Setter
public class ErrorList  extends Exception{
    private List<Error> errorList;

    public ErrorList(List<Error> errorList)
    {
        this.errorList=errorList;
    }
}
