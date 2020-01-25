package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.util.Range;

@Autonomous (name = "Mark Two Auto")
public class Navigation extends OpMode{
    Jerry jerry = new Jerry();
    public int Robotx   = 0;
    public int Roboty   = 0;
    public int obstacle = 1;
    public int robotLocation;
    public int locationArray[][] = new int[48][48];
    public double a = 90;
    public double b, c, A, B, C;
    public double currentDirection;
    public void init(){
        locationArray[0][13] = 4;
        robotLocation = locationArray[Robotx][Roboty];
    }

    public void loop(){
        robotLocation = locationArray[Robotx][Roboty];
    }
    public void stop(){
    }
    /**public void instruct(String targetLocation, int directionInInches){
        if(targetLocation.equals("BluePlatform.left")){
            int newerX = 11;
            int newerY = 5;
            double checkerDist = distanceCalc(Robotx,Roboty,newerX,newerY);
            double travelAngle = angleCalc(newerX,newerY,(int)checkerDist);
            if(Roboty<17){
                cmd.rotate(.75,.75,.75,.75,round(travelAngle));
                cmd.drive(.75,.75,.75,.75,checkerDist);
            } else {
                //THIS WON'T WORK BUT IT IS THE BASIC PRINCIPLE
                double directionClone = currentDirection;
                double directionSetter = 90-directionClone;
                double angleCorrection = directionSetter+directionClone;
                double courseCorrection = robotLocation - Math.abs(17-robotLocation);
                cmd.rotate(.75,.75,.75,.75,angleCorrection);
                cmd.drive(.75,.75,.75,.75,courseCorrection);
            }
        }
    }
     */

    public void instruct(String targetLocation){

    }
    public double distanceCalc(int currentX, int currentY, int newX, int newY){
        return  Math.sqrt((Math.pow((newX-currentX),2))+ (Math.pow((newY-currentY),2)));
    }
    public double angleCalc(int sideA, int sideB, int sideC){
        return 1/(Math.cos(((Math.pow(sideA,2))+(Math.pow(sideC,2))-(Math.pow(sideB,2)))/(2*sideA*sideC)));
    }
    public int round(double number){
        return (int)Math.round(number);
    }
}