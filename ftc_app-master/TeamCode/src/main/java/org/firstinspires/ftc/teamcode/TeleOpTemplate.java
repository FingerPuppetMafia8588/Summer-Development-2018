package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by isaac.blandin on 2/26/18.
 */
@TeleOp(name = "name")
@Disabled
public class TeleOpTemplate extends LinearOpMode {
    //declare hardware here


    @Override public void runOpMode() {
        //initialize hardware here


        waitForStart();
        while(opModeIsActive()){
            //main TeleOp loop here


        }
        stop();
    }
}
