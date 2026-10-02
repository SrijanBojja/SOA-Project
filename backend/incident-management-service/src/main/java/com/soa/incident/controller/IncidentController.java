package com.soa.incident.controller;

import com.soa.incident.entity.Incident;
import com.soa.incident.service.IncidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    // Create incident
    @PostMapping
    public ResponseEntity<Incident> createIncident(
            @RequestBody Incident incident) {

        Incident created = incidentService.createIncident(incident);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    // Get all incidents
    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {

        return ResponseEntity.ok(
                incidentService.getAllIncidents()
        );
    }

    // Get incident by ID
    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                incidentService.getIncidentById(id)
        );
    }

    // Update incident
    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(
            @PathVariable Long id,
            @RequestBody Incident incident) {

        return ResponseEntity.ok(
                incidentService.updateIncident(id, incident)
        );
    }

    // Delete incident
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(
            @PathVariable Long id) {

        incidentService.deleteIncident(id);

        return ResponseEntity.noContent().build();
    }
}