package model;

import utilities.Visitor;

public class Colonist extends Unit{

	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}
