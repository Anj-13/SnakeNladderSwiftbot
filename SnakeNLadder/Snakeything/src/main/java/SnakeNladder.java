import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

import swiftbot.*;

public class SnakeNladder {
	// Put the game code into button A and it should be in method
	static Scanner A = new Scanner(System.in);
	static String Player_A = "", Player_Swiftbot = "Swiftbot";
	static int Player_A1 = 0, Player_Swiftbot1 = 0;
//	private static int ABC = 0;
	private static boolean pressed = false;
	static Random R = new Random();
	// this is for the board 
	private static int[][] board = new int[5][5];
	private static int LadderTop;
	private static int LadderBottom;
	private static int SnakeHead;
	private static int randomColumn;
	private static int randomColumn2;
	private static int LadderTop2, LadderBottom2, SnakeHead2, SnakeTail2, SnakeTail, randomColumn3, randomColumn4;
	static int position1 = 0, position2 = 0;
	// instance variable in class so all method can access it.
	static int tempasignPlayer, tempasignSwiftbot; // this is to assign who's first in game loop
	private static int dice;
	public static void main(String[] args) {
		Sharing.API1.enableButton(Button.X, () -> {
			System.out.println("Shutting down...");
			Exit();
		});
		Sharing.Space();
		System.out.println("\nWelcome to the game\n");
		Sharing.Space();
		String title =
				"\033[32m  ____              _               \033[0m           _   \033[31m_              _     _           \033[0m\r\n"
						+ "\033[32m / ___| _ __   __ _| | _____    \033[0m__ _ _ __   __| | \033[31m| |    __ _  __| | __| | ___ _ __ \033[0m\r\n"
						+ "\033[32m \\___ \\| '_ \\ / _` | |/ / _ \\ \033[0m / _` | '_ \\ / _` | \033[31m| |   / _` |/ _` |/ _` |/ _ \\ '__|\033[0m\r\n"
						+ "\033[32m  ___) | | | | (_| |   <  __/ \033[0m| (_| | | | | (_| | \033[31m| |__| (_| | (_| | (_| |  __/ |   \033[0m\r\n"
						+ "\033[32m |____/|_| |_|\\__,_|_|\\_\\___|\033[0m  \\__,_|_| |_|\\__,_| \033[31m|_____\\__,_|\\__,_|\\__,_|\\___|_|   \033[0m\r\n";
		System.out.println(title + "\n");
		Sharing.Space();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\nPress [Y] to start the game");

		Sharing.API1.enableButton(Button.Y, () -> {
			Sharing.Space();
			System.out.println("\nReadying...");
			Player_A = "";
			if (Player_A.isBlank()) {
				Sharing.Name();
			}
			if (!Player_A.isBlank()) {
				System.out.println("\nPress [B] to see who goes first");
			}
		});
		// starting game and assign player name

		Sharing.API1.enableButton(Button.B, () -> {
			Sharing.Space();
			WHOFIRST();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Sharing.Space();
			System.out.println("\nPlease wait a moment \nCreating board now\n");
			Sharing.Space();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Board();
			Sharing.Space();
			System.out.println("\nPlayer please roll your dice!\nPress [A] to roll your dice!");
			Sharing.Space();
		});

		Sharing.API1.enableButton(Button.A, () -> {
			if (pressed == true) {
					try {
						Sharing.resetGame();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				pressed = false;
			} else if (tempasignPlayer == 1 && tempasignSwiftbot == 2) {
				System.out.println("Player " +Player_A+ " first!");
				Gameloop();
			} else {
				System.out.println("SwiftBot first!");
				Gameloop2();
			}
		});
	}

	public static void Board() {
		board = new int [5][5];

		int counter = 1;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[i][j] = (counter);
				counter++;
			}
		}
		int [] testdata = Sharing.snakeassign(board, R);
		LadderTop = testdata[0];
		LadderBottom = testdata[1];
		SnakeHead = testdata[2];
		SnakeTail = testdata[3];
		randomColumn = testdata[4];
		randomColumn2 = testdata[5];
		LadderTop2 = testdata[6];
		LadderBottom2 = testdata[7];
		SnakeHead2 = testdata[8];
		SnakeTail2 = testdata[9];
		randomColumn3 = testdata[10];
		randomColumn4 = testdata[11];


		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if ((i == LadderTop && j == randomColumn) || (i == LadderBottom && j == randomColumn) || (i == LadderTop2 && j == randomColumn3) || (i == LadderBottom2 && j == randomColumn3)) {
					System.out.print(String.format("%-4s", "| "+ "\u001B[33m" + board[i][j]) + "\u001B[0m " +"| ");
				}
				else if ((i == SnakeHead && j == randomColumn) || (i == SnakeTail && j == randomColumn2) || (i == SnakeHead2 && j == randomColumn3) || (i == SnakeTail2 && j == randomColumn4)){
					System.out.print(String.format("%-4s", "| "+ "\u001B[32m" + board[i][j]) + "\u001B[0m " +"| ");
				}else { // changed this, to print the board number
					System.out.print(String.format("%-4s", "| " + board[i][j]) + " | ");
				}
			} 
			System.out.println();
		}
		System.out.println("Board setup is done!");

	}
