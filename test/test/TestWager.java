package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Wager;

public class TestWager {
  private Wager w;

  @Before
  public void setUp() throws Exception {
    w = new Wager(10,"w","m",5,4,3);
  }

  //test getStake()
  @Test
  public void testGetStake() {
    assertEquals(10,w.getStake(),0);
  }
  
  //test getGolfer()
  @Test
  public void testGetGolfer() {
    assertEquals("m",w.getGolfer());
  }
  
  //test getMarket()
  @Test
  public void testGetMarket() {
    assertEquals("w",w.getMarket());
  }
  
  //test getOdds()
  @Test
  public void testGetOdds() {
    assertEquals(5,w.getOdds(),0);
  }
  
  //test getProb()
  @Test
  public void testGetProb() {
    assertEquals(4,w.getProb(),0);
  }
  
  //test getEV()
  @Test
  public void testGetEV() {
    assertEquals(3,w.getEV(),0);
  }

}
