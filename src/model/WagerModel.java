package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author Bernie Sander
 * 
 * Wager Model Object. Determines value wagers based on odds and probability
 * using the Kelly Criterion. Odds and probability data from DataGolf API
 *
 */
public class WagerModel implements IModel {
  

  private List<Wager> wagers;
  private double bankroll;
  
  /**
   * Constructor. Initializes wagers to empty ArrayList
   * and bankroll to 0
   */
  public WagerModel() {
    this.wagers = new ArrayList<>();
    this.bankroll = 0;
  }
  
  /**
   * Finds the value wagers in the data passed in. Uses kellyCriterion()
   * to determine stake.
   */
  @Override
  public void determineWagers(List<Map<String,String>> data) {
    for (int i = 0; i < data.size(); i++) {
      double odds = Double.parseDouble(data.get(i).get("odds"));
      double prob = Double.parseDouble(data.get(i).get("prob"));
      double ev = ((odds-1)*prob)-(1-prob);
      //how value wagers are determined
      if (ev > 0) {
        double stake = bankroll * kellyCriterion(odds,prob)/2;
        //add to wagers as long as stake does not exceed bankroll
        if (stake+this.getTotalStake() <= bankroll) {
          String market = data.get(i).get("market");
          String golfer = data.get(i).get("golfer");
          wagers.add(new Wager(stake,market,golfer,odds,prob,ev));
        }
      }
    }
  }
  
  /**
   * Clear wagers
   */
  @Override
  public void clearWagers() {
    wagers.clear();
  }
  
  /**
   * set bankroll
   */
  @Override
  public void setBankroll(double b) {
    bankroll = b;
  }
  
  /**
   * get bankroll
   */
  @Override
  public double getBankroll() {
    return bankroll;
  }
  
  /**
   * get wagers
   */
  @Override
  public List<Wager> getWagers() {
    return wagers;
  }
  
  /**
   * Internal iterator to get total stake
   * 
   * @return total stake
   */
  @Override
  public double getTotalStake() {
    return wagers.stream().mapToDouble(w -> w.getStake())
                               .reduce(0, (acc_stake ,curr_stake) -> acc_stake + curr_stake);
  }
  
  /**
   * get number of wagers
   */
  @Override
  public int getNumWagers() {
    return wagers.size();
  }
  
  /**
   * Internal iterator to get expected profit
   * 
   * @return expected profit
   * 
   */
  @Override
  public double getExpectedProfit() {
    return wagers.stream().mapToDouble(w -> w.getEV()*w.getStake())
                               .reduce(0, (acc_ep, curr_ep) -> acc_ep + curr_ep);
  }
  
  /**
   * private helper function to determine stake of wager
   * based on odds received and probability of event happening.
   * Uses Kelly Criterion formula
   * 
   * @param odds  Odds received
   * @param prob  Probability of event happening
   * @return      Percent of bankroll to place on wager
   */
  private double kellyCriterion(double odds, double prob) {
    return prob+(prob-1)/(odds-1);
  }
  
  /**
   * Accept method allows for visitation from a WagerModelVisitor object
   */
  public void accept(ModelVisitor v) {
    v.visitWagerModel(this);
  }
  
}
