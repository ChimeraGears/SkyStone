package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Mark Two TeleOp")
//12.1211 inches per rotation--JB
public class RobotMaster extends OpMode {
    HardwareMapping robot = new HardwareMapping();
        //ArmCode1 arm = new ArmCode1();
    public final static double REV_MIN = 0.00;
    public final static double REV_MAX = 1.00;

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
        double leftFrontPower;
        double rightFrontPower;
        double leftBackPower;
        double rightBackPower;
        double drive = gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;
        int brickLevel = 0;
        double leftCollectorPower = 0;
        double rightCollectorPower = -1*leftCollectorPower;
        /*
        if (gamepad1.right_bumper){
            leftCollectorPower = 1.00;
        }
        if (gamepad1.left_bumper){
            leftCollectorPower = -1.00;
        }
        if (gamepad1.dpad_up) {
            robot.HitchServo.setPosition(REV_MAX);
        }
        if (gamepad1.dpad_down) {
            robot.HitchServo.setPosition(REV_MIN);
        }
        //Arm Movement--see ArmCode1 for method details
        int brickStackTurnary = 0;
        if (gamepad2.right_bumper){
            brickStackTurnary = (brickLevel++ < 4)?brickLevel++:brickLevel;
        }
        if (gamepad2.left_bumper){
            brickStackTurnary = (brickLevel-- > 0)?brickLevel--:brickLevel;
        }
        if (gamepad2.a){
            robot.ClawServo.setPosition(0);
            boolean x = arm.armOut(brickStackTurnary);
            if (x) {
                arm.armIn();
            }
        }
        */
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
            leftFrontPower = Range.clip(drive + strafe + turn, -1.0, 1.0);
            leftBackPower = Range.clip(drive + strafe - turn, -1.0, 1.0);
            rightFrontPower = Range.clip(drive - strafe - turn, -1.0, 1.0);
            rightBackPower = Range.clip(drive - strafe + turn, -1.0, 1.0);
            //armMotorPower = Range.clip(armPowerUp - armPowerDown, -1.0, 1.0);
            
            robot.leftFrontDrive.setPower(leftFrontPower);
            robot.rightFrontDrive.setPower(rightFrontPower);
            robot.leftBackDrive.setPower(leftBackPower);
            robot.rightBackDrive.setPower(rightBackPower);
            /*robot.collectorLeft.setPower(leftCollectorPower);
            robot.collectorRight.setPower(rightCollectorPower);
            */
        //Telemetry readings
            telemetry.addData("Motor", "left (%.2f), right(%.2f)", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
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