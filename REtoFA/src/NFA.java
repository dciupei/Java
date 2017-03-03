import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

//David Ciupei
//Project #1

public class NFA {
	
	int startState;
	int finalState;
	LinkedList<Transition> states;
	
	public NFA(int sState, int fState, Transition t){
		startState = sState;
		finalState = fState;
		states = new LinkedList<Transition>();
		states.add(t);
	}
	
	private static NFA Concatenation(NFA nfa1, NFA nfa2){
		// transitions the final state of nfa1 to the start state of nfa2 with epsilon
		// nfa1 final state is now nfa2 final state
		nfa1.states.add(new Transition(nfa1.finalState, nfa2.startState, 'E'));
		nfa1.states.addAll(nfa2.states);	 // add transition states from nfa2
		nfa1.finalState = nfa2.finalState;
		return nfa1;
	}
	

	private static NFA Union(int q, NFA nfa1, NFA nfa2){
		// adds a new start state and transitions it to the start state of
		// both nfas with epsilon. Then both final states transition with epsilon
		// to a new final state
		nfa1.states.add(new Transition(q, nfa1.startState,'E'));
		nfa1.states.add(new Transition(q, nfa2.startState,'E'));
		nfa1.states.add(new Transition(nfa1.finalState, q+1, 'E'));
		nfa1.states.add(new Transition(nfa2.finalState, q+1, 'E'));
		nfa1.states.addAll(nfa2.states);	// add transition states from nfa2
		nfa1.startState = q; // the new start state
		nfa1.finalState = q+1; // the new final state
		return nfa1;
	}

	private static NFA kleeneStar(int q, NFA nfa){
		// adds a new start state which is q and epsilons it to the start state
		// from the final state it goes to the new start state
		nfa.states.add(new Transition(q, nfa.startState, 'E'));
		nfa.states.add(new Transition(nfa.finalState, q, 'E'));
		nfa.startState = q; // assigns the new start stats
		nfa.finalState = q; // assigns the new final state 
		
		return nfa;
	}
	
	
	public static NFA postfix2nfa(String postfix, Scanner data){
		LinkedList<NFA> list = new LinkedList<NFA>();
		NFA nfa1 = null;
		NFA nfa2 = null;
		int q = 1;		// state number 
		for (int i = 0; i < postfix.length(); i++){
			char c = postfix.charAt(i);
			if (c == '&'){		// checks for & then jumps to concatenation
				nfa2 = list.pop();
				nfa1 = list.pop();
				list.push(Concatenation(nfa1, nfa2));
			}else if (c == '+'){		// checks if + then jumps to union
				nfa2 = list.pop();
				nfa1 = list.pop();
				list.push(Union(q,nfa1,nfa2));
				q+=2;			// a new start and final state is added
			}else if (c == '*'){	// if * jumps to kleenestar method with nfa1
				nfa1 = list.pop();
				list.push(kleeneStar(q, nfa1));
				q++;			// a new start state is added
			}else if (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e'){  // if its a single character
				list.push(new NFA(q, q+1, new Transition(q, q+1, postfix.charAt(i))));	// just makes the 2 states with the letter as a transition
				q+=2;			// 2 new states are added for each character
			}else if(c == 0){
				break;
			}else{
				System.out.println("Error! Wrong character or symbol!");
				continue;
			}
				
		}
		
		NFA nfaprint = list.pop();	// pops the nfa for printing
		
		return nfaprint;
			
	}
	
	public String Print(){	
		System.out.println("\nStart state: q" + this.startState + "\nFinal state: q" + this.finalState);
		System.out.println("\nTransition Table:\n" + this.states.toString().replace("[", "").replace("]", "").replace(",",""));
		return ("------------------------------------");

	}
	
	
	public static void main (String arg[]) throws FileNotFoundException {
		Scanner input = new Scanner (new File("RE.txt"));	//I read from a text file that is in the same directory
		while(input.hasNextLine()){
			String RE = input.nextLine();
			System.out.println("Postfix Expression: "+ RE);
			NFA states = postfix2nfa(RE, input); 
			System.out.println(states.Print());
		}
			input.close();
	}
}
