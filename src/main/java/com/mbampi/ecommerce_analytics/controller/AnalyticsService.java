package com.mbampi.ecommerce_analytics.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;

import com.mbampi.ecommerce_analytics.model.AccessLog;
import com.mbampi.ecommerce_analytics.model.CalendarDate;

public class AnalyticsService {

    private List<AccessLog> accessLogs;

    public void addAccess(AccessLog access) {
        if (this.accessLogs == null) {
            this.accessLogs = new LinkedList<>();
        }
        accessLogs.add(access);
    }

    public String getAllLogs() {
        if (this.accessLogs == null) {
            return "Empty access logs list";
        }

        StringBuilder allLogs = new StringBuilder();
        for (AccessLog log : accessLogs) {
            allLogs.append(log.toString());
            allLogs.append("\n");
        }

        return allLogs.toString();
    }

    public String getTopPages(String country, int topX) {
        boolean ignoreCountry = false;
        if (country.equals(""))
            ignoreCountry = true;

        Map<String, Integer> topAccessed = new HashMap<>();

        for (AccessLog access : this.accessLogs) {
            if (ignoreCountry || access.getCallerCountry().equals(country)) {
                String urlPath = access.getUrlPath();
                Integer numAccess = topAccessed.get(urlPath);
                if (numAccess == null)
                    topAccessed.put(urlPath, 1);
                else
                    topAccessed.put(urlPath, numAccess + 1);
            }
        }

        Map<String, Integer> sorted = topAccessed.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        Iterator<Entry<String, Integer>> sortedPages = sorted.entrySet().iterator();

        StringBuilder result = new StringBuilder();
        int i = 1;
        while (sortedPages.hasNext() && i <= topX) {
            Entry<String, Integer> access = sortedPages.next();
            result.append(i).append("- ").append(access.getKey()).append(" = ").append(access.getValue())
                    .append(" acessos").append("\n");
            i++;
        }
        return result.toString();
    }

    public String getHighestAccessVolume(int topX) {
        Map<String, Integer> dateVolume = new HashMap<>();
        for (AccessLog access : this.accessLogs) {
            String date = access.getCalendarDate().toString();
            Integer numAccess = dateVolume.get(date);
            if (numAccess == null)
                dateVolume.put(date, 1);
            else
                dateVolume.put(date, numAccess + 1);
        }

        Map<String, Integer> sorted = dateVolume.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        Iterator<Entry<String, Integer>> sortedDates = sorted.entrySet().iterator();

        StringBuilder result = new StringBuilder();
        int i = 1;
        while (sortedDates.hasNext() && i <= topX) {
            Entry<String, Integer> access = sortedDates.next();
            result.append(i).append("- ").append(access.getKey()).append(" = ").append(access.getValue())
                    .append(" acessos").append("\n");
            i++;
        }
        return result.toString();
    }

    public String getMostAndLeastAccessed(String date) {
        String mostAccessedPage = "";
        int mostAccessedPageCount = 0;
        String leastAccessedPage = "";
        int leastAccessedPageCount = Integer.MAX_VALUE;

        boolean ignoreDate = false;
        if (date.equals(""))
            ignoreDate = true;

        Map<String, Integer> pageVolume = new HashMap<>();
        for (AccessLog access : this.accessLogs) {
            String strDate = access.getCalendarDate().toString();
            if (ignoreDate || strDate.equals(date)) {
                String urlPath = access.getUrlPath();
                Integer numAccess = pageVolume.get(urlPath);
                if (numAccess == null)
                    pageVolume.put(urlPath, 1);
                else
                    pageVolume.put(urlPath, numAccess + 1);
            }
        }

        Iterator<Entry<String, Integer>> pages = pageVolume.entrySet().iterator();
        while (pages.hasNext()) {
            Entry<String, Integer> page = pages.next();
            int numAccess = page.getValue();
            String urlPath = page.getKey();
            if (numAccess > mostAccessedPageCount) {
                mostAccessedPageCount = numAccess;
                mostAccessedPage = urlPath;
            }
            if (numAccess < leastAccessedPageCount) {
                leastAccessedPageCount = numAccess;
                leastAccessedPage = urlPath;
            }
        }

        String result = "Most Accessed: " + mostAccessedPage + "(" + mostAccessedPageCount + ")\n";
        result += "Least Accessed: " + leastAccessedPage + "(" + leastAccessedPageCount + ")\n";
        return result;
    }

    public void initializeWithSamples() {
        this.addAccess(new AccessLog("/products|Brazil|1037825323259|DELETE"));
        this.addAccess(new AccessLog("/products|USA|1017825323259|DELETE"));
        this.addAccess(new AccessLog("/products|USA|1077825323259|GET"));
        this.addAccess(new AccessLog("/cart|China|1099825323250|POST"));
        this.addAccess(new AccessLog("/cart|Brazil|1107800323251|GET"));
        this.addAccess(new AccessLog("/products|China|10378234523259|GET"));
        this.addAccess(new AccessLog("/products|China|10370123323239|GET"));
        this.addAccess(new AccessLog("/products|Brazil|1037815323229|POST"));
        this.addAccess(new AccessLog("/cart|Brazil|10379234523259|DELETE"));
        this.addAccess(new AccessLog("/cart|China|10378234523259|GET"));
        this.addAccess(new AccessLog("/products|Brazil|2007825333259|POST"));
        this.addAccess(new AccessLog("/cart|Brazil|1237825323259|POST"));
        this.addAccess(new AccessLog("/products|China|10178123323239|GET"));
        this.addAccess(new AccessLog("/products|Brazil|1032825323229|POST"));
        this.addAccess(new AccessLog("/cart|Brazil|1077825323259|DELETE"));
        this.addAccess(new AccessLog("/cart|China|1037825323259|POST"));
        this.addAccess(new AccessLog("/products|Brazil|1077825323259|POST"));
        this.addAccess(new AccessLog("/cart|China|1037825323259|POST"));
        this.addAccess(new AccessLog("/products|Brazil|1037825323259|DELETE"));
        this.addAccess(new AccessLog("/products|USA|1017825323259|DELETE"));
        this.addAccess(new AccessLog("/products|USA|1077825323259|GET"));
        this.addAccess(new AccessLog("/cart|China|1099825323250|POST"));
        this.addAccess(new AccessLog("/cart|Brazil|1107800323251|GET"));
        this.addAccess(new AccessLog("/products|China|1037825323259|GET"));
        this.addAccess(new AccessLog("/products|China|10378123323239|PUT"));
        this.addAccess(new AccessLog("/products|USA|1037825323229|POST"));
        this.addAccess(new AccessLog("/cart|Brazil|10378234523259|DELETE"));
        this.addAccess(new AccessLog("/cart|China|1037825323259|GET"));
        this.addAccess(new AccessLog("/products|Brazil|1007825333259|POST"));
        this.addAccess(new AccessLog("/cart|Brazil|1037825323259|POST"));
        this.addAccess(new AccessLog("/products|China|10378123323239|GET"));
        this.addAccess(new AccessLog("/products|Brazil|1037825323229|POST"));
        this.addAccess(new AccessLog("/checkout|Brazil|1077825323259|DELETE"));
        this.addAccess(new AccessLog("/checkout|China|1037823323259|GET"));
        this.addAccess(new AccessLog("/checkout|Brazil|1007812333259|POST"));
        this.addAccess(new AccessLog("/checkout|Brazil|1037825323259|GET"));
        this.addAccess(new AccessLog("/cart|China|1037825323259|GET"));
        this.addAccess(new AccessLog("/products|Brazil|1007825333259|POST"));
        this.addAccess(new AccessLog("/cart|Brazil|1037825323259|POST"));
        this.addAccess(new AccessLog("/products|China|10378123323239|GET"));
        this.addAccess(new AccessLog("/products|Brazil|1037825323229|POST"));
        this.addAccess(new AccessLog("/checkout|Brazil|10378134523259|DELETE"));
        this.addAccess(new AccessLog("/checkout|China|1037823323259|GET"));
        this.addAccess(new AccessLog("/checkout|Brazil|1007812333259|POST"));
        this.addAccess(new AccessLog("/checkout|Brazil|1037825323259|DELETE"));
        this.addAccess(new AccessLog("/checkout|Brazil|1037825323259|POST"));
        this.addAccess(new AccessLog("/checkout|Brazil|1037825323259|POST"));
    }
}
