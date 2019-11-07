package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareMapping {

    public CRServo SlideServo;
    public Servo ArmServo;
    public Servo ClawServo;
    public Servo WristServo;
    public Servo LazyServo;
    public Servo HitchServo;
    public DcMotor leftFrontDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor rightBackDrive = null;
    public DcMotor ArmMotor = null;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public HardwareMapping() {
    }

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        //SlideServo = hwMap.get(CRServo.class, "Slide");
        //ArmServo = hwMap.servo.get("Arm");
        //ClawServo = hwMap.servo.get("Claw");
        //WristServo = hwMap.servo.get("Wrist");
        //LazyServo = hwMap.servo.get("Lazy");
        HitchServo = hwMap.servo.get("Hitch");
        //ArmMotor = hwMap.get(DcMotor.class, "Arm");
        leftFrontDrive = hwMap.get(DcMotor.class,"front_left");
        rightFrontDrive = hwMap.get(DcMotor.class,"front_right");
        leftBackDrive = hwMap.get(DcMotor.class,"back_left");
        rightBackDrive = hwMap.get(DcMotor.class,"back_right");

    }

}

