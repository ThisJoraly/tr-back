package com.danilscheglov.transport.service;

import com.danilscheglov.transport.model.Dispatcher;
import com.danilscheglov.transport.repository.DispatcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DispatcherService {
    private final DispatcherRepository dispatcherRepository;

    public List<Dispatcher> getAllDispatchers() {
        return dispatcherRepository.findAll();
    }

    public Optional<Dispatcher> getDispatcherById(Long id) {
        return dispatcherRepository.findById(id);
    }

    public Dispatcher saveDispatcher(Dispatcher dispatcher) {
        return dispatcherRepository.save(dispatcher);
    }

    public void deleteDispatcher(Long id) {
        dispatcherRepository.deleteById(id);
    }
}
