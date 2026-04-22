package com.example.demo.service;

import com.example.demo.entity.Incident;
import com.example.demo.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository repository;

    public IncidentServiceImpl(IncidentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Incident createIncident(Incident incident) {
        return repository.save(incident);
    }

    @Override
    public List<Incident> getAllIncidents() {
        return repository.findAll();
    }

    @Override
    public Incident getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Incident update(Long id, Incident incident) {
        Incident existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setTitle(incident.getTitle());
            existing.setDescription(incident.getDescription());
            existing.setSeverity(incident.getSeverity());
            existing.setStatus(incident.getStatus());
            return repository.save(existing);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Incident> getByStatus(String status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<Incident> getBySeverity(String severity) {
        return repository.findBySeverity(severity);
    }
}