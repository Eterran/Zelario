package Entity.Monster;
import Entity.Entity;
import Monster.Goblin;
import Monster.Harpy;
import Monster.Witch;
import Monster.Orc;
import Monster.Skeleton;
public class Monster extends Entity{
    Goblin goblinObj = new Goblin("Goblin", 40, 0, 10, 0, 5, 2);
        Harpy harpyObj = new Harpy("Harpy", 60, 20, 14, 10, 8, 8, 100, 150, 4, 6);
        Witch witchObj = new Witch("Witch", 40, 0, 10, 0, 5, 2, 100, 4);
        Orc orcObj = new Orc("Orc", 70, 0, 15, 0, 9, 4);
        Skeleton skeletonObj = new Skeleton("Skeleton", 60, 0, 12, 0, 7, 3);
}
