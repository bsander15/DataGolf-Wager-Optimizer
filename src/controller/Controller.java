package controller;

import java.util.HashSet;
import java.util.Set;

import model.DGReader;
import model.DefaultReader;
import model.IModel;
import model.MultipleMarkets;
import model.WriteToCSVVisitor;
import view.IView;

/**
 * @author Bernie Sander
 * 
 * Controller of program, implements features that are used in the view
 */
public class Controller implements Features {
  
  //fields
  private IModel model;
  private IView view;
  private Set<String> markets;

  /**
   * Constructor. Sets the model and initializes markets
   * to a HashSet. This set is used to provide wagers in 
   * additional markets that the user selects
   * 
   * @param m the model to use
   */
  public Controller(IModel m) {
    this.model = m;
    this.markets = new HashSet<>();
     
  }

  /**
   * Invoked when the "Generate Wagers" button is pushed.
   * Makes call to pull appropriate data from DataGolf API
   * and passes that data to model to determine value bets.
   * Updates view with value wagers
   */
  @Override
  public void generateWagers() throws Exception {
    //bankroll needs to be set prior to generating wagers
    if (model.getBankroll() == 0) {
      view.pushText("Enter a bankroll before generating wagers");
    }
    else {
      //initialize a default DG API reader and pull data from the 'win' market
      DGReader dgr = new DefaultReader();
      dgr.cleanData();
      
      //if multiple markets are selected
      if (!markets.isEmpty()) {
        //decorate DefaultReader
        dgr = new MultipleMarkets(dgr,markets);
        dgr.cleanData();
      }
      
      //reset wager list and determine value bets from pulled data
      model.clearWagers();
      model.determineWagers(dgr.getData());
      
      //if no value bets, say so
      if (model.getWagers().isEmpty()) {
        view.pushText("No wagers available");
      }
      //otherwise display bets and meta data to user
      else {
        view.addWagers(model.getWagers());
        view.addWagersMeta(model.getNumWagers(),model.getExpectedProfit(),model.getTotalStake());
      }
    }
  }
  
  /**
   * Invoked when "Set" button is pushed by user.
   * Text from JTextField is passed in as b and used
   * to set the bankroll for the model
   * 
   * @param b Value to set bankroll to
   */
  @Override
  public void setBankroll(String b) {
    try {
      double bankroll = Double.parseDouble(b);
      //bankroll can't be negative or zero
      if (bankroll <= 0) {
        throw new IllegalArgumentException("Bankroll can't be <= 0");
      }
      else {
        model.setBankroll(bankroll);
      }
    }
    //if invalid bankroll entered, then set bankroll to zero and rest wagers
    catch (Exception e) {
      model.setBankroll(0);
      model.clearWagers();
      view.addWagersMeta(model.getNumWagers(),model.getExpectedProfit(),model.getTotalStake());
      view.pushText("Please add a positive number as a bankroll, Ex. 1000");
    }
  }

  /**
   * Connects the view to this controller
   * 
   * @param v the view to use
   */
  public void setView(IView v) {
    view = v;
    // give the feature callbacks to the view
    view.setFeatures(this);
  } 

  /**
   * Invoked when an "Additional Markets" JCheckBox is checked.
   * If the checkbox is selected, the market is added to the set. If 
   * it isn't, its removed from the set
   * 
   * @param isSelected Whether the checkbox is selected or not
   * @param market     String representation of market used by DataGolf API
   */
  @Override
  public void setMarkets(boolean isSelected, String market) {
    if (isSelected) {
      markets.add(market);
    }
    else {
      markets.remove(market);
    }
  }
  
  /**
   * Get markets currently stored
   * 
   * @return markets
   */
  public Set<String> getMarkets() {
    return markets;
  }
  
  
  /**
   * Invoked when the "Export to CSV" button is pushed by the user.
   * If the model contains wagers, this method provides a WriteToCSVVisitor
   * object to the model's accept method. This visitor has functionality
   * to export the wagers stored in the model to a CSV file. 
   */
  @Override
  public void export() {
    //wagers need to be present to export to CSV
    if (model.getWagers().isEmpty()) {
      view.pushText("Generate wagers before exporting to CSV");
    }
    //use visitor to export to CSV
    else {
      model.accept(new WriteToCSVVisitor());
    }
  }
  
 
}