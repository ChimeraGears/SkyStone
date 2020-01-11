package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareMapping {

    //public CRServo SlideServo;
    public Servo     armServo1;
    public Servo     armServo2;
    public Servo     clawServo;
    public Servo collectorDrop;
    public Servo     flipBlock;
    public Servo       upServo;  //!
    public CRServo    outServo; //!
    //public Servo WristServo;
    //public Servo LazyServo;
    public Servo HitchServo;
    public Servo blockGrabber;
    public DcMotor leftFrontDrive  = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftBackDrive   = null;
    public DcMotor rightBackDrive  = null;
    public DcMotor armMotor  = null;
    public DcMotor collector = null;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public HardwareMapping() {
    }

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

            //SlideServo = hwMap.get(CRServo.class, "Slide");
        armServo1 = hwMap    .servo  .get("wrist");
        armServo2 = hwMap    .servo  .get("elbow");
        clawServo = hwMap    .servo  .get("claw");
        flipBlock = hwMap    .servo  .get("flip");
        collectorDrop = hwMap.servo  .get("drop");
        upServo   = hwMap    .servo  .get("upServo");   //!
        outServo  = hwMap    .crservo.get("outServo");  //!
            //WristServo = hwMap.servo.get("Wrist");
            //LazyServo = hwMap.servo.get("Lazy");
            //HitchServo = hwMap.servo.get("Hitch");
        armMotor        = hwMap.get(DcMotor.class, "arm");
        leftFrontDrive  = hwMap.get(DcMotor.class, "lf");
        rightFrontDrive = hwMap.get(DcMotor.class, "rf");
        leftBackDrive   = hwMap.get(DcMotor.class, "lb");
        rightBackDrive  = hwMap.get(DcMotor.class, "rb");
        collector       = hwMap.get(DcMotor.class, "c");
            //blockGrabber = hwMap.servo.get("block_grabber");
            //collectorLeft = hwMap.get(DcMotor.class,"collector_left");
            //collectorRight = hwMap.get(DcMotor.class,"collector_right");

            //leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

}