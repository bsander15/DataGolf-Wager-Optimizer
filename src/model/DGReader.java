package model;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Bernie Sander
 * 
 * DGReader object that defines functionality for objects that
 * interact with DataGolf API
 *
 */
public interface DGReader {
  
  /**
   * Cleans data provided by DataGolf API. Formats in way
   * WagerModel can interact with
   */
  void cleanData() throws Exception;

  /**
   * Get cleaned data
   * 
   * @return data stored by DGReader object
   */
  List<Map<String,String>> getData();
}
