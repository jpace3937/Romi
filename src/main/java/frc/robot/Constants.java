// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final GenericHID JOYSTICK = new GenericHID(0);
    public static final GenericHID KEYBOARD = new GenericHID(1);
    public static double getJoystick(Joystick joystick){
        if(-joystick.getRawAxis(1) == -1.5259021893143654E-5){
            return 0.0;
        }
        return -joystick.getRawAxis(1);
    }
    public static double getJoystickTurn(Joystick joystick){
        if(joystick.getRawAxis(4) == -1.5259021893143654E-5){
            return 0.0;
        }
        return joystick.getRawAxis(4);
    }
}
