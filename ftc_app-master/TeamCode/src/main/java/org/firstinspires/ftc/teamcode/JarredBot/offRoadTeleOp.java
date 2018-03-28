package org.firstinspires.ftc.teamcode.JarredBot;

/**
 * Created by isaac.blandin on 2/28/18.
 */

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


            xOffset = lastAngles.thirdAngle;
            yOffset = lastAngles.secondAngle;

            xPower = xOffset/75 + 0.2;
            yPower = yOffset/75 + 0.2;

            rfVAr = yPower + xPower;
            rbVar = -yPower - xPower;
            lfVar = yPower - xPower;
            lbVar = -yPower + xPower;

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
