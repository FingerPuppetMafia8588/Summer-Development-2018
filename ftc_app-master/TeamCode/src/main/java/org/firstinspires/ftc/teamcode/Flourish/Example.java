package org.firstinspires.ftc.teamcode.Flourish;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by isaac.blandin on 10/7/18.
 */

@TeleOp(name = "test")

public class Example extends LinearOpMode {

    DcMotor Right;
    DcMotor Left;
    Servo servo;

    @Override
    public void runOpMode(){

        Right = hardwareMap.dcMotor.get("Right");
        Left = hardwareMap.dcMotor.get("Left");
        Right.setDirection(DcMotorSimple.Direction.REVERSE);

        servo = hardwareMap.servo.get("servo");

        waitForStart();
        while(opModeIsActive()){
            Right.setPower(-gamepad1.right_stick_y);
            Left.setPower(-gamepad1.left_stick_y);

            if (gamepad1.a){
                servo.setPosition(0.5);
            } else if (gamepad1.b){
                servo.setPosition(1);
            }
        }
        stop();
    }
}
