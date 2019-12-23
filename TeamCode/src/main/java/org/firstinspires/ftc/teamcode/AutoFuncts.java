package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class AutoFuncts extends AutonomousBlue{
    public int motorNum = 0;
    public double ticksPerInch = 0;

    public AutoFuncts(){
    }

    public void init(int motorCount, double wheelDiameter, double ticksPerRotation){
        ticksPerInch = ticksPerRotation/(wheelDiameter*Math.PI);
        motorNum = motorCount;
    }
    public void drive(double leftFrontPower, double rightFrontPower, double leftBackPower, double rightBackPower, double inches){
        targetFL=robot.leftFrontDrive.getCurrentPosition()+(int)(inches*TICKS_PER_INCH);
        targetFR=robot.rightFrontDrive.getCurrentPosition()+(int)(inches*TICKS_PER_INCH);
        targetBL=robot.leftBackDrive.getCurrentPosition()+(int)(inches*TICKS_PER_INCH);
        targetBR=robot.rightBackDrive.getCurrentPosition()+(int)(inches*TICKS_PER_INCH);

        robot.leftFrontDrive.setTargetPosition(targetFL);
        robot.rightFrontDrive.setTargetPosition(targetFR);
        robot.leftBackDrive.setTargetPosition(targetBL);
        robot.rightBackDrive.setTargetPosition(targetBR);

        robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.leftFrontDrive.setPower(leftFrontPower);
        robot.rightFrontDrive.setPower(rightFrontPower);
        robot.leftBackDrive.setPower(leftBackPower);
        robot.rightBackDrive.setPower(rightBackPower);
    }
    public void rotate(double leftFrontPower, double rightFrontPower, double leftBackPower, double rightBackPower, double angleInInches){

        targetFL=robot.leftFrontDrive.getCurrentPosition()+(int)(angleInInches*TICKS_PER_INCH);
        targetBL=robot.leftBackDrive.getCurrentPosition()+(int)(angleInInches*TICKS_PER_INCH);
        targetFR=robot.rightFrontDrive.getCurrentPosition()+(int)(-angleInInches*TICKS_PER_INCH);
        targetBR=robot.rightBackDrive.getCurrentPosition()+(int)(-angleInInches*TICKS_PER_INCH);

        robot.leftFrontDrive.setTargetPosition(targetFL);
        robot.rightFrontDrive.setTargetPosition(targetFR);
        robot.leftBackDrive.setTargetPosition(targetBL);
        robot.rightBackDrive.setTargetPosition(targetBR);

        robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.leftFrontDrive.setPower(leftFrontPower);
        robot.rightFrontDrive.setPower(rightFrontPower);
        robot.leftBackDrive.setPower(leftBackPower);
        robot.rightBackDrive.setPower(rightBackPower);
    }
    public void utility(DcMotor motor, double motorPower){
        motor.setPower(motorPower);
    }
    public void utility(DcMotor motor, double motorPower, int targetMotorPosition){
        motor.setTargetPosition(targetMotorPosition);
    }
    public void utility(Servo servo, double servoPosition){
        servo.setPosition(servoPosition);
    }
    public boolean targetsReached(){
        return((LFpos>=targetFL && RBpos>=targetBR) || (LFpos>=targetFL && RFpos>=targetFR) || (LBpos>=targetBL && RFpos>=targetFR) || (LBpos>=targetBL && RBpos >=targetBL));
    }
}