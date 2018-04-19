package org.firstinspires.ftc.teamcode.JarredBot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by isaac.blandin on 2/28/18.
 */

@TeleOp(name = "offroad")
public class offRoadTeleOp extends offRoadHardware{

    public void runOpMode() {
        initOffRoad();

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

            //pull angles from gyro for x and y axes
            xOffset = lastAngles.thirdAngle;
            yOffset = lastAngles.secondAngle;

            //scale the angle in degrees in order to be acceptable by motor
            xPower = xOffset/75 + 0.2;
            yPower = yOffset/75 + 0.2;

            //kinematic equation for leveling the robot when off angle
            rfVAr = yPower + xPower;
            rbVar = -yPower - xPower;
            lfVar = yPower - xPower;
            lbVar = -yPower + xPower;

            //scale combined values down to -1 to 1 values
            max = rfVAr;
            if (rbVar > max){
                max = rbVar;
            }
            if (lfVar > max){
                max = lfVar;
            }
            if (lbVar > max){
                max = lbVar;
            }

            rfVAr = rfVAr/max;
            rbVar = rbVar/max;
            lfVar = lfVar/max;
            lbVar = lbVar/max;

            //prevent motors from running unless angles are above certain threshold
            if (Math.abs(xOffset) > 8 || Math.abs(yOffset) > 8){
                rightFrontElevation.setPower(rfVAr);
                rightBackElevation.setPower(rbVar);
                leftFrontElevation.setPower(lfVar);
                leftBackElevation.setPower(lbVar);
            } else {
                rightFrontElevation.setPower(0);
                rightBackElevation.setPower(0);
                leftFrontElevation.setPower(0);
                leftBackElevation.setPower(0);
            }

        }
        stop();
    }
}