// UIboard to print in game loop
	public static void UIboard() {
		int counter1 = 1, counter2 = 1;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (counter1 == position1) {
					System.out.print(String.format("%-4s", "| \u001B[31mP1\u001B[0m |"));
				} else if (counter2 == position2) {
					System.out.print(String.format("%-4s", "| \u001B[34mP2\u001B[0m |"));
				} else { // changed this, to print the board number
					if ((i == LadderTop && j == randomColumn) || (i == LadderBottom && j == randomColumn) || (i == LadderTop2 && j == randomColumn3) || (i == LadderBottom2 && j == randomColumn3)) {
						System.out.print(String.format("%-4s", "| "+ "\u001B[33m" + board[i][j]) + "\u001B[0m " +"| ");
					}
					else if ((i == SnakeHead && j == randomColumn) || (i == SnakeTail && j == randomColumn2) || (i == SnakeHead2 && j == randomColumn3) || (i == SnakeTail2 && j == randomColumn4)){
						System.out.print(String.format("%-4s", "| "+ "\u001B[32m" + board[i][j]) + "\u001B[0m " +"| ");
					}else { // changed this, to print the board number
						System.out.print(String.format("%-4s", "| " + board[i][j]) + " | ");
					}
				}
				counter1++; // error here because i put it inside else{}
				counter2++;
			} 
			System.out.println();
			// array board
		}
	}
	// For when Player A is first
	public static void Gameloop() {

		System.out.println("*********************************************************");
		System.out.println("Press [A] to roll the dice");
		System.out.println("*********************************************************");



		//**************************************************************************************

		try {
			if (position1 == 0 ) System.out.println("Player " + Player_A +" current position is " + position1 );
			if (position2 == 0) System.out.println("Player Swiftbot current position is " + position2 );
			if (position1 != 25 && position1 < 25 && position2 != 25 && position2 < 25) {
				dice = Sharing.rolleddice();
				System.out.println("Player's "+Player_A+" Dice roll: " + dice);
				position1 += dice; // changed this because it only put it in dice number but didn't add from previous position
				if (position1 > 25) {
					position1 = 25;
					pressed = true;
				}

				if (position1 == board[LadderBottom][randomColumn]) {
					position1 = board[LadderTop][randomColumn];
					System.out.println("Congratulation!!! [Player] has landed on square that has the ladder from heaven!!! \r\n"
							+ "\nYour new position is " + position1);
					System.out.println("Player " + Player_A +" current position is " + position1 + "\n");
				} else if (position1 == board[LadderBottom2][randomColumn3]) {
					position1 = board[LadderTop2][randomColumn3];
					System.out.println("Congratulation!!! [Player] has landed on square that has the ladder from heaven!!! \"\r\n"
							+ " \nYour new position is " + position1+ "\n");
					System.out.println("Player " + Player_A +" current position is " + position1 + "\n");
				}else if (position1 == board[SnakeHead][randomColumn]) {
					position1 = board[SnakeTail][randomColumn2];
					System.out.println("Oh no!!! [Player] has landed a square that has the snake head!!! \r\n"
							+ "\nYour new position is " + position1 + "\n");
					System.out.println("Player " + Player_A +" current position is " + position1 + "\n");
				} else if (position1 == board[SnakeHead2][randomColumn3]) {
					position1 = board[SnakeTail2][randomColumn4];
					System.out.println("Oh no!!! [Player] has landed a square that has the snake head!!! \r\n" +"\nYour new position is " + position1);
					System.out.println("Player " + Player_A +" current position is " + position1 + "\n");
				}
				else {
					System.out.println("Player " + Player_A +" current position is " + position1 + "\n");
				}
			}
			if (position1 >= 25) {
				position1 = 25; 
				pressed = true;
				System.out.println("Player " + Player_A +"  win!"); 
				System.out.println("\nPress [X] button to quit the game! \nOr \nPress [A] button to restart the game!");
				return; // STOP further execution
			}

			if (position2 != 25 && position2 < 25 && position1 != 25 && position1 < 25) {
				dice = Sharing.rolleddice();
				System.out.println("SwiftBot's Dice roll: " + dice);
				int newPosition = position2 + dice; // Calculate new position
				if (newPosition > 25) {
					newPosition = 25;
					pressed = true;
				}

				// Move the bot to the new position
				SwiftBotMovement.Moving(position2, newPosition, false);

				// Update position2 after movement
				position2 = newPosition;


				if (position2 == board[LadderBottom][randomColumn]) {
					position2 = board[LadderTop][randomColumn];
					System.out.println("Congratulation!!! [SwiftBot] has landed on square that has the ladder from heaven!!! \r\n" + "\nYour new position is " + position2);
					System.out.println("Player SwiftBot current position is " + position2 + "\n");
				} else if (position2 == board[LadderBottom2][randomColumn3]) {
					position2 = board[LadderTop2][randomColumn3];
					System.out.println("Congratulation!!! [SwiftBot] has landed on square that has the ladder from heaven!!! \r\n" + "\nYour new position is " + position2);
					System.out.println("Player SwiftBot current position is " + position2 + "\n");
				}else if (position2 == board[SnakeHead][randomColumn]) {
					newPosition = board[SnakeTail][randomColumn2];
					System.out.println("Oh no!!! [SwiftBot] has landed on square that has the snake head!!! \r\n"+ "\nYour new position is " + newPosition);
					SwiftBotMovement.Moving(position2, newPosition, true);
					position2 = newPosition;
					System.out.println("Player SwiftBot current position is " + position2 + "\n");
				} else if (position2 == board[SnakeHead2][randomColumn3]) {
					newPosition = board[SnakeTail2][randomColumn4];
					System.out.println("Oh no!!! [SwiftBot] has landed on square that has the snake head!!! \r\n"+ "\nYour new position is " + newPosition);
					SwiftBotMovement.Moving(position2, newPosition, true);
					position2 = newPosition;
					System.out.println("Player SwiftBot current position is " + position2 + "\n");
				} 
				else {
					System.out.println("Player SwiftBot current position is " + position2 + "\n");
				}
			} if (position2 >= 25) {
				position2 = 25; 
				pressed = true;
				System.out.println("Player SwiftBot  win!"); 
				System.out.println("\nPress [X] button to quit the game! \nOr \nPress [A] button to restart the game!");
				// insert press x to end game
				return; // STOP further execution
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		String A ="Player " + Player_A + " 		Swiftbot\\r\\n\";		Swiftbot\r\n";
		UIboard();
		System.out.println(A);
		System.out.println("*********************************************************");
		System.out.println("Current position: \nPlayer " + Player_A +": " + position1 +"\nPlayer SwiftBot: "+ position2);
		System.out.println("*********************************************************");
	} 
// For when Player Swiftbot is first
	public static void Gameloop2() {


		System.out.println("*********************************************************");
		System.out.println("Press [A] to roll the dice");
		System.out.println("*********************************************************");

		//**************************************************************************************
		try {
			if (position2 == 0) System.out.println("Player Swiftbot current position is " + position2 );
			if (position1 == 0 ) System.out.println("Player " + Player_A +" current position is " + position1 );

			if (position2 != 25 && position2 < 25 && position1 != 25 && position1 < 25) {
				dice = Sharing.rolleddice();
				System.out.println("SwiftBot's Dice roll: " + dice);
				int newPosition = position2 + dice; // Calculate new position
				if (newPosition > 25) {
					newPosition = 25;
					pressed = true;
				}

				// Move the bot to the new position
				SwiftBotMovement.Moving(position2, newPosition, false);

				// Update position2 after movement
				position2 = newPosition;


				if (position2 == board[LadderBottom][randomColumn]) {
					position2 = board[LadderTop][randomColumn];
					System.out.println("Congratulation!!! [SwiftBot] has landed on square that has the ladder from heaven!!! \r\n" + "\nYour new position is " + position2);
					System.out.println("Player SwiftBot current position is " + position2 + "\n");
				} else if (position2 == board[LadderBottom2][randomColumn3]) {
					position2 = board[LadderTop2][randomColumn3];
					System.out.println("Congratulation!!! [SwiftBot] has landed on square that has the ladder from heaven!!! \r\n" + "\nYour new position is " + position2);
					System.out.println("Player SwiftBot current position is " + position2 + "\n");
				}else if (position2 == board[SnakeHead][randomColumn]) {
					newPosition = board[SnakeTail][randomColumn2];
					System.out.println("Oh no!!! [SwiftBot] has landed on square that has the snake head!!! \r\n"+ "\nYour new position is " + newPosition);
					SwiftBotMovement.Moving(position2, newPosition, true);
					position2 = newPosition;
					System.out.println("Player SwiftBot current position is " + position2 + "\n");
				} else if (position2 == board[SnakeHead2][randomColumn3]) {
					newPosition = board[SnakeTail2][randomColumn4];
					System.out.println("Oh no!!! [SwiftBot] has landed on square that has the snake head!!! \r\n"+ "\nYour new position is " + newPosition);
					SwiftBotMovement.Moving(position2, newPosition, true);
					position2 = newPosition;
					System.out.println("Player SwiftBot current position is " + position2 + "\n");
				} 
				else {
					System.out.println("Player SwiftBot current position is " + position2 + "\n");
				}
			} 
			if (position2 >= 25) {
				position2 = 25; 
				pressed = true;
				System.out.println("Player Swiftbot win!"); 
				System.out.println("\nPress [X] button to quit the game! \nOr \nPress [A] button to restart the game!");
				return; // STOP further execution
			}
			if (position1 != 25 && position1 < 25 && position2 != 25 && position2 < 25) {
				dice = Sharing.rolleddice();
				System.out.println("Player's "+Player_A+" Dice roll: " + dice);
				position1 += dice; // changed this because it only put it in dice number but didn't add from previous position
				if (position1 > 25) {
					position1 = 25;
					pressed = true;
				}

				if (position1 == board[LadderBottom][randomColumn]) {
					position1 = board[LadderTop][randomColumn];
					System.out.println("Congratulation!!! [Player] has landed on square that has the ladder from heaven!!! \r\n"
							+ "\nYour new position is " + position1);
					System.out.println("Player " + Player_A +" current position is " + position1 + "\n");
				} else if (position1 == board[LadderBottom2][randomColumn3]) {
					position1 = board[LadderTop2][randomColumn3];
					System.out.println("Congratulation!!! [Player] has landed on square that has the ladder from heaven!!! \"\r\n"
							+ " \nYour new position is " + position1);
					System.out.println("Player " + Player_A +" current position is " + position1 + "\n");
				}else if (position1 == board[SnakeHead][randomColumn]) {
					position1 = board[SnakeTail][randomColumn2];
					System.out.println("Oh no!!! [Player] has landed a square that has the snake head!!! \r\n"
							+ "\nYour new position is " + position1);
					System.out.println("Player " + Player_A +" current position is " + position1 + "\n");
				} else if (position1 == board[SnakeHead2][randomColumn3]) {
					position1 = board[SnakeTail2][randomColumn4];
					System.out.println("Oh no!!! [Player] has landed a square that has the snake head!!! \r\n" +"\nYour new position is " + position1);
					System.out.println("Player " + Player_A +" current position is " + position1 + "\n");
				}
				else {
					System.out.println("Player " + Player_A +" current position is " + position1 + "\n");
				}
			}
			if (position1 >= 25) {
				position1 = 25;
				pressed = true;
				System.out.println("Player " + Player_A +"  win!"); 
				System.out.println("\nPress [X] button to quit the game! \nOr \nPress [A] button to restart the game!");
				return; // STOP further execution
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String A ="Player " + Player_A +" 		Swiftbot\r\n";

		UIboard();
		System.out.println(A);
		System.out.println("*********************************************************");
		System.out.println("Current position: \nPlayer SwiftBot: " + position2 + "\nPlayer " + Player_A +": " + position1 + "\n");
		System.out.println("*********************************************************");

	}
// to decide who goes first
	public static void WHOFIRST() {
		int [] WHOFIRST = Sharing.Playerrollthedice();
		int NUM = WHOFIRST[0], NUM1 = WHOFIRST[1];

		//		int[] This = new int[2];
		try {
			if (NUM > NUM1) {
				tempasignPlayer = 1;
				tempasignSwiftbot = 2;
				Thread.sleep(500);
				System.out.println("Player_A: "+ NUM +"\nPlayer Swiftbot: " + NUM1);
				System.out.println("First player will be Player " + Player_A + " then Swiftbot");
			} else {
				tempasignPlayer = 2;
				tempasignSwiftbot = 1;
				Thread.sleep(500);
				System.out.println("Player_A: "+ NUM +"\nPlayer Swiftbot: " + NUM1);
				System.out.println("First player will be Swiftbot then Player " + Player_A );
			} 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
// Exit.
	public static void Exit() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);
		try {
			Thread.sleep(1000); // 1.5 sec wait
			System.out.println("Closing in 2 second.");
			// to inform user that it will close in 2 sec
			Thread.sleep(500);
			int [] green = {0,255,0};
			Sharing.API1.fillUnderlights(green);
			String Bye = "\u001b[36m  ____               ____             \r\n"
					+ " | __ ) _   _  ___  | __ ) _   _  ___ \r\n"
					+ " |  _ \\| | | |/ _ \\ |  _ \\| | | |/ _ \\\r\n"
					+ " | |_) | |_| |  __/ | |_) | |_| |  __/\r\n"
					+ " |____/ \\__, |\\___| |____/ \\__, |\\___|\r\n"
					+ "        |___/              |___/      \033[0m";
			System.out.println(Bye);
			Thread.sleep(500);
			int [] blue = {0,0,255};
			Sharing.API1.fillUnderlights(blue);
			String path = "/data/home/pi/Logfile.txt";
			// The path file will be on Swiftbot

			FileWriter T = new FileWriter(path);
			int ASS = position1, butt = position2;
			int Snake1 = board[SnakeHead][randomColumn], Snake2 = board[SnakeHead2][randomColumn3], Snaketail1 = board[SnakeTail][randomColumn2], Snaketail2 = board[SnakeTail2][randomColumn4];
			int Ladder1 = board[LadderTop][randomColumn], Ladder2 = board[LadderTop2][randomColumn3], Ladderb1 =  board[LadderBottom][randomColumn], Ladderb2 = board[LadderBottom2][randomColumn3];
			T.write("Last position of Player: [" + ASS + "] \nLast position of Swiftbot: [" + butt + "]" + "\nAt " + formattedDateTime + "\nSnake : (" + Snake1+ ","+ Snaketail1 +")"+ "("+Snake2 +"," +Snaketail2+")"+"\nLadder : (" + Ladder1+ ","+ Ladderb1 +")"+ "("+Ladder2 + "," +Ladderb2+")");
			Thread.sleep(500);
			System.out.println("The log file should be in " + path);
			// informing the user where the log file is.
			Thread.sleep(100);
			Sharing.API1.disableUnderlights();
			T.close();
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}

}
