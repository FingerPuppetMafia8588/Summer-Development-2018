package org.firstinspires.ftc.teamcode.JarredBot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by isaac.blandin on 4/17/18.
 */
@TeleOp(name = "testoff")
public class offRoadDrive extends offRoadHardware{
    public void runOpMode() {
        initOffRoad();

        double holdpower = 0;

        rightFrontElevation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackElevation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontElevation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackElevation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightFrontElevation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackElevation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontElevation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackElevation.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        waitForStart();
        while (opModeIsActive()) {

            //grabs input for two joystick arcade tank drive
            xDrive = gamepad1.right_stick_x;
            yDrive = -gamepad1.left_stick_y;

            //equation for drive and set power
            rightFrontDrive.setPower(yDrive - xDrive);
            rightBackDrive.setPower(yDrive - xDrive);
            leftFrontDrive.setPower(yDrive + xDrive);
            leftBackDrive.setPower(yDrive + xDrive);


            rightFrontElevation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftFrontElevation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightBackElevation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftBackElevation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            rightFrontElevation.setTargetPosition(0);
            rightBackElevation.setTargetPosition(0);
            leftFrontElevation.setTargetPosition(0);
            leftBackElevation.setTargetPosition(0);

            rightFrontElevation.setPower(0.3);
            rightBackElevation.setPower(0.3);
            leftFrontElevation.setPower(0.3);
            leftBackElevation.setPower(0.3);

        }
        stop();
    }
}
