
package DataStorage;

import Models.Item;
import Models.Puzzle;

public class GamePuzzles {

	public static Puzzle getFirstPuzzle() {
		return new Puzzle(0, "What are Saturn’s Rings made of?", "Ice", "Dust, Ice, or Boron", GameItems.getLaserRifle(1), 0);
	}
	
	public static Puzzle getSecondPuzzle() {
		return new Puzzle(1, "True or False: Saturn is less dense than water.", "True", "Saturn can float on water", null, 100);
	}
	
	public static Puzzle getThirdPuzzle() {
		return new Puzzle(2, "Saturn was Discovered by?", "Galileo", "Isaac Newton, Copernicus, or Galileo", GameItems.getElixir(1), 0);
	}
	
	public static Puzzle getFourthPuzzle() {
		return new Puzzle(3, "What kind of natural disaster happens on the moon?", "Moonquake", "Not an earthquake", null, 200);
	}
	
	public static Puzzle getFifthPuzzle() {
		return new Puzzle(4, "Sometimes I’m full, but I never overflow. What am I?", "The moon", "Not the sun", null, 100);
	}
	
	public static Puzzle getSixthPuzzle() {
		return new Puzzle(5, "It dances bright, Banishing all but darkest night. Give it food and it will live; Give it water and it will die. What is it?", "Fire", "Everything changed when the __ nation attacked", GameItems.getPlasmaKnife(1), 0);
	}
	
	public static Puzzle getSeventhPuzzle() {
		return new Puzzle(6, "Sometimes you can see my tail. I’m not a planet. I am _.", "Comet", "I visit earth every 76 years", GameItems.getDefenseTurret(), 0);
	}
	
	public static Puzzle getEighthPuzzle() {
		return new Puzzle(7, "How many years does it take for a solar eclipse to occur?", "1.5", "Eighteen months", null, 300);
	}
	
	public static Puzzle getNinthPuzzle() {
		return new Puzzle(8, "How many days does it take mercenary to orbit the sun?", "88", "I ate and ate", null, 100);
	}
	
	public static Puzzle getTenthPuzzle() {
		return new Puzzle(9, "True or False: The Grand Canyon is the biggest canyon in the solar system.", "False", "There is a grander canyon", null, 100);
	}
}
