import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.HardwareMapping;

@TeleOp (name = "Mark 9")
public class Mark9 extends OpMode {
    public HardwareMapping robot = new HardwareMapping();
    public double lfPower, rfPower, lbPower, rbPower;
    public int zed;
    public void init(){
        robot.init(hardwareMap);

        robot.leftFrontDrive .setDirection(DcMotor.Direction.REVERSE);
        robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.leftBackDrive  .setDirection(DcMotor.Direction.REVERSE);
        robot.rightBackDrive .setDirection(DcMotor.Direction.FORWARD);
        robot.collectorLeft  .setDirection(DcMotor.Direction.FORWARD);
        robot.collectorRight .setDirection(DcMotor.Direction.REVERSE);
        robot.outMotor       .setDirection(DcMotor.Direction.REVERSE);
        zed = 0;
    }

    public void single(int i){
        if(i == 0) {
            robot.clawServo.setPosition(0);
            robot.clawServo.setPosition(0);
        }
    }

    public void loop(){
        single(zed);
        zed = 1;
        boolean doMoveForward  = gamepad1.dpad_up;
        boolean doMoveBackward = gamepad1.dpad_down;
        boolean doStrafeLeft   = gamepad1.dpad_left;
        boolean doStrafeRight  = gamepad1.dpad_right;
        double doRotate        = gamepad1.right_stick_x;
        boolean doCollect      = gamepad1.a;
        boolean outCollect     = gamepad1.b;

        double liftUp      = -gamepad2.left_stick_y;

        double clawOut     = -gamepad2.right_stick_y;

        boolean openClaw   = gamepad2.a;
        boolean closeClaw  = gamepad2.b;

        if(doMoveForward){
            lfPower  = 0.75;
            lbPower  = 0.75;
            rfPower  = 0.75;
            rbPower  = 0.75;
        }
        else if(doMoveBackward){
            lfPower  = -0.75;
            lbPower  = -0.75;
            rfPower  = -0.75;
            rbPower  = -0.75;
        }
        else if(doStrafeLeft){
            rfPower  = -0.75;
            lbPower  = -0.75;
            lfPower  =  0.75;
            rbPower  =  0.75;
        }
        else if(doStrafeRight){
            rfPower  =  0.75;
            lbPower  =  0.75;
            lfPower  = -0.75;
            rbPower  = -0.75;
        }
        else {
            rfPower  =  0.00;
            lbPower  =  0.00;
            lfPower  =  0.00;
            rbPower  =  0.00;
        }

        if (doRotate >= 0.25 && doRotate <= 1.00)        {
            lfPower  =  0.75;
            rfPower  = -0.75;
            lbPower  =  0.75;
            rbPower  = -0.75;
        }
        else if (doRotate <= -0.25 && doRotate >= -1.00) {
            lfPower  = -0.75;
            rfPower  =  0.75;
            lbPower  = -0.75;
            rbPower  =  0.75;
        }

        if(doCollect){
            robot.collectorLeft .setPower(-1.00);
            robot.collectorRight.setPower(-1.00);
        }
        else if(outCollect){
            robot.collectorLeft .setPower(1.00);
            robot.collectorRight.setPower(1.00);
        }
        else{
            robot.collectorLeft .setPower(0.00);
            robot.collectorRight.setPower(0.00);
        }


        if(closeClaw)
            robot.clawServo.setPosition(1.00);
        if(openClaw)
            robot.clawServo.setPosition(0.00);

        robot.leftFrontDrive .setPower(lfPower);
        robot.leftBackDrive  .setPower(lbPower);
        robot.rightFrontDrive.setPower(rfPower);
        robot.leftBackDrive  .setPower(rbPower);
        robot.outMotor       .setPower(clawOut);
        robot.upServo        .setPower(liftUp);

        telemetry.addData("Front Motors", "left front (%.2f), right front(%.2f)", lfPower, rfPower);
        telemetry.addData("Back Motors",  "left back (%.2f), right back(%.2f)",   lbPower,rbPower);
        telemetry.update();
    }

    public void stop(){
        robot.leftFrontDrive .setPower(0);
        robot.leftBackDrive  .setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive  .setPower(0);
    }

    /**public double boolToDoub(boolean convertMe, boolean isNegative){
        double x = 1.00;
        if(convertMe){
            if(isNegative) {
                x -= .01;
            } else {
                x += .01;
            }
        }
        return x;
    }*/


}
