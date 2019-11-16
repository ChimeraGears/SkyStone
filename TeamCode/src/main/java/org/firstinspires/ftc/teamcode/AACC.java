package org.firstinspires.ftc.teamcode.AydenCode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.HardwareMapping;
import org.firstinspires.ftc.teamcode.SleepFunction;

@TeleOp(name="Ayden Code")
public class AACC extends OpMode {

    HardwareMapping robot = new HardwareMapping();
    SleepFunction sleep = new SleepFunction();
    // public final static double ARM_HOME = 0.5;
    // public final static double ARM_MIN = 0.0;
    // public final static double ARM_MAX = 1.0;
    // public final static double SLIDE_HOME = 0.5;
    // public final static double SLIDE_MIN = 0.0;
    // public final static double SLIDE_MAX = 1.0;
    public final static double REV_HOME = 0.5;
    public final static double REV_MIN = 0.0;
    public final static double REV_MAX = 1.0;
    // public final double ARM_SPEED = 0.01;
    // public double armPosition = ARM_HOME;


    public void init() {
        robot.init(hardwareMap);
        robot.ClawServo.setPosition(REV_HOME);
        robot.WristServo.setPosition(REV_HOME);
        robot.LazyServo.setPosition(REV_HOME);
        robot.HitchServo.setPosition(REV_HOME);
        robot.leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    public void loop() {
        double leftFrontPower;
        double rightFrontPower;
        double leftBackPower;
        double rightBackPower;
        double drive = .8 * gamepad1.left_stick_y;
        double turn = .8 * gamepad1.right_stick_x;
        double strafe = .8 * gamepad1.left_stick_x;
        boolean leftTriggerButton = false, rightTriggerButton = false;
        if (gamepad2.left_trigger > 0.50) {
            leftTriggerButton = true;
        }
        if (gamepad2.right_trigger > 0.50) {
            rightTriggerButton = true;
        }
        //possibly try .scale function
        //End Coders let motors run to a set position.

        leftFrontPower = Range.clip(drive - strafe + turn, -1.0, 1.0);
        leftBackPower = Range.clip(drive + strafe + turn, -1.0, 1.0);
        rightFrontPower = Range.clip(drive + strafe - turn, -1.0, 1.0);
        rightBackPower = Range.clip(drive - strafe - turn, -1.0, 1.0);

        /*
        Arm - dpad.up, dpad.down
        Lazy - dpad.right, dpad.left
        Hitch    - left_trigger, right_trigger
        Slide - left_bumper, right_bumper
        Claw - x, b
        Wrist - y, a

         */

        if (gamepad1.a) {
            robot.LazyServo.setPosition(REV_MAX);
            sleep.SleepyTime(1);
            robot.ClawServo.setPosition(REV_MAX);
        }

        if (gamepad1.b) {
            robot.LazyServo.setPosition(REV_MIN);
            sleep.SleepyTime(1);
            robot.ClawServo.setPosition(REV_MIN);

        }
    }
}