package dg;

import java.io.IOException;

import org.json.JSONException;

import controller.Controller;
import model.WagerModel;
import view.IView;
import view.JFrameView;

/**
 * 
 * @author Bernie Sander
 * 
 * Program starter.
 * 
 * General Notes about the project:
 * 
 * MVC pattern is implemented.
 * 
 * Java ADTs are used throughout the project. Specifically: List<>, Set<>, Map<> 
 * 
 * Internal iterator is used in the form of a Stream object with Java's
 * built-in ADTs. The internal iterator takes in a HOF and applies it
 * to each wager in the wagers list. It is used for getting the total stake
 * of all the wagers and the expected profit of all the wagers. External iterators from
 * Java's built-in ADTs are also used with for-each loops.
 * 
 * Decorator pattern is used pull additional market information from the DataGolf API. The
 * default market is 'win' which is pulled by the DefaultReader object. The MultipleMarkets
 * object pulls from additional markets depending on user input. Both object are of type
 * DGReader
 * 
 * Visitor pattern is used to visit the model and export wagers to CSV file. ModelVisitor
 * object is passed into the models accept method.
 * **********************
 * Changes from proposal:
 * 
 * Did not implement historical object - not enough time
 * 
 * Changed decorator function from making the wager display scrollable to allowing the
 * DGReader to pull multiple markets (default is 'win')
 *
 * Did not implement functionality for users to add their own bets or remove bets from
 * the ones that are generated. I probably would have if I had more time, but this app
 * turned into less of a wager tracker and more into a wager calculator so there is really
 * no need for the user to remove bets or add bets.
 * 
 *
 */
public class Main {

  /**
   * main method that initializes the program
   * 
   * @param args
   * @throws JSONException
   * @throws IOException
   */
  public static void main(String[] args) throws JSONException, IOException {
    
    //Initialize the model
    WagerModel model = new WagerModel();

    //Initialize the controller with the model
    Controller controller = new Controller(model);
    
    //Initialize the view
    IView view = new JFrameView("DataGolf Wagers", controller);
    
    //Connect the view and controller
    controller.setView(view);

  }
}
