package org.firstinspires.ftc.teamcode.OldCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Thread.sleep;
@Deprecated
@TeleOp(name="Old_MacenumTest", group="Opmode")

public class MacenumTest extends OpMode{
    public DcMotor armMotor = null;
    /*public DcMotor leftFrontDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor rightBackDrive = null;*/
    public Servo susan;
    public Servo claw;
    @Override
    public void init() {
                //servo declaration
        //susan = hardwareMap.get(Servo.class, "lazy_susan");
        susan = hardwareMap.servo.get("lazy_susan");
        //claw = hardwareMap.get(Servo.class, "claw");
        claw = hardwareMap.servo.get("claw");
                //names of motors here must match their names in the phone
        armMotor = hardwareMap.get(DcMotor.class, "arm_motor");
        /*
        leftFrontDrive = hardwareMap.get(DcMotor.class,"front_left");
        rightFrontDrive = hardwareMap.get(DcMotor.class,"front_right");
        leftBackDrive = hardwareMap.get(DcMotor.class,"back_left");
        rightBackDrive = hardwareMap.get(DcMotor.class,"back_right");
        //this is the part determining directions!
        */
        armMotor.setDirection(DcMotor.Direction.FORWARD);
        /*
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
         */
    }
    @Override
    public void loop(){
        double armMotorPower;
        double leftFrontPower;
        double rightFrontPower;
        double leftBackPower;
        double rightBackPower;
        boolean susanRight = gamepad2.right_bumper;
        boolean susanLeft = gamepad2.left_bumper;
        double lift = gamepad2.right_trigger;
        double drop = -1*gamepad2.left_trigger;
        double drive = gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;
        double susanPosition = 0.5;

        if(susanRight){
            susanPosition = susanPosition + 0.10;
        }
        if(susanLeft){
            susanPosition = susanPosition - 0.10;
        }
        susan.setPosition(susanPosition);
        if (gamepad2.a &&!gamepad2.b){
            claw.setPosition(0.0);
        } else if (gamepad2.b && !gamepad2.a) {
            claw.setPosition(1.0);
        }
        leftFrontPower = Range.clip(drive-strafe+turn,-1.0,1.0);
        leftBackPower = Range.clip(drive+strafe+turn,-1.0,1.0);
        rightFrontPower = Range.clip(drive+strafe-turn,-1.0,1.0);
        rightBackPower = Range.clip(drive-strafe-turn,-1.0,1.0);
        armMotorPower = Range.clip(lift+drop,-1.0,1.0);

        /*leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftBackDrive.setPower(leftBackPower);
        rightBackDrive.setPower(rightBackPower);*/
        armMotor.setPower(armMotorPower);

        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
        telemetry.addData("Susan Position","position (%.2f)",susanPosition);
        telemetry.addData("Arm Position", "level (%2f)", armMotorPower);
        telemetry.addData("Claw Position", "position (%.2f)" ,claw.getPosition());
        telemetry.update();
    }
}