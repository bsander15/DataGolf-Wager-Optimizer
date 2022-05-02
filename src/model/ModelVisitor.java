package model;

/**
 * 
 * @author Bernie Sander
 * 
 * ModelVisitor interface, defines vistWagerModel method
 * all ModelVisitors need to implement
 *
 */
public interface ModelVisitor {
  
  /**
   * Visitor method for WagerModel
   * 
   * @param m IModel to be visited
   */
  void visitWagerModel(IModel m);
}
