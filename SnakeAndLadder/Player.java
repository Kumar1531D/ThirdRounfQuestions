package snakeandladder;

public class Player {
	
	private int position;
	private String name;
	
	Player(String name){
		position = 0;
		this.name = name;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public String getName() {
		return name;
	}
	

}
