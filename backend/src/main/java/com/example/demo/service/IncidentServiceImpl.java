package com.example.demo.service.impl;

import com.example.demo.entity.Incident;
import com.example.demo.repository.IncidentRepository;
import com.example.demo.service.IncidentService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository repository;

    public IncidentServiceImpl(IncidentRepository repository) {
        this.repository = repository;
    }

   @Override
@CacheEvict(value = "incidents", allEntries = true)
public Incident update(Long id, Incident incident) {

    Incident existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Incident not found"));

    existing.setTitle(
            incident.getTitle() != null ? incident.getTitle() : existing.getTitle()
    );

    existing.setDescription(
            incident.getDescription() != null ? incident.getDescription() : existing.getDescription()
    );

    existing.setSeverity(
            incident.getSeverity() != null ? incident.getSeverity() : existing.getSeverity()
    );

    existing.setStatus(
            incident.getStatus() != null ? incident.getStatus() : existing.getStatus()
    );

    return repository.save(existing);
}

    @Override
    @Cacheable("incidents")
    public List<Incident> getAllIncidents() {
        return repository.findAll();
    }

    @Override
    public Incident getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incident not found"));
    }

    @Override
    @CacheEvict(value = "incidents", allEntries = true)
    public Incident createIncident(Incident incident) {
        return repository.save(incident);
    }

    @Override
    @CacheEvict(value = "incidents", allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }
}




