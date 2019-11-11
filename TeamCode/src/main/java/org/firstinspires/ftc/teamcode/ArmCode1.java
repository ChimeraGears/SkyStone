package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Arm Op")
public class ArmCode1 extends OpMode{
    HardwareMapping robot = new HardwareMapping();
    public final static double REV_MIN = 0.00;
    public final static double REV_MAX = 1.00;

    public void init() {
        robot.init(hardwareMap);
        robot.ArmMotor.setDirection(DcMotor.Direction.REVERSE);
    }
    public void loop(){
        double armMotorPower;
        if (gamepad1.right_bumper)
        {
            int iterPos;
            if(robot.ArmMotor.getCurrentPosition()+40 <= 160) {
                iterPos = robot.ArmMotor.getCurrentPosition()+40;
            } else {
                iterPos = 160;
            }
            encMovement(robot.ArmMotor,1,iterPos);

        }
        if (gamepad1.left_bumper)
        {
            int iterPos;
            if(robot.ArmMotor.getCurrentPosition()>= 0 && robot.ArmMotor.getCurrentPosition()-40 >= 0){
                iterPos = robot.ArmMotor.getCurrentPosition()-40;
            } else{
                iterPos = 0;
            }
            encMovement(robot.ArmMotor,1,iterPos);

        }
    }

    public void encMovement(DcMotor motorName, double motorPower, int targetPos){
        motorName.setTargetPosition(motorName.getCurrentPosition()+ targetPos);
        motorName.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorName.setPower(motorPower);
    }
}
