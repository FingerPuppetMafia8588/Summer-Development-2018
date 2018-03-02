package org.firstinspires.ftc.teamcode.JarredBot;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

/**
 * Created by isaac.blandin on 2/28/18.
 */

public abstract class offRoadHardware extends LinearOpMode{

    //declare modern robotics gyroscope

    public IntegratingGyroscope gyro;
    public ModernRoboticsI2cGyro imu;

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


    public void initOffRoad () {
        //initializes gyro
        imu = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro = (IntegratingGyroscope) imu;

        //initializes elevation motors
        rightFrontElevation = hardwareMap.dcMotor.get("rfe");
        leftFrontElevation = hardwareMap.dcMotor.get("lfe");
        rightBackElevation = hardwareMap.dcMotor.get("rbe");
        leftBackElevation = hardwareMap.dcMotor.get("lbe");

        rightFrontElevation.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontElevation.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackElevation.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackElevation.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //initializes drive motors
        rightFrontDrive = hardwareMap.dcMotor.get("rfd");
        leftFrontDrive = hardwareMap.dcMotor.get("lfd");
        rightBackDrive = hardwareMap.dcMotor.get("rbd");
        leftBackDrive = hardwareMap.dcMotor.get("lbd");

        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        imu.calibrate();

    }
}
