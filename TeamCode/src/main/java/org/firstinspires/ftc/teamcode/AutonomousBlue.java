package org.firstinspires.ftc.teamcode;
//A tick is .0056 inches
//1 inch is 178.57 ticks
import java.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.util.Range;

@Autonomous (name="Blue Auto 1")
public class AutonomousBlue extends OpMode {

    public final static double REV_MIN = 0.07;
    public final static double REV_MAX = 1.0;
    HardwareMapping robot = new HardwareMapping();
    SleepFunction sleep = new SleepFunction();
    public final static double TICKS_PER_ROTATION = 2240;
    public final static double WHEEL_DIAMETER_INCHES = 4.0;
    public final static double TICKS_PER_INCH = (TICKS_PER_ROTATION)/(WHEEL_DIAMETER_INCHES*(Math.PI));
    public int state;
    public int targetFL = 0;
    public int targetFR = 0;
    public int targetBL = 0;
    public int targetBR = 0;
    public int LFpos;
    public int RFpos;
    public int LBpos;
    public int RBpos;

    public void init() {
        robot.init(hardwareMap);
        robot.leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        state = 0;
        telemetry.addData("starting targets:","lf (%d), rf (%d), lb (%d), rb (%d)", robot.leftFrontDrive.getCurrentPosition(), robot.rightFrontDrive.getCurrentPosition(), robot.leftBackDrive.getCurrentPosition(), robot.rightBackDrive.getCurrentPosition());

    }

    public void loop() {
        double leftFrontPower;
        double rightFrontPower;
        double leftBackPower;
        double rightBackPower;
        double drive = 0.50;
        double turn = 0.00;
        double strafe = 0.00;


        leftFrontPower = Range.clip(drive-strafe+turn,-1.0,1.0);
        leftBackPower = Range.clip(drive+strafe+turn,-1.0,1.0);
        rightFrontPower = Range.clip(drive+strafe-turn,-1.0,1.0);
        rightBackPower = Range.clip(drive-strafe-turn,-1.0,1.0);

        //encoder_drive(leftFrontPower, rightFrontPower, leftBackPower, rightBackPower, 4);
        switch (state) {
            case 0:
                encoder_drive(leftFrontPower,rightFrontPower,leftBackPower,rightBackPower, 5);
                state++;
                break;
            case 1:
                if(targets_reached()) {
                    state++;
                }
                LFpos = robot.leftFrontDrive.getCurrentPosition();
                RFpos = robot.rightFrontDrive.getCurrentPosition();
                LBpos = robot.leftBackDrive.getCurrentPosition();
                RBpos = robot.rightBackDrive.getCurrentPosition();
                break;
            case 2:
                robot.leftFrontDrive.setPower(0);
                robot.rightFrontDrive.setPower(0);
                robot.leftBackDrive.setPower(0);
                robot.rightBackDrive.setPower(0);
                robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                break;
        }

        telemetry.addData("Motors", "left , right ", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
        telemetry.addData("encoderPosition", "lf (%d), rf (%d), lb (%d), rb (%d)", LFpos,RFpos,LBpos,RBpos);
        telemetry.addData("encoder targets","lf (%d), rf (%d), lb (%d), rb (%d)",targetFL,targetFR,targetBL,targetBR);
        telemetry.addData("case","%d", state);
        telemetry.update();

    }
    public boolean targets_reached(){
        return ((LFpos > targetFL - 50 && LFpos < targetFL)&&(RFpos > targetFR - 50 && RFpos < targetFR)&&(LBpos > targetBL - 50 && LBpos < targetBL)&&(RBpos > targetBR - 50 && RBpos < targetBR));
    }
    public void encoder_drive(double leftFrontPower, double rightFrontPower, double leftBackPower, double rightBackPower, double inches){

        targetFL=robot.leftFrontDrive.getCurrentPosition()+(int)(inches*TICKS_PER_INCH);
        targetFR=robot.rightFrontDrive.getCurrentPosition()+(int)(inches*TICKS_PER_INCH);
        targetBL=robot.leftBackDrive.getCurrentPosition()+(int)(inches*TICKS_PER_INCH);
        targetBR=robot.rightBackDrive.getCurrentPosition()+(int)(inches*TICKS_PER_INCH);

        robot.leftFrontDrive.setTargetPosition(targetFL);
        robot.rightFrontDrive.setTargetPosition(targetFR);
        robot.leftBackDrive.setTargetPosition(targetBL);
        robot.rightBackDrive.setTargetPosition(targetBR);

        robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.leftFrontDrive.setPower(leftFrontPower);
        robot.rightFrontDrive.setPower(rightFrontPower);
        //if(robot.leftFrontDrive.get)
        robot.leftBackDrive.setPower(leftBackPower);
        robot.rightBackDrive.setPower(rightBackPower);
    }
}
