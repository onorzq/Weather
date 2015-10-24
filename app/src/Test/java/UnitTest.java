import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UnitTest {

    @Test
    public void weatherApiTest() {
        String weatherApiKey = "a44ecdabbb773734";
        String baseUrl = "http://api.wunderground.com/api/";
        String forecastUrl = "/forecast/geolookup/conditions/q/";
        String locationUrl = "22202.json";
        JSONObject jsonResponse = new JSONObject();

        try {
            URL url = new URL(baseUrl + weatherApiKey + forecastUrl + locationUrl);

            System.out.println(url.toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            jsonResponse = new JSONObject(response.toString());
            System.out.println(response.toString());
//            assertEquals(0, jsonResponse.getString("response"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}