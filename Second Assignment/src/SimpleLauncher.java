import java.util.Scanner;

public class SimpleLauncher {

    public static void main(String [] args) {

        SimpleEliza eliza = new SimpleEliza();
        Scanner userInput = new Scanner(System.in);
        String response;

        while(eliza.hasMoreQuestions()) {
            System.out.println(eliza.askQuestion());

            // userInput.hasNext() will return true until
            // you type 'Control-D' on a mac, or Control-Z on
            // a windows machine.  if that happens, we need
            // to break out of the loop and stop.
            if (!userInput.hasNext()) break;

            response = eliza.listen(userInput.nextLine());
            if (response != null) {
                System.out.println(response);
            }
        }
    }
}

