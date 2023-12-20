import Entitypack.*;
import Entitypack.Playerpack.*;
import Gamepack.*;

public class Start {
    public static void main(String[] args) {
        Entity player = new Paladin();
        Game game = new Game(player);
        while(true)
            game.beginCombat(player, game.spawnRandom());
    }
}