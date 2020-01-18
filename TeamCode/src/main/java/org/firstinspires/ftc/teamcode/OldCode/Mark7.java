import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


//@TeleOp(name="Mark 7")
//12.1211 inches per rotation--JB
//2240 ticks per rotation
/**public class Mark7 extends OpMode {
    public HardwareMapping robot = new HardwareMapping();
    //We had a lot of difficulty in making power controls that worked,
    //and realized that if we made them global they would actually
    //function. This was a small hurdle that took weeks to see.
    public boolean doSlowControls = false;
    public int doCollection = 0;
    public double totalPower;
    public double fastPower = .9;
    public double slowPower = fastPower/2;
    public double leftFrontPower;
    public double rightFrontPower;
    public double leftBackPower;
    public double rightBackPower;
    public double collectorPower;
    public double armPower;
    public int singleLoopRun;

    public void init() {
        robot.init(hardwareMap);
        singleLoopRun = 0;
        //This step in the code allows us to program all the motors the same,
        //even though they face different directions.

        robot.leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        robot.rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.armMotor.setDirection(DcMotor.Direction.REVERSE);
        robot.collector.setDirection(DcMotor.Direction.FORWARD);
    }

    /**
     * Gamepad1 controls
     *   -- With "mode" button pressed, replace with
     *      joystick equivalent!
     ------------------------------------------------
     * -- Up on dPad    - Forwards
     * -- Down on dPad  - Backwards
     * -- Left on dPad  - Strafe left
     * -- Right on dPad - Strafe right
     * -- Left on right Joystick  - Rotate left
     * -- Right on right Joystick - Rotate right
     ------------------------------------------------
     * -- a button                - Turn on collector
     * -- b button                - Turn off collector
     * -- right Joystick button   - Reverse collector
     ------------------------------------------------
     * -- right bumper - Turn on slow controls
     * -- left bumper  - Turn off slow controls
     **

    /**
     * Gamepad2 controls
     * -- Right Trigger - Move arm up
     * -- Left Trigger  - Move arm down
     ----------------------------------
     * -- Right Bumper  - Extend Arm
     * -- Left Bumper   - Retract Arm
     ----------------------------------
     * -- a button      - Open Claw
     * -- b button      - Close Claw
     * -- x button      - Flip in block
     **

    //////////////////////////////////////

    public void loop() {

        /**
 * Gamepad1 controls
 *   -- With "mode" button pressed, replace with
 *      joystick equivalent!
 ------------------------------------------------
 * -- Up on dPad    - Forwards
 * -- Down on dPad  - Backwards
 * -- Left on dPad  - Strafe left
 * -- Right on dPad - Strafe right
 * -- Left on right Joystick  - Rotate left
 * -- Right on right Joystick - Rotate right
 ------------------------------------------------
 * -- a button                - Turn on collector
 * -- b button                - Turn off collector
 * -- right Joystick button   - Reverse collector
 ------------------------------------------------
 * -- right bumper - Turn on slow controls
 * -- left bumper  - Turn off slow controls
 **
        boolean moveForward = gamepad1.dpad_up;
        boolean moveBackward = gamepad1.dpad_down;
        boolean strafeLeft = gamepad1.dpad_left;
        boolean strafeRight = gamepad1.dpad_right;

        double rotate = gamepad1.right_stick_x;

        boolean collectorIn = gamepad1.a;
        boolean collectorOut = gamepad1.right_stick_button;
        boolean collectorOff = gamepad1.b;
        boolean slowItDown = gamepad1.right_bumper;
        boolean speedItUp = gamepad1.left_bumper;

        /**
         * Gamepad2 controls
         * -- Right Trigger - Move arm up
         * -- Left Trigger  - Move arm down
         ----------------------------------
         * -- Right Bumper  - Extend Arm
         * -- Left Bumper   - Retract Arm
         ----------------------------------
         * -- a button      - Open Claw
         * -- b button      - Close Claw
         * -- x button      - Flip in block
         **

        double armUp = gamepad2.right_trigger;
        double armDown = gamepad2.left_trigger;

        boolean extendArm = gamepad2.right_bumper;
        boolean retractArm = gamepad2.left_bumper;

        boolean openClaw = gamepad2.a;
        boolean closeClaw = gamepad2.b;
        boolean flipBlock = gamepad2.x;


        if(singleLoopRun == 0){
            robot.collectorDrop.setPosition(1.00);
            robot.clawServo.setPosition(1.00);
            singleLoopRun = 1;
        }
        //This if statement is crucial. it allows us to
        //slow down or speed up driving, for better precision in-game.

        //Gamepad1:
        //Speed controls
        if(slowItDown){
            doSlowControls = true;
        }
        else if (speedItUp){
            doSlowControls = false;
        }
        totalPower = (doSlowControls)?slowPower:fastPower;

        //This piece of code allows us to turn on, reverse, and off the collector.
        if(collectorIn){
            doCollection = 2;
            collectorPower = 1.00;
        }
        else if (collectorOff){
            doCollection = 1;
            collectorPower = 0.00;
        }
        else if (collectorOut){
            doCollection = 0;
            collectorPower = -0.90;
        }

        //This is the drive train
        if(moveForward){
            leftFrontPower = totalPower;
            rightFrontPower = totalPower;
            leftBackPower = totalPower;
            rightBackPower = totalPower;
            //Drive backwards
        }
        else if(moveBackward){
            leftFrontPower = -totalPower;
            rightFrontPower = -totalPower;
            leftBackPower = -totalPower;
            rightBackPower = -totalPower;
            //This chunk of code allows our robot to strafe.
        }
        else if(strafeRight){
            rightFrontPower = -totalPower;
            leftBackPower = -totalPower;
            leftFrontPower = totalPower;
            rightBackPower = totalPower;

        }
        else if(strafeLeft){
            rightFrontPower = totalPower;
            leftBackPower = totalPower;
            leftFrontPower = -totalPower;
            rightBackPower = -totalPower;
            //This is called "no power behavior." When there
            //is no power to the motors, they won't move.
        }
        else {
            leftFrontPower = totalPower-totalPower;
            rightFrontPower = totalPower-totalPower;
            leftBackPower = totalPower-totalPower;
            rightBackPower = totalPower-totalPower;
        }
        //This controls rotation
        if (rotate >= 0.25 && rotate <= 1.00) {
            leftFrontPower = totalPower;
            rightFrontPower = -totalPower;
            leftBackPower = totalPower;
            rightBackPower = -totalPower;
        }
        else if (rotate <= -.25 && rotate >= -1.00) {
            leftFrontPower = -totalPower;
            rightFrontPower = totalPower;
            leftBackPower = -totalPower;
            rightBackPower = totalPower;
        }

        /////////////////////////////////////////////////
        //Gamepad2://
        //this is the code controlling the arm.
        if (armUp > 0.60){
            armPower = armUp;
        }
        else if (armDown > .30){
            armPower = -armDown;
        }
        else {
            armPower = 0.00;
        }
        //this is the code that controls the claw.
        if (openClaw){
            robot.clawServo.setPosition(0.00);
        }
        if (closeClaw){
            robot.clawServo.setPosition(1.00);
        }
        //this is the code that fully extends the arm.
        if (extendArm){
            extendArm();
        }
        if (retractArm){
            retractArm();
        }
        //this is the code that has the block-spank.
        if (flipBlock){
            flipBlockIn();
        } else {
            flipBlockOut();
        }

        /////////////////////////////////////////////////


        //These commands set power to each motor.
        robot.leftFrontDrive.setPower(leftFrontPower);
        robot.rightFrontDrive.setPower(rightFrontPower);
        robot.leftBackDrive.setPower(leftBackPower);
        robot.rightBackDrive.setPower(rightBackPower);
        robot.armMotor.setPower(armPower);
        robot.collector.setPower(collectorPower);
        //This is data for the drivers to see.
        telemetry.addData("Motor", "left (%.2f), right(%.2f)", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
        telemetry.update();
    }

    public void retractArm(){
        robot.armServo1.setPosition(0.00);
        robot.armServo2.setPosition(-0.05);
    }
    public void extendArm(){
        robot.armServo1.setPosition(0.46);
        robot.armServo2.setPosition(0.57);
    }
    public void flipBlockIn(){
        robot.flipBlock.setPosition(0.00);
    }
    public void flipBlockOut(){
        robot.flipBlock.setPosition(1.00);
    }
}
*/
