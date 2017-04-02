#pragma config(Sensor, S1,     leftBumper,    sensorEV3_Touch)
#pragma config(Sensor, S2,     rightBumper,   sensorEV3_Touch)
#pragma config(Sensor, S3,     colourSensor,  sensorEV3_Color)
#pragma config(Sensor, S4,     sonar,         sensorEV3_Ultrasonic)
/**
 * COSC 343 Assignment One
 * Semester One 2017
 * Robots on a Chessboard
 *
 * Group 15
 * group15Final.c
 */

/**
 * Determines the whether the current tile is black. Returns true if the
 * reflected colour sensor senses light levels less than given the threshold.
 */
bool black(int threshold) {
	return getColorReflected(colourSensor) < threshold;
}

/**
 * Determines the whether the current tile is white. Returns true if the
 * reflected colour sensor senses light levels greater than given the threshold.
 */
bool white(int threshold) {
	return getColorReflected(colourSensor) > threshold;
}

/**
 * Uses the sonar sensor to align the robot with the tower.
 *
 * Pivots clockwise until the sonar sensor detects something closer than the
 * threshold distance, or has done a full 360.
 *
 * If an object has been detected close by, the robot aligns itself with that
 * object. If the robot has done a full 360 and not found anything, it moves
 * forward, and looks again.
 */
void locateTower(int distanceThreshold) {
	 int turnSpeed = 10;
	resetMotorEncoder(motorB);

	while(getUSDistance(sonar) > distanceThreshold) {
		setMotorSyncEncoder(motorB, motorC, 100, 10, turnSpeed);
		waitUntilMotorStop(motorB);

		if(getMotorEncoder(motorB) > 700) {
			playSound(soundBeepBeep);
			setMotorSyncEncoder(motorB, motorC, 0, 700, 50);
			waitUntilMotorStop(motorB);
			resetMotorEncoder(motorB);
		}
	}
	stopAllMotors();

	int distanceToTower = getUSDistance(sonar);

	/* Turn left until tower is gone. */
	setMotorSync(motorB, motorC, -100, turnSpeed);
	waitUntil(getUSDistance(sonar) > 10 + distanceToTower);
	stopAllMotors();

	/* Turn right until tower is there again. */
	setMotorSync(motorB, motorC, 100, turnSpeed);
	waitUntil(getUSDistance(sonar) < 10 + distanceToTower);
	stopAllMotors();

	resetMotorEncoder(motorC); 	// start counting.

	/* Turn right until tower is gone. */
	setMotorSync(motorB, motorC, 100, turnSpeed);
	waitUntil(getUSDistance(sonar) > 10 + distanceToTower);
	stopAllMotors();

	/* Turn back to face the tower. */
	float angleToPivotBack = getMotorEncoder(motorC) * 0.6;
	setMotorSyncEncoder(motorB, motorC, -100, angleToPivotBack, 10);
	waitUntilMotorStop(motorB);
}

/**
 * Uses the edge of a black tile to straighten the robot.
 *
 * Robot pivots clockwise until it finds the edge, anti clockwise until it finds
 * the edge again, and and clockwise back to the center again, depending on the
 * magnitude of the first two turns. This strategy assumes the robothas made it
 * to the start of a white tile.
 *
 * Robot always turns right left right.
 */
