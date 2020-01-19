package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareMapping;

@Autonomous (name = "BlueSquareRedTriangleClosePark")
public class BlueSquareRedTriangleClosePark extends LinearOpMode{
    HardwareMapping robot = new HardwareMapping();

    public double lfPower, rfPower, lbPower, rbPower;
    public double collectorPower;

    public boolean doMoveForward, doMoveBackward;
    public boolean doStrafeLeft, doStrafeRight;
    public double  doRotate;
    public boolean doCollection, reverseCollection;

    public int currentX = 0, currentY = 0;
    public int targetX, targetY;
    public double currentAngle;
    public int[][] digitalField = new int[48][48];

    public final double TICKS_PER_INCH = 2240*4*Math.PI;



    @Override
    public void runOpMode() throws InterruptedException {
        //declare local variables here
        robot.init(hardwareMap);

        robot.leftFrontDrive .setDirection(DcMotor.Direction.REVERSE);
        robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.leftBackDrive  .setDirection(DcMotor.Direction.REVERSE);
        robot.rightBackDrive .setDirection(DcMotor.Direction.FORWARD);
        robot.collectorLeft  .setDirection(DcMotor.Direction.REVERSE);
        robot.collectorRight .setDirection(DcMotor.Direction.FORWARD);

        doMoveForward     = false;
        doMoveBackward    = false;
        doStrafeLeft      = false;
        doStrafeRight     = false;
        doRotate          = 0.00;
        doCollection      = false;
        reverseCollection = false;


        waitForStart();
        //main body of code
        if(isStarted())
            while(opModeIsActive()){
                updateDriving();
                doMoveForward = true;
                updateDriving();
                sleep(1000);
                doMoveForward = false;
                doStrafeRight = true;
                updateDriving();
                sleep(1250);
                doStrafeRight = false;
                idle();
                break;
            }

        //Use While Loops Here for big stuff--this runs sequentially
        //Sleep(millis) works here
        //use while(opModeIsActive()){
        //      house code here
        // }
        // put idle() at the end of the loop **always**
        //***After*** the while loop, put any necessary termination code
    }
    public void updatePosition(){

    }
    public void updateDriving(){
        if(doCollection){
            collectorPower = 1.00;
        }
        else if(reverseCollection){
            collectorPower = -1.00;
        }
        else{
            collectorPower = 0.00;
        }

        if(doMoveBackward){
            lfPower  = 0.75;
            lbPower  = 0.75;
            rfPower  = 0.75;
            rbPower  = 0.75;
        }
        else if(doMoveForward){
            lfPower  = -0.75;
            lbPower  = -0.75;
            rfPower  = -0.75;
            rbPower  = -0.75;
        }
        else if(doStrafeRight){
            rfPower  = -0.75;
            lbPower  = -0.75;
            lfPower  =  0.75;
            rbPower  =  0.75;
        }
        else if(doStrafeLeft){
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

        this.robot.leftFrontDrive .setPower(lfPower);
        this.robot.leftBackDrive  .setPower(lbPower);
        this.robot.rightFrontDrive.setPower(rfPower);
        this.robot.rightBackDrive .setPower(rbPower);
        this.robot.collectorLeft  .setPower(collectorPower);
        this.robot.collectorRight .setPower(collectorPower);

    }
}
