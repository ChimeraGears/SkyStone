package org.firstinspires.ftc.teamcode.Autonomous;

public class MyNavigator {
    public int[][] digitalMap = new int[48][48];
    public int robotX, robotY;
    public int targetX, targetY;
    public int robotStart;
    //0-3 ^^^
    public final int NINETY = 925;
    public final int QUARTER = 925/2;
    public final int UNIT = 200;

    public MyNavigator(){

    }
    public double robotAngle;
    public double targetAngle;

    public void init(int robotPosition){
        robotStart = robotPosition;
        buildField();
    }

    private void buildField(){
        fillArea(0 ,47,0 ,47,digitalMap,0); //Flush Array
        fillArea(0 ,11,13,18,digitalMap,1); //Blue Platform
        fillArea(0 ,11,29,34,digitalMap,1); //Red  Platform
        fillArea(20,25,16,31,digitalMap,1); //Center boundary
        fillArea(32,47,15,17,digitalMap,1); //Left  Bricks
        fillArea(32,47,30,32,digitalMap,1); //Right Bricks
        if(robotStart == 0)     { //Bottom Left Position
            fillArea(11,16,0 ,5 ,digitalMap,1); //Top Left  Robot Start
            fillArea(11,16,42,47,digitalMap,1); //Top Right Robot Start
            fillArea(29,34,42,47,digitalMap,1); //Bottom Right Robot Start
        }
        else if(robotStart == 1){ //Top Left Position
            fillArea(11,16,42,47,digitalMap,1); //Top Right Robot Start
            fillArea(29,34,42,47,digitalMap,1); //Bottom Right Robot Start
            fillArea(29,34,0 ,5 ,digitalMap,1); //Bottom Left Robot Start
        }
        else if(robotStart == 2){ //Bottom Right Position
            fillArea(11,16,0 ,5 ,digitalMap,1); //Top Left  Robot Start
            fillArea(11,16,42,47,digitalMap,1); //Top Right Robot Start
            fillArea(29,34,0 ,5 ,digitalMap,1); //Bottom Left Robot Start
        }
        else                    { //Top Right Position
            fillArea(11,16,0 ,5 ,digitalMap,1); //Top Left  Robot Start
            fillArea(29,34,0 ,5 ,digitalMap,1); //Bottom Left Robot Start
            fillArea(29,34,42,47,digitalMap,1); //Bottom Right Robot Start
        }
    }

    public void updatePosition(int x, int y){
        robotX += x;
        robotY += y;
    }
    public void updateAngle(double angle){
        robotAngle += angle;
    }
    public void setTargets(int x, int y) {
        targetX = x;
        targetY = y;
    }

    private void fillArea(int startArray, int endArray, int startIndex, int endIndex, int[][] Array, int fillWith){
        for(int i = startArray; i < endArray; i++){
            for(int j = startIndex; j < endIndex; j++){
                Array[i][j] = fillWith;
            }
        }
    }

    public double calculateDistance(int currentX, int currentY, int newX, int newY){
        return  Math.sqrt((Math.pow((newX-currentX),2))+ (Math.pow((newY-currentY),2)));

    }
    public double calculateAngle(double sideA, double sideB, double sideC){
        return 1/(Math.cos(((Math.pow(sideA,2))+(Math.pow(sideC,2))-(Math.pow(sideB,2)))/(2*sideA*sideC)));
    }
    public int roundNum(double number){
        return (int)Math.round(number);
    }

    public boolean targetsReached(){
        return robotX == targetX && robotY == targetY;
    }
    public double calculateX(){
        if(targetX > robotX){
            return targetX-robotX;
        }
        return robotX-targetX;
    }
    public double calculateY(){
        if(targetY > robotY){
            return targetY-robotY;
        }
        return robotY-targetY;
    }

}
