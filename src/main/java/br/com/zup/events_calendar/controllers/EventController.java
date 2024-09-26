package br.com.zup.events_calendar.controllers;

import br.com.zup.events_calendar.controllers.dtos.DataEventDTO;
import br.com.zup.events_calendar.controllers.dtos.EventDTO;
import br.com.zup.events_calendar.service.EventService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/event")
public class EventController { //funções no homem post
    @Autowired
    private EventService eventService;
    private SearchesController searchesController;

    @PostMapping
    public ResponseEntity<?> addEvent(@Valid @RequestBody DataEventDTO dataEvent){
        try{
            DataEventDTO dataEventDTO = eventService.saveDataEvent(dataEvent);
            return ResponseEntity.status(201).body(dataEventDTO);
        } catch (RuntimeException e){
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteEventByID(@PathVariable @RequestParam(name = "id") String id) {
        DataEventDTO dataEventDTO = eventService.deleteEvent(id);
        return ResponseEntity.status(204).body(dataEventDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateEventActiveStatus(@RequestBody EventDTO eventDTO, @PathVariable String id) {

        Optional<DataEventDTO> dataEventOptional = eventService.getEvents().stream().filter
                (event -> event.getEvent().getId().equals(id)).findFirst();

        if (dataEventOptional.isEmpty()) {
            return ResponseEntity.status(400).body(Map.of("message", "event not found"));
        }

        DataEventDTO dataEvent = dataEventOptional.get();
        EventDTO eventOnList = dataEvent.getEvent();

        if (eventDTO.isActiveEvent()) {
            eventOnList.setActiveEvent(eventDTO.isActiveEvent());
        }

        return ResponseEntity.ok(eventOnList);
    }


}
