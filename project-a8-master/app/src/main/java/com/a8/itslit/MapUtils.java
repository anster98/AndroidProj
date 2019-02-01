package com.a8.itslit;
/*
This class was taken from: http://julien.gunnm.org/geek/programming/2015/09/13/how-to-get-geocoding-information-in-java-without-google-maps-api/
It uses the OpenStreetMaps geocoding webservice to take the address of any given location and turn it into latlng coordinates
that can be plotted on our map. It uses the library json-simple version 1.1.1
 */
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class MapUtils
{

//    public final static Logger log = Logger.getLogger("OpenStreetMapUtils");

    private static MapUtils instance = null;
    @SuppressWarnings("FieldCanBeLocal")
    private final JSONParser jsonParser;

    public MapUtils() {
        jsonParser = new JSONParser();
    }

    public static MapUtils getInstance()
    {
        if (instance == null)
        {
            instance = new MapUtils();
        }
        return instance;
    }

    private String getRequest(String url) throws Exception
    {
//        Log.d("000000", "BEFORE BUFF");
        final URL obj = new URL(url);
        final HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        if (con.getResponseCode() != 200)
        {
            return null;
        }

        Log.d("000000", "BEFORE BUFF");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public Map<String, Double> getCoordinates(String address)
    {
        Map<String, Double> res;
        StringBuffer query;
        String[] split = address.split(" ");
        String queryResult = null;

        query = new StringBuffer();
        res = new HashMap<>();

        query.append("http://nominatim.openstreetmap.org/search?q=");

        if (split.length == 0)
        {
            return null;
        }

        for (int i = 0; i < split.length; i++)
        {
            query.append(split[i]);
            if (i < (split.length - 1))
            {
                query.append("+");
            }
        }

        query.append("&format=json&addressdetails=1");

        Log.d("Query:" , query.toString());

        try
        {
            queryResult = getRequest(query.toString());
        } catch (Exception e)
        {
            Log.e("Error when trying to get data with the following query " , query.toString());
        }

        if (queryResult == null)
        {
            Log.d("QUERY NULL", "NULL");
            return null;
        }

        Object obj = JSONValue.parse(queryResult);
        Log.d("JSON Value: " ,"obj=" + obj);

        if (obj instanceof JSONArray)
        {
            JSONArray array = (JSONArray) obj;
            if (array.size() > 0)
            {
                JSONObject jsonObject = (JSONObject) array.get(0);

                String lon = (String) jsonObject.get("lon");
                String lat = (String) jsonObject.get("lat");
                Log.d("LONG" ,"lon=" + lon);
                Log.d("LAT" ,"lat=" + lat);
                res.put("lon", Double.parseDouble(lon));
                res.put("lat", Double.parseDouble(lat));

            }
        }
        return res;
    }
}