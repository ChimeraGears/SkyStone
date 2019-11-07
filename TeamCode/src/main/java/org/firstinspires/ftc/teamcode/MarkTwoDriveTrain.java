package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Mark Two TeleOp")
//12.1211 inches per rotation--JB
public class MarkTwoDriveTrain extends OpMode{
    HardwareMapping robot = new HardwareMapping();
    SleepFunction sleep = new SleepFunction();
    public final static double REV_MIN = 0.00;
    public final static double REV_MAX = 1.00;

    public void init() {
        robot.init(hardwareMap);
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
        double drive = gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;

        if (gamepad1.dpad_up)
        {
            robot.HitchServo.setPosition(REV_MAX);
        }

        if (gamepad1.dpad_down)
        {
            robot.HitchServo.setPosition(REV_MIN);
        }

        leftFrontPower = Range.clip(drive+strafe+turn,-1.0,1.0);
        leftBackPower = Range.clip(drive+strafe-turn,-1.0,1.0);
        rightFrontPower = Range.clip(drive-strafe-turn,-1.0,1.0);
        rightBackPower = Range.clip(drive-strafe+turn,-1.0,1.0);

        robot.leftFrontDrive.setPower(leftFrontPower);
        robot.rightFrontDrive.setPower(rightFrontPower);
        robot.leftBackDrive.setPower(leftBackPower);
        robot.rightBackDrive.setPower(rightBackPower);

    telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
    telemetry.update();
    }
}