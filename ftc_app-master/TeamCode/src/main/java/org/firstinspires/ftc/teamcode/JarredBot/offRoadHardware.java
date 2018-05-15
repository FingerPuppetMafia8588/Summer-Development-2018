package org.firstinspires.ftc.teamcode.JarredBot;

import android.graphics.drawable.GradientDrawable;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by isaac.blandin on 2/28/18.
 */

public abstract class offRoadHardware extends LinearOpMode{

    //Declares elevation motors
    DcMotor rightFrontElevation;
    DcMotor leftFrontElevation;
    DcMotor rightBackElevation;
    DcMotor leftBackElevation;

    //Declares Drive Motors
    DcMotor rightFrontDrive;
    DcMotor leftFrontDrive;
    DcMotor rightBackDrive;
    DcMotor leftBackDrive;

    double xDrive;
    double yDrive;

    int xOffset;
    int yOffset;
    int elevation;

    double xPower;
    double yPower;

    double rfVAr;
    double rbVar;
    double lfVar;
    double lbVar;

    double max;

    // declares gyro
    BNO055IMU imu;
    Orientation lastAngles = new Orientation();
    Orientation angles;


    public void initOffRoad () {



        //initializes elevation motors
        rightFrontElevation = hardwareMap.dcMotor.get("rfe");
        leftFrontElevation = hardwareMap.dcMotor.get("lfe");
        rightBackElevation = hardwareMap.dcMotor.get("rbe");
        leftBackElevation = hardwareMap.dcMotor.get("lbe");

        rightFrontElevation.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontElevation.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackElevation.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackElevation.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightFrontElevation.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBackElevation.setDirection(DcMotorSimple.Direction.REVERSE);

        //initializes drive motors
        rightFrontDrive = hardwareMap.dcMotor.get("rfd");
        leftFrontDrive = hardwareMap.dcMotor.get("lfd");
        rightBackDrive = hardwareMap.dcMotor.get("rbd");
        leftBackDrive = hardwareMap.dcMotor.get("lbd");

        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFrontDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        //initialize gyro
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.loggingEnabled = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);

        telemetry.addData("Mode", "Calibrating");
        telemetry.update();

        while (!isStopRequested() && !imu.isGyroCalibrated()){
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calibration", imu.getCalibrationStatus().toString());
        telemetry.update();


    }
}
