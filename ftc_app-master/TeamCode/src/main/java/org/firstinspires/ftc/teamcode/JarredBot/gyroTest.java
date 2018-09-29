package org.firstinspires.ftc.teamcode.JarredBot;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by isaac.blandin on 5/14/18.
 */

@TeleOp(name = "gyro test")
@Disabled
public class gyroTest extends offRoadHardware {

    Orientation angles;

    @Override
    public void runOpMode(){

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

        waitForStart();
        while (opModeIsActive()) {

            angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

            telemetry.addData("heading", angles.firstAngle);
            telemetry.addData("y-axis", angles.secondAngle);
            telemetry.addData("x-axis", angles.thirdAngle);
            telemetry.update();
        }
        stop();
    }
}
