package view;

import java.util.List;

import controller.Features;
import model.Wager;

/**
 * 
 * @author Bernie Sander
 * 
 * Interface for View
 *
 */
public interface IView {

  /**
   * Connect components of view
   * to corresponding methods in
   * controller
   * 
   * @param f Features object (controller)
   */
  void setFeatures(Features f);
  
  /**
   * Add wagers to the JTextPane
   * 
   * @param wagers  Wagers to be added
   */
  void addWagers(List<Wager> wagers);
  
  /**
   * Add meta data for wagers
   * 
   * @param numWagers   Total number of wagers
   * @param ep          Expected profit
   * @param totalStake  Total stake
   */
  void addWagersMeta(int numWagers, double ep, double totalStake);
  
  /**
   * Add passed in text to the JTextPane
   * 
   * @param s Text to be added
   */
  void pushText(String s);
  
  
}
