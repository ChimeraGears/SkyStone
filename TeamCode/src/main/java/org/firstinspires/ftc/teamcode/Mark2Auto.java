package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.util.Range;

@Autonomous
public class Mark2Auto extends OpMode{
    public int Robotx = 0;
    public int Roboty = 0;
    public int obstacle = 1;
    public int robotLocation;
    public int locationArray[][] = new int[48][48];
    public Object robot;
    public void init(){
        robotLocation = locationArray[Robotx][Roboty];

    }
    public void loop(){
        robotLocation = locationArray[Robotx][Roboty];
    }
    public void stop(){

    }
}
