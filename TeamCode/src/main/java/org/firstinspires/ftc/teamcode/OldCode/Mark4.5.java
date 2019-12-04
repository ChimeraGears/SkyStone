package org.firstinspires.ftc.teamcode.OldCode;
/*
public class MeetZeroTeleOp extends OpMode{

    HardwareMapping robot = new HardwareMapping();
    SleepFunction sleep = new SleepFunction();
    // public final static double ARM_HOME = 0.5;
    // public final static double ARM_MIN = 0.0;
    // public final static double ARM_MAX = 1.0;
    // public final static double SLIDE_HOME = 0.5;
    // public final static double SLIDE_MIN = 0.0;
    // public final static double SLIDE_MAX = 1.0;
    //public final static double REV_HOME = 0.5;
    public final static double REV_MIN = 0.07;
    public final static double REV_MAX = 1.0;
    // public final double ARM_SPEED = 0.01;
    // public double armPosition = ARM_HOME;

    public void init () {
        robot.init(hardwareMap);
        robot.leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    public void loop () {
        double leftFrontPower;
        double rightFrontPower;
        double leftBackPower;
        double rightBackPower;
        double drive = gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;

        //possibly try .scale function
        //End Coders let motors run to a set position.

        leftFrontPower = Range.clip(drive-strafe+turn,-1.0,1.0);
        leftBackPower = Range.clip(drive+strafe+turn,-1.0,1.0);
        rightFrontPower = Range.clip(drive+strafe-turn,-1.0,1.0);
        rightBackPower = Range.clip(drive-strafe-turn,-1.0,1.0);

        /*
        Arm - dpad.up, dpad.down
        Lazy - dpad.right, dpad.left
        Hitch    - left_trigger, right_trigger
        Slide - left_bumper, right_bumper
        Claw - x, b
        Wrist - y, a
         *
        robot.leftFrontDrive.setPower(leftFrontPower);
        robot.rightFrontDrive.setPower(rightFrontPower);
        robot.leftBackDrive.setPower(leftBackPower);
        robot.rightBackDrive.setPower(rightBackPower);

        if (gamepad1.dpad_up)
        {
            robot.HitchServo.setPosition(REV_MAX);
        }

        if (gamepad1.dpad_down)
        {
            robot.HitchServo.setPosition(REV_MIN);
        }

        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
        telemetry.update();
    }

}
*/