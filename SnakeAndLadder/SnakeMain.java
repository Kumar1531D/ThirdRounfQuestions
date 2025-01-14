package snakeandladder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SnakeMain {
	
	private int boardSize;
	private Map<Integer,Integer> snakes;
	private Map<Integer,Integer> ladders;
	private List<Player> players;
	
	SnakeMain(int boardSize){
		this.boardSize = boardSize;
		snakes = new HashMap<>();
		ladders = new HashMap<>();
		players = new ArrayList<>();
	}
	
	public void addSnake(int head,int tail) {
		snakes.put(head, tail);
	}
	
	public void addLadder(int start,int end) {
		ladders.put(start, end);
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void startGame() throws InterruptedException {
		
		boolean winOrNot = false;
		Random rand = new Random();
		
		while(!winOrNot) {
			for(Player player : players) {
				
				int diceRoll = rand.nextInt(6)+1;
				int oldPosition = player.getPosition();
				int newPosition = oldPosition+diceRoll;
				
				if(newPosition>boardSize*boardSize) {
					System.out.println(player.getName()+" : New dice roll make position out of bounds so no position change!!!");
				}
				else if(snakes.containsKey(newPosition)) {
					System.out.println(player.getName()+" rolled "+diceRoll+" Initially at position "+oldPosition+" "+ " move to "+newPosition+" and hit by snake and move to "+snakes.get(newPosition) );
					newPosition = snakes.get(newPosition);
//					player.setPosition(pos);
				}
				else if(ladders.containsKey(newPosition)) {
					System.out.println("Ladder");
					System.out.println(player.getName()+" rolled "+diceRoll+" Initially at position "+oldPosition+" and move to "+newPosition+" and climbed the ladder to "+ladders.get(newPosition));
					newPosition = ladders.get(newPosition);
//					player.setPosition(pos);
				}
				else {
					System.out.println(player.getName()+" rolled "+diceRoll+" and move from "+oldPosition+" to "+newPosition);
				}
				
				player.setPosition(newPosition);
				
				if(newPosition==boardSize*boardSize) {
					System.out.println(player.getName()+" win the game :)");
					winOrNot = true;
					break;
				}
				
//				Thread.sleep(1000);
				
			}
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter the boardSize");
		int boardSize = in.nextInt();
		
		SnakeMain board = new SnakeMain(boardSize);
		
		System.out.println("Enter the number of Snakes");
		int noOfSnakes = in.nextInt();
		
		System.out.println("Enter the Snake's Position (Head,Tail)");
		for(int i=0;i<noOfSnakes;i++) {
			int head = in.nextInt();
			int tail = in.nextInt();
			
			if(head>boardSize*boardSize || tail>boardSize*boardSize) {
				System.out.println("Ladder's Position is Invalid! Enter the position between 1 and "+boardSize*boardSize);
				i--;
				continue;
			}
			
			board.addSnake(head, tail);
		}
		
		System.out.println("Enter the number of Ladders");
		int noOfLadders = in.nextInt();
		
		System.out.println("Enter the Ladder's Position (Start,End)");
		for(int i=0;i<noOfLadders;i++) {
			
			int start = in.nextInt();
			int end = in.nextInt();
			
			if(board.snakes.containsKey(start)) {
				System.out.println("A Snake's head is already In this start point! please enter another position");
				i--;
				continue;
			}
			
			if(start>boardSize*boardSize || end>boardSize*boardSize) {
				System.out.println("Snake's Position is Invalid! Enter the position between 1 and "+boardSize*boardSize);
				i--;
				continue;
			}
			
			board.addLadder(start, end);
		}
		
		System.out.println("Enter the Number of Players");
		int noOfPlayers = in.nextInt();
		
		for(int i=0;i<noOfPlayers;i++) {
			System.out.println("Enter the Player "+(i+1)+" name : ");
			String name = in.next();
			in.nextLine();
			board.addPlayer(new Player(name));
		}
		
		board.startGame();
		
		in.close();

	}

}


/*
Output 

Enter the boardSize
7
Enter the number of Snakes
3
Enter the Snake's Position (Head,Tail)
44 1
25 6
48 33
Enter the number of Ladders
3
Enter the Ladder's Position (Start,End)
17 47
5 11
23 36
Enter the Number of Players
4
Enter the Player 1 name : 
Kumar
Enter the Player 2 name : 
Anbu
Enter the Player 3 name : 
Naveen
Enter the Player 4 name : 
Chandru
Kumar rolled 1 and move from 0 to 1
Anbu rolled 3 and move from 0 to 3
Naveen rolled 1 and move from 0 to 1
Chandru rolled 3 and move from 0 to 3
Kumar rolled 3 and move from 1 to 4
Anbu rolled 1 and move from 3 to 4
Ladder
Naveen rolled 4 Initially at position 1 and move to 5 and climbed the ladder to 11
Chandru rolled 4 and move from 3 to 7
Kumar rolled 4 and move from 4 to 8
Anbu rolled 3 and move from 4 to 7
Naveen rolled 3 and move from 11 to 14
Chandru rolled 3 and move from 7 to 10
Kumar rolled 5 and move from 8 to 13
Anbu rolled 1 and move from 7 to 8
Naveen rolled 2 and move from 14 to 16
Chandru rolled 4 and move from 10 to 14
Ladder
Kumar rolled 4 Initially at position 13 and move to 17 and climbed the ladder to 47
Anbu rolled 3 and move from 8 to 11
Naveen rolled 4 and move from 16 to 20
Chandru rolled 5 and move from 14 to 19
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 5 and move from 11 to 16
Naveen rolled 6 and move from 20 to 26
Chandru rolled 2 and move from 19 to 21
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 5 and move from 16 to 21
Naveen rolled 6 and move from 26 to 32
Chandru rolled 3 and move from 21 to 24
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 4 Initially at position 21  move to 25 and hit by snake and move to 6
Naveen rolled 3 and move from 32 to 35
Chandru rolled 1 Initially at position 24  move to 25 and hit by snake and move to 6
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 4 and move from 6 to 10
Naveen rolled 2 and move from 35 to 37
Chandru rolled 3 and move from 6 to 9
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 2 and move from 10 to 12
Naveen rolled 6 and move from 37 to 43
Chandru rolled 3 and move from 9 to 12
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 1 and move from 12 to 13
Naveen rolled 5 Initially at position 43  move to 48 and hit by snake and move to 33
Ladder
Chandru rolled 5 Initially at position 12 and move to 17 and climbed the ladder to 47
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 3 and move from 13 to 16
Naveen rolled 5 and move from 33 to 38
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 4 and move from 16 to 20
Naveen rolled 3 and move from 38 to 41
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 6 and move from 20 to 26
Naveen rolled 4 and move from 41 to 45
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 3 and move from 26 to 29
Naveen rolled 3 Initially at position 45  move to 48 and hit by snake and move to 33
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 5 and move from 29 to 34
Naveen rolled 4 and move from 33 to 37
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 1 and move from 34 to 35
Naveen rolled 1 and move from 37 to 38
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 6 and move from 35 to 41
Naveen rolled 4 and move from 38 to 42
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 2 and move from 41 to 43
Naveen rolled 4 and move from 42 to 46
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 1 Initially at position 43  move to 44 and hit by snake and move to 1
Naveen rolled 2 Initially at position 46  move to 48 and hit by snake and move to 33
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 6 and move from 1 to 7
Naveen rolled 4 and move from 33 to 37
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 2 and move from 7 to 9
Naveen rolled 2 and move from 37 to 39
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu rolled 4 and move from 9 to 13
Naveen rolled 2 and move from 39 to 41
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Ladder
Anbu rolled 4 Initially at position 13 and move to 17 and climbed the ladder to 47
Naveen rolled 6 and move from 41 to 47
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu : New dice roll make position out of bounds so no position change!!!
Naveen rolled 1 Initially at position 47  move to 48 and hit by snake and move to 33
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu : New dice roll make position out of bounds so no position change!!!
Naveen rolled 6 and move from 33 to 39
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu : New dice roll make position out of bounds so no position change!!!
Naveen rolled 6 and move from 39 to 45
Chandru : New dice roll make position out of bounds so no position change!!!
Kumar : New dice roll make position out of bounds so no position change!!!
Anbu : New dice roll make position out of bounds so no position change!!!
Naveen rolled 4 and move from 45 to 49
Naveen win the game :)



*/



