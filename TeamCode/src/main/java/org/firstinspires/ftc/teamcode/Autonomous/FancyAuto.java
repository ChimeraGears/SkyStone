package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareMapping;

public class FancyAuto extends LinearOpMode {
    ComplicatedAutonomous Auto = new ComplicatedAutonomous();
    HardwareMapping robot = new HardwareMapping();

    public double lfPower, rfPower, lbPower, rbPower;
    public double collectorPower;

    public boolean doMoveForward, doMoveBackward;
    public boolean doStrafeLeft, doStrafeRight;
    public double  doRotate;
    public boolean doCollection, reverseCollection;

    @Override
    public void runOpMode() throws InterruptedException {
        //160 rpm for a 40/1 motor
        //.0444444444444444 rpmls for a 40/1 motor

        //2.250000000000002 to make it to 1 rotation
        //1 rotations every 2.25 milliseconds
        //4 rotations every 9 milliseconds
        //2240 ticks per 2.25 mlseconds
        //8960 ticks per 9 milliseconds
        //1759.3 ticks per inch
        //   8960          5.09293469
        //      9          8960
        //  9 milliseconds per 5.1 inches
        //.56588163 inches per 9 milliseconds
        //.063 inches per millisecond
        //.25 inches per 4 milliseconds
        //16 milliseconds == 1 inch

        Auto.init(0);
        Auto.buildField();

        while(opModeIsActive()){
            findPath("blue Platform");
            //this should rotate the robot to the angle it needs to go to the correct place -- may be off some
            Auto.targetAngle = Auto.calculateAngle(Auto.calculateX(), Auto.calculateY(), Auto.calculateDistance(Auto.robotX, Auto.robotY, Auto.targetX, Auto.targetY));
            updateDriving();
            doRotate = -1.00;
            //use another method to edit this -- speed calculations
            int targetInches = (int)Auto.targetAngle;
            updateDriving();
            sleep(targetInches * 16);
            Auto.updateAngle(-.1);
            doRotate = 0.00;


            idle();
        }
    }
    public void findPath(String navigateHere){
        switch (navigateHere){
            case "blue Platform":
                Auto.targetX = 19;
                Auto.targetY = 5;
                Auto.targetAngle = -1.00;
                break;
            case "red Platform":
                Auto.targetX = 25;
                Auto.targetY = 5;
                Auto.targetAngle = 1.00;
                break;
            case "blue Bridge":
                break;
            case "red Bridge":
                break;
            default:
                break;
        }

        if(!Auto.targetsReached()){
            Auto.setTargets((int)Auto.calculateX(),(int)Auto.calculateY());
        }
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
