package model;

/**
 * 
 * @author Bernie Sander
 * 
 * Wager Object. Represents a wager on a particular golfer.
 * Stores information for stake, market, golfer, odds, probability,
 * and expected value.
 *
 */
public class Wager {
  

  private double stake;
  private String market;
  private String golfer;
  private double odds;
  private double prob;
  private double ev;
  
  /**
   * Constructor
   * 
   * @param stake  Stake of wager
   * @param market Market of wager
   * @param golfer Golfer wager is on
   * @param odds   Odds received on wager
   * @param prob   Probability of wager hitting
   * @param ev     Expected value on a unit stake
   */
  public Wager(double stake, String market, String golfer, double odds, double prob, double ev) {
    this.stake = stake;
    this.market = market;
    this.golfer = golfer;
    this.odds = odds;
    this.prob = prob;
    this.ev = ev;
  }
  

    /**
     * Get stake
     * 
     * @return stake
     */
    public double getStake() {
      return this.stake;
    }
    
    /**
     * Get market
     * 
     * @return market
     */
    public String getMarket() {
      return this.market;
    }
    
    /**
     * Get golfer
     * 
     * @return golfer
     */
    public String getGolfer() {
      return this.golfer;
    }
    
    /**
     * Get odds
     * 
     * @return odds
     */
    public double getOdds() {
      return this.odds;
    }
    
    /**
     * Get probability
     * 
     * @return prob
     */
    public double getProb() {
      return this.prob;
    }
    
    /**
     * Get expected value
     * 
     * @return ev
     */
    public double getEV() {
      return this.ev;
    }
    
    /**
     * String representation of Wager
     */
    @Override
    public String toString() {
      return String.format("{Golfer: %s, Market: %s, Stake: $%g, Odds: %g, Probability: %.4g%%, EV: %g}", this.golfer,this.market,this.stake,this.odds,this.prob*100,this.ev);
    }
}
