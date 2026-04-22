package com.example.demo.controller;

import com.example.demo.entity.Incident;
import com.example.demo.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public List<Incident> getAll() {
        return incidentService.getAllIncidents();
    }

    @PostMapping
    public Incident create(@Valid @RequestBody Incident incident) {
        return incidentService.createIncident(incident);
    }

    @GetMapping("/{id}")
    public Incident getById(@PathVariable Long id) {
        return incidentService.getById(id);
    }

    @PutMapping("/{id}")
    public Incident update(@PathVariable Long id, @Valid @RequestBody Incident incident) {
        return incidentService.update(id, incident);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        incidentService.delete(id);
        return "Deleted successfully";
    }

    @GetMapping("/status/{status}")
    public List<Incident> getByStatus(@PathVariable String status) {
        return incidentService.getByStatus(status);
    }

    @GetMapping("/severity/{severity}")
    public List<Incident> getBySeverity(@PathVariable String severity) {
        return incidentService.getBySeverity(severity);
    }
}