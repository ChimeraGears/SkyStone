package org.firstinspires.ftc.teamcode;
/*
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ArmCode1 {
    HardwareMapping robot = new HardwareMapping();

    public boolean armOut(int brickLevel){
        int iter = 0;
        if (brickLevel/4 == 0){
            robot.armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        robot.armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        switch (iter){
            case 0:
                robot.armMotor.setTargetPosition(brickLevel/4);
                robot.armMotor.setPower(.15);
                iter++;
                break;
            case 1:
                robot.ArmServo1.setPosition(1.00);
                iter++;
                break;
            case 2:
                robot.ArmServo2.setPosition(1.00);
                iter++;
                break;
            case 3:
                robot.ClawServo.setPosition(0.00);
                break;
            case 4:
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
}*/