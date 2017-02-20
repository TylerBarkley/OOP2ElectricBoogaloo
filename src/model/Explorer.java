package model;

import utilities.Visitor;

public class Explorer extends Unit{

	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}
