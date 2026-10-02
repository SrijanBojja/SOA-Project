package com.soa.incident.event;

import java.time.LocalDateTime;

public class IncidentEvent {

    private String eventType;
    private Long incidentId;
    private String title;
    private String severity;
    private String status;
    private String priority;
    private String assignedTo;
    private String createdBy;
    private LocalDateTime timestamp;

    public IncidentEvent() {
    }

    public IncidentEvent(String eventType, Long incidentId, String title,
                          String severity, String status, String priority,
                          String assignedTo, String createdBy,
                          LocalDateTime timestamp) {
        this.eventType = eventType;
        this.incidentId = incidentId;
        this.title = title;
        this.severity = severity;
        this.status = status;
        this.priority = priority;
        this.assignedTo = assignedTo;
        this.createdBy = createdBy;
        this.timestamp = timestamp;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(Long incidentId) {
        this.incidentId = incidentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}