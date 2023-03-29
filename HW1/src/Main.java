import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        //game references game board and not a new game
        Game game = new Game();
        Computer computer = new Computer();

        //1 is for user, 2 is for the computer
        int whosTurn = 0;

        //#1 answers
        System.out.println("#1 Double trouble is also called Nim and was solved by Charles L. Bouton in 1901. \n    Today, there are many instantiations of the game, including 15 Sticks and Mancala.");


        //first have to input who goes first
        System.out.println("\nWho makes the first move? 1 for user, 2 for computer: ");
        whosTurn = Integer.parseInt(scan.nextLine());

        while(whosTurn != 1 && whosTurn != 2){
            System.out.print("Please enter either 1 or 2 (1 for user, 2 for computer): ");
            whosTurn = Integer.parseInt(scan.nextLine());
        }

        //while loop to continue game until it's finished
        while(!game.isGameOver()) {

            //if the game starts with the computers turn
            if (whosTurn == 2) {
                game.displayGame();
                String move = computer.takeTurn(game);
                int num = Integer.parseInt(move.substring(move.indexOf(" ") + 1, move.length()));
                String color = move.substring(0, move.indexOf(" "));
                game.updateGame(color, num);
                System.out.print("The computer chose to take " + num + " piece(s) from " + color + "!\n");
                whosTurn = 1;

                //if the game starts with the users turn
            } else {
                boolean turnValid = false;
                game.displayGame();
                System.out.print("Please enter a color and a number that you want gone. For example, yellow 1: ");
                String input;


                String color = "";
                int number = 0;

                //Makes sure input is true, using the boolean turnValid and therefore valid
                while(true){
                    input = scan.nextLine();
                    if(input.contains(" ")) {
                        color = input.substring(0, input.indexOf(" "));
                        number = Integer.parseInt(input.substring(input.indexOf(" ") + 1, input.length()));
                    }
                    //If not valid prints out saying to enter valid turn
                    if(game.isTurnValid(color, number)){
                        break;
                    } else {
                        System.out.print("Please enter a valid move. For example, yellow 1: ");
                    }
                }

                //if the move is valid swap turns over to computer
                if (game.isTurnValid(color, number)) {
                    game.updateGame(color, number);
                }
                whosTurn = 2;
            }
        }

        //Once game gets finished
        game.displayGame();
        System.out.println("Game over.");
        //Checks who had last move to determine winner
        System.out.println((whosTurn == 2 ? "You win! Great Job!" : "Computer wins. Better luck next time!"));

    }
}