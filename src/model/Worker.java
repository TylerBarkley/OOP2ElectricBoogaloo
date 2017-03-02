package model;

import utilities.Visitor;

public class Worker{


	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}
