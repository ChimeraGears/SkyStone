package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
public class Jerry{
    ElapsedTime clock = new ElapsedTime();
    public Jerry(){
    }
    public int xCoord;
    public int yCoord;
    public int Location[][] = new int[48][48];
    //Location can be red box, red triangle, blue box, or blue triangle
    public void init(String location){
        if(location.equals("red box")){
            xCoord = 31;
            yCoord = 2;
        } else if (location.equals("red triangle")){
            xCoord = 14;
            yCoord = 45;
        } else if (location.equals("blue box")){
            xCoord = 32;
            yCoord = 45;
        } else if (location.equals("blue triangle")){
            xCoord = 13;
            yCoord = 2;
        }
    }

    public void loop(){

    }
}
