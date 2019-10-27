package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp Mode")
public class TeleOpMode extends OpMode {

    HardwareMapping robot = new HardwareMapping();

    public void init ()
    {
        robot.init(hardwareMap);
    }

    public void loop ()
    {

        if (gamepad1.a)
        {
            robot.ArmLiftServo.setPower(.75);
        }

        else if (gamepad1.y)
        {
            robot.ArmLiftServo.setPower(-.75);
        }

        else
        {
            robot.ArmLiftServo.setPower(0);
        }

    }

    public void stop ()
    {
        robot.ArmLiftServo.setPower(0);
    }


}
