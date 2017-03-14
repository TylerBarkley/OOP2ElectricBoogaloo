package utilities;

import model.Technology;

/**
 * Created by Trevor on 3/13/2017.
 */
public interface TechnologyVisitor {
    public abstract void visit(Technology technology);
}
