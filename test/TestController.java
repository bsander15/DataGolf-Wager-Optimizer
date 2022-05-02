package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import model.DGReader;
import model.DefaultReader;
import model.IModel;
import view.IView;

/**
 * 
 * @author Bernie Sander
 * 
 * Tests controller with mock view and mock model
 *
 */
public class TestController {
  private Controller controller;
  private IModel mock;
  private IView view;
  private StringBuilder inputLog;
  private StringBuilder outputLog;
  private DGReader dg;

  //tie controller to mock view and model
  @Before
  public void setUp() throws Exception {
    inputLog = new StringBuilder();
    mock = new MockModel(inputLog,1);
    controller = new Controller(mock);
    outputLog = new StringBuilder();
    view = new MockView(outputLog);
    controller.setView(view);
    dg = new DefaultReader();
    dg.cleanData();
  }

  //test generate wagers functionality of controller
  //ensure inputs to model and outputs to view are correct
  @Test
  public void testGenerateWagers() throws Exception {
    controller.generateWagers();
    assertEquals("Input: " + dg.getData() + "\n",inputLog.toString());
    assertEquals("Output: No wagers available\n", outputLog.toString());
    mock = new MockModel(inputLog, 0);
    controller = new Controller(mock);
    controller.setView(view);
    controller.generateWagers();
    assertEquals("Output: No wagers available\nOutput: Enter a bankroll before generating wagers\n", outputLog.toString());
    controller = new Controller(new MockModel2(inputLog,1));
    controller.setView(view);
    controller.generateWagers();
    assertEquals("Output: No wagers available\nOutput: Enter a bankroll before generating wagers\nOutput: [{Golfer: w, Market: w, Stake: $10.0000, Odds: 10.0000, Probability: 1000%, EV: 10.0000}]\nOutput: 11.01.0\n",outputLog.toString());
  }
  
  //test set bankroll functionality of controller
  //ensure inputs to model and output to view are correct
  @Test
  public void testSetBankroll() {
    controller.setBankroll("10");
    assertEquals("Input: 10.0\n",inputLog.toString());
    controller.setBankroll("w");
    assertEquals("Input: 10.0\nInput: 0.0\n",inputLog.toString());
    assertEquals("Output: 11.01.0\nOutput: Please add a positive number as a bankroll, Ex. 1000\n",outputLog.toString());
    controller.setBankroll("-10");
    assertEquals("Input: 10.0\nInput: 0.0\nInput: 0.0\n",inputLog.toString());
    assertEquals("Output: 11.01.0\nOutput: Please add a positive number as a bankroll, Ex. 1000\nOutput: 11.01.0\nOutput: Please add a positive number as a bankroll, Ex. 1000\n", outputLog.toString());
  }
  
  //test export functionality  of controller
  //ensure input to model and output to view are correct
  @Test
  public void testExport() {
    controller.export();
    assertEquals("Output: Generate wagers before exporting to CSV\n", outputLog.toString());
    controller = new Controller(new MockModel2(inputLog,1));
    controller.export();
    assertEquals("Input: WriteToCSVVisitor", inputLog.toString());
  }
  
  
  //test setMarket
  //ensure markets are added and removed appropriately
  @Test
  public void testSetMarkets() {
    Set<String> test = new HashSet<>();
    controller.setMarkets(true, "top_5");
    controller.setMarkets(true, "top_10");
    controller.setMarkets(false, "top_20");
    test.add("top_5");
    test.add("top_10");
    assertEquals(test,controller.getMarkets());
    controller.setMarkets(false, "top_5");
    test.remove("top_5");
    assertEquals(test,controller.getMarkets());
  }
  

}
