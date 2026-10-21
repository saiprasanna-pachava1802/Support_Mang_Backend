
package com.supportmanagement.controller;

import com.supportmanagement.dto.AnalyticsResponse;
import com.supportmanagement.service.AnalyticsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService service;

    public AnalyticsController(AnalyticsService service) {
        this.service = service;
    }

    @GetMapping
    public AnalyticsResponse getAnalytics(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fromDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate toDate,

            @RequestParam(defaultValue = "ALL")
            String personType,

            @RequestParam(required = false)
            Long personId) {

        return service.getAnalytics(
                fromDate, toDate, personType, personId);
    }
}
