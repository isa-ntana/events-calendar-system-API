package br.com.zup.events_calendar.controllers;

import br.com.zup.events_calendar.controllers.dtos.DataEventDTO;
import br.com.zup.events_calendar.service.EventService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class SearchesController {
    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findEventByID(@PathVariable String id){
            DataEventDTO dataEventDTO = eventService.getEvents().stream().filter
                    (event -> event.getEvent().getId().equals(id)).findFirst().get();
            return ResponseEntity.status(302).body(dataEventDTO);
    }

    @GetMapping
    public ResponseEntity<?> findEvents(
            @RequestParam(name = "days", required = false) Integer days,
            @RequestParam(name = "activeEvent", required = false) Boolean activeEvent) {

        List<DataEventDTO> events = eventService.getEvents();

        if (days != null) {
            if (days > 0) {
                events = eventService.pushEventsInNextDays(days, events);
            } else {
                events = events.stream()
                        .filter(eventDTO -> eventDTO.getDateBegin().isBefore(LocalDate.now()))
                        .collect(Collectors.toList());
            }
        }

        if (activeEvent != null) {
            events = events.stream()
                    .filter(eventDTO -> eventDTO.getEvent().isActiveEvent() == activeEvent)
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(events);
    }

}
