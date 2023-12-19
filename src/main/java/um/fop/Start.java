import Entitypack.*;
import Entitypack.Monsterpack.Goblin;
import Entitypack.Playerpack.Archer;
import Gamepack.*;

public class Start {
    public static void main(String[] args) {
        Entity player = new Archer();
        Game game = new Game(player);
        while(true)
            game.beginCombat(player, game.spawnRandom());
    }
}