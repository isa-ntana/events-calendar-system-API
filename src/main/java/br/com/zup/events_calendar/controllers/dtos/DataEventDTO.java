package br.com.zup.events_calendar.controllers.dtos;

import jakarta.validation.constraints.*;

import java.time.*;

public class DataEventDTO { //características
    @NotNull(message = "O campo é obrigatório.")
    private LocalDate dateBegin;

    @NotNull(message = "O campo é obrigatório.")
    private LocalDate dateEnd;

    @NotNull(message = "O campo é obrigatório.")
    private LocalTime timeBegin;

    @NotNull(message = "O campo é obrigatório.")
    private LocalTime timeEnd;

    @NotNull(message = "O campo é obrigatório.")
    private EventDTO event;

    public DataEventDTO() {} //padrão BEAN

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }
}
