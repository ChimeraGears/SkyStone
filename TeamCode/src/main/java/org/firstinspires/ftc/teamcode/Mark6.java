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
    public int doCollection = 0;
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
        robot.collector.setDirection(DcMotor.Direction.FORWARD);
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
        boolean collectorIn = gamepad2.x;
        boolean collectorOut = gamepad2.y;
        boolean slowItDown = gamepad1.right_bumper;
        boolean speedItUp = gamepad2.left_bumper;
        //This if statement is crucial. it allows us to
        //slow down or speed up driving with a single button.
        if(slowItDown){
            doSlowControls = true;
        } else if (speedItUp){
            doSlowControls = false;
        }
        //This piece of code allows us to turn on and off the collector
        if(collectorIn){
            doCollection = 2;
            collectorPower = 0.90;
        } else if (gamepad2.right_stick_button){
            doCollection = 1;
            collectorPower = 0.00;
        } else if (collectorOut){
            doCollection = 0;
            collectorPower = -0.90;
        }

        double speedToggle = (doSlowControls)?slowPower:fastPower;
        totalPower = speedToggle;
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
        } else if(strafeRight){
            rightFrontPower = -totalPower;
            leftBackPower = -totalPower;
            leftFrontPower = totalPower;
            rightBackPower = totalPower;

        } else if(strafeLeft){
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
        //this will be the arm code. it works! (mostly)
        if (gamepad2.right_trigger > 0.60){
            armPower = gamepad2.right_trigger;
        } else if (gamepad2.left_trigger > .30){
            armPower = -gamepad2.left_trigger;
        } else {
            armPower = 0.00;
        }

        if (gamepad2.a){
            robot.clawServo.setPosition(0.25);
            robot.armServo2.setPosition(0.65);
            robot.armServo1.setPosition(0.00);
        }
        if (gamepad2.b){
            robot.armServo2.setPosition(0.00);
            robot.armServo1.setPosition(0.00);
        }

        //These commands set power to each motor.
        robot.leftFrontDrive.setPower(leftFrontPower);
        robot.rightFrontDrive.setPower(rightFrontPower);
        robot.leftBackDrive.setPower(leftBackPower);
        robot.rightBackDrive.setPower(rightBackPower);
        robot.armMotor.setPower(armPower);
        robot.collector.setPower(collectorPower);
        //This is data for the drivers to see.
        telemetry.addData("Motor", "left (%.2f), right(%.2f)", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
        telemetry.update();
    }

    public boolean ClawToggle  (String toggle) {
        if (toggle.equals("Open")){
            robot.clawServo.setPosition(0.00);
        } else if (toggle.equals("Close")){
            robot.clawServo.setPosition(0.75);
        }
        return true;
    }
    public boolean ElbowToggle (String toggle) {
        if (toggle.equals("Open")){
            robot.armServo2.setPosition(0.00);
        } else if (toggle.equals("Close")){
            robot.armServo2.setPosition(-0.50);
        }
        return true;
    }
    public boolean WristToggle (String toggle) {
        if (toggle.equals("Open")){
            robot.armServo1.setPosition(0.00);
        }
        if (toggle.equals("Closed")){
            robot.armServo1.setPosition(0.75);
        }
        return true;
    }
}