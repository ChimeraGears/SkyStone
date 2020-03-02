package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareMapping;

@TeleOp(name = "Mark11")
public class Mark11 extends OpMode {

        public HardwareMapping robot = new HardwareMapping();

        public double lfPower, rfPower, lbPower, rbPower;
        public double collectorPower, outMotorPower;
        public double lockPower, upPower, clawPower;

        public void init(){
            //Initialize Our hardware map
            robot.init(hardwareMap);
            //Declare which side of our robot is which
            robot.leftFrontDrive .setDirection(DcMotor.Direction.REVERSE);
            robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
            robot.leftBackDrive  .setDirection(DcMotor.Direction.REVERSE);
            robot.rightBackDrive .setDirection(DcMotor.Direction.FORWARD);
            robot.collectorLeft  .setDirection(DcMotor.Direction.REVERSE);
            robot.collectorRight .setDirection(DcMotor.Direction.FORWARD);
            robot.outMotor       .setDirection(DcMotor.Direction.REVERSE);

            robot.upServo2       .setDirection(CRServo.Direction.REVERSE);

            robot.clawServo      .setDirection(CRServo.Direction.REVERSE);
        }

        public void loop(){

            boolean doMoveForward     = gamepad1.left_stick_y < -0.05;
            boolean doMoveBackward    = gamepad1.left_stick_y > 0.05;
            double  doRotate          = gamepad1.right_stick_x;
            boolean doCollection      = gamepad1.right_trigger >  0.05;
            boolean reverseCollection = gamepad1.left_trigger  >  0.05;
            boolean doMoveOut         = gamepad2.right_trigger >  0.05;
            boolean doMoveIn          = gamepad2.left_trigger  >  0.05;
            boolean doMoveUp          = gamepad2.left_stick_y  >  0.05;
            boolean doMoveDown        = gamepad2.left_stick_y  < -0.05;
            boolean doIntakeLock      = gamepad1.x;
            boolean useClaw           = gamepad2.b;
            boolean quitClaw          = gamepad2.a;

            if(useClaw){
                clawPower = 0.80;
            }
            if(quitClaw){
                clawPower = -0.80;
            }

            if(doMoveUp){
                upPower = .8;
            } else if(doMoveDown) {
                upPower = -.8;
            } else {
                upPower = 0.00;
            }
            if(doIntakeLock || doCollection){
                lockPower = 0.75;
            } else {
                lockPower = 0.00;
            }


            if(doCollection){
                collectorPower = 1.00;
            }
            else if(reverseCollection){
                collectorPower = -1.00;
                lockPower = -.80;
            }
            else{
                collectorPower = 0.00;
            }

            if(doMoveIn)      {
                outMotorPower = 1.00;
            }
            else if(doMoveOut){
                outMotorPower = -1.00;
            }
            else              {
                outMotorPower = 0.00;
            }

            if(doMoveBackward)    {
                lfPower  = 0.75;
                lbPower  = 0.75;
                rfPower  = 0.75;
                rbPower  = 0.75;
            }
            else if(doMoveForward){
                lfPower  = -0.75;
                lbPower  = -0.75;
                rfPower  = -0.75;
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

            robot.clawServo      .setPower(clawPower);
            robot.leftFrontDrive .setPower(lfPower);
            robot.leftBackDrive  .setPower(lbPower);
            robot.rightFrontDrive.setPower(rfPower);
            robot.rightBackDrive .setPower(rbPower);
            robot.collectorLeft  .setPower(collectorPower);
            robot.collectorRight .setPower(collectorPower);
            robot.outMotor       .setPower(outMotorPower );

            robot.intakeLock     .setPower(lockPower);
            robot.upServo        .setPower(upPower  );
            robot.upServo2       .setPower(upPower  );
            telemetry.addData("Front Motors", "left front (%.2f), right front(%.2f)", lfPower, rfPower);
            telemetry.addData("Back Motors",  "left back (%.2f), right back(%.2f)",   lbPower,rbPower);

            telemetry.update();
        }

        public void stop(){
            robot.leftFrontDrive .setPower(0);
            robot.leftBackDrive  .setPower(0);
            robot.rightFrontDrive.setPower(0);
            robot.leftBackDrive  .setPower(0);

            robot.intakeLock     .setPower(0);
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
