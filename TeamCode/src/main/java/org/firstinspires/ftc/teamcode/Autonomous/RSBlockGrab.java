package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareMapping;


@Autonomous(name = "grabAuto")
public class RSBlockGrab extends LinearOpMode {
    HardwareMapping robot = new HardwareMapping();
    MyNavigator cmd = new MyNavigator();

    public double lfPower, rfPower, lbPower, rbPower;
    public double collectorPower;

    public boolean doMoveForward, doMoveBackward;
    public double  doRotate;
    public boolean doCollection, reverseCollection;

    public boolean doIntake;
    public boolean feedThrough;
    private boolean doMoveIn;
    private boolean spitBlock;
    private boolean slurpBlock;
    private boolean useClaw;
    private double clawPower;
    private boolean quitClaw;
    private boolean doMoveUp;
    private double upPower;
    private boolean doMoveDown;
    private boolean doIntakeLock;
    private double lockPower;
    private boolean stopIntakeLock;
    private double outMotorPower;
    private boolean doMoveOut;


    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        while(opModeIsActive()){
            robot.init(hardwareMap);
            cmd.init(2);
            //Command, Update, Sleep, endCommand
            doMoveForward = true;
            updateDriving();
            sleep(cmd.UNIT*2);
            doMoveForward = false;

            doRotate = -1.00;
            updateDriving();
            sleep(cmd.NINETY);
            doRotate = 0.00;

            doMoveForward = true;
            updateDriving();
            sleep(cmd.UNIT*5);
            doMoveForward = false;

            doRotate = 1.00;
            updateDriving();
            sleep(cmd.NINETY);
            doRotate = 0.00;

            doMoveForward = true;
            doIntake = true;
            updateDriving(0.50);
            sleep(cmd.UNIT / 2);
            doMoveForward = false;
            sleep(4000);
            doIntake = false;
            updateDriving();

            idle();
            break;
        }

    }
    public void updateDriving(){
        if(spitBlock){
            robot.secondIntake.setPower(0.80);
        }
        else if(slurpBlock){
            robot.secondIntake.setPower(-0.80);
        }

        if(useClaw){
            clawPower = 0.80;
        }
        if(quitClaw){
            clawPower = -0.80;
        }
        else{
            clawPower = 0.00;
        }

        if(doMoveUp){
            upPower = .8;
        } else if(doMoveDown) {
            upPower = -.8;
        } else {
            upPower = 0.00;
        }
        if(doIntakeLock){
            lockPower = 0.75;
            robot.secondIntake.setPower(0.80);
        } else if (stopIntakeLock){
            lockPower = -0.75;
            robot.secondIntake.setPower(-0.80);
        }else{
            lockPower = 0;
            robot.secondIntake.setPower(0.00);
        }

        if(doCollection){
            collectorPower = 1.00;
            lockPower = 0.80;
        }
        else if(reverseCollection){
            collectorPower = -1.00;
            lockPower = -.80;
        }
        else{
            collectorPower = 0.00;
        }

        if(doMoveIn)      {
            outMotorPower = 1.00;
        }
        else if(doMoveOut){
            outMotorPower = -1.00;
        }
        else              {
            outMotorPower = 0.00;
        }

        if(doMoveBackward)    {
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
    public void updateDriving(double DrivePower){
        if(spitBlock){
            robot.secondIntake.setPower(0.80);
        }
        else if(slurpBlock){
            robot.secondIntake.setPower(-0.80);
        }

        if(useClaw){
            clawPower = 0.80;
        }
        if(quitClaw){
            clawPower = -0.80;
        }
        else{
            clawPower = 0.00;
        }

        if(doMoveUp){
            upPower = .8;
        } else if(doMoveDown) {
            upPower = -.8;
        } else {
            upPower = 0.00;
        }
        if(doIntakeLock){
            lockPower = 0.75;
            robot.secondIntake.setPower(0.80);
        } else if (stopIntakeLock){
            lockPower = -0.75;
            robot.secondIntake.setPower(-0.80);
        }else{
            lockPower = 0;
            robot.secondIntake.setPower(0.00);
        }

        if(doCollection){
            collectorPower = 1.00;
            lockPower = 0.80;
        }
        else if(reverseCollection){
            collectorPower = -1.00;
            lockPower = -.80;
        }
        else{
            collectorPower = 0.00;
        }

        if(doMoveIn)      {
            outMotorPower = 1.00;
        }
        else if(doMoveOut){
            outMotorPower = -1.00;
        }
        else              {
            outMotorPower = 0.00;
        }

        if(doMoveBackward)    {
            lfPower  = DrivePower;
            lbPower  = DrivePower;
            rfPower  = DrivePower;
            rbPower  = DrivePower;
        }
        else if(doMoveForward){
            lfPower  = -DrivePower;
            lbPower  = -DrivePower;
            rfPower  = -DrivePower;
            rbPower  = -DrivePower;
        }
        else {
            rfPower  =  0.00;
            lbPower  =  0.00;
            lfPower  =  0.00;
            rbPower  =  0.00;
        }

        if (doRotate >= 0.25 && doRotate <= 1.00)        {
            lfPower  =  DrivePower;
            rfPower  = -DrivePower;
            lbPower  =  DrivePower;
            rbPower  = -DrivePower;
        }
        else if (doRotate <= -0.25 && doRotate >= -1.00) {
            lfPower  = -DrivePower;
            rfPower  =  DrivePower;
            lbPower  = -DrivePower;
            rbPower  =  DrivePower;
        }

        this.robot.leftFrontDrive .setPower(lfPower);
        this.robot.leftBackDrive  .setPower(lbPower);
        this.robot.rightFrontDrive.setPower(rfPower);
        this.robot.rightBackDrive .setPower(rbPower);
        this.robot.collectorLeft  .setPower(collectorPower);
        this.robot.collectorRight .setPower(collectorPower);

    }
}
