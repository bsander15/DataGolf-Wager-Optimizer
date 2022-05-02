package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.IModel;
import model.ModelVisitor;
import model.Wager;

/**
 * 
 * @author Bernie Sander
 * Mock Model for testing. Methods with input log
 * the input. Other methods are just there due to interface
 *
 */
public class MockModel implements IModel {
  private StringBuilder log;
  private int uniqueCode;

  //---
  public MockModel(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }
  
  //---
  @Override
  public void determineWagers(List<Map<String, String>> data) {
    log.append("Input: " + data +"\n");
  }

//---
  @Override
  public void clearWagers() {
  }

//---
  @Override
  public void setBankroll(double b) {
    log.append("Input: " + b + "\n");
    
  }

//---
  @Override
  public double getBankroll() {
    return uniqueCode;
  }

//---
  @Override
  public List<Wager> getWagers() {
    return new ArrayList<>();
  }

//---
  @Override
  public void accept(ModelVisitor v) {
    log.append("Input :" + v.toString());
  }

//---
  @Override
  public int getNumWagers() {
    return uniqueCode;
  }

//---
  @Override
  public double getExpectedProfit() {
    return uniqueCode;
  }

//---
  @Override
  public double getTotalStake() {
    return uniqueCode;
  }

}
