package ru.astinezh.audience.event_manager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.astinezh.audience.event_manager.api.EventApi;
import ru.astinezh.audience.event_manager.dto.Event;
import ru.astinezh.audience.event_manager.dto.Response;

@RestController
public class EventController implements EventApi {
    @Override
    public ResponseEntity<Response> _createEvent(Event event) {
        return ResponseEntity.ok(new Response());
    }
}
