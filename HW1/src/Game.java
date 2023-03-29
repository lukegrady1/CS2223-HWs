import java.util.HashMap;

public class Game {
    private HashMap<String, Integer> pieces = new HashMap<>();

    //Setup of the game board
    public Game(){
        pieces.put("green", 3);
        pieces.put("yellow", 7);
        pieces.put("red", 5);
    }

    //display of the game board
    public void displayGame(){
        System.out.println("\nGame Board");
        System.out.print("Green Pieces (" + pieces.get("green") + "): ");
        for(int i = 0; i < pieces.get("green"); i++){
            System.out.print(" g ");
        }
        System.out.println();

        System.out.print("Yellow Pieces (" + pieces.get("yellow") + "):");
        for(int i = 0; i < pieces.get("yellow"); i++){
            System.out.print(" y ");
        }
        System.out.println();

        System.out.print("Red Pieces (" + pieces.get("red") + "):");
        for(int i = 0; i < pieces.get("red"); i++){
            System.out.print(" r ");
        }
        System.out.println();
    }

    //Sees if user move is valid and if game can continue
    public boolean isTurnValid(String color, int number){
        if(pieces.get(color) == null || pieces.get(color) < number || number <= 0){
            return false;
        }
        return true;
    }

    //updates the game board after a user or computer move
    public void updateGame(String color, int number){
        pieces.replace(color, pieces.get(color) - number);
    }

    //See if game has ended or not
    public boolean isGameOver(){
        int tilesLeft = pieces.get("green") + pieces.get("yellow") + pieces.get("red");
        return tilesLeft == 0;
    }

    public HashMap<String, Integer> getMap(){
        return pieces;
    }
}