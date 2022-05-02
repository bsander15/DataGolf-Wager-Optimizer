package controller;


/**
 * 
 * @author Bernie Sander
 * 
 * Features interface, implemented by the controller and used in the view
 *
 */
public interface Features {
  
  /**
   * Invoked when the "Generate Wagers" button is pushed
   * Makes call to scrape appropriate data from DataGolf API
   * and passes that data to model to determine value bets.
   * Updates view with value wagers
   * 
   * @throws Exception
   */
  void generateWagers() throws Exception;
  
  /**
   * * Invoked when "Set" button is pushed by user.
   * Text from JTextField is passed in as b and used
   * to set the bankroll for the model
   * 
   * @param b Value to set bankroll to
   */
  void setBankroll(String b);
  
  /**
   * Invoked when an "Additional Markets" JCheckBox is checked.
   * If the checkbox is selected, the market is added to the set. If 
   * it isn't, its removed from the set
   * 
   * @param isSelected Whether the checkbox is selected or not
   * @param market     String representation of market used by DataGolf API
   */
  void setMarkets(boolean isSelected, String market);
  
  /**
   * Invoked when the "Export to CSV" button is pushed by the user.
   * If the model contains wagers, this method provides a WriteToCSVVisitor
   * object to the model's accept method. This visitor has functionality
   * to export the wagers stored in the model to a CSV file. 
   */
  void export();

}
