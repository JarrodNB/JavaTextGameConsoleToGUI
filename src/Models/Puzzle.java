
package Models;

import java.io.Serializable;

public class Puzzle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3268064475105672974L;
	private int id;
	private String question; 
	private String answer; 
	private String hint; 
	private Item itemReward;
	private int goldReward;
	
	public Puzzle(int id, String question, String answer, String hint, Item itemReward, int goldReward) {
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.hint = hint;
		this.itemReward = itemReward;
		this.goldReward = goldReward;
	}
	
	public int getId() {
		return id;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public String getHint() {
		return hint;
	}
	
	public Item getItemReward() {
		return itemReward;
	}
	
	public int getGoldReward() {
		return goldReward;
	}
	

}
