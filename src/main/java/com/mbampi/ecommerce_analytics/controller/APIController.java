package com.mbampi.ecommerce_analytics.controller;

import javax.crypto.AEADBadTagException;

import com.mbampi.ecommerce_analytics.model.AccessLog;
import com.mbampi.ecommerce_analytics.model.CalendarDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * APIController
 */
@RestController
public class APIController {

    private AnalyticsService analyticsService;

    public APIController() {
        analyticsService = new AnalyticsService();
        analyticsService.initializeWithSamples();
    }

    @GetMapping(value = "/access")
    public ResponseEntity<String> getAccess() {
        String result = analyticsService.getAllLogs();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/top")
    public ResponseEntity<String> getTopPages(@RequestParam(value = "country", defaultValue = "") String country,
            @RequestParam(value = "num", defaultValue = "5") String num) {
        int topX = Integer.parseInt(num);
        System.out.println("/top  country=" + country + " num=" + num);
        String result = analyticsService.getTopPages(country, topX);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/access")
    public ResponseEntity<String> createAccess(@RequestBody String accessLog) {
        System.out.println("createAccess:" + accessLog);
        analyticsService.addAccess(new AccessLog(accessLog));
        return new ResponseEntity<>("Created", HttpStatus.OK);
    }

    @GetMapping(value = "/highestaccessvolume")
    public ResponseEntity<String> getHighestAccessVolume(@RequestParam(value = "num", defaultValue = "1") String num) {
        int topX = Integer.parseInt(num);
        System.out.println("getHighestAccessVolume: " + topX);
        String result = analyticsService.getHighestAccessVolume(topX);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/mostaandleastccessed")
    public ResponseEntity<String> getMostAndLeastAccessed(
            @RequestParam(value = "date", defaultValue = "") String date) {
        System.out.println("getMostAndLeastAccessed: " + date);
        String result = analyticsService.getMostAndLeastAccessed(date);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}