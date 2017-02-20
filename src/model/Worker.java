package model;

import utilities.Visitor;

public class Worker extends Unit{


	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}
