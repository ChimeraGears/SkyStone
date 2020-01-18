package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp (name = "Mark 9")
public class Mark9 extends OpMode {
    public HardwareMapping robot = new HardwareMapping();
    public double lfPower, rfPower, lbPower, rbPower;

    public void init(){
        robot.init(hardwareMap);

        robot.leftFrontDrive. setDirection(DcMotor.Direction.REVERSE);
        robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.leftBackDrive.  setDirection(DcMotor.Direction.REVERSE);
        robot.rightBackDrive. setDirection(DcMotor.Direction.FORWARD);
    }

    public void single(){
        robot.clawServo.setPosition(0);
        robot.clawServo.setPosition(1);
    }

    public void loop(){
        single();

        boolean doMoveForward  = gamepad1.dpad_up;
        boolean doMoveBackward = gamepad1.dpad_down;
        boolean doStrafeLeft   = gamepad1.dpad_left;
        boolean doStrafeRight  = gamepad1.dpad_right;
        double doRotate        = gamepad1.right_stick_x;
        boolean doCollect      = gamepad1.a;

        double liftUp      = gamepad2.left_stick_y;
        double liftDown    = -gamepad2.left_stick_y;

        double clawOut     = gamepad2.right_stick_y;
        Range.clip(clawOut,0.00,1.00);
        double clawIn      = gamepad2.right_stick_y;
        Range.clip(clawIn,-1.00,0.00);

        boolean openClaw   = gamepad2.a;
        boolean closeClaw  = gamepad2.b;

        if(doMoveForward){
            lfPower  = 0.75;
            lbPower  = 0.75;
            rfPower  = 0.75;
            rbPower  = 0.75;
        }
        else if(doMoveBackward){
            lfPower  = -0.75;
            lbPower  = -0.75;
            rfPower  = -0.75;
            rbPower  = -0.75;
        }
        else if(doStrafeLeft){
            rfPower  = -0.75;
            lbPower  = -0.75;
            lfPower  =  0.75;
            rbPower  =  0.75;
        }
        else if(doStrafeRight){
            rfPower  =  0.75;
            lbPower  =  0.75;
            lfPower  = -0.75;
            rbPower  = -0.75;
        }
        else {
            rfPower  =  0.00;
            lbPower  =  0.00;
            lfPower  =  0.00;
            rbPower  =  0.00;
        }
        //(951)-331-0925
        if (doRotate >= 0.25 && doRotate <= 1.00)       {
            lfPower  =  0.75;
            rfPower  = -0.75;
            lbPower  =  0.75;
            rbPower  = -0.75;
        }

        else if (doRotate <= -0.25 && doRotate >= -1.00) {
            lfPower  = -0.75;
            rfPower  =  0.75;
            lbPower  = -0.75;
            rbPower  =  0.75;
        }

        robot.leftFrontDrive .setPower(lfPower);
        robot.leftBackDrive  .setPower(lbPower);
        robot.rightFrontDrive.setPower(rfPower);
        robot.leftBackDrive  .setPower(rbPower);

        if((clawOut > 0.00 || clawIn > 0.00) && !(clawOut > 0.00 && clawIn > 0.00)){
            robot.outServo.setPower(clawOut + clawIn);
        }
        else {
            robot.outServo.setPower(0);
        }
        robot.upServo.setPosition(liftUp + liftDown);

        if(closeClaw)
            robot.clawServo.setPosition(1.00);
        if(openClaw)
            robot.clawServo.setPosition(0.00);

        telemetry.addData("Front Motors", "left front (%.2f), right front(%.2f)", lfPower, rfPower);
        telemetry.addData("Back Motors",  "left back (%.2f), right back(%.2f)",   lbPower,rbPower);
        telemetry.update();
    }

    public void stop(){
        robot.leftFrontDrive .setPower(0);
        robot.leftBackDrive  .setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive  .setPower(0);
    }

    /**public double boolToDoub(boolean convertMe, boolean isNegative){
        double x = 1.00;
        if(convertMe){
            if(isNegative) {
                x -= .01;
            } else {
                x += .01;
            }
        }
        return x;
    }*/


}
