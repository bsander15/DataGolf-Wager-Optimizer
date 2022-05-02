package model;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;


/**
 * 
 * @author Bernie Sander
 * 
 * WagerVisitorObject that provides functionality to write a list 
 * of Wager objects to a CSV file
 *
 */
public class WriteToCSVVisitor implements ModelVisitor {
  

  /**
   * Writes the wagers stored in the wager model to a CSV
   * Code gotten from: https://stackoverflow.com/questions/3666007/how-to-serialize-object-to-csv-file
   */
  @Override 
  public void visitWagerModel(IModel m) {
    try {
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("wagers.csv"), "UTF-8"));
      StringBuffer oneLine = new StringBuffer();
      //Write headers
      oneLine.append("Golfer (Last)");
      oneLine.append(",");
      oneLine.append("Golfer (First)");
      oneLine.append(",");
      oneLine.append("Market");
      oneLine.append(",");
      oneLine.append("Stake");
      oneLine.append(",");
      oneLine.append("Odds");
      oneLine.append(",");
      oneLine.append("Probability");
      oneLine.append(",");
      oneLine.append("EV");
      bw.write(oneLine.toString());
      bw.newLine();
      //Write rest of data
      for (Wager w: m.getWagers()) {
        oneLine = new StringBuffer();
        oneLine.append(w.getGolfer());
        oneLine.append(",");
        oneLine.append(w.getMarket());
        oneLine.append(",");
        oneLine.append(w.getStake());
        oneLine.append(",");
        oneLine.append(w.getOdds());
        oneLine.append(",");
        oneLine.append(w.getProb());
        oneLine.append(",");
        oneLine.append(w.getEV());
        bw.write(oneLine.toString());
        bw.newLine();
      }
      bw.flush();
      bw.close();
    }
    catch (Exception e) {
      e.printStackTrace();;
    }
 
  }
  
  //String representation for testing
  @Override
  public String toString() {
    return "WriteToCSVVisitor";
  }
}

