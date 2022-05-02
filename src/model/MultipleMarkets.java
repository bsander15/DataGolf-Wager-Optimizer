package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Bernie Sander
 *
 * Decorator object of DGReader. Allows wagers from additional markets to be
 * added
 */
public class MultipleMarkets extends DGReaderDecorator {

  //additional markets to be scraped
  private Set<String> markets;
  
  /**
   * Constructor. Calls parent constructor with passed in DGReader object
   * Sets markets field
   * 
   * @param dgr     DGReader object to be decorated
   * @param markets Additional markets to be scraped
   */
  public MultipleMarkets(DGReader dgr, Set<String> markets) {
    super(dgr);
    this.markets = markets;
  }

  /**
   * Scans additional markets and in the markets set and formats
   * data from DataGolf API so that it can be used by the model.
   * Calls parent add method to add new data to this DGReader object
   */
  @Override
  public void cleanData() throws JSONException, IOException {
    //iterates through different markets, selected by the user, that are offered by the API
    for(String market: markets) {
    
      JSONObject json = ReadFromURL.readJsonFromUrl(String.format("https://feeds.datagolf.com/betting-tools/outrights?tour=pga&market=%s&key=1b8fd21b7e3b79f3cd8ffff676b8",market));
      Map<String, Object> map = json.toMap();
      
      //if market is not available
      if (map.get("odds") instanceof String) {
        continue;
      }
      ArrayList<Map<String,Object>> mapArr = (ArrayList<Map<String,Object>>) map.get("odds");
    
      for(int i = 0;i < mapArr.size();i++) {
        Map<String,String> playerData = new HashMap<>();
        Map<String,Object> dg_probs = (Map<String,Object>) mapArr.get(i).get("datagolf");
        
        //Only use odds from DraftKings for simplicity
        if (mapArr.get(i).containsKey("draftkings") && dg_probs.get("baseline_history_fit") != null)  {
          String sprob = dg_probs.get("baseline_history_fit").toString();
          double prob = 1/Double.parseDouble(sprob);
          sprob = String.valueOf(prob);
          playerData.put("golfer", mapArr.get(i).get("player_name").toString());
          playerData.put("odds", mapArr.get(i).get("draftkings").toString());
          playerData.put("prob", sprob);
          playerData.put("market", market);
          //call the parent add method to add this potential wager to the data pulled
          super.add(playerData);
        }
      }
    }
  }
}
