package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Fresh Test")
//12.1211 inches per rotation--JB
//2240 ticks per rotation
public class roboTest extends OpMode {
    HardwareMapping robot = new HardwareMapping();
    public final static double REV_MIN = 0.00;
    public final static double REV_MAX = 0.75;
    public boolean doSlowControls = false;
    public double totalPower;
    public double fastPower = .8;
    public double slowPower = .4;
    public double leftFrontPower;
    public double rightFrontPower;
    public double leftBackPower;
    public double rightBackPower;

    public void init() {
        robot.init(hardwareMap);
        robot.leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        //robot.armMotor.setDirection(DcMotor.Direction.REVERSE);
        //robot.collectorLeft.setDirection(DcMotor.Direction.REVERSE);
        //robot.collectorRight.setDirection(DcMotor.Direction.FORWARD);
    }


    public void loop() {

        double rotate = gamepad1.right_stick_x;
        boolean moveForward = gamepad1.dpad_up;
        boolean moveBackward = gamepad1.dpad_down;
        boolean strafeLeft = gamepad1.dpad_left;
        boolean strafeRight = gamepad1.dpad_right;

        //speed toggle--untested
        if(gamepad1.right_bumper){
            doSlowControls = !doSlowControls;
        }
        double speedToggle = (doSlowControls)?slowPower:fastPower;
        totalPower = speedToggle;

        double leftCollectorPower = totalPower;

        //Move Forward
        if(moveForward){
            leftFrontPower = totalPower;
            rightFrontPower = totalPower;
            leftBackPower = totalPower;
            rightBackPower = totalPower;
            //Move Backward
        } else if(moveBackward){
            leftFrontPower = -totalPower;
            rightFrontPower = -totalPower;
            leftBackPower = -totalPower;
            rightBackPower = -totalPower;
            //Strafe Light
        } else if(strafeLeft){
            rightFrontPower = totalPower;
            leftBackPower = totalPower;
            leftFrontPower = -totalPower;
            rightBackPower = -totalPower;
            //Strafe Right
        } else if(strafeRight){
            rightFrontPower = -totalPower;
            leftBackPower = -totalPower;
            leftFrontPower = totalPower;
            rightBackPower = totalPower;
            //When Busy Stuffs
        } else {
            leftFrontPower = totalPower-totalPower;
            rightFrontPower = totalPower-totalPower;
            leftBackPower = totalPower-totalPower;
            rightBackPower = totalPower-totalPower;
        }
        //rotation
        if (rotate >= 0.25 && rotate <= 1.00) {
            leftFrontPower = totalPower;
            rightFrontPower = -totalPower-totalPower;
            leftBackPower = totalPower-totalPower;
            rightBackPower = -totalPower-totalPower;
        } else if (rotate <= -.25 && rotate >= -1.00) {
            leftFrontPower = -totalPower-totalPower;
            rightFrontPower = totalPower-totalPower;
            leftBackPower = -totalPower-totalPower;
            rightBackPower = totalPower-totalPower;
        }


        robot.leftFrontDrive.setPower(leftFrontPower);
        robot.rightFrontDrive.setPower(rightFrontPower);
        robot.leftBackDrive.setPower(leftBackPower);
        robot.rightBackDrive.setPower(rightBackPower);

        if (gamepad1.right_bumper){
            leftCollectorPower = 1.00;
        }
        if (gamepad1.left_bumper){
            leftCollectorPower = -1.00;
        }
        if (!gamepad1.left_bumper && !gamepad1.right_bumper){
            leftCollectorPower = 0.00;
        }

        robot.collectorLeft.setPower(leftCollectorPower);
        robot.collectorRight.setPower(-1*leftCollectorPower);

        //Telemetry readings
        telemetry.addData("Motor", "left (%.2f), right(%.2f)", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
        telemetry.addData("Servo Pos","claw (%2f)",robot.ClawServo.getPosition());
        //telemetry.addData("Arm Position:","(%.2d)", brickLevel);
        //telemetry.addData("Intake:","left (%.2f), right(%.2f)",leftCollectorPower,rightCollectorPower);
        telemetry.update();

    }
}