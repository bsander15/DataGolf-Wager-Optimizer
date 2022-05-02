package test;

import java.util.List;

import controller.Features;
import model.Wager;
import view.IView;

/**
 * 
 * @author Bernie Sander
 * 
 * Mock view for testing. Methods with input log
 * the input. Other methods are just there due to interface
 *
 */
public class MockView implements IView {
  private StringBuilder outputLog;
  
//---
  public MockView(StringBuilder outputLog) {
    this.outputLog = outputLog;
  }

//---
  @Override
  public void setFeatures(Features f) {
    
    
  }

//---
  @Override
  public void addWagers(List<Wager> wagers) {
    outputLog.append("Output: " + wagers + "\n");
    
  }

//---
  @Override
  public void addWagersMeta(int numWagers, double ep, double totalStake) {
     outputLog.append("Output: " + numWagers + ep + totalStake +"\n");
  }

//---
  @Override
  public void pushText(String s) {
    outputLog.append("Output: " + s +"\n");
  }

}
