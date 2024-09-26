package br.com.zup.events_calendar.service;

import br.com.zup.events_calendar.controllers.dtos.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService { //utilidade, o que faz
    private List<DataEventDTO> dataEvents = new ArrayList<>();

    public List<DataEventDTO> getEvents(){ return dataEvents; }

    public Optional<DataEventDTO> findEvent(String id){
        return dataEvents.stream().filter(DataEventDTO -> DataEventDTO.getEvent().getId()
                        .equalsIgnoreCase(id)).findFirst();
    }

    public DataEventDTO saveDataEvent(@Valid DataEventDTO dataEvent) {
        Optional<DataEventDTO> dataEventOptional = findEvent(dataEvent.getEvent().getId());
        if (dataEventOptional.isPresent()) throw new RuntimeException("Event already exists");
        DataEventDTO newDataEventDTO = new DataEventDTO();
        newDataEventDTO.setDateBegin(dataEvent.getDateBegin());
        newDataEventDTO.setDateEnd(dataEvent.getDateEnd());
        newDataEventDTO.setTimeBegin(dataEvent.getTimeBegin());
        newDataEventDTO.setTimeEnd(dataEvent.getTimeEnd());

        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(dataEvent.getEvent().getId());
        eventDTO.setNameEvent(dataEvent.getEvent().getNameEvent());
        eventDTO.setDescription(dataEvent.getEvent().getDescription());
        eventDTO.setActiveEvent(dataEvent.getEvent().isActiveEvent());
        eventDTO.setPriority(dataEvent.getEvent().getPriority());

        newDataEventDTO.setEvent(eventDTO);

        dataEvents.add(newDataEventDTO);
        return newDataEventDTO;
    }

    public DataEventDTO deleteEvent(@RequestParam(name = "id") String id) {
        dataEvents = dataEvents.stream().filter
                (DataEventDTO -> !DataEventDTO.getEvent().getId().equals(id))
                .collect(Collectors.toList());
        return null;
    }

    public List<DataEventDTO> pushEventsInNextDays(int days, List<DataEventDTO> events) {
        LocalDate today = LocalDate.now();
        LocalDate targetDate = today.plusDays(days);

        return events.stream().filter
                        (eventDTO -> eventDTO.getDateBegin().isAfter(today) &&
                                     eventDTO.getDateBegin().isBefore(targetDate))
                                        .collect(Collectors.toList());
    }

    public static void selectionSort(EventDTO[] eventos) {
        int posicao_menor;
        EventDTO auxiliar;

        Map<String, Integer> prioridadeMap = Map.of(
                "Muito alta", 1,
                "Alta", 2,
                "Media", 3,
                "Baixa", 4
        );

        for (int indice = 0; indice < eventos.length; indice++) {
            posicao_menor = indice;

            for (int subIndice = indice + 1; subIndice < eventos.length; subIndice++) {
                if (prioridadeMap.get(eventos[subIndice].getPriority()) <
                        prioridadeMap.get(eventos[posicao_menor].getPriority())) {
                    posicao_menor = subIndice;
                }
            }

            auxiliar = eventos[posicao_menor];
            eventos[posicao_menor] = eventos[indice];
            eventos[indice] = auxiliar;
        }
    }

}
