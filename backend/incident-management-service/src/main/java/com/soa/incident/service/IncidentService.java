package com.soa.incident.service;

import com.soa.incident.entity.Incident;
import com.soa.incident.event.IncidentEvent;
import com.soa.incident.kafka.IncidentEventProducer;
import com.soa.incident.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository repository;
    private final IncidentEventProducer eventProducer;

    public IncidentService(IncidentRepository repository,
                           IncidentEventProducer eventProducer) {
        this.repository = repository;
        this.eventProducer = eventProducer;
    }

    public Incident createIncident(Incident incident) {

        Incident createdIncident = repository.save(incident);

        IncidentEvent event = new IncidentEvent(
                "INCIDENT_CREATED",
                createdIncident.getId(),
                createdIncident.getTitle(),
                createdIncident.getSeverity(),
                createdIncident.getStatus(),
                createdIncident.getPriority(),
                createdIncident.getAssignedTo(),
                createdIncident.getCreatedBy(),
                LocalDateTime.now()
        );

        eventProducer.publishIncidentCreated(event);

        return createdIncident;
    }

    public List<Incident> getAllIncidents() {
        return repository.findAll();
    }

    public Incident getIncidentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Incident not found with id: " + id));
    }

    public Incident updateIncident(Long id, Incident updatedIncident) {

        Incident existing = getIncidentById(id);

        existing.setTitle(updatedIncident.getTitle());
        existing.setDescription(updatedIncident.getDescription());
        existing.setSeverity(updatedIncident.getSeverity());
        existing.setStatus(updatedIncident.getStatus());
        existing.setPriority(updatedIncident.getPriority());
        existing.setAssignedTo(updatedIncident.getAssignedTo());
        existing.setCreatedBy(updatedIncident.getCreatedBy());

        return repository.save(existing);
    }

    public void deleteIncident(Long id) {
        Incident existing = getIncidentById(id);
        repository.delete(existing);
    }
}