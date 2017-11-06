package Controllers;

import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.MonsterIsDeadException;
import GameExceptions.PlayerIsDeadException;
import Models.Monster;
import Models.Player;
import Models.RoomMonster;

public class Arena {

    private static final int DEFEND = 2; // defense to be added when defending
    private static final String HELP = "Your possible commands are \"attack\", \"defend\", \"open inventory\", and \"retreat\".";

    public static void fight(Player player, RoomMonster roomMonster) throws PlayerIsDeadException {
        Monster monster = roomMonster.getMonster();
        System.out.println("You have entered into a fight with " + monster.getName() + ".");
        try {
            while (true) {
                String userInput = GameEngine.nextLine().toLowerCase();
                if (userInput.equals("retreat")) {
                    monster.heal(monster.getMaxHealth());
                    System.out.println("You ran away like a coward and they have lost interest in you, though you are still in the same room");
                    return;
                }
                fightInput(player, monster, userInput);
            }
        } catch (MonsterIsDeadException dead) {
            try {
                System.out.println("You defeated " + monster.getName());
                if (monster.getInventory() != null) {
                    player.getInventory().addInventory(monster.getInventory());
                    System.out.println("You have received " + monster.getInventory().toString() + ".");
                }

                if (monster.getGold() != 0) {
                    player.setGold(player.getGold() + monster.getGold());
                    System.out.println("You have recieved " + monster.getGold() + " gold!");
                }
                System.out.println("You are back in the same room.");
                roomMonster.setIsInRoom(false);
            } catch (CharacterException | ItemException e) {
                System.out.println(e.getMessage());
            }
        } finally {
        }
    }

    private static void determineDamage(Player player, Monster monster, int defense) throws PlayerIsDeadException, MonsterIsDeadException {
        int playerDamage = monster.getBaseAttack() - defense;
        if (playerDamage < 1) {
            System.out.println(monster.getName() + " was unable to damage you!");
        } else {
            System.out.println(monster.getName() + " attacked you and did " + playerDamage + " damage!");
            player.takeDamage(playerDamage);
        }
    }

    private static void fightInput(Player player, Monster monster, String userInput) throws PlayerIsDeadException, MonsterIsDeadException {
        if (userInput.equals("")) {
            return;
        }
        switch (userInput) {

            case "attack":
                monster.takeDamage(player.getCalcAttack());
                System.out.println("You dealt " + player.getCalcAttack() + " damage!");
                determineDamage(player, monster, player.getCalcDefense());
                break;

            case "defend":
                determineDamage(player, monster, player.getCalcDefense() + DEFEND);
                break;

            case "help":
                System.out.println(HELP);
                break;
            default:
                System.out.println(GameEngine.UNRECOGNIZED_COMMAND);
        }
    }
}
