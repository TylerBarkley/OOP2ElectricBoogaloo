package model.Controllables;
import utilities.Visitor;

public class Worker{

	//private PID myPID;

	public void upkeep(int food) {
		//TODO just bulking
		//if(food < 1){
			//this.killMe();
		//}

	}

	public void killMe(){
		//TODO kill me
		//TODO me_irl
		//pm.removeWorker(myPID);
	}

	public void accept(Visitor visitor){
		visitor.visit(this);
	}

}
