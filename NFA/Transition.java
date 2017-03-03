//David Ciupei
//Project #1

public class Transition {

	int StartState;
	int FinalState;
	char character;
	
	//for each new transition that is added
	Transition(int sState, int fState, char a){
		StartState = sState;
		FinalState = fState;
		character = a;
	}
	
	//prints the transitions into a transition table 
	public String toString(){
		return"(q" + this.StartState + "; " + this.character +") -> q" + this.FinalState + "\n";
	}


}
	

