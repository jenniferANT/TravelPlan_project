package com.app.travelplan.utils;

import com.app.travelplan.model.entity.Places;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;

public class AppUtils {
    public static String getBaseUrl() {
        return "http://localhost:8081";
    }

    public static LocalTime[] getTimeInit() {
        return new LocalTime[]{LocalTime.of(0, 0),
                LocalTime.of(6, 0),
                LocalTime.of(8, 0),
                LocalTime.of(12, 0),
                LocalTime.of(13, 30),
                LocalTime.of(17, 30),
                LocalTime.of(18, 30),
                LocalTime.of(20, 0),
                LocalTime.of(23,59)
        };
    }
    // 0h    6h    8h     12h

    public static Double[] getLatitudeAndLongitude(String u) {
        URL url;
        try {
            url = new URL(u);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        double latitude = 0, longitude = 0;
        // Tạo đối tượng BufferedReader để đọc dữ liệu từ URL
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {

            // Đọc dữ liệu từ URL
            String line;
            while ((line = reader.readLine()) != null) {
                // Tìm kiếm chuỗi ký tự "@[vĩ độ],[kinh độ],[zoom]"
                int index = line.indexOf("@");
                if (index > 0) {
                    // Lấy vĩ độ và kinh độ từ chuỗi ký tự
                    int endIndex = line.indexOf(",", index);
                    if (endIndex == -1) {
                        endIndex = line.length();
                    }
                    latitude = Double.parseDouble(line.substring(index + 1, endIndex));

                    int secondCommaIndex = line.indexOf(",", endIndex + 1);
                    if (secondCommaIndex == -1) {
                        secondCommaIndex = line.length();
                    }
                    longitude = Double.parseDouble(line.substring(endIndex + 1, secondCommaIndex));
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Double[]{latitude, longitude};
    }

    public static double getGoTwoPlace(Places places1, Places places2) {
        return distance(
                places1.getAddress().getLatitude(),
                places2.getAddress().getLatitude(),
                places1.getAddress().getLongitude(),
                places2.getAddress().getLongitude());
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in km
     * @returns Distance in km
     * kinh do - vi do
     */
    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance) / 1000.0;
    }
}
