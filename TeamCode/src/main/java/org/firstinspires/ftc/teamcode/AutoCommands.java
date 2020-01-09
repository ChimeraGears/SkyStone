package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class AutoCommands{

    public HardwareMapping robot = new HardwareMapping();
    public int    motorNum     = 0;
    public double ticksPerInch = 0;
    public double drive        = 0;
    public double turn         = 0;
    public double strafe       = 0;
    private final static double TICKS_PER_ROTATION    = 2240;
    private final static double WHEEL_DIAMETER_INCHES = 4.0;
    public final static double TICKS_PER_INCH        = (TICKS_PER_ROTATION)/(WHEEL_DIAMETER_INCHES*(Math.PI));
    public int targetFL, targetFR, targetBL, targetBR;
    public int LFpos, RFpos, LBpos, RBpos;
public AutoCommands(){}

public void init(int motorCount, double wheelDiameter, double ticksPerRotation){
    ticksPerInch = ticksPerRotation/(wheelDiameter*Math.PI);
    motorNum     = motorCount;

    robot.leftFrontDrive .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    robot.rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    robot.leftBackDrive  .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    robot.rightBackDrive .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
}

public void drive(double leftFrontPower, double rightFrontPower, double leftBackPower, double rightBackPower, double inches){

    robot.leftFrontDrive .setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.leftBackDrive  .setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.rightBackDrive .setMode(DcMotor.RunMode.RUN_TO_POSITION);

    targetFL=robot.leftFrontDrive .getCurrentPosition()+(int)(inches*TICKS_PER_INCH);
    targetFR=robot.rightFrontDrive.getCurrentPosition()+(int)(inches*TICKS_PER_INCH);
    targetBL=robot.leftBackDrive  .getCurrentPosition()+(int)(inches*TICKS_PER_INCH);
    targetBR=robot.rightBackDrive .getCurrentPosition()+(int)(inches*TICKS_PER_INCH);

    robot.leftFrontDrive .setTargetPosition(targetFL);
    robot.rightFrontDrive.setTargetPosition(targetFR);
    robot.leftBackDrive  .setTargetPosition(targetBL);
    robot.rightBackDrive .setTargetPosition(targetBR);

    robot.leftFrontDrive .setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.leftBackDrive  .setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.rightBackDrive .setMode(DcMotor.RunMode.RUN_TO_POSITION);

    robot.leftFrontDrive .setPower(leftFrontPower);
    robot.rightFrontDrive.setPower(rightFrontPower);
    robot.leftBackDrive  .setPower(leftBackPower);
    robot.rightBackDrive .setPower(rightBackPower);

    LFpos += inches;
    RFpos += inches;
    LBpos += inches;
    RBpos += inches;
}
public void rotate(double leftFrontPower, double rightFrontPower, double leftBackPower, double rightBackPower, double angleInInches){
    //IMPORTANT: Use negatives for left, and positives for right
    targetFL=robot.leftFrontDrive .getCurrentPosition()+(int)(angleInInches*TICKS_PER_INCH);
    targetBL=robot.leftBackDrive  .getCurrentPosition()+(int)(angleInInches*TICKS_PER_INCH);
    targetFR=robot.rightFrontDrive.getCurrentPosition()+(int)(-angleInInches*TICKS_PER_INCH);
    targetBR=robot.rightBackDrive .getCurrentPosition()+(int)(-angleInInches*TICKS_PER_INCH);

    robot.leftFrontDrive .setTargetPosition(targetFL);
    robot.rightFrontDrive.setTargetPosition(targetFR);
    robot.leftBackDrive  .setTargetPosition(targetBL);
    robot.rightBackDrive .setTargetPosition(targetBR);

    robot.leftFrontDrive .setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.leftBackDrive  .setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.rightBackDrive .setMode(DcMotor.RunMode.RUN_TO_POSITION);

    robot.leftFrontDrive .setPower(leftFrontPower);
    robot.rightFrontDrive.setPower(rightFrontPower);
    robot.leftBackDrive  .setPower(leftBackPower);
    robot.rightBackDrive .setPower(rightBackPower);


    LFpos += angleInInches;
    RFpos -= angleInInches;
    LBpos += angleInInches;
    RBpos -= angleInInches;
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
}