package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareMapping {

    public CRServo SlideServo;
    public Servo ArmServo1;
    public Servo ArmServo2;
    public Servo ClawServo;
    public Servo WristServo;
    public Servo LazyServo;
    public Servo HitchServo;
    public DcMotor leftFrontDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor rightBackDrive = null;
    public DcMotor armMotor = null;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public HardwareMapping() {
    }

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        //SlideServo = hwMap.get(CRServo.class, "Slide");
        ArmServo1 = hwMap.servo.get("Arm_Wrist_1");
        ArmServo2 = hwMap.servo.get("Arm_Wrist_2");
        //ClawServo = hwMap.servo.get("Claw");
        //WristServo = hwMap.servo.get("Wrist");
        //LazyServo = hwMap.servo.get("Lazy");
        //HitchServo = hwMap.servo.get("Hitch");
        armMotor = hwMap.get(DcMotor.class, "Arm");
        leftFrontDrive = hwMap.get(DcMotor.class, "front_left");
        rightFrontDrive = hwMap.get(DcMotor.class, "front_right");
        leftBackDrive = hwMap.get(DcMotor.class, "back_left");
        rightBackDrive = hwMap.get(DcMotor.class, "back_right");

        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

}

