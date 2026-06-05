public class SwiftBotMovement {

	public static void turn180() throws Exception {
		System.out.println("180 turning");
		Thread.sleep(200); 
		Sharing.API1.move(-42, 80, 500); 
		Thread.sleep(200);
		System.out.println("2");
		Sharing.API1.move(-42, 80, 500);
		Thread.sleep(300);
	}

	public static void Moving(int Currentposition, int Newposition, boolean isBackward) throws Exception {
		int steps = Math.abs(Newposition - Currentposition);
		Thread.sleep(1500);
		
		try {
			if (isBackward) {
				turn180();
				int [] green = {0,255,0};
				Sharing.API1.fillUnderlights(green);
			}
			
			for (int i = 0; i < steps; i++) {
				
				// Check the position and perform moves based on specific values
				if (Currentposition + i == 20) {//
					Sharing.API1.stopMove();
					Thread.sleep(500);
					System.out.println("ATuring right at 20"); // 
					Sharing.API1.move(63, -40, 500); //right turn
					Sharing.API1.move(86,100,500);
					Thread.sleep(500);
					Sharing.API1.move(63, -40, 500);

				} else if (Currentposition + i == 15) {
					Sharing.API1.stopMove();
					Thread.sleep(500);
					System.out.println("BTurning left at 15");
					Sharing.API1.move(-42, 80, 500); // Turn **left**
					Sharing.API1.move(86, 100, 500); // Move **up**
					Thread.sleep(500);
					Sharing.API1.move(-42, 80, 500); // Turn **left** again

				} else if (Currentposition + i == 10) {//
					Sharing.API1.stopMove();
					Thread.sleep(500);
					System.out.println("CTuring right at 10");
					Sharing.API1.move(63, -40, 500); // right turn
					Sharing.API1.move(86,100,500);
					Thread.sleep(500);
					Sharing.API1.move(63, -40, 500);

				} else if (Currentposition + i == 5) {
					Sharing.API1.stopMove();
					Thread.sleep(500);
					System.out.println("DTuring left at 5");
					Sharing.API1.move(-42, 80, 500); // Turn **left**
					Sharing.API1.move(86, 100, 500); // Move **up**
					Thread.sleep(500);
					Sharing.API1.move(-42, 80, 500); // Turn **left** again

				} else {
					Sharing.API1.move(86, 100, 500); // Default move
				}

			}
			if (isBackward) {
				turn180();
				Sharing.API1.disableUnderlights();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}