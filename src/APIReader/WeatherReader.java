package APIReader;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherReader {
    public static void main(String[] args) throws IOException {
        //
        String cityName = "Vancouver";
        String countryID = "ca";

        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "," + countryID +
                "&APPID=8487394f1f1f868599f984f84f962e3f");
        URLConnection urlConn = url.openConnection();
        InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
        BufferedReader buff = new BufferedReader(inStream);

        String line = buff.readLine();
        if (line != null) {
//            System.out.println(line);
//            line = buff.readLine();
            JSONObject data = new JSONObject(line);
            JSONArray weather = data.getJSONArray("weather");
            JSONObject mainWeather = weather.getJSONObject(0);
            System.out.println(mainWeather.get("description"));
//            System.out.println(data);

//            print(data)
        }


//        String s = "s";


    }
}
