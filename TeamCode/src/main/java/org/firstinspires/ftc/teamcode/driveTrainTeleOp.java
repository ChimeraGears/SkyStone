package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Sherlock Holmes")
//12.1211 inches per rotation--JB
//2240 ticks per rotation
public class driveTrainTeleOp extends OpMode {
    HardwareMapping robot = new HardwareMapping();
    public boolean doSlowControls = false;
    public boolean doCollection = false;
    public double totalPower;
    public double fastPower = .9;
    public double slowPower = fastPower/2;
    public double leftFrontPower;
    public double rightFrontPower;
    public double leftBackPower;
    public double rightBackPower;
    public double collectorPower;
    public double armPower;

    public void init() {
        robot.init(hardwareMap);
        robot.leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.armMotor.setDirection(DcMotor.Direction.REVERSE);
        robot.collector.setDirection(DcMotor.Direction.REVERSE);
        //robot.collectorLeft.setDirection(DcMotor.Direction.REVERSE);
    }


    public void loop() {

        double rotate = gamepad1.right_stick_x;
        boolean moveForward = gamepad1.dpad_up;
        boolean moveBackward = gamepad1.dpad_down;
        boolean strafeLeft = gamepad1.dpad_left;
        boolean strafeRight = gamepad1.dpad_right;
        boolean collectorSwitch = gamepad2.b;
        boolean slowItDown = gamepad1.right_bumper;
        //functioning speed toggle
        if(slowItDown){
            doSlowControls = !doSlowControls;
        }

        if(collectorSwitch){
            doCollection = !doCollection;
        }

        //This stuff works! But the collector is UNTESTED
        double collectorToggle = (doCollection)?1.00:0.00;
        double speedToggle = (doSlowControls)?slowPower:fastPower;
        totalPower = speedToggle;
        collectorPower = collectorToggle;
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
            rightFrontPower = -totalPower;
            leftBackPower = -totalPower;
            leftFrontPower = totalPower;
            rightBackPower = totalPower;
            //Strafe Right
        } else if(strafeRight){
            rightFrontPower = totalPower;
            leftBackPower = totalPower;
            leftFrontPower = -totalPower;
            rightBackPower = -totalPower;
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
            rightFrontPower = -totalPower;
            leftBackPower = totalPower;
            rightBackPower = -totalPower;
        } else if (rotate <= -.25 && rotate >= -1.00) {
            leftFrontPower = -totalPower;
            rightFrontPower = totalPower;
            leftBackPower = -totalPower;
            rightBackPower = totalPower;
        }
        //this will be the arm code. UNTESTED
        if (gamepad2.right_trigger > 0.60){
            armPower = gamepad2.right_trigger;
        } else if (gamepad2.left_trigger > .60){
            armPower = -gamepad2.left_trigger;
        } else {
            armPower = 0.00;
        }

        if ((armPower >= 0.60 && armPower <= 1.00)&&(gamepad2.a)){
            int operation = 1;
            switch(operation){
                case 1:
                    robot.ClawServo.setPosition(0.80);
                    operation++;
                case 2:
                    robot.ArmServo2.setPosition(0.80);
                    operation++;
                case 3:
                    robot.ArmServo1.setPosition(0.80);
                    break;
            }
        }
        //All those fancy set power commands
        robot.leftFrontDrive.setPower(leftFrontPower);
        robot.rightFrontDrive.setPower(rightFrontPower);
        robot.leftBackDrive.setPower(leftBackPower);
        robot.rightBackDrive.setPower(rightBackPower);
        robot.armMotor.setPower(armPower);
        //Telemetry readings
        telemetry.addData("Motor", "left (%.2f), right(%.2f)", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
        telemetry.update();

    }
}