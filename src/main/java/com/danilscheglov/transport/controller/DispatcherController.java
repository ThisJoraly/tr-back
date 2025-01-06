package com.danilscheglov.transport.controller;

import com.danilscheglov.transport.model.Dispatcher;
import com.danilscheglov.transport.service.DispatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispatchers")
@RequiredArgsConstructor
public class DispatcherController {
    private final DispatcherService dispatcherService;

    @GetMapping
    public List<Dispatcher> getAllDispatchers() {
        return dispatcherService.getAllDispatchers();
    }

    @GetMapping("/{id}")
    public Dispatcher getDispatcherById(@PathVariable Long id) {
        return dispatcherService.getDispatcherById(id).orElse(null);
    }

    @PostMapping
    public Dispatcher createDispatcher(@RequestBody Dispatcher dispatcher) {
        return dispatcherService.saveDispatcher(dispatcher);
    }

    @PutMapping("/{id}")
    public Dispatcher updateDispatcher(@PathVariable Long id, @RequestBody Dispatcher dispatcher) {
        dispatcher.setDispatcherId(id);
        return dispatcherService.saveDispatcher(dispatcher);
    }

    @DeleteMapping("/{id}")
    public void deleteDispatcher(@PathVariable Long id) {
        dispatcherService.deleteDispatcher(id);
    }
}
