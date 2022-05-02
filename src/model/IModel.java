package model;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Bernie sander
 * 
 * Interface for model
 *
 */
public interface IModel {
  
  /**
   * Determine which available wagers on DataGolf
   * are value plays. Makes use of the Kelly Criterion
   * @param data  List of bets from the DataGolf API 
   */
  void determineWagers(List<Map<String,String>> data);
  
  /**
   * Sets list of wagers to empty
   */
  void clearWagers();
  
  /**
   * Sets the bankroll for the model to calculate
   * stakes with
   * 
   * @param b Bankroll
   */
  void setBankroll(double b);
  
  /**
   * Gets bankroll currently used by model
   * 
   * @return bankroll
   */
  double getBankroll();
  
  /**
   * Gets list of wagers stored by the model
   * 
   * @return list of wagers
   */
  List<Wager> getWagers();
  
  /**
   * Allows the model to be visited
   * 
   * @param v WagerModelVisitor object
   */
  void accept(ModelVisitor v);

  /**
   * Get the number of wagers stored by the model
   * 
   * @return number of wagers
   */
  int getNumWagers();
  
  /**
   * Returns expected profit of all wagers
   * 
   * @return Expected profit
   */
  double getExpectedProfit();
   
  /**
   * Returns total stake of wagers
   * 
   * @return  Total stake
   */
  double getTotalStake();
  
  
  

}
