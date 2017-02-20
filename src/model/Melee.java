package model;

import utilities.Visitor;

public class Melee extends Unit{

	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}
