package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Bernie Sander
 * 
 * Calls thats reads a JSON object from a URL. Code gotten from: https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
 *
 */
public class ReadFromURL {
  
  /**
   * Read text provided 
   * 
   * @param rd    Text to be read
   * @return      String representation of JSON
   * @throws IOException
   */
  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  /**
   * Open a URL and read its JSON.
   * 
   * @param url   URL to read JSON from
   * @return      JSONObject created from JSON in URL
   * @throws IOException
   * @throws JSONException
   */
  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
}
