package com.example.controller;

import com.example.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Roman Horilyi
 */
@RestController
public class MetricController {

    private MetricService metricService;

    @Autowired
    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @RequestMapping(value = "/metric", method = RequestMethod.GET)
    public Map getMetric() {
        return metricService.getFullMetric();
    }
}
