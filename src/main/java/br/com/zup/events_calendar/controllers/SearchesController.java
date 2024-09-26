package br.com.zup.events_calendar.controllers;

import br.com.zup.events_calendar.controllers.dtos.*;
import br.com.zup.events_calendar.service.EventService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
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
                events = events.stream().filter(
                                eventDTO -> eventDTO.getDateBegin().isBefore(LocalDate.now()))
                        .collect(Collectors.toList());
            }
        }

        if (activeEvent != null) {
            events = events.stream().filter(
                            eventDTO -> eventDTO.getEvent().isActiveEvent() == activeEvent)
                    .collect(Collectors.toList());
        }

        Map<LocalDate, List<EventDTO>> eventsByDate = new HashMap<>();
        for (DataEventDTO dataEvent : events) {
            LocalDate date = dataEvent.getDateBegin();
            eventsByDate.computeIfAbsent(date, key -> new ArrayList<>()).add(dataEvent.getEvent());
        }

        List<EventsByDayDTO> responseList = new ArrayList<>();
        for (Map.Entry<LocalDate, List<EventDTO>> entry : eventsByDate.entrySet()) {
            EventDTO[] eventArray = entry.getValue().toArray(new EventDTO[0]);
            EventService.selectionSort(eventArray);

            EventsByDayDTO response = new EventsByDayDTO();
            response.setStartDate(entry.getKey());
            response.setEvents(Arrays.asList(eventArray));
            responseList.add(response);
        }

        return ResponseEntity.ok(responseList);
    }
}