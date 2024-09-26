package br.com.zup.events_calendar.controllers.dtos;

import java.util.*;

public class EventsByDayDTO {
    private Date startDate;
    private List<EventDTO> events;

    public EventsByDayDTO() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }
}
