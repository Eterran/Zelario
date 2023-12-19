import Entitypack.*;
import Entitypack.Monsterpack.Goblin;
import Entitypack.Playerpack.Archer;
import Gamepack.*;


public class Start {
    public static void main(String[] args) {
        Game game = new Game();
        Entity player = new Archer();
        Entity monster = new Goblin();
        game.beginCombat(player, monster);
    }
}