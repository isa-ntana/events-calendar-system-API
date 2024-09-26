package br.com.zup.events_calendar.controllers.dtos;

import jakarta.validation.constraints.NotNull;

public class EventDTO {
    @NotNull(message = "O campo é obrigatório.")
    private String id;

    @NotNull(message = "O campo é obrigatório.")
    private String nameEvent;

    @NotNull(message = "O campo é obrigatório.")
    private String description;

    @NotNull(message = "O campo é obrigatório.")
    private boolean activeEvent;

    public EventDTO() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActiveEvent() {
        return activeEvent;
    }

    public void setActiveEvent(boolean activeEvent) {
        this.activeEvent = activeEvent;
    }
}
