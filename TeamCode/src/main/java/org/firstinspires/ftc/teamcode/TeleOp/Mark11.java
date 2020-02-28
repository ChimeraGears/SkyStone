package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareMapping;
import org.firstinspires.ftc.teamcode.QuickVargs;

@TeleOp(name = "Mark11")
public class Mark11 extends OpMode{

    public HardwareMapping robot = new HardwareMapping();
    public QuickVargs v = new QuickVargs();
    private double maxPower = 0.80;

    public void init(){
        robot.init(hardwareMap);
        robot.leftDrive .setDirection(DcMotor.Direction.REVERSE);
        robot.rightDrive.setDirection(DcMotor.Direction.FORWARD);

        robot.collectorLeft  .setDirection(DcMotor.Direction.REVERSE);
        robot.collectorRight .setDirection(DcMotor.Direction.FORWARD);

        robot.outMotor       .setDirection(DcMotor.Direction.REVERSE);

        robot.upServo2       .setDirection(CRServo.Direction.REVERSE);

        robot.clawServo      .setDirection(CRServo.Direction.REVERSE);
    }

    public void loop(){
        boolean doMoveForward  = gamepad1.left_stick_y < -0.05;
        boolean doMoveBackward = gamepad1.left_stick_y > 0.05;
        double  rotate         = gamepad1.right_stick_x;

        boolean doCollection      = gamepad1.right_trigger >  0.05;
        boolean reverseCollection = gamepad1.left_trigger  >  0.05;

        boolean doMoveOut         = gamepad2.right_trigger >  0.05;
        boolean doMoveIn          = gamepad2.left_trigger  >  0.05;

        boolean doMoveUp          = gamepad2.left_stick_y  >  0.05;
        boolean doMoveDown        = gamepad2.left_stick_y  < -0.05;

        boolean doIntakeLock      = gamepad1.x;

        boolean useClaw           = gamepad2.b;
        boolean quitClaw          = gamepad2.a;

        if(doMoveForward) {
            setPowers(maxPower,robot.leftDrive,robot.rightDrive);
        }
        if(doMoveBackward){
            setPowers(-maxPower,robot.leftDrive,robot.rightDrive);
        }
        if(rotate != 0)   {
            setPowers(-rotate,rotate,robot.leftDrive,robot.rightDrive);
        }
        if(doCollection)  {
            setPowers(maxPower,robot.collectorLeft,robot.collectorRight);
            setPowers(0.80,robot.intakeLock);
        }
        if(reverseCollection){
            setPowers(-maxPower,robot.collectorLeft,robot.collectorRight);
            setPowers(-0.80,robot.intakeLock);
        }
        if(doMoveOut){
            setPowers(maxPower,robot.outMotor);
        }
        if(doMoveIn){
            setPowers(-maxPower,robot.outMotor);
        }
        if(doMoveUp){
            setPowers(0.80,robot.upServo,robot.upServo2);
        }
        if(doMoveDown){
            setPowers(-0.80,robot.upServo,robot.upServo2);
        }
        if(doIntakeLock){
            setPowers(0.80,robot.intakeLock);
        }
        if(useClaw){
            setPowers(0.80,robot.clawServo);
        }
        if(quitClaw){
            setPowers(-0.80,robot.clawServo);
        }

        telemetry.addData("Left Side", "left (%b)", robot.leftDrive.getPower() != 0);
        telemetry.addData("Right Side",  "right (%b)", robot.rightDrive.getPower() != 0);

        telemetry.update();

    }
    public void stop(){
    }

    private void setPowers(double power, CRServo ... crServos){
        for(CRServo c: crServos){
            c.setPower(power);
        }
    }
    private void setPowers(double power1,double power2,CRServo ... crServos){
        int i = 0;
        for(CRServo c: crServos){
            if(i % 2 == 0) {
                c.setPower(power1);
                i++;
            }
            if(i % 2 != 0){
                c.setPower(power2);
                i--;
            }
        }
    }
    private void setPowers(double power, DcMotor ... dcMotors){
        for(DcMotor d: dcMotors){
            d.setPower(power);
        }
    }
    private void setPowers(double power1, double power2, DcMotor ... dcMotors){
        int i = 0;
        for(DcMotor d: dcMotors){
            if(i % 2 == 0) {
                d.setPower(power1);
                i++;
            }
            if(i % 2 != 0){
                d.setPower(power2);
                i--;
            }
        }
    }
}
