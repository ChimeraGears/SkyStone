package org.firstinspires.ftc.teamcode;
//A tick is .0056 inches
//1 inch is 178.57 ticks
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@Autonomous (name="Auto")
public class AutonomousFile extends OpMode {

    public final static double REV_MIN = 0.07;
    public final static double REV_MAX = 1.0;
    HardwareMapping robot = new HardwareMapping();
    AutoCommands cmd      = new AutoCommands();
    public final static double TICKS_PER_ROTATION    = 2240;
    public final static double WHEEL_DIAMETER_INCHES = 4.0;
    public final static double TICKS_PER_INCH        = (TICKS_PER_ROTATION)/(WHEEL_DIAMETER_INCHES*(Math.PI));
    public int state;
    public int targetFL = 0;
    public int targetFR = 0;
    public int targetBL = 0;
    public int targetBR = 0;
    public int LFpos;
    public int RFpos;
    public int LBpos;
    public int RBpos;
    public double leftFrontPower;
    public double rightFrontPower;
    public double leftBackPower;
    public double rightBackPower;
    public double drive  = 0.50;
    public double turn   = 0.00;
    public double strafe = 0.00;
    public void init() {
        robot.init(hardwareMap);
        cmd  .init(4,WHEEL_DIAMETER_INCHES,TICKS_PER_ROTATION);
        robot.leftFrontDrive .setDirection(DcMotor.Direction.REVERSE);
        robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.leftBackDrive  .setDirection(DcMotor.Direction.REVERSE);
        robot.rightBackDrive .setDirection(DcMotor.Direction.FORWARD);
        state = 0;
        telemetry.addData("starting targets:","lf (%d), rf (%d), lb (%d), rb (%d)", robot.leftFrontDrive.getCurrentPosition(), robot.rightFrontDrive.getCurrentPosition(), robot.leftBackDrive.getCurrentPosition(), robot.rightBackDrive.getCurrentPosition());
    }
    public void single_Loop() {
        int runOnce = 0;
        robot.collectorDrop.setPosition(1.00);
        robot.clawServo    .setPosition(1.00);
        runOnce = 1;
    }
    public void loop() {
        double leftFrontPower;
        double rightFrontPower;
        double leftBackPower;
        double rightBackPower;

        //figure out power problem
        leftFrontPower  = Range.clip(drive-strafe+turn,-1.0,1.0);
        leftBackPower   = Range.clip(drive+strafe+turn,-1.0,1.0);
        rightFrontPower = Range.clip(drive+strafe-turn,-1.0,1.0);
        rightBackPower  = Range.clip(drive-strafe-turn,-1.0,1.0);

        single_Loop();

        boolean tester = doAction();
        telemetry.addData("case","%d", tester);
        telemetry.update     ();
        if(tester){
            tester = isComplete();
            telemetry.addData("case","%d", tester);
            telemetry.update ();
            if (tester){
                resetMotors  ();
            }
        }

        /** switch (state) {
            case 0:
                cmd.drive(leftFrontPower,rightFrontPower,leftBackPower,rightBackPower, 5);
                state++;
                break;
            case 1:
                if(isComplete()){
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
        */

        telemetry.addData("Motors", "left , right ", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
        telemetry.addData("encoderPosition", "lf (%d), rf (%d), lb (%d), rb (%d)", LFpos,RFpos,LBpos,RBpos);
        telemetry.addData("encoder targets","lf (%d), rf (%d), lb (%d), rb (%d)",targetFL,targetFR,targetBL,targetBR);
        telemetry.addData("case","%d", tester);
        telemetry.update ();
    }

    public boolean isComplete(){
        return((LFpos>=targetFL && RBpos>=targetBR) || (LFpos>=targetFL && RFpos>=targetFR) || (LBpos>=targetBL && RFpos>=targetFR) || (LBpos>=targetBL && RBpos >=targetBL));
    }

    //for testing purposes--will probably be string based eventually
    public boolean doAction()    {
        cmd.drive(leftFrontPower,rightFrontPower,leftBackPower,rightBackPower,5);
        return true;
    }
    public void resetMotors()    {
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
        robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}