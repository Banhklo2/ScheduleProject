package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.*;
import com.example.scheduleproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성 API
    // POST /schedules
    // - 사용자가 입력한 일정 데이터를 받아 DB에 저장
    // - 저장된 일정 정보를 응답으로 반환 (201 created)
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleCreateResponse> create(@RequestBody ScheduleCreateRequest request) {
        ScheduleCreateResponse response = scheduleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 전체 일정 조회  API
    // GET /schedules
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetResponse>> getAll(
            @RequestParam(required = false) String writer
    ) {
        return ResponseEntity.ok(scheduleService.findAll(writer));
    }

    // 단건 일정 조회 API
    // GET ("/schedules/{scheduleId}")
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetResponse> getOne(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findOne(scheduleId));
    }

    // 일정 수정 API
    // PUT /schedules/{scheduleId}
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> update(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest request
    ) {
        return ResponseEntity.ok(scheduleService.update(scheduleId, request));
    }

    // 일정 삭제 API
    // DELETE /schedules/{scheduleId}
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleDeleteRequest request
    ) {
        scheduleService.delete(scheduleId, request);
        return ResponseEntity.noContent().build(); // 204
    }
}
