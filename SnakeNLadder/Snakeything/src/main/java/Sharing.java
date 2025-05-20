import java.awt.image.BufferedImage;
import java.util.Random;
import swiftbot.*;

public class Sharing {
	public static final Random R = new Random();
	public static final SwiftBotAPI API1 = new SwiftBotAPI();
	// constant variable that is accessible fixed values within class
	// this is to simplify the code for me
	public static void resetGame() throws Exception {
		System.out.println("Game restarting...");
		SnakeNladder.tempasignPlayer = 0;
		SnakeNladder.tempasignSwiftbot = 0;
		SnakeNladder.position1 = 0;
		SnakeNladder.position2 = 0;
		Thread.sleep(300);
		System.out.println("Please press B");		

	}

	public static void Name() {
		try {
			Thread.sleep(1500);
			System.out.println("Please ready your Player name in a QR code \nlimited character to 10 only");
			Thread.sleep(5000);
			String str = "";

			do {
				BufferedImage img = Sharing.API1.getQRImage();
				str = Sharing.API1.decodeQRImage(img);
				if (str.isEmpty() || img == null) {
					System.out.println("No QR code is detected, try again");
					Thread.sleep(1000);
					continue;
				}
				if (str.length() > 10) {
					System.out.println("Name is too long, Please make the QR code again");
					str = "";
				}
			} while (str.isEmpty());
			SnakeNladder.Player_A = str;
			System.out.println("Hello Player "+ SnakeNladder.Player_A + " !");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// added by eclipse to do exception e
		}
	}

	public static int[] Playerrollthedice() {
		Sharing.Space();
		System.out.println("System will roll each Player's dice");
		int[] diceResults = new int[2];
		diceResults[0] = rolleddice();
		diceResults[1] = rolleddice();

		return diceResults;
	}

	public static int rolleddice() {
		int RolledDice = R.nextInt(6)+1;
		return RolledDice;
	}

	public static int[] snakeassign(int[][] board, Random R) {
		int snakeheadRow = R.nextInt(4)+1; // 1 to 4 
		int snaketailRow = 0; // default value 
		int randomColumn = R.nextInt(5), randomColumn2 = R.nextInt(5); // Random column between 0 and 4

		do {
			if (snakeheadRow == 1) {
				snaketailRow = 0;
			} else {
				snaketailRow = R.nextInt(snakeheadRow);
			}
			// Step 2: Randomly select Snaketail, must be in the same column but above the Snakehead row
			// Get the Snaketail number
		}while (snakeheadRow == snaketailRow || (snakeheadRow == 4 && randomColumn == 4) || (snaketailRow == 4 && randomColumn == 4)); // potential bug

		int LadderTopRow = R.nextInt(4)+1; // 1 to 4 
		int LadderBottomRow = 0; // default value 

		do {
			LadderTopRow = R.nextInt(4)+1;
		} while (LadderTopRow == snakeheadRow || (LadderTopRow == 4 && randomColumn == 4));
		do {
			LadderBottomRow = R.nextInt(LadderTopRow);
		}while (LadderTopRow == LadderBottomRow || LadderBottomRow == snaketailRow || (LadderBottomRow == 4 && randomColumn2 == 4)); // potential bug


		int snakeheadRow2 = R.nextInt(4)+1; // 1 to 4 
		int snaketailRow2 = 0; // default value 
		int randomColumn3 = R.nextInt(5), randomColumn4 = R.nextInt(5); // Random column between 0 and 4

		while (randomColumn3 == randomColumn) {
			randomColumn3 = R.nextInt(5);
		}

		do {
			snaketailRow2 = R.nextInt(snakeheadRow2);
			// Step 2: Randomly select Snaketail2, must be in the same column but above the Snakehead row
			// Get the Snaketail number
		}while (snakeheadRow2 == snaketailRow2 || (snakeheadRow2 == 4 && randomColumn3 == 4) || (snaketailRow2 == 4 && randomColumn3 == 4)); // potential bug

		int LadderTopRow2 = R.nextInt(4)+1; // 1 to 4 
		int LadderBottomRow2 = 0; // default value 

		while (randomColumn3 == randomColumn4) {
			randomColumn4 = R.nextInt(5);
		}

		do {
			LadderTopRow2 = R.nextInt(4)+1;
		} while (LadderTopRow2 == snakeheadRow2 || (LadderTopRow2 == 4 && randomColumn3 == 4));
		do {
			LadderBottomRow2 = R.nextInt(LadderTopRow2);
		}while (LadderTopRow2 == LadderBottomRow2 || LadderBottomRow2 == snaketailRow2 || (LadderBottomRow2 == 4 && randomColumn4 == 4)); // potential bug

		return new int[] {
				LadderTopRow, LadderBottomRow, snakeheadRow, snaketailRow, randomColumn, randomColumn2,
				LadderTopRow2, LadderBottomRow2, snakeheadRow2, snaketailRow2, randomColumn3, randomColumn4
				};
	}

	public static void Space() {
		//		System.out.println("*"); //46
		for (int i = 1; i < 46; i++) {
			System.out.print("*");
		}
		System.out.println();
	}

}