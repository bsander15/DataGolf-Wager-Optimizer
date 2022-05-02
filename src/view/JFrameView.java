package view;


import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;

import controller.Controller;
import controller.Features;
import model.Wager;



/**
 * 
 * @author Bernie Sander
 * 
 * Implementation of the view. Subclass of JFrame and implements view interface
 */
public class JFrameView extends JFrame implements IView {
  private static final long serialVersionUID = -2179965453492637485L;

  //components of view
  private JButton setBankrollButton;
  private JButton generateWagersButton;
  private JTextField input;
  private JTextPane wagers;
  private JPanel wagerOutput;
  private JPanel inputs;
  private JCheckBox top5Tog;
  private JCheckBox top10Tog;
  private JCheckBox top20Tog;
  private JButton exportCSV;
  private JLabel numWagers;
  private JLabel expectedProfit;
  private JLabel totalStake;
  

  /**
   * Constructor.
   * 
   * @param caption    Caption of the application
   * @param controller Connected controller
   */
  public JFrameView(String caption, Controller controller) {
    super(caption);

    setSize(1000, 1000);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(null);
    inputs = new JPanel();
    inputs.setBounds(0,10,1000,100);
    inputs.setLayout(new FlowLayout());
    this.add(inputs);
    
    inputs.add(new JLabel("Enter Bankroll"));

    // the text field
    input = new JTextField(10);
    inputs.add(input);

    // bankroll button
    setBankrollButton = new JButton("Set");
    inputs.add(setBankrollButton);

    // generate wagers button
    generateWagersButton = new JButton("Generate Wagers");
    inputs.add(generateWagersButton);
    
    //label for check boxes
    inputs.add(new JLabel("Additional Markets:"));
    
    //top 5 market check box
    top5Tog = new JCheckBox("Top 5");
    inputs.add(top5Tog);
    
    //top 10 market check box
    top10Tog = new JCheckBox("Top 10");
    inputs.add(top10Tog);
    
    //top 20 market check box
    top20Tog = new JCheckBox("Top 20");
    inputs.add(top20Tog);
    
    //export CSV button
    exportCSV = new JButton("Export To CSV");
    inputs.add(exportCSV);
    
    //label for number of wagers
    numWagers = new JLabel("Number of Wagers: 0");
    inputs.add(numWagers);
    
    //label for expected profit
    expectedProfit = new JLabel("Expected Profit: $0.00");
    inputs.add(expectedProfit);
    
    //label for total stake
    totalStake = new JLabel("Total Stake: $0.00");
    inputs.add(totalStake);
    
    
    
    //panel for wager output
    wagerOutput = new JPanel();
    wagerOutput.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    wagerOutput.setBounds(10, inputs.getBounds().y+inputs.getBounds().height, this.getBounds().width-20, 700);
    wagerOutput.setLayout(new FlowLayout());
    this.add(wagerOutput);
    
    //text pane for wagers
    wagers = new JTextPane();
    wagers.setText("Enter your bankroll and press Set, then Generate Wagers!\n\nSelected additional markets if you want wagers to be calculated for those as well\n\nNote the default market is outright\n\nSelected markets that do not appear in the wager list are either not available or there are no value plays in those markets");
    wagerOutput.add(wagers);
    
//    pack();
    setVisible(true);

  }
  
  /**
   * Connect each component to the corresponding method in the controller 
   */
  public void setFeatures(Features f) {
    setBankrollButton.addActionListener(l -> f.setBankroll(input.getText()));
    generateWagersButton.addActionListener(l -> {
      try {
        f.generateWagers();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    top5Tog.addActionListener(l -> f.setMarkets(top5Tog.isSelected(), "top_5"));
    top10Tog.addActionListener(l -> f.setMarkets(top10Tog.isSelected(), "top_10"));
    top20Tog.addActionListener(l -> f.setMarkets(top20Tog.isSelected(), "top_20"));
    exportCSV.addActionListener(l -> f.export());
  }
  
  /**
   * Add wagers passed in to the text pane
   */
  @Override
  public void addWagers(List<Wager> wagers) {
    this.wagers.setText("");
    for (Wager w: wagers) {
      this.wagers.setText(this.wagers.getText()+w.toString()+"\n");
    }
  }
  
  /**
   * Adds meta data to view for wagers
   */
  @Override
  public void addWagersMeta(int numWagers, double expectedProfit, double totalStake) { 
      DecimalFormat df = new DecimalFormat("0.00");
      this.numWagers.setText(String.format("Number of Wagers: %d", numWagers));
      this.expectedProfit.setText(String.format("Expected Profit: $%s", df.format(expectedProfit)));
      this.totalStake.setText(String.format("Total Stake: $%s", df.format(totalStake)));
  }
  
  /**
   * push text passed in to the text pane
   */
  @Override
  public void pushText(String s) {
    this.wagers.setText(s);
  }
}
