package br.com.zup.events_calendar.controllers.dtos;

import jakarta.validation.constraints.NotBlank;

import java.sql.*;

public class DateDTO {
    @NotBlank(message = "Campo obrigatório")
    private Date dateBegin;

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }
}
