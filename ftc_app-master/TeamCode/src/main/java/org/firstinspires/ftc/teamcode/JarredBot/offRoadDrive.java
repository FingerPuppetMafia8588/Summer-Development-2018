package org.firstinspires.ftc.teamcode.JarredBot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by isaac.blandin on 4/17/18.
 */
@TeleOp(name = "testoff")
public class offRoadDrive extends offRoadHardware{
    public void runOpMode() {
        initOffRoad();

        double holdpower = 0;

        xOffset = 0;
        yOffset = 0;
        elevation = 0;

        balance = true;

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

            angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

            //equation for drive and set power
            rightFrontDrive.setPower(yDrive - xDrive);
            rightBackDrive.setPower(yDrive - xDrive);
            leftFrontDrive.setPower(yDrive + xDrive);
            leftBackDrive.setPower(yDrive + xDrive);


            rightFrontElevation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftFrontElevation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightBackElevation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftBackElevation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            if(gamepad1.b){
                elevation += 2;
            }
            if (gamepad1.a){
                elevation -= 2;
            }

            if (gamepad1.y){
                if (balance){
                    balance = false;
                } else {
                    balance = true;
                }
                sleep(70);
            }

            if (!balance) {
                if (gamepad1.dpad_up) {
                    yOffset += 2;
                }
                if (gamepad1.dpad_down) {
                    yOffset -= 2;
                }
                if (gamepad1.dpad_right) {
                    xOffset -= 2;
                }
                if (gamepad1.dpad_left) {
                    xOffset += 2;
                }
            } else {
                if (angles.thirdAngle > 5) {
                    yOffset += 2;
                }
                if (angles.thirdAngle < -5) {
                    yOffset -= 2;
                }
                if (angles.secondAngle < -5) {
                    xOffset -= 2;
                }
                if (angles.secondAngle > 5) {
                    xOffset += 2;
                }
            }
            rightFrontElevation.setTargetPosition(0 - xOffset - yOffset + elevation);
            rightBackElevation.setTargetPosition(0 + xOffset - yOffset - elevation);
            leftFrontElevation.setTargetPosition(0 + xOffset - yOffset + elevation);
            leftBackElevation.setTargetPosition(0  - xOffset - yOffset - elevation);

            rightFrontElevation.setPower(0.6);
            rightBackElevation.setPower(0.6);
            leftFrontElevation.setPower(0.6);
            leftBackElevation.setPower(0.6);

            telemetry.addData("xOffset", -xOffset);
            telemetry.addData("yoffset", yOffset);
            telemetry.addData("elevationOffset", -elevation);

            telemetry.addData("heading", angles.firstAngle);
            telemetry.addData("y-axis", angles.secondAngle);
            telemetry.addData("x-axis", angles.thirdAngle);

            telemetry.update();

        }
        stop();
    }
}
