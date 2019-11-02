package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp Mode")
public class TeleOpMode extends OpMode {

    HardwareMapping robot = new HardwareMapping();

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


    public void init ()

    {
        robot.init(hardwareMap);
        robot.ClawServo.setPosition(REV_HOME);
        //robot.WristServo.setPosition(REV_HOME);
        robot.LazyServo.setPosition(REV_HOME);
        robot.HitchServo.setPosition(REV_HOME);
        robot.leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    public void loop ()

    {
        double leftFrontPower;
        double rightFrontPower;
        double leftBackPower;
        double rightBackPower;
        double drive = .8*gamepad1.left_stick_y;
        double turn = .8*gamepad1.right_stick_x;
        double strafe = .8*gamepad1.left_stick_x;
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

         */

        if (gamepad2.a)
        {
            robot.ClawServo.setPosition(REV_MAX);
        }

        if (gamepad2.b)
        {
            robot.ClawServo.setPosition(REV_MIN);
        }

        /*if (gamepad1.y)
        {
            robot.WristServo.setPosition(REV_MAX);
        }
*/
        /*if (gamepad1.a)
        {
            robot.WristServo.setPosition(REV_MIN);
        }
*/
        if (gamepad1.dpad_right)
        {
            robot.LazyServo.setPosition(REV_MAX);
        }

        if (gamepad1.dpad_left)
        {
            robot.LazyServo.setPosition(REV_MIN);
        }

        if (gamepad1.dpad_up)
        {
            robot.HitchServo.setPosition(REV_MAX);
        }

        if (gamepad1.dpad_down)
        {
            robot.HitchServo.setPosition(REV_MIN);
        }
        if(gamepad2.left_trigger > 0.50)
        {
            if(robot.ArmServo.getPosition()<1.00)
            {
                double armPosCheck = robot.ArmServo.getPosition();
                armPosCheck += 0.10;
                robot.ArmServo.setPosition(armPosCheck);
                robot.WristServo.setPosition(-1*armPosCheck);
            }
        }
        if(gamepad2.right_trigger > 0.50)
        {
            if(robot.ArmServo.getPosition()>0.00)
            {
                double armPosCheck = robot.ArmServo.getPosition();
                armPosCheck -= 0.10;
                robot.ArmServo.setPosition(armPosCheck);
                robot.WristServo.setPosition(-1*armPosCheck);
            }
        }
        /*if (gamepad1.dpad_up)
        {
            robot.ArmServo.setPower(.75);
        }

        else if (gamepad1.dpad_down)
        {
            robot.ArmServo.setPower(-.75);
        }

        else
        {
            robot.ArmServo.setPower(0);
        }
*/
        if (gamepad2.dpad_up)
        {
            robot.SlideServo.setPower(.75);
        }

        else if (gamepad2.dpad_down)
        {
            robot.SlideServo.setPower(-.75);
        }

        else
        {
            robot.SlideServo.setPower(0);
        }

        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
        telemetry.update();
    }

    public void stop ()
    {
        robot.SlideServo.setPower(0);
        robot.ArmServo.setPosition(robot.ArmServo.getPosition());
        robot.ClawServo.setPosition(REV_HOME);
        //robot.WristServo.setPosition(REV_HOME);
        robot.LazyServo.setPosition(REV_HOME);
        robot.HitchServo.setPosition(REV_HOME);
    }


}
