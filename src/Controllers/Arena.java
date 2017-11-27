package Controllers;

import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.MonsterIsDeadException;
import GameExceptions.PlayerIsDeadException;
import Models.Monster;
import Models.Player;
import Models.RoomMonster;
import Sound.SoundPlayer;

/**
 * Controller for handling the games fighting system
 * @author jnbcb
 */
public class Arena {

    private static final int DEFEND = 2; // defense to be added when defending
    private static final String HELP = "Your possible commands are \"attack\", \"defend\", \"open inventory\", and \"retreat\".";

    /**
     * Enters the player and monster into a fight
     * @param player
     * @param roomMonster
     * @throws PlayerIsDeadException 
     */
    public static void fight(Player player, RoomMonster roomMonster) throws PlayerIsDeadException {
        Monster monster = roomMonster.getMonster();
        System.out.println("You have entered into a fight with " + monster.getName() + ".");
        try {
            while (true) {
                String userInput = GameEngine.nextLine().toLowerCase();
                if (userInput.equals("retreat")) {
                    (new SoundPlayer()).pPlay(SoundPlayer.RETREAT);
                    monster.heal(monster.getMaxHealth());
                    System.out.println("You ran away like a coward and they have lost interest in you, though you are still in the same room");
                    return;
                }
                fightInput(player, monster, userInput);
            }
        } catch (MonsterIsDeadException dead) {
            try {
                (new SoundPlayer()).pPlay(SoundPlayer.VICTORY);
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

    /**
     * Determines the damage player will take and deals it
     * @param player
     * @param monster
     * @param defense
     * @throws PlayerIsDeadException
     * @throws MonsterIsDeadException 
     */
    private static void determineDamage(Player player, Monster monster, int defense) throws PlayerIsDeadException, MonsterIsDeadException {
        int playerDamage = monster.getBaseAttack() - defense;
        if (playerDamage < 1) {
            System.out.println(monster.getName() + " was unable to damage you!");
        } else {
            System.out.println(monster.getName() + " attacked you and did " + playerDamage + " damage!");
            player.takeDamage(playerDamage);
        }
        if (player.getHealthPoints() < 10) {
            //SoundPlayer.play(SoundPlayer.LOW_HP);
        }
    }

    /**
     * Parses user input and takes appropriate action
     * @param player
     * @param monster
     * @param userInput
     * @throws PlayerIsDeadException
     * @throws MonsterIsDeadException 
     */
    private static void fightInput(Player player, Monster monster, String userInput) throws PlayerIsDeadException, MonsterIsDeadException {
        if (userInput.equals("")) {
            return;
        }
        switch (userInput) {

            case "attack":
                (new SoundPlayer()).pPlay(SoundPlayer.ATTACK);
                monster.takeDamage(player.getCalcAttack());
                System.out.println("You dealt " + player.getCalcAttack() + " damage!");
                determineDamage(player, monster, player.getCalcDefense());
                break;

            case "defend":
                (new SoundPlayer()).pPlay(SoundPlayer.DEFEND);
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
