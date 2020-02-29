package org.firstinspires.ftc.teamcode;

public class QuickVargs {
    public int[] Args(int...ints){
        return ints;
    }
    public double[] Args(double ... doubles){
        return doubles;
    }
    public long[] Args(long ... longs) {
        return longs;
    }
    public byte[] Args(byte...bytes   ){
        return bytes;
    }
    public short[] Args(short...shorts){
        return shorts;
    }
    public float[] Args(float...floats){
        return floats;
    }
    public boolean[] Args(boolean...booleans){
        return booleans;
    }
    public char[] Args(char...chars){
        return chars;
    }
    public String[] Args(String ... strings){
        return strings;
    }
    public Object[] Args(Object...objects)  {
        return objects;
    }
    public Void[] Args(){
        return null;
    }
    public static void main(String[] args){
        QuickVargs v = new QuickVargs();
        int[] nums = v.Args(4,4,4,4,4);
    }
}
