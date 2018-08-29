package org.firstinspires.ftc.teamcode.Isaac;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by isaac.blandin on 8/28/18.
 */

public abstract class RobotHardware extends RobotBase {

    //declares drive motors
    protected DcMotor rightDrive;
    protected DcMotor leftDrive;

    // declares gyro
    protected BNO055IMU imu;
    protected Orientation lastAngles = new Orientation();
    protected Orientation angles;

    protected int heading;

    protected final double WHEEL_DIAMTER = 4;
    protected final double WHEEL_CIRC = WHEEL_DIAMTER*Math.PI;
    protected final double ORBITAL20_PPR = 537.6;
    protected final double DRIVE_GEAR_RATIO = 1;

    @Override
    protected void initRobot(RobotRunType robotRunType){

        rightDrive = hardwareMap.dcMotor.get("right_drive");
        leftDrive = hardwareMap.dcMotor.get("left_drive");

        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // initialize gyro if starting in autonomous
        if (robotRunType == RobotRunType.AUTONOMOUS){
            //initialize gyro
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

            parameters.mode = BNO055IMU.SensorMode.IMU;
            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parameters.loggingEnabled = false;

            imu = hardwareMap.get(BNO055IMU.class, "imu");

            imu.initialize(parameters);

            //post to telemetry when gyro is calibrating
            telemetry.addData("Mode", "Calibrating");
            telemetry.update();

            //post to telemetry when gyro is calibrated
            while (!isStopRequested() && !imu.isGyroCalibrated()){
                sleep(50);
                idle();
            }

            telemetry.addData("Mode", "waiting for start");
            telemetry.addData("imu calibration", imu.getCalibrationStatus().toString());
            telemetry.update();
        }

    }
    // method to simplify setting drive power for the robot drive motors
    protected void setDrivePower (double rightPower, double leftPower) {
        rightDrive.setPower(rightPower);
        leftDrive.setPower(leftPower);
    }

    //method to easily stop the robot
    protected void stopDrive() {
        setDrivePower(0, 0);
    }

}
