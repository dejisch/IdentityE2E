package utilities;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleDetailsReader {

    public static List<String> extractRegistrations(String file) throws IOException {

        String readFileToString = org.apache.commons.io.FileUtils.readFileToString(new File(file), "UTF-8");
        Pattern pattern = Pattern.compile("\\b[A-Z]{2}\\d{2}\\s?[A-Z]{3}\\b");
        Matcher matcher = pattern.matcher(readFileToString);

        List<String> registrations = new ArrayList<>();
        while (matcher.find()) {
            registrations.add(matcher.group().replaceAll(" ", ""));
        }
        return registrations;
    }

    public static Map<String, VehicleDetails> readCarOutputFile(String fileName) throws IOException {

        InputStream input = VehicleDetailsReader.class.getClassLoader().getResourceAsStream("testData/" + fileName);

        if (input == null)
        {
            throw new FileNotFoundException("File with name'" + fileName + "' is not found.");
        }

        Map<String, VehicleDetails> carDetailsMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8)))
        {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null)
            {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length >= 4)
                {
                    String make = parts[0].trim();
                    String model = parts[1].trim();
                    String color = parts[2].trim();
                    try {
                        int year = Integer.parseInt(parts[3].trim());
                        VehicleDetails details = new VehicleDetails(make, model, color, year);
                        carDetailsMap.put(make, details);
                    } catch (Exception e) {
                    }
                }
            }
        }

        return carDetailsMap;
    }
}