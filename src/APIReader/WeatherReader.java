package APIReader;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Scanner;

public class WeatherReader {
    public static void main(String[] args) throws IOException {
        //
        String cityName = "Vancouver";
        String countryID = "ca";

        Scanner reader = new Scanner(System.in);

        while (true) {
            System.out.println("Where u at");
            cityName = reader.next();

            String check = cityName.toLowerCase();
            if (check.equals("exit")) {
                break;
            }
            String lower = cityName.toLowerCase();
            String notFirst = lower.substring(1);
            String first = lower.substring(0,1);
            cityName = first.toUpperCase() + notFirst;


            System.out.println("whats ur country code in the format - ca,uk,ect");
            String IDcountry = reader.next();
            countryID = IDcountry.toLowerCase();

            if (countryID.equals("exit")) {
                break;
            }



            String line = null;

            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "," + countryID +
                        "&APPID=8487394f1f1f868599f984f84f962e3f");
                URLConnection urlConn = url.openConnection();
                InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
                BufferedReader buff = new BufferedReader(inStream);
                line = buff.readLine();
            } catch (FileNotFoundException e) {
                System.out.println("dude, why u tripping that place doesn't exist try again" + "\n");
            } catch (UnknownHostException e) {
                System.out.println("Its the 21 century get some wifi!");
            }

            if (line != null) {

                JSONObject data = new JSONObject(line);
                JSONArray weather = data.getJSONArray("weather");
                JSONObject mainWeather = weather.getJSONObject(0);
                System.out.println("\n" + mainWeather.get("description") + "\n");

            }
        }
    }
}
