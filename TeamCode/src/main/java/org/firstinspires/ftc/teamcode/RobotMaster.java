package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Mark Two TeleOp")
//12.1211 inches per rotation--JB
//2240 ticks per rotation
public class RobotMaster extends OpMode {
    HardwareMapping robot = new HardwareMapping();
    public final static double REV_MIN = 0.00;
    public final static double REV_MAX = 0.75;
    public boolean doSlowControls = false;
    public int brickLevel = 0;
    public boolean lieDetector;
    public int brickStackTurnary = 0;
    public int iterable;

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

    public void armServoTest(){
        robot.ClawServo.setPosition(1.00);
        robot.ClawServo.setPosition(0.00);
        robot.ClawServo.setPosition(1.00);
        robot.ClawServo.setPosition(0.00);
    }
    public boolean armOut(int brickLevel){

        if (brickLevel == 0){
            //robot.armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        else {
            //robot.armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //robot.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        switch (iterable){
            case 0:
                //robot.armMotor.setTargetPosition(brickLevel);
                robot.armMotor.setPower(.15);
                iterable++;
                break;
            case 1:
                robot.ArmServo1.setPosition(1.00);
                iterable++;
                break;
            case 2:
                robot.ArmServo2.setPosition(1.00);
                iterable++;
                break;
            case 3:
                robot.ClawServo.setPosition(0.00);
                break;
        }
        return true;
    }
    public void armIn(){
        int iter = 0;
        switch (iter){
            case 0:
                robot.ArmServo2.setPosition(0.00);
                iter++;
                break;
            case 1:
                robot.ArmServo1.setPosition(0.00);
                iter++;
                break;
            case 2:
                robot.armMotor.setTargetPosition(0);
                robot.armMotor.setPower(.15);
                break;
        }
    }

    public void loop() {
        double fastPower = .8;
        double slowPower = .4;
        double leftFrontPower;
        double rightFrontPower;
        double leftBackPower;
        double rightBackPower;
        double totalPower;

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
        double rightCollectorPower = -1*totalPower;

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

        if (gamepad1.a){
            robot.blockGrabber.setPosition(REV_MAX);
        }
        if (gamepad2.a){
            robot.ClawServo.setPosition(REV_MAX);
        }
        if (gamepad2.b){
            robot.blockGrabber.setPosition(REV_MIN);
        }

        if (gamepad1.right_bumper){
            leftCollectorPower = 1.00;
        }
        if (gamepad1.left_bumper){
            leftCollectorPower = -1.00;
        }
        if (!gamepad1.left_bumper && !gamepad1.right_bumper){
            leftCollectorPower = 0.00;
        }

        /*
         */
        //Arm Movement--see ArmCode1 for method details

        /*if (gamepad2.right_bumper){
            brickStackTurnary = (brickLevel+560 < 2240)?brickLevel+560:brickLevel;
        }
        if (gamepad2.left_bumper){
            brickStackTurnary = (brickLevel-560 > 0)?brickLevel-560:brickLevel;
        }



        if (gamepad2.a){
            robot.ClawServo.setPosition(1);
            lieDetector = arm.armOut(brickStackTurnary);
        }


        if (gamepad2.b && lieDetector){
            arm.armIn();
        }

         */

        //Use gamepad2.(something) for up-down
        //Check encoder values for brickLevel nums (see diff for num)

        //old arm code attempts--mark_1
        /*if (gamepad1.right_bumper) {
            int iterPos = 0;
            int armPressure = ((robot.armMotor.getCurrentPosition() >= 0) && (robot.armMotor.getCurrentPosition() + 40 <= 180)) ? robot.armMotor.getCurrentPosition() - 40 : 0;
            iterPos = armPressure;
            robot.armMotor.setTargetPosition(iterPos);
            robot.armMotor.setPower(.5);
            robot.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }*/

        /*if (gamepad1.left_bumper) {
            int iterPos = 0;
            int armPressure = ((robot.armMotor.getCurrentPosition() >= 0) && (robot.armMotor.getCurrentPosition() - 40 >= 0)) ? robot.armMotor.getCurrentPosition() - 40 : 0;
            iterPos = armPressure;
            robot.armMotor.setTargetPosition(iterPos);
            robot.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.armMotor.setPower(.5);
        }*/
        //Driving controls and maths

            //armMotorPower = Range.clip(armPowerUp - armPowerDown, -1.0, 1.0);


            /*robot.collectorLeft.setPower(leftCollectorPower);
            robot.collectorRight.setPower(rightCollectorPower);
            */
        //Telemetry readings
            telemetry.addData("Motor", "left (%.2f), right(%.2f)", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
            telemetry.addData("Servo Pos","claw (%2f)",robot.ClawServo.getPosition());
            //telemetry.addData("Arm Position:","(%.2d)", brickLevel);
            //telemetry.addData("Intake:","left (%.2f), right(%.2f)",leftCollectorPower,rightCollectorPower);
            telemetry.update();


        /*double motor1power;
        DcMotor motor1 = null;
        double motor2power;
        DcMotor motor2 = null;

        motor1power = motor1.getPower();
        motor2power = motor2.getPower();
        if(motor1power != motor2power) {
            if (motor1power < motor2power) {
                motor1.setPower(motor1power + (motor2power - motor1power));
            } else if (motor2power < motor1power) {
                motor2.setPower(motor2power + (motor1power - motor2power));
            }
        }*/
        }
    }