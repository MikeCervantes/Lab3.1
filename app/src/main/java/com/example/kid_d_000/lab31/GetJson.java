package com.example.kid_d_000.lab31;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kid_d_000 on 21/03/2018.
 */

public class GetJson extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Tepic,mx&appid=f6f3a17707ba5de0dbcd58e9e4c6fc4a");
            HttpURLConnection httpURLConnection = (HttpURLConnection)
                    url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            //JSONArray JA = new JSONArray(data);
            //for(int i =0 ;i <JA.length(); i++){
                /*JSONObject JO = new JSONObject(data);(JSONObject) JA.get(i)
                singleParsed = "City:" + JO.get("name") + "\n"+
                        "Country: "+JO.getJSONObject("sys").get("country")+
                        "Sky Status:" + JO.getJSONObject("weather").get("description") + "\n"+
                        "Temperature:" + JO.getJSONObject("main").get("temp") + "\n"+
                        "Pressure:" + JO.getJSONObject("main").get("pressure") + "\n"+
                        "Humidity:" + JO.getJSONObject("main").get("humidity") + "\n"+
                        "Visibility:" + JO.get("visibility") + "\n"+
                        "Wind Speed:" + JO.getJSONObject("wind").get("speed") + "\n";
                dataParsed = dataParsed + singleParsed +"\n" ;*/
            //}
            JSONObject JO = new JSONObject(data);
            JSONObject sys = JO.getJSONObject("sys");
            JSONObject main = JO.getJSONObject("main");
            JSONObject wind = JO.getJSONObject("wind");

            singleParsed = "City: " + JO.get("name") + "\n"+
                    "Country: "+ sys.get("country")+ "\n"+
                    "Temperature: " + main.get("temp") + "\n"+
                    "Pressure: " + main.get("pressure") + "\n"+
                    "Humidity: " + main.get("humidity") + "\n"+
                    "Wind Speed: " + wind.get("speed") + "\n";

            dataParsed = dataParsed + singleParsed +"\n" ;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.data.setText(this.dataParsed);
    }

}
