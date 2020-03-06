package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareMapping {

    public CRServo     clawServo;
    public Servo collectorDrop;
    public CRServo    upServo;
    public CRServo    upServo2;
    public CRServo intakeLock;
    public CRServo secondIntake;
    public DcMotor leftFrontDrive  = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftBackDrive   = null;
    public DcMotor rightBackDrive  = null;

    public DcMotor collectorLeft  = null;
    public DcMotor collectorRight = null;

    public DcMotor[] leftDrive = null;
    public DcMotor[] rightDrive = null;

    HardwareMap hwMap = null;

    public DcMotor outMotor = null;
    //private ElapsedTime period = new ElapsedTime();

    public HardwareMapping() {
    }

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        leftFrontDrive  = hwMap.get(DcMotor.class, "lf");
        rightFrontDrive = hwMap.get(DcMotor.class, "rf");
        leftBackDrive   = hwMap.get(DcMotor.class, "lb");
        rightBackDrive  = hwMap.get(DcMotor.class, "motor1");
        collectorLeft   = hwMap.get(DcMotor.class, "cl");
        collectorRight  = hwMap.get(DcMotor.class,"cr");
        outMotor        = hwMap.get(DcMotor.class,"outMotor");

        clawServo  = hwMap    .crservo.get("claw");
        upServo    = hwMap    .crservo.get("upServo");
        upServo2   = hwMap    .crservo.get("upServoLeft");
        intakeLock = hwMap    .crservo.get("intakeServo");
        secondIntake = hwMap  .crservo.get("intakeServo2");

        leftDrive = new DcMotor[2];
        leftDrive[0] = leftFrontDrive;
        leftDrive[1] = leftBackDrive;

        rightDrive = new DcMotor[2];
        rightDrive[0] = rightFrontDrive;
        rightDrive[1] = rightBackDrive;
    }
}