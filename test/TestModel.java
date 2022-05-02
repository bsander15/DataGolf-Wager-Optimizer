package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import dg.*;
import model.IModel;
import model.Wager;
import model.WagerModel;

/**
 * 
 * @author Bernie Sander
 * 
 * Testing class for the model
 *
 */
public class TestModel {
  
  private IModel model;
  private Map<String,String> wager1 = new HashMap<>();
  private Map<String,String> wager2 = new HashMap<>();
  private Map<String,String> wager3 = new HashMap<>();
  private List<Map<String,String>> potentialWagers = new ArrayList<>();
  private Wager w;

  //add potential wagers to a list, set the bankroll for the
  //model, intitialize a Wager object
  @Before
  public void setUp() throws Exception {
    model = new WagerModel();
    model.setBankroll(100);
    wager1.put("market","win");
    wager1.put("prob","0.5");
    wager1.put("odds","10");
    wager1.put("golfer","test1");
    wager2.put("market","win");
    wager2.put("prob","0.25");
    wager2.put("odds","2");
    wager2.put("golfer","test2");
    potentialWagers.add(wager1);
    potentialWagers.add(wager2);
    w = new Wager((0.5+(0.5-1)/(10-1))*100/2,"win","test1",10.0,0.5,4.0);
    model.determineWagers(potentialWagers);
  }
  
  //test determineWagers(). ensure that only one of the wagers
  //added to potentialWagers in the setup ends up in the actual
  //wagers list
  @Test
  public void testDetermineWagers() {
    List<Wager> ws = new ArrayList<>();
    ws.add(w);
    assertEquals(ws.get(0).getEV(), model.getWagers().get(0).getEV(),0);
    assertEquals(ws.get(0).getGolfer(), model.getWagers().get(0).getGolfer());
    assertEquals(ws.get(0).getMarket(), model.getWagers().get(0).getMarket());
    assertEquals(ws.get(0).getOdds(), model.getWagers().get(0).getOdds(),0);
    assertEquals(ws.get(0).getProb(), model.getWagers().get(0).getProb(),0);
    assertEquals(ws.get(0).getStake(), model.getWagers().get(0).getStake(),0);
  }

  //test clearWagers()
  @Test
  public void testClearWagers() {
    model.clearWagers();
    potentialWagers.clear();
    assertEquals(potentialWagers,model.getWagers());
  }
  
  //test getBankroll()
  @Test
  public void testBankRoll() {
    assertEquals(100,model.getBankroll(),0);
  }
  
  //test getNumWagers()
  @Test
  public void testGetNumWagers() {
    assertEquals(1, model.getNumWagers());
  }
  
  //test getExpectedProfit()
  //wagers list w/ one wager should have an ep equal to that wager
  //wagers list w/ zero wagers should have an ep equal to 0
  @Test
  public void testGetExpectedProfit() {
    assertEquals((((0.5+(0.5-1)/(10-1))*100/2)*0.5*9)-((0.5+(0.5-1)/(10-1))*100/2)*0.5,model.getExpectedProfit(),0);
    model.clearWagers();
    assertEquals(0,model.getExpectedProfit(),0);
  }
  
  @Test
  public void testGetTotalStake() {
    assertEquals((0.5+(0.5-1)/(10-1))*100/2,model.getTotalStake(),0);
    wager1.put("odds","2");
    wager1.put("prob", "1");
    wager2.put("odds","2");
    wager2.put("prob", "1");
    wager3.put("market","win");
    wager3.put("prob","1");
    wager3.put("odds","2");
    wager3.put("golfer","test3");
    potentialWagers.add(wager3);
    model.clearWagers();
    model.determineWagers(potentialWagers);
    //three potential wagers of all 100% probability should
    //use the entire bankroll but only the first two wagers should be included
    assertEquals(2,model.getNumWagers());
    assertEquals(model.getBankroll(),model.getTotalStake(),0);
    
  }
  
  //only one visitor in program, namely the one that 
  //writes wagers to a CSV file. Am not required to test
  //this functionality. Tests in the controller ensure
  //that the appropriate visitWagerModel method is called
  @Test
  public void testAccept() {
    
  }

}
