package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


//Arm   - dpad.up, dpad.down
//Lazy  - dpad.right, dpad.left
//Hitch - left_trigger, right_trigger

@TeleOp(name="Mark 6")
//12.1211 inches per rotation--JB
//2240 ticks per rotation
public class Mark6 extends OpMode {
    HardwareMapping robot = new HardwareMapping();
    //We had a lot of difficulty in making power controls that worked,
    //and realized that if ewe made them global they would actually
    //function. This was a small hurdle that took weeks to see.
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
        //This step in the code allows us to program all the motors the same,
        //even though they face different directions.
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
        //These variables that are declared here just add a
        //user control aspect to each of the variables by
        //assigning a button for each.
        double rotate = gamepad1.right_stick_x;
        boolean moveForward = gamepad1.dpad_up;
        boolean moveBackward = gamepad1.dpad_down;
        boolean strafeLeft = gamepad1.dpad_left;
        boolean strafeRight = gamepad1.dpad_right;
        boolean collectorSwitch = gamepad2.b;
        boolean slowItDown = gamepad1.right_bumper;

        //This if statement is crucial. it allows us to
        //slow down or speed up driving with a single button.
        if(slowItDown){
            doSlowControls = !doSlowControls;
        }
        //This piece of code allows us to turn on and off the collector
        //with one button.
        if(collectorSwitch){
            doCollection = !doCollection;
        }

        //This stuff works! But the collector is UNTESTED
        double collectorToggle = (doCollection)?1.00:0.00;
        double speedToggle = (doSlowControls)?slowPower:fastPower;
        totalPower = speedToggle;
        collectorPower = collectorToggle;
        //Drive forward
        if(moveForward){
            leftFrontPower = totalPower;
            rightFrontPower = totalPower;
            leftBackPower = totalPower;
            rightBackPower = totalPower;
            //Drive backwards
        } else if(moveBackward){
            leftFrontPower = -totalPower;
            rightFrontPower = -totalPower;
            leftBackPower = -totalPower;
            rightBackPower = -totalPower;
            //This chunk of code allows our robot to strafe.
        } else if(strafeLeft){
            rightFrontPower = -totalPower;
            leftBackPower = -totalPower;
            leftFrontPower = totalPower;
            rightBackPower = totalPower;

        } else if(strafeRight){
            rightFrontPower = totalPower;
            leftBackPower = totalPower;
            leftFrontPower = -totalPower;
            rightBackPower = -totalPower;
            //This is called "no power behavior." When there
            //is no power to the motors, they won't move.
        } else {
            leftFrontPower = totalPower-totalPower;
            rightFrontPower = totalPower-totalPower;
            leftBackPower = totalPower-totalPower;
            rightBackPower = totalPower-totalPower;
        }
        //robot rotation!
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
                    robot.clawServo.setPosition(0.80);
                    operation++;
                    break;
                case 2:
                    robot.armServo2.setPosition(0.80);
                    operation++;
                    break;
                case 3:
                    robot.armServo1.setPosition(0.80);
                    break;
            }
        }

        //These commands set power to each motor.
        robot.leftFrontDrive.setPower(leftFrontPower);
        robot.rightFrontDrive.setPower(rightFrontPower);
        robot.leftBackDrive.setPower(leftBackPower);
        robot.rightBackDrive.setPower(rightBackPower);
        robot.armMotor.setPower(armPower);
        //This is data for the drivers to see.
        telemetry.addData("Motor", "left (%.2f), right(%.2f)", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
        telemetry.update();

    }
}