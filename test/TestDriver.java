package test;
import controller.Controller;
import dg.*;
import model.IModel;
import model.WagerModel;
import view.IView;

/**
 * 
 * @author Bernie Sander
 * 
 * Driver test for the DG application
 *
 */
public class TestDriver {
  
  public static void main(String[] args) throws Exception {
    
    //initialize MVC (use mock view)
    IModel model = new WagerModel();
    Controller controller = new Controller(model);
    IView view = new MockView2();
    controller.setView(view);
    
    /*----Start Program----*/
    
    //first, set a bankroll
    controller.setBankroll("100");
    
    //next, generate wagers
    controller.generateWagers();
    System.out.println("\n----------------");
    
    //add additional markets
    controller.setMarkets(true, "top_5");
    controller.setMarkets(true, "top_10");
    controller.setMarkets(true, "top_20");
    
    //generate wagers with additional markets
    //note that only available markets with value bets will be displayed
    controller.generateWagers();
    System.out.println("\n--------------");
    
    //export these wagers to CSV
    controller.export();
    
    /*----Potential Errors----*/
    
    //set invalid bankroll
    controller.setBankroll("w");
    controller.setBankroll("-10");
    System.out.println("------------");
    
    //attempt to generate wagers with a bankroll <= 0
    System.out.println(String.format("Current Bankroll: $%.3g", model.getBankroll()));
    controller.generateWagers();
    System.out.println("------------");
    
    //attempt to export wagers to CSV without any stored
    System.out.println(String.format("Current Number of Wagers: %d", model.getNumWagers()));
    controller.export();
    
  }

}
