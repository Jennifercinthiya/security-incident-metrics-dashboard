package com.example.demo.service;

import com.example.demo.entity.Incident;

import java.util.List;

public interface IncidentService {

    Incident createIncident(Incident incident);

    List<Incident> getAllIncidents();

    Incident getById(Long id);

    Incident update(Long id, Incident incident);

    void delete(Long id);

    List<Incident> getByStatus(String status);

    List<Incident> getBySeverity(String severity);
}