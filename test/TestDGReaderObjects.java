package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.DGReader;
import model.DGReaderDecorator;
import model.DefaultReader;
import model.MultipleMarkets;

/**
 * 
 * @author Bernie Sander
 * 
 * Test for DGReader Objects.
 * Ensures proper decoration
 *
 */
public class TestDGReaderObjects {
  
  private DGReader reader;
  private DGReader decoratedReader;
  
  //Set up object to be decorated and decorator 
  @Before
  public void setUp() throws Exception {
    reader = new DefaultReader();
    reader.cleanData();
    decoratedReader = new DGReaderDecorator(reader);
  }

  //show that data is equal for object and decorated object,
  //then even though same cleanData() method is called, decorated
  //object adds functionality and data is not the same
  @Test
  public void testDecoration() throws Exception {
    assertEquals(reader.getData(),decoratedReader.getData());
    decoratedReader.cleanData(); //this just calls the cleanData() method in the reader object so data should be equal
    assertEquals(reader.getData(),decoratedReader.getData());
    Set<String> markets = new HashSet<>();
    markets.add("top_5");
    markets.add("top_10");
    markets.add("top_20");
    decoratedReader = new MultipleMarkets(decoratedReader,markets);
    decoratedReader.cleanData();
    assertNotEquals(reader.getData(),decoratedReader.getData());
  }

}
