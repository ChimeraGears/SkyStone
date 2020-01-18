package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
public class Jerry{
    ElapsedTime clock = new ElapsedTime();
    public Jerry(){
    }

    public int xCoord;
    public int yCoord;
    public int startingXCoord;
    public int startingYCoord;
    public int Location[][] = new int[48][48];
    //Location can be red box, red triangle, blue box, or blue triangle
    public void init(String location){
        if(location.equals("red box")){
            startingXCoord = 31;
            startingYCoord = 2;
        } else if (location.equals("red triangle")){
            startingXCoord = 14;
            startingYCoord = 45;
        } else if (location.equals("blue box")){
            startingXCoord = 32;
            startingYCoord = 45;
        } else if (location.equals("blue triangle")){
            startingXCoord = 13;
            startingYCoord = 2;
        }
    }
}