void straightenUp(){
	int threshold = 30;
	int turnSpeed = 10;

	/* inch forward a bit first. */
	setMotorSyncEncoder(motorB, motorC, 0, 30, turnSpeed);
	waitUntilMotorStop(motorB);

	/* Turn right until black then stop and record the encoder counts. */
	resetMotorEncoder(motorB);
	setMotorSync(motorB, motorC, 100, turnSpeed);
	waitUntil(black(threshold));
	stopAllMotors();
	float firstAngle = getMotorEncoder(motorB);

	/* Get off the black tile */
	setMotorSync(motorB, motorC, -100, turnSpeed);
	waitUntil(white(threshold));

	/* Turn left until black then stop and record the encoder counts. */
	resetMotorEncoder(motorC);
	setMotorSync(motorB, motorC, -100, turnSpeed);
	waitUntil(black(threshold));

	/* Get off the black tile */
	setMotorSync(motorB, motorC, 100, turnSpeed);
	waitUntil(white(threshold));
	stopAllMotors();
	float secondAngle = getMotorEncoder(motorC);

	/* Calculate the angle to turn back at. */
	float encoderCounts;
	if(secondAngle > 120){
		encoderCounts = secondAngle / 2;
	} else {
		float ratio = (firstAngle/secondAngle) * 100;
		if(ratio < 70) {
			encoderCounts = secondAngle * 0.75;
		} else {
			encoderCounts = secondAngle * 0.25;
		}
	}

	/* Execute the final turn. */
	setMotorSyncEncoder(motorB, motorC, 100, encoderCounts, turnSpeed);
	waitUntilMotorStop(motorB);
}

/**
 * Robot crosses a white tile, then a black tile, then straightens up until
 * the target number of black tiles have been traversed, then stops.
 *
 * The robot travels forwards with motorC synced to motorB.
 */
void goForwardFor(int tileTarget) {
	int tilesBeen = 0;
	while(tilesBeen < tileTarget) {
		setMotorSync(motorB, motorC, 0, 15);
		waitUntil(black(30));
		playSound(soundBlip);
		tilesBeen++;
		waitUntil(white(30));
		stopAllMotors();
		straightenUp();
	}
	stopAllMotors();
}

/**
 * Entry point of the program.
 *
 * Robot completes the first turn, travels forward until the end of the first
 * black tile, and straightens up. It then travels forward while straigtening up
 * as it goes for 13 black tiles, before traversing the last white tile to reach
 * the final black tile. The robot uses a blip sound to indicate when it detects
 * a black tile. The robot then complets a right hand turn and travels forward
 * for 4440 motor encoder counts, before beginning its locate tower routine.
 *
 * Once the tower is located, the robot travels forward and uses its bump
 * sensors and sonar to detect when it has contacted the tower. During this
 * phase the robot locates the tower again to ensure it stays in line with it.
 * Once the tower has been contacted, the robot moves forward at full power for
 * 1.5 seconds to push the tower off its tile.
 */
task main() {
	/* travel forward to first tile. */
	setMotorSyncEncoder(motorB, motorC, 0, 250, 15);
	waitUntilMotorStop(motorB);

	/* Turn right until first white tile is reached, then straighten up. */
	setMotorSync(motorB, motorC, 50, 10);
	playSound(soundBlip);
	waitUntil(white(30));
	stopAllMotors();
	straightenUp();

	goForwardFor(13);

	/* Cross last white tile. */
	setMotorSync(motorB, motorC, 0, 15);
	waitUntil(black(30));
	playSound(soundBlip);

	/* Right hand turn 90 degrees. */
	setMotorSyncEncoder(motorB, motorC, 50, 350, 10);
	waitUntilMotorStop(motorB);
	playSound(soundDownwardTones);

	/* travel straight towards tower. */
	setMotorSyncEncoder(motorB, motorC, 0, 4440, 70);
	waitUntilMotorStop(motorB);

	locateTower(120);

	/* Travel forward until tower contacted, realign with tower if necessary. */
	resetMotorEncoder(motorB);
	while(getUSDistance(sonar) > 10 && SensorValue[rightBumper] == 0 && SensorValue[leftBumper] == 0){
		if(getMotorEncoder(motorB) > 400) {
			stopAllMotors();
			resetMotorEncoder(motorB);
			locateTower(120);
		}
		setMotorSync(motorB, motorC, 0, 50);
	}

	/* Push tower off tile. */
	setMotorSync(motorB, motorC, 0, 100);
	wait1Msec(1500);
	stopAllMotors();

	/* Indicate completion with sound. */
	playSound(soundUpwardTones);
	wait1Msec(1500);
}
