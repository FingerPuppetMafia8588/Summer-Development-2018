package org.firstinspires.ftc.teamcode.Isaac;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by isaac.blandin on 8/28/18.
 */

public abstract class AutonomousBase extends RobotHardware {

    protected void resetEncoders(){
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        while (opModeIsActive() && rightDrive.getCurrentPosition() > 3 && leftDrive.getCurrentPosition() > 3){}
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while (opModeIsActive() && rightDrive.getMode() != DcMotor.RunMode.RUN_USING_ENCODER && leftDrive.getMode() != DcMotor.RunMode.RUN_USING_ENCODER){}
    }
    protected void wait(double seconds) {
        ElapsedTime t = new ElapsedTime(System.nanoTime());
        while (opModeIsActive() && t.time() <= seconds) {

        }
    }
    protected int getRightAbs(){return Math.abs(rightDrive.getCurrentPosition());}
    protected int getleftAbs(){return Math.abs(leftDrive.getCurrentPosition());}

    protected int getHeading(){
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return (int)angles.firstAngle;
    }

    ///////////////////////////////////
    /////////////Movement//////////////
    ///////////////////////////////////

    protected void drive(double power){
        setDrivePower(power,power);
    }

    protected void drive(double power, double inches){ // Moves the robot in a straight line at a determined power and distance
        resetEncoders();
        drive(power);
        double targetPosition = inches*ORBITAL20_PPR*DRIVE_GEAR_RATIO/WHEEL_CIRC;
        boolean hasCorrected = false;
        while(opModeIsActive() && getleftAbs() <= targetPosition && getRightAbs() <= targetPosition) {
            if (!hasCorrected && getleftAbs() >= (targetPosition / 2)) { // Slowing down once we reach 1/2 to our target
                double newPower;
                if (power > 0) {
                    newPower = power - (power / 2);
                } else {
                    newPower = power + (power / 2);
                }
                drive(newPower);
                hasCorrected = true;
            }
        }
        stopDrive();
        telemetry.addLine("Drove " + inches + " inches to target");
        telemetry.update();
    }

    protected void turnHeading(double power, int degreeTarget){ //turns the robot at a set speed to a given z-axis value
        heading = getHeading();

        while (opModeIsActive() && Math.abs(heading - degreeTarget) > degreeTarget/2){
            if (heading > degreeTarget){
                setDrivePower(-power, power);
            }
            if (heading < degreeTarget){
                setDrivePower(power, -power);
            }
            heading = getHeading();
        }
        while (opModeIsActive() && Math.abs(heading-degreeTarget) > 2){
            if (heading > degreeTarget){
                setDrivePower(-power/2,power/2);
            }
            if (heading < degreeTarget){
                setDrivePower(power/2, -power/2);
            }
            heading = getHeading();
        }
        stopDrive();
        telemetry.addLine("Turned " + degreeTarget + "degrees to target");
        telemetry.update();
    }

}
