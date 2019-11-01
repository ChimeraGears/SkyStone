package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareMapping {

    public CRServo SlideServo;
    public CRServo ArmServo;
    public Servo ClawServo;
    public Servo WristServo;
    public Servo LazyServo;
    public Servo HitchServo;
    public DcMotor leftFrontDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor rightBackDrive = null;


    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public HardwareMapping() {
    }

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        SlideServo = hwMap.get(CRServo.class, "Slide");
        ArmServo = hwMap.get(CRServo.class, "Arm");
        ClawServo = hwMap.servo.get("Claw");
        WristServo = hwMap.servo.get("Wrist");
        LazyServo = hwMap.servo.get("Lazy");
        HitchServo = hwMap.servo.get("Hitch");
        leftFrontDrive = hwMap.get(DcMotor.class,"front_left");
        rightFrontDrive = hwMap.get(DcMotor.class,"front_right");
        leftBackDrive = hwMap.get(DcMotor.class,"back_left");
        rightBackDrive = hwMap.get(DcMotor.class,"back_right");

    }

}
