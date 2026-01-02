package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByOrderByModifiedAtDesc();
    List<Schedule> findAllByWriterOrderByModifiedAtDesc(String writer);
}
