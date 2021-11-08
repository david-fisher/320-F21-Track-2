//package GameObjects.tests;
//import GameObjects.objects.*;
//
//public class testTimer {
//
//	private void testStartStop(){
//		GameTimer timer = new GameTimer();
//		var startTime = timer.getTime();
//		timer.start();
//		if (timer.getTime() != startTime) {
//			timer.stop();
//		}
//		System.out.print("Start and Stop: " + (timer.getTime() != startTime));
//	}
//
//	private void testReset() {
//		GameTimer timer = new GameTimer();
//		var startTime = timer.getTime();
//		timer.start();
//		if (timer.getTime() != startTime) {
//			timer.stop();
//		}
//		timer.reset();
//		System.out.print("Reset: " + (timer.getTime() == startTime));
//	}
//
//	//whitebox testing (structural testing)
//	private boolean timerNotStatic() {
//		try {
//			GameTimer timer = new GameTimer();
//			System.out.print("PASSED - Timer is initializable");
//			return true;
//		}
//		catch {
//			System.out.print("ERROR - Timer is static and can't be initialized - ERROR");
//			return false;
//		}
//	}
//
//	private boolean timerHasSetterAndGetter() {
//		try {
//			GameTimer timer = new GameTimer();
//			timer.setInitialTime(130);
//			double output = timer.getInitialTime();
//			System.out.print("PASSED - Timer has getters and setters");
//			return true;
//		}
//		catch {
//			System.out.print("ERROR - Timer has NO getters and setters");
//			return false;
//		}
//	}
//
//	private boolean timerCanStart() {
//		try {
//			GameTimer timer = new GameTimer();
//			timer.start();
//			System.out.print("PASSED - Timer can start");
//			return true;
//		}
//		catch {
//			System.out.print("ERROR - Timer unable to start, do you have start function?");
//			return false;
//		}
//	}
//
//	private boolean timerCanStop() {
//		try {
//			GameTimer timer = new GameTimer();
//			timer.stop();
//			System.out.print("PASSED - Timer can stop");
//			return true;
//		}
//		catch {
//			System.out.print("ERROR - Timer unable to stop, do you have start function?");
//			return false;
//		}
//	}
//
//	private boolean timerCanReset() {
//		try {
//			GameTimer timer = new GameTimer();
//			timer.reset();
//			System.out.print("PASSED - Timer can stop");
//			return true;
//		}
//		catch {
//			System.out.print("ERROR - Timer unable to stop, do you have start function?");
//			return false;
//		}
//	}
//
//	public static void main(String[] args) {
//		timerNotStatic();
//		timerHasSetterAndGetter();
//		timerCanStart();
//		timerCanStop();
//		timerCanReset();
//	}
//}
