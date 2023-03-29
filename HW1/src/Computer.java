import java.util.HashMap;

public class Computer {

    public Computer() {

    }

    //computer to make its moves
    public String takeTurn(Game game) {
        String turn = "";

        int sumOfNim = this.calculateSumOfNim(game);
        //if sumOfNim is 0, make a move at random.  if sumOfNim != 0, make the most optimal move possible

        HashMap<String, Integer> nim = game.getMap();

        //Computer makes a winning turn if possible
        if (this.calculateSumOfNim(game) != 0) {
            for (String key : nim.keySet()) {
                if ((nim.get(key) ^ sumOfNim) < nim.get(key)) {
                    turn += key + " " + (nim.get(key) - (nim.get(key) ^ sumOfNim));
                    break;
                }
            }

            //else, computer makes a randomized turn if winning isn't possible
        } else {
            String color = "";
            int randColor = (int) (Math.random() * 3);
            if (randColor == 0) {
                color = "green";
            } else if (randColor == 1) {
                color = "yellow";
            } else if (randColor == 2) {
                color = "red";
            }

            int randNum = (int) (Math.random() * nim.get(color) + 1);

            turn += color + " " + randNum;
        }

        return turn;
    }

    //calculates the sum of nim to let the computer know to make an optimal or random turn
    private int calculateSumOfNim(Game game) {
        int sumOfNim = 0;
        for (String key : game.getMap().keySet()) {
            sumOfNim = sumOfNim ^ game.getMap().get(key);
        }
        return sumOfNim;
    }
}