package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import java.util.ArrayList;
import java.util.List;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp (name= "colorCode", group = "a")
 @Disabled
 public class ColorCode extends LinearOpMode {
    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AUvVk17/////AAABmb73yPbaU0emmFq1upMCLptLYU/MpnNPfTeLNRjXmwgzA4rq3Lvrn1mbXiybydcVaFsaF14TFlGMhun5D2xtrhYMRe2MLbqsDOeEVsYIPIcrCsfRXSdr9pmpHQt1ZGMJ3gcjc4odzQqejUbrvwXTPnSi9CJrIJB2c6HB5GDZRifK9zBPyRIfz8o4Bekt0LpRck4XkJl7GBB7z8i3RBJ+iL67wZJWIzVw2gj11JJXQXUKprmdjXVzpaT2mnjQ/lTswKnpYq5oT7tyS5g7sZWEd2VcHYyztXvKXpdEZB1qdWEaAeJUkA8wBcWkE7ZiRm85YQUEAqh7dSoucJS6OofOwIAzONWAeXuAe07gWfZXU+QF";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
        VuforiaTrackables targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");
        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");
        VuforiaTrackable blueRearBridge = targetsSkyStone.get(1);
        blueRearBridge.setName("Blue Rear Bridge");
        VuforiaTrackable redRearBridge = targetsSkyStone.get(2);
        redRearBridge.setName("Red Rear Bridge");
        VuforiaTrackable redFrontBridge = targetsSkyStone.get(3);
        redFrontBridge.setName("Red Front Bridge");
        VuforiaTrackable blueFrontBridge = targetsSkyStone.get(4);
        blueFrontBridge.setName("Blue Front Bridge");
        VuforiaTrackable red1 = targetsSkyStone.get(5);
        red1.setName("Red Perimeter 1");
        VuforiaTrackable red2 = targetsSkyStone.get(6);
        red2.setName("Red Perimeter 2");
        VuforiaTrackable front1 = targetsSkyStone.get(7);
        front1.setName("Front Perimeter 1");
        VuforiaTrackable front2 = targetsSkyStone.get(8);
        front2.setName("Front Perimeter 2");
        VuforiaTrackable blue1 = targetsSkyStone.get(9);
        blue1.setName("Blue Perimeter 1");
        VuforiaTrackable blue2 = targetsSkyStone.get(10);
        blue2.setName("Blue Perimeter 2");
        VuforiaTrackable rear1 = targetsSkyStone.get(11);
        rear1.setName("Rear Perimeter 1");
        VuforiaTrackable rear2 = targetsSkyStone.get(12);
        rear2.setName("Rear Perimeter 2");
        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targetsSkyStone);

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        targetsSkyStone.activate();
        boolean targetVisible = false;
        while (opModeIsActive()) {

// check all the trackable targets to see which one (if any) is visible.
            targetVisible = false;
            for (VuforiaTrackable trackable : allTrackables) {
                if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                    telemetry.addData("Visible Target", trackable.getName());
                    targetVisible = true;
                    break;
                }
            }
            if (!targetVisible) {
                telemetry.addData("Visible Target", "none");
            }
            telemetry.update();

        }
        targetsSkyStone.deactivate();
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }

    //color = hardwareMap.get(ColorSensor.class,"color");
    ColorSensor color = hardwareMap.colorSensor.get("color");
    HardwareMapping robot = new HardwareMapping();
    public void colorMethod() {
            //#FFBC00
            boolean isYellow = (color.red()<= 221 || color.red() >= 234) && (color.green()<= 206 || color.green()>= 164) && (color.blue()<=175 || color.blue()>=92);
            boolean isBlack = false;
            if (!isYellow && isBlack){
                // light color -- dark color (This should detect the value of yellow given certain lightings)
                robot.leftFrontDrive .setPower(0.00); //PROBABLY NEED TO MOVE FOREWARD THEN LEFT/RIGHT TO GET CAMERA ANGLED AT THE BLOCKS
                robot.rightFrontDrive.setPower(0.00);
                robot.leftBackDrive  .setPower(0.00);
                robot.rightBackDrive .setPower(0.00);
                //TALK TO JAYSON ABOUT HOW WE ARE GOING TO MOVE THE ROBOT... WHERE IT STARTS AND IF CODE NEEDS TO BE DUPLICATED BASED ON WHERE WE START
            } else {
                telemetry.addData("red", color.red());
                telemetry.addData("green", color.red());
                telemetry.addData("blue", color.blue());
                telemetry.addData("alpha", color.alpha());
            }
    }
}