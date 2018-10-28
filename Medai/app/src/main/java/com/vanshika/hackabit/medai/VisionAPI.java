
package com.vanshika.hackabit.medai;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
//import org.apache.commons.codec.binary.Base64;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import org.apache.commons.io.FileUtils;;;

public class VisionAPI {

    private static final String TARGET_URL =
            "https://vision.googleapis.com/v1/images:annotate?";
    private static final String API_KEY =
            "key=AIzaSyBpa9Syok9eucCZAlF5amM-dIyEN9JlwXY";

    private static HttpURLConnection makeConnection() throws IOException {
        URL serverUrl = new URL(TARGET_URL + API_KEY);
        URLConnection urlConnection = serverUrl.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;
        return httpConnection;
    }

    private static HashSet<String> processResponseStream(HttpURLConnection httpConnection) throws IOException {

        if (httpConnection.getInputStream() == null) {
            System.out.println("No stream");
            return new HashSet<String>();
        }
        HashSet<String> extractedTextSet = new HashSet<String>();
        Scanner httpResponseScanner = new Scanner (httpConnection.getInputStream());
        //String resp = "";

        while (httpResponseScanner.hasNext()) {
            String line = httpResponseScanner.nextLine();
            //System.out.println(line);
            if(checkJSONTag(line)) {
                line = processJSONLine(line);
                extractedTextSet.add(line);
            }
        }
        httpResponseScanner.close();

        return extractedTextSet;
    }

    private static boolean checkJSONTag(String line) {
        return line.contains("description");
    }

    private static String processJSONLine(String line) {
        String[] splitLine = line.trim().split("\"", 0);
        String extractedText = splitLine[splitLine.length - 2];
        return extractedText;
    }

    private static String createURLRequestJSON(String url) {
        String requestJSON = "{\"requests\":  [{ \"features\":  [ {\"type\": \"TEXT_DETECTION\"" +
                "}], \"image\": {\"source\": { \"imageUri\": \""+url+"\"}}}]}";
        return requestJSON;
    }

    private static String createContentRequestJSON(File image) throws IOException {

        byte[] fileContent = FileUtils.readFileToByteArray(image);
        String encodedString = Base64.encodeToString(fileContent, 1);
        //System.out.println(encodedString);
        String requestJSON = "{ "
                + "\"requests\": ["
                + "{"
                + "\"image\": {"
                + "\"content\": \""+encodedString+"\""
                + "},"
                + "\"features\": ["
                + "{"
                + "\"type\": \"TEXT_DETECTION\""
                + "}"
                + "]"
                + "}"
                + "]"
                + "}";
        return requestJSON;
    }

    public static HashSet<String> testRequest(String path) throws IOException {

        HttpURLConnection httpConnection = makeConnection();

        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");

        httpConnection.setDoOutput(true);

        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                OutputStreamWriter(httpConnection.getOutputStream()));
        httpRequestBodyWriter.write
                ("{\"requests\":  [{ \"features\":  [ {\"type\": \"TEXT_DETECTION\""
                        +"}], \"image\": {\"source\": { \"imageUri\":"
                        +" \"https://i.imgur.com/1vTnlT9.png\"}}}]}");
        httpRequestBodyWriter.close();

        String response = httpConnection.getResponseMessage();

        System.out.println(response);
        return processResponseStream(httpConnection);
    }

    public static HashSet<String> extractTextFromURL(String url) throws IOException{

        HttpURLConnection httpConnection = makeConnection();

        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");

        httpConnection.setDoOutput(true);

        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                OutputStreamWriter(httpConnection.getOutputStream()));

        String requestJSON = createURLRequestJSON(url);
        httpRequestBodyWriter.write(requestJSON);
        httpRequestBodyWriter.close();

        String response = httpConnection.getResponseMessage();
        System.out.println(response);

        return processResponseStream(httpConnection);
    }

    public static HashSet<String> extractTextFromImage(String filePath) throws IOException{

        File image = new File(filePath);
        if(!image.exists()) {
            System.out.println("File Not Found! Check Path.");
            return new HashSet<String>();
        }

        HttpURLConnection httpConnection = makeConnection();

        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");

        httpConnection.setDoOutput(true);

        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                OutputStreamWriter(httpConnection.getOutputStream()));

        String requestJSON = createContentRequestJSON(image);
        httpRequestBodyWriter.write(requestJSON);
        httpRequestBodyWriter.close();

        String response = httpConnection.getResponseMessage();
        System.out.println(response);

        return processResponseStream(httpConnection);
    }

    public static void main(String[] args) throws IOException {

        HashSet<String> extractedText = extractTextFromImage("/home/sponde/Downloads/test1.jpeg");
        for(String s: extractedText)
            System.out.println(s);
    }

}

