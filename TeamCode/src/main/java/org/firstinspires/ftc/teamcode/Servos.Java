// run until the end of the match (driver presses STOP)
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
Servo servo;
double servoPosition=0.0;
@TeleOp(name="Servo", group="Linear Opmode")
boolean rotateRight = gamepad2.right_bumper;
boolean rotateLeft = gamepad2.left_bumper;
    // THIS CODE IS FOR THE SERVOS ON THE LAZY servo
    public void loop(){
        servo = hardwareMap.servo.get("servo");
        servo.setPosition(servoPosition);

        waitForStart();
        runtime.reset();

        while(rotateRight){
            servoPosition += 0.5;
            servo.setPosition(servoPosition);
            sleep(2000);
        }
        while(rotateLeft){
            servoPosition -= 0.5;
            servo.setPosition(servoPosition);
            sleep(2000);
        }
        telemetry.addData("Servo Position", servo.getPosition());
        telemetry.addData("Status", "Running");
        telemetry.update();
    }
    
    /*
    while (opModeIsActive()) {
        tgtPower = -this.gamepad1.left_stick_y;
        motorTest.setPower(tgtPower);
        // check to see if we need to move the servo.
            if(rotateRight) {
                // move to 0 degrees.
                servo.setPosition(0);
            } else if (rotateRight|| rotateLeft) {
                // move to 90 degrees.
                servo.setPosition(0.5);
            } else if (rotateLeft) {
                // move to 180 degrees.
                servo.setPosition(1);
            }
            telemetry.addData("Servo Position", servo.getPosition());
            telemetry.addData("Target Power", servoPower);
            telemetry.addData("Motor Power", servoPower.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();
     */

            }