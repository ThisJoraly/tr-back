package com.danilscheglov.transport.repository;

import com.danilscheglov.transport.model.Dispatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatcherRepository extends JpaRepository<Dispatcher, Long> {
}