/*import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
//import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import android.util.Base64;
import android.util.Log;

public class VisionAPI {

    private static final String TARGET_URL =
            "https://vision.googleapis.com/v1/images:annotate?";
    private static final String API_KEY =
            "key=AIzaSyBpa9Syok9eucCZAlF5amM-dIyEN9JlwXY";

    private static HttpURLConnection makeConnection() throws IOException {
        URL serverUrl = new URL(TARGET_URL + API_KEY);
        URLConnection urlConnection = serverUrl.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;

        return httpConnection;
    }

    private static HashSet<String> processResponseStream(HttpURLConnection httpConnection) throws IOException {

        if (httpConnection.getInputStream() == null) {
            System.out.println("No stream");
            return new HashSet<String>();
        }
        HashSet<String> extractedTextSet = new HashSet<String>();
        Scanner httpResponseScanner = new Scanner (httpConnection.getInputStream());
        //String resp = "";

        while (httpResponseScanner.hasNext()) {
            String line = httpResponseScanner.nextLine();
            //System.out.println(line);
            if(checkJSONTag(line)) {
                line = processJSONLine(line);
                extractedTextSet.add(line);
            }
        }
        httpResponseScanner.close();

        return extractedTextSet;
    }

    private static boolean checkJSONTag(String line) {
        return line.contains("description");
    }

    private static String processJSONLine(String line) {
        String[] splitLine = line.trim().split("\"", 0);
        String extractedText = splitLine[splitLine.length - 2];
        return extractedText;
    }

    private static String createURLRequestJSON(String url) {
        String requestJSON = "{\"requests\":  [{ \"features\":  [ {\"type\": \"TEXT_DETECTION\"" +
                "}], \"image\": {\"source\": { \"imageUri\": \""+url+"\"}}}]}";
        return requestJSON;
    }

    private static String createContentRequestJSON(File image) throws IOException {
        Log.v("VISION75", String.valueOf(image.exists()));
        final byte[] fileContent = FileUtils.readFileToByteArray(image);
        long size_file = image.length();
        Log.v("VISION78", ""+size_file + " "+ fileContent.length);
        Log.v("VISION77", fileContent.toString());
        String encodedString = Base64.encodeToString(fileContent, 1);
        Log.v("VISION78", encodedString);
        //System.out.println(encodedString);
        String requestJSON = "{ "
                + "\"requests\": ["
                + "{"
                + "\"image\": {"
                + "\"content\": \""+encodedString+"\""
                + "},"
                + "\"features\": ["
                + "{"
                + "\"type\": \"TEXT_DETECTION\""
                + "}"
                + "]"
                + "}"
                + "]"
                + "}";
        return requestJSON;
    }

    public static void testRequest() throws IOException {

        HttpURLConnection httpConnection = makeConnection();

        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");

        httpConnection.setDoOutput(true);

        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                OutputStreamWriter(httpConnection.getOutputStream()));
        httpRequestBodyWriter.write
                ("{\"requests\":  [{ \"features\":  [ {\"type\": \"TEXT_DETECTION\""
                        +"}], \"image\": {\"source\": { \"imageUri\":"
                        +" \"https://i.imgur.com/1vTnlT9.png\"}}}]}");
        httpRequestBodyWriter.close();

        String response = httpConnection.getResponseMessage();

        System.out.println(response);
    }

    public static HashSet<String> extractTextFromURL(String url) throws IOException{

        HttpURLConnection httpConnection = makeConnection();

        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");

        httpConnection.setDoOutput(true);

        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                OutputStreamWriter(httpConnection.getOutputStream()));

        String requestJSON = createURLRequestJSON(url);
        httpRequestBodyWriter.write(requestJSON);
        httpRequestBodyWriter.close();

        String response = httpConnection.getResponseMessage();
        System.out.println(response);

        return processResponseStream(httpConnection);
    }

    public static HashSet<String> extractTextFromImage(String filePath) throws IOException{

        File image = new File(filePath);

        Log.v("VISION File", "Incoming Request:"+image.getAbsolutePath());
        if(!image.exists()) {
            System.out.println("File Not Found! Check Path.");
            return new HashSet<String>();
        }

        HttpURLConnection httpConnection = makeConnection();
        Log.v("Vision31", httpConnection.toString());
        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");

        httpConnection.setDoOutput(true);

        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                OutputStreamWriter(httpConnection.getOutputStream()));

        String requestJSON = createContentRequestJSON(image);
        httpRequestBodyWriter.write(requestJSON);
        httpRequestBodyWriter.close();

        String response = httpConnection.getResponseMessage();
        System.out.println(response);

        return processResponseStream(httpConnection);
    }


}
*/
