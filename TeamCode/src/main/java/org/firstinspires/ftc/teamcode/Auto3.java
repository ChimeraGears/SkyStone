package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@Autonomous (name = "Auto 3")
public class Auto3 extends OpMode {
    public final static double REV_MIN = 0.07;
    public final static double REV_MAX = 1.0;
    HardwareMapping robot    = new HardwareMapping();
    private AutoCommands cmd = new AutoCommands();
    private final static double TICKS_PER_ROTATION    = 2240;
    private final static double WHEEL_DIAMETER_INCHES = 4.0;
    public final static double TICKS_PER_INCH         = (TICKS_PER_ROTATION)/(WHEEL_DIAMETER_INCHES*(Math.PI));
    int targetFL = 0;
    int targetFR = 0;
    int targetBL = 0;
    int targetBR = 0;

    public double leftFrontPower;
    public double rightFrontPower;
    public double leftBackPower;
    public double rightBackPower;
    public double drive;
    public double turn;
    public double strafe;
    public void init() {
        robot.init(hardwareMap);
        cmd  .init(4,WHEEL_DIAMETER_INCHES,TICKS_PER_ROTATION);
        robot.leftFrontDrive .setDirection(DcMotor.Direction.REVERSE);
        robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.leftBackDrive  .setDirection(DcMotor.Direction.REVERSE);
        robot.rightBackDrive .setDirection(DcMotor.Direction.FORWARD);

        //telemetry.addData("starting targets:","lf (%d), rf (%d), lb (%d), rb (%d)", robot.leftFrontDrive.getCurrentPosition(), robot.rightFrontDrive.getCurrentPosition(), robot.leftBackDrive.getCurrentPosition(), robot.rightBackDrive.getCurrentPosition());
    }

    private void single_Loop() {
        int runOnce = 0;
        while(runOnce == 0) {
            robot.collectorDrop.setPosition(1.00);
            robot.clawServo.setPosition(1.00);
            runOnce = 1;
        }
    }

    public void loop() {

        //figure out power problem
        leftFrontPower  = Range.clip(drive-strafe+turn,-1.0,1.0);
        leftBackPower   = Range.clip(drive+strafe+turn,-1.0,1.0);
        rightFrontPower = Range.clip(drive+strafe-turn,-1.0,1.0);
        rightBackPower  = Range.clip(drive-strafe-turn,-1.0,1.0);

        int LFpos = cmd.LFpos;
        int RFpos = cmd.RFpos;
        int LBpos = cmd.LBpos;
        int RBpos = cmd.RBpos;

        single_Loop();
        int i = 1;
        if(i == 1){
            doAction();
            int tester = 1;
            telemetry.addData("case","%d", tester);
            telemetry.update();
            isComplete(LFpos,RFpos,LBpos,RBpos);
            tester = 2;
            telemetry.update();
            resetMotors();
            tester = 3;
            telemetry.update();
            i = i+1;
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
        telemetry.update ();
    }

    //for testing purposes--will probably be string based eventually
    public boolean doAction()    {
        cmd.drive(leftFrontPower,rightFrontPower,leftBackPower,rightBackPower,5);
        return true;
    }

    public boolean isComplete(int LFpos, int RBpos, int RFpos, int LBpos)  {
        return((LFpos>=targetFL && RBpos>=targetBR) || (LFpos>=targetFL && RFpos>=targetFR) || (LBpos>=targetBL && RFpos>=targetFR) || (LBpos>=targetBL && RBpos >=targetBL));
    }

    public boolean resetMotors() {
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
        robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        return true;
    }
}
