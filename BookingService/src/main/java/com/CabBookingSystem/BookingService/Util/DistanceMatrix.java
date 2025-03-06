package com.CabBookingSystem.BookingService.Util;

import java.util.HashMap;
import java.util.Map;

public class DistanceMatrix {
    private static final Map<String, Map<String, Double>> distances = new HashMap<>();

    static {

        distances.put("CityA", new HashMap<>());
        distances.put("CityB", new HashMap<>());
        distances.put("CityC", new HashMap<>());

        distances.get("CityA").put("CityB", 1400.0);
        distances.get("CityA").put("CityC", 2000.0);
        distances.get("CityB").put("CityC", 980.0);


        distances.get("CityB").put("CityA", 1400.0);
        distances.get("CityC").put("CityA", 2000.0);
        distances.get("CityC").put("CityB", 980.0);
    }

    public static double getDistance(String from, String to) {
        if (distances.containsKey(from) && distances.get(from).containsKey(to)) {
            return distances.get(from).get(to);
        }
        throw new IllegalArgumentException("Distance not found between locations");
    }
}