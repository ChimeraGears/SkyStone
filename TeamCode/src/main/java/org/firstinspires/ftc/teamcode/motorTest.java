package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "motorTest")
public class motorTest extends OpMode {

        public HardwareMapping robot = new HardwareMapping();
        public double lfPower, rfPower, lbPower, rbPower;
        public double collectorPower;
        public void init(){
            robot.init(hardwareMap);

            robot.leftFrontDrive .setDirection(DcMotor.Direction.REVERSE);
            robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
            robot.leftBackDrive  .setDirection(DcMotor.Direction.REVERSE);
            robot.rightBackDrive .setDirection(DcMotor.Direction.FORWARD);
            robot.collectorLeft  .setDirection(DcMotor.Direction.REVERSE);
            robot.collectorRight .setDirection(DcMotor.Direction.FORWARD);
        }

        public void loop(){

            boolean doMoveForward     = gamepad1.dpad_up;
            boolean doMoveBackward    = gamepad1.dpad_down;
            boolean doStrafeLeft      = gamepad1.dpad_left;
            boolean doStrafeRight     = gamepad1.dpad_right;
            double  doRotate          = gamepad1.right_stick_x;
            boolean doCollection      = gamepad1.right_trigger > 0.05;
            boolean reverseCollection = gamepad1.left_trigger > 0.05;

            if(doCollection){
                collectorPower = 1.00;
            }
            else if(reverseCollection){
                collectorPower = -1.00;
            }
            else{
                collectorPower = 0.00;
            }

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
            else if(doStrafeRight){
                rfPower  = -0.75;
                lbPower  = -0.75;
                lfPower  =  0.75;
                rbPower  =  0.75;
            }
            else if(doStrafeLeft){
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

            if (doRotate >= 0.25 && doRotate <= 1.00)        {
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
            robot.rightBackDrive .setPower(rbPower);
            robot.collectorLeft  .setPower(collectorPower);
            robot.collectorRight .setPower(collectorPower);
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
