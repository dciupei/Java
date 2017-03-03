
public class SimpleEliza {
	
	
	
		public boolean hasMoreQuestions(){
			return true;
		}
		public String askQuestion(){
			if (hasMoreQuestions() == true);
			String [] Questions = {"How is your day?","What is your favorite car?","What are you majoring in?",
					"Where is your favorite place to eat?", "Have you seen the movie ride along?"};
			//System.out.println("How are you");
			for (int i = 0;i<Questions.length;i++);
				
			int firstLength = Questions.length;
			int random1 = (int) (Math.random()*firstLength);
			//String one = Integer.toString(random1);
			String one = Questions[random1];
			
			return one;
			
		
		};
		public String listen(String statement){
			String[] positive = {"adore","love","enjoy","ADORE","LOVE","ENJOY"};
			if(statement.contains(positive[0])){
			
			return ("That's nice");
			}
			if(statement.contains(positive[1])){
			
				return ("That's nice");
			}
			if(statement.contains(positive[2])){

			return ("Thats's nice");
			}
			if(statement.contains(positive[3])){

				return ("Thats's nice");
				}
			if(statement.contains(positive[4])){

				return ("Thats's nice");
				}
			if(statement.contains(positive[5])){

				return ("Thats's nice");
				}
			String [] negative = {"dislike","hate","despise","DISLIKE","HATE","DESPISE"};
			if(statement.contains(negative[0])){
				return("Yikes");
			}
			if(statement.contains(negative[1])){
				return("Yikes");
			}
			if(statement.contains(negative[2])){
				return("Yikes");
			}
			if(statement.contains(negative[3])){
				return("Yikes");
			}
			if(statement.contains(negative[4])){
				return("Yikes");
			}
			if(statement.contains(negative[5])){
				return("Yikes");
			}

			else;
			return "";
		}


		
}		
		
		


