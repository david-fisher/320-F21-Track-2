package tests;
import objects.*;

import java.awt.Color;

public class testTimer {

	private void testStartStop(){
		GameTimer timer = new GameTimer();
		var startTime = timer.getTime();
		timer.start();
		if (timer.getTime() != startTime) {
			timer.stop();
		} 
		print("Start and Stop: " + (timer.getTime() != startTime));
	}

	private void testReset() {
		GameTimer timer = new GameTimer();
		var startTime = timer.getTime();
		timer.start();
		if (timer.getTime() != startTime) {
			timer.stop();
		}
		timer.reset();
		print("Reset: " + (timer.getTime() == startTime));
	}

	//whitebox testing (structural testing)
	private boolean timerNotStatic() {
		try {
			GameTimer timer = new GameTimer();
			print("PASSED - Timer is initializable");
			return true;
		}
		catch {
			print("ERROR - Timer is static and can't be initialized - ERROR");
			return false;
		}
	}

	private boolean timerHasSetterAndGetter() {
		try {
			GameTimer timer = new GameTimer();
			timer.setInitialTime(130);
			Integer output = timer.getInitialTime();
			print("PASSED - Timer has getters and setters");
			return true;
		}
		catch {
			print("ERROR - Timer has NO getters and setters");
			return false;
		}
	}

	private boolean timerCanStart() {
		try {
			GameTimer timer = new GameTimer();
			timer.start();
			print("PASSED - Timer can start");
			return true;
		}
		catch {
			print("ERROR - Timer unable to start, do you have start function?");
			return false;
		}
	}

	private boolean timerCanStop() {
		try {
			GameTimer timer = new GameTimer();
			timer.stop();
			print("PASSED - Timer can stop");
			return true;
		}
		catch {
			print("ERROR - Timer unable to stop, do you have start function?");
			return false;
		}
	}

	private boolean timerCanReset() {
		try {
			GameTimer timer = new GameTimer();
			timer.reset();
			print("PASSED - Timer can stop");
			return true;
		}
		catch {
			print("ERROR - Timer unable to stop, do you have start function?");
			return false;
		}
	}
	
	public static void main(String[] args) {
		timerNotStatic();
		timerHasSetterAndGetter();
		timerCanStart();
		timerCanStop();
		timerCanReset();
	}
}
