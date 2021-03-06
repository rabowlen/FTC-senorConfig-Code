/*
Copyright 2018 FIRST Tech Challenge Team 12978

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.io.Console;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
 */
@TeleOp

public class Driver extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor backLeft;
    private DcMotor frontLeft;
    private DistanceSensor sensor_color_distance;
    private Servo rightClaw;
    private Servo leftClaw;
    private DcMotor armMotor;
    private Servo jewelArmHorizontal;
    private Servo jewelArmVertical;

    @Override
    public void runOpMode() {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        sensor_color_distance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        jewelArmHorizontal = hardwareMap.get(Servo.class, "jewelArmHorizontal");
        jewelArmVertical = hardwareMap.get(Servo.class, "jewelArmVertical");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
            /*Control the claw servos by pushing the x button to open them and the
            b button to close them.*/


            //Keep jewel arm in upright position
            jewelArmVertical.setPosition(.10);
            jewelArmHorizontal.setPosition(.65);



            double tgtPower = 0;
            while (opModeIsActive()) {
                // check to see if we need to move the servo.
            if(gamepad1.x) {
                // move to open position
                rightClaw.setPosition(.15);
                leftClaw.setPosition(.75);


             }

            if(gamepad1.b) {
                // move to closed position
                rightClaw.setPosition(.75);
                leftClaw.setPosition(.15);
            }


            /*Allow the arm to raise with Y and lower with A*/
            while(gamepad1.y){
                armMotor.setPower(-.250);
                if(gamepad1.y == false){
                    armMotor.setPower(0);
                }
            }

            while(gamepad1.a){
                armMotor.setPower(.250);
                if(gamepad1.a == false){
                    armMotor.setPower(0);
                }
            }




        /* Creating a holonomic drive by using the two joysticks.*/


        // left stick controls direction
        // right stick X controls rotation

        float gamepad1LeftY = -gamepad1.left_stick_y;
        float gamepad1LeftX = gamepad1.left_stick_x;
        float gamepad1RightX = gamepad1.right_stick_x;

        // holonomic formulas

        double FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        double FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        double BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
        double BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

        // clip the right/left values so that the values never exceed +/- 1
        FrontRight = (double)Range.clip(FrontRight, -.80, .80);
        FrontLeft = (double)Range.clip(FrontLeft, -.80, .80);
        BackLeft = (double)Range.clip(BackLeft, -.80, .80);
        BackRight = (double)Range.clip(BackRight, -.80, .80);

        // write the values to the motors
        frontRight.setPower(scaleInput(FrontRight));
        frontLeft.setPower(scaleInput(FrontLeft));
        backLeft.setPower(scaleInput(BackLeft));
        backRight.setPower(scaleInput(BackRight));

            }
        }
    }

    /*
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 0.85, 0.85 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

}
