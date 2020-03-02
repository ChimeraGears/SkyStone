package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareMapping;



@Autonomous (name = "TestMe")
public class TesterAuto extends LinearOpMode {
    HardwareMapping robot = new HardwareMapping();
    MyNavigator cmd = new MyNavigator();

    public double lfPower, rfPower, lbPower, rbPower;
    public double collectorPower;

    public boolean doMoveForward, doMoveBackward;
    public double  doRotate;
    public boolean doCollection, reverseCollection;

     public void runOpMode() throws InterruptedException{
         cmd.init(0);

         waitForStart();

         while(opModeIsActive()){
             //Initialize Drive:
             updateDriving();
             //Command:
             doMoveForward = true;
             //Begin execution:
             updateDriving();
             //Execution Time* in Milliseconds (seconds/1000):
             sleep(cmd.UNIT * 4); //Drive 8 inches forward
             cmd.updatePosition(cmd.UNIT * 4,0); //positive for up, negative for down - 4 units forward
             //Stop Execution Command:
             doMoveForward = false;

             updateDriving();
             //Command:
             doRotate = 1.00;
             //Begin execution:
             updateDriving();
             //Execution Time* in Milliseconds (seconds/1000):
             sleep(cmd.NINETY * 1); //Drive 8 inches forward
             cmd.updatePosition(cmd.NINETY * 1,0); //positive for up, negative for down - 4 units forward
             //Stop Execution Command:
             doMoveForward = false;

             updateDriving();
             //Command:
             doRotate = -1.00;
             //Begin execution:
             updateDriving();
             //Execution Time* in Milliseconds (seconds/1000):
             sleep(cmd.NINETY * 1); //Drive 8 inches forward
             cmd.updatePosition(cmd.NINETY * 1,0); //positive for up, negative for down - 4 units forward
             //Stop Execution Command:
             doMoveForward = false;



             idle();
             break;
             //*Constants from the MyNavigator class (QUARTER, NINETY, UNIT, etc) can be used in place.
             // They are each written in terms of milliseconds (at full power)
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
