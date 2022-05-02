package model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Bernie Sander
 * 
 * Connects to DataGolf API and returns appropriate data
 *
 */
public class DefaultReader implements DGReader{
  
  //clean data
  private ArrayList<Map<String,String>> data;
  
  /**
   * Constructor. Initializes data field to empty ArrayList
   */
  public DefaultReader() {
    this.data = new ArrayList<>();
  }
  
  /**
   * Returns the data
   */
  public ArrayList<Map<String,String>> getData() {
    return this.data;
  }

  
  /**
   * Reads in data from DataGolf API and formats it in a way
   * expected by the model
   */
  @Override
  public void cleanData() throws JSONException, IOException {
    try {
      JSONObject json = ReadFromURL.readJsonFromUrl("https://feeds.datagolf.com/betting-tools/outrights?tour=pga&market=win&key=1b8fd21b7e3b79f3cd8ffff676b8");
      Map<String, Object> map = json.toMap();
      ArrayList<Map<String,Object>> mapArr = (ArrayList<Map<String,Object>>) map.get("odds");
    
       //formats data from DG API in a way to model can interpret, only uses odds from DraftKings for simplicity
      for(int i = 0;i < mapArr.size();i++) {
        Map<String,String> playerData = new HashMap<>();
        Map<String,Object> dg_probs = (Map<String,Object>) mapArr.get(i).get("datagolf");
      
        if (mapArr.get(i).containsKey("draftkings") && dg_probs.get("baseline_history_fit") != null)  {
          String sprob = dg_probs.get("baseline_history_fit").toString();
          double prob = 1/Double.parseDouble(sprob);
          sprob = String.valueOf(prob);
          playerData.put("golfer", mapArr.get(i).get("player_name").toString());
          playerData.put("odds", mapArr.get(i).get("draftkings").toString());
          playerData.put("prob", sprob);
          playerData.put("market", "win");
          this.data.add(playerData);
        }
      }
    }
    //if something goes wrong with API, set data to empty list
    catch (Exception e) {
      this.data = new ArrayList<>();
    }
  }
}