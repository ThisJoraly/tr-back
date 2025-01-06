package com.danilscheglov.transport.service;

import com.danilscheglov.transport.model.Operator;
import com.danilscheglov.transport.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperatorService {
    private final OperatorRepository operatorRepository;

    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }

    public Optional<Operator> getOperatorById(Long id) {
        return operatorRepository.findById(id);
    }

    public Operator saveOperator(Operator operator) {
        return operatorRepository.save(operator);
    }

    public void deleteOperator(Long id) {
        operatorRepository.deleteById(id);
    }
}
