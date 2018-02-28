package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by isaac.blandin on 2/26/18.
 */
@TeleOp(name = "name")
@Disabled
public class TeleOpTemplate extends LinearOpMode {
    //declare hardware here
    //DcMotor rightDrive;
    //DcMotor leftDrive;

    @Override public void runOpMode() {
        //initialize hardware here
        //rightDrive = hardwareMap.dcMotor.get("right");

        waitForStart();
        while(opModeIsActive()){
            //main TeleOp loop here

        }
        stop();
    }
}
