/*
Copyright 2017 FIRST Tech Challenge Team 12978
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
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;



public class AutoSenor {
    public DcMotor frontRight;
    public DcMotor backRight;
    public DcMotor backLeft;
    public DcMotor frontLeft;
    public Servo rightClaw;
    public Servo leftClaw;
    public Servo rightArm;
    public Servo leftArm;
    public Servo jewelArmHorizontal;
    public Servo jewelArmVertical;
    
    

    //Methods to lift and lower the claw arm.
    public void liftArm(){
        rightArm.setPosition(1);
        leftArm.setPosition(0);
    }

    public void lowerArm(){
        rightArm.setPosition(0);
        leftArm.setPosition(1);
    }

    //Methods to automatically open or close the claw.
    public void grab(){
        rightClaw.setPosition(.75);
        leftClaw.setPosition(.15);
    }

    public void release(){
        rightClaw.setPosition(.15);
        leftClaw.setPosition(.75);
    }

    //A method to stop the wheels.
    public void brake(){
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    //Methods to move the bot true forward or backward.
    public void driveForward(){
        frontLeft.setPower(-0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(-0.5);
        backRight.setPower(0.5);
    }

    public void driveBackward(){
        frontLeft.setPower(0.5);
        frontRight.setPower(-0.5);
        backLeft.setPower(0.5);
        backRight.setPower(-0.5);
    }
    
    public void strafeLeft(){
        frontLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(-0.5);
        backRight.setPower(-0.5);
    }
    
    public void strafeRight(){
        frontLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backLeft.setPower(0.5);
        backRight.setPower(0.5);
    }

    //Methods to rotate the bot on 0 radius.
    public void rotateRight(){
        frontLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backLeft.setPower(-0.5);
        backRight.setPower(-0.5);
    }

    public void rotateLeft(){
        frontLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(0.5);
        backRight.setPower(0.5);
    }
    
    public void raiseJewelArm() {
        jewelArmVertical.setPosition(1);
    }

    public void lowerJewelArm() {
        jewelArmVertical.setPosition(.75);
    }
    
    public void jewelArmRight() {
        jewelArmHorizontal.setPosition(.65);
    }
    
    public void jewelArmCenter() {
        jewelArmHorizontal.setPosition(.40);
    }
    
    public void jewelArmLeft() {
        jewelArmHorizontal.setPosition(.25);
    }
    
    public void jewelArmUp() {
        jewelArmVertical.setPosition(.05);
    }
    
    public void jewelArmDown() {
        jewelArmVertical.setPosition(.620);
    }
    
}
