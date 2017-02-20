package model;

import utilities.Visitor;

public class Ranged extends Unit{

	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}
