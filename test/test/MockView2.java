package test;
import java.util.List;

import controller.Features;
import model.Wager;
import view.IView;

/**
 * 
 * @author Bernie Sander
 * 
 * Mock view used for the driver test. Turns view into console based
 * output
 *
 */
public class MockView2 implements IView {

  //dud
  @Override
  public void setFeatures(Features f) {
    
    
  }

  //print wagers to console
  @Override
  public void addWagers(List<Wager> wagers) {
    for (Wager w: wagers) {
      System.out.println(w);
    }
    
  }

  //print meta to console
  @Override
  public void addWagersMeta(int numWagers, double ep, double totalStake) {
    System.out.println(String.format("Number of wagers: %d, Expected profit: %g, Total Stake: %g", numWagers, ep, totalStake));
  }

  //print text to console
  @Override
  public void pushText(String s) {
    System.out.println(s);
  }

}
