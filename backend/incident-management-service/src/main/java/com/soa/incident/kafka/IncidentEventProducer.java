package com.soa.incident.kafka;

import com.soa.incident.event.IncidentEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class IncidentEventProducer {

    private static final String TOPIC = "incident-events";

    private final KafkaTemplate<String, IncidentEvent> kafkaTemplate;

    public IncidentEventProducer(KafkaTemplate<String, IncidentEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishIncidentCreated(IncidentEvent event) {
        kafkaTemplate.send(TOPIC, event.getIncidentId().toString(), event);
    }
}