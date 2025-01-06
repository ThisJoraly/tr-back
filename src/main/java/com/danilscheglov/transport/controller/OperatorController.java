package com.danilscheglov.transport.controller;

import com.danilscheglov.transport.model.Operator;
import com.danilscheglov.transport.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
@RequiredArgsConstructor
public class OperatorController {
    private final OperatorService operatorService;

    @GetMapping
    public List<Operator> getAllOperators() {
        return operatorService.getAllOperators();
    }

    @GetMapping("/{id}")
    public Operator getOperatorById(@PathVariable Long id) {
        return operatorService.getOperatorById(id).orElse(null);
    }

    @PostMapping
    public Operator createOperator(@RequestBody Operator operator) {
        return operatorService.saveOperator(operator);
    }

    @PutMapping("/{id}")
    public Operator updateOperator(@PathVariable Long id, @RequestBody Operator operator) {
        operator.setOperatorId(id);
        return operatorService.saveOperator(operator);
    }

    @DeleteMapping("/{id}")
    public void deleteOperator(@PathVariable Long id) {
        operatorService.deleteOperator(id);
    }
}
