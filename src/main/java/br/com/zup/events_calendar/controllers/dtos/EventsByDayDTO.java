package br.com.zup.events_calendar.controllers.dtos;

import java.time.LocalDate;
import java.util.*;

public class EventsByDayDTO {
    private LocalDate startDate;
    private List<EventDTO> events;

    public EventsByDayDTO() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }
}
