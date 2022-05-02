package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Bernie Sander
 * 
 * General purpose decorator for a DGReader object. Has functionality to add additional markets to the data
 *
 */
public class DGReaderDecorator implements DGReader {
  
  //fields
  private DGReader dgr;
  private List<Map<String,String>> data;
  
  /**
   * Constructor. Stores the DGReader objects and sets this data
   * field to a copy of the data stored in the passed in DGReader object
   * @param dgr
   */
  public DGReaderDecorator(DGReader dgr) {
    this.dgr = dgr;
    this.data = new ArrayList<>(dgr.getData());
  }
  
  /**
   * Adds more data read in from the DataGolf API to the
   * data stored by this object
   * 
   * @param moreData
   */
  public void add(Map<String,String> moreData) {
    data.add(moreData);
  }
  
  /**
   * Returns the data stored by this object
   */
  @Override
  public List<Map<String, String>> getData() {
    return data;
  }

  /**
   * Call the clean data method of the passed in DGReader.
   * Set this data to a copy of data received from DGReader object
   */
  @Override
  public void cleanData() throws Exception {
    dgr.cleanData();
    data = new ArrayList<>(dgr.getData());
  }

}
