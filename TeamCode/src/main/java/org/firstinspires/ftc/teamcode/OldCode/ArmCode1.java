/*
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ArmCode1 {
    public int iterable = 0;
    HardwareMapping robot = new HardwareMapping();

    public ArmCode1(){
    }

    public void armServoTest(){
        robot.clawServo.setPosition(1.00);
        robot.clawServo.setPosition(0.00);
        robot.clawServo.setPosition(1.00);
        robot.clawServo.setPosition(0.00);
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
                robot.armServo1.setPosition(1.00);
                iterable++;
                break;
            case 2:
                robot.armServo2.setPosition(1.00);
                iterable++;
                break;
            case 3:
                robot.clawServo.setPosition(0.00);
                break;
        }
        return true;
    }
    public void armIn(){
        int iter = 0;
        switch (iter){
            case 0:
                robot.armServo2.setPosition(0.00);
                iter++;
                break;
            case 1:
                robot.armServo1.setPosition(0.00);
                iter++;
                break;
            case 2:
                robot.armMotor.setTargetPosition(0);
                robot.armMotor.setPower(.15);
                break;
        }
    }
}

 */