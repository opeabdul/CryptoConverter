package com.example.opeyemi.yourbitcoinconverter;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
 * Created by opeyemi on 10/29/2017.
 */
public class UtilityClass extends AppCompatActivity{


    public static final String[] currencies = {"NGN","GBP","USD","EUR","CNY","EGP","GHS","AED",
            "INR","KWD","QAR","SAR","ZAR","IDR","CAD","DZD","UGX","RUB","PHP","MYR"};

    private static final String[] currencyNames = {
            "Nigerian Naira","Pound sterling","United States dollar","Euro", "Chinese yuan",
            "Egyptian pound","Ghanaian cedi","U.A.E. dirham", "Indian rupee",
            "Kuwaiti dinar","Qatari riyal","Saudi riyal","South African rand", "Indonesian rupiah",
            "Canadian dollar","Algerian dinar","Ugandan shilling","Russian ruble",
            "Philippine piso","Malaysian ringgit"
    };

    private static final String LOG_TAG = UtilityClass.class.getSimpleName();

    /**
     * private constructor with no implementation to make sure
     * the Utility class cannot be instantiated
     */
    private UtilityClass() {

    }


    /**
     * Method to process the api url and return an arraylist of Developers
     * using other utility method
     * @param APIurl
     * @return
     */
    public static Card[] extractCryptoCards(String APIurl) {


        String jsonResponse = null;
        Card[] cards = null;

        try {
            URL url = makeURL(APIurl);
            jsonResponse = makeHttpRequest(url);
            if (jsonResponse != null) {
                cards = extractFromJSON(jsonResponse);
            } else {
                Log.e(LOG_TAG, "the http request returns a null string");
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "error while making http request", e);
        }
        return cards;
    }


    private static Card[] extractFromJSON(String jsonResponse) {

        // Create an empty ArrayList that we can start adding earthquakes to
        Card[] cards = new Card[currencies.length];

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // build up a list of Developer objects with the corresponding data.
            JSONObject root = new JSONObject(jsonResponse);
            JSONObject bit = root.getJSONObject("BTC");
            JSONObject eth = root.getJSONObject("ETH");

            for(int i = 0; i < currencies.length; i++){

                //creating each display card based on the value returned from the json response
                cards[i] = new Card(currencyNames[i],currencies[i],bit.getDouble(currencies[i]),
                        eth.getDouble(currencies[i]));
            }

        } catch (JSONException e) {
            // catch any error that might occur while analysing the JSON response and log it
            Log.e(LOG_TAG, "Problem parsing the cards JSON results", e);
        }

        return cards;

    }


    /**
     * method that create a URL object form string objects
     * @param url
     * @return makeURL
     */
    public static URL makeURL(String url) {

        URL urlString = null;
        try {
            urlString = new URL(url);
        } catch (MalformedURLException e) {

            Log.e(LOG_TAG, "error while forming url", e);

        }
        return urlString;

    }

    /**
     * method that helps in making a connection to the internet using a URL object
     * @param url
     * @return String jsonResponse
     * @throws IOException
     */
    public static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {

            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000 /*milliseconds*/);
            connection.setConnectTimeout(50000 /*milliseconds*/);
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                jsonResponse = readInputStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Unhandled response code from the connection:" + connection.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "error while making Http connection", e);

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.v(LOG_TAG, "error while closing connection", e);
                }
            }
        }


        return jsonResponse;

    }

    /**
     * method that accepts an InputStream object analyses it and return
     * coressponding String object
     * @param input
     * @return String jsonResponse
     * @throws IOException
     */
    public static String readInputStream(InputStream input)  {

        // a string builder object to hold the output from analysed input stream
        StringBuilder readJsonResponse = new StringBuilder();
try {
    if (input != null) {
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String readLine = bufferedReader.readLine();

        while (readLine != null) {
            readJsonResponse.append(readLine);
            readLine = bufferedReader.readLine();
        }
    }
}catch (IOException ex){
    Log.e(LOG_TAG,"error while reading input stream",ex);
}

        return readJsonResponse.toString();
    }

}
