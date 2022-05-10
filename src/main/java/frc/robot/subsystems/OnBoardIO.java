// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * This class represents the onboard IO of the Romi reference robot. This includes the pushbuttons
 * and LEDs.
 *
 * <p>DIO 0 - Button A (input only) DIO 1 - Button B (input) or Green LED (output) DIO 2 - Button C
 * (input) or Red LED (output) DIO 3 - Yellow LED (output only)
 */
public class OnBoardIO extends SubsystemBase {
  private final DigitalInput buttonA = new DigitalInput(0);
  private final DigitalOutput yellowLed = new DigitalOutput(3);

  // DIO 1
  private DigitalInput buttonB;
  private DigitalOutput greenLed;

  // DIO 2
  private DigitalInput buttonC;
  private DigitalOutput redLed;

  private static final double MESSAGE_INTERVAL = 1.0;
  private double nextMessageTime;

  public enum ChannelMode {
    INPUT,
    OUTPUT
  }

  private Drivetrain s_Drivetrain;
  /**
   * Constructor.
   *
   * @param dio1 Mode for DIO 1 (input = Button B, output = green LED)
   * @param dio2 Mode for DIO 2 (input = Button C, output = red LED)
   */
  public OnBoardIO(ChannelMode dio1, ChannelMode dio2) {
    initializeDios(dio1, dio2);
  }
  /**
   * Constructor.
   *
   * @param dio1 Mode for DIO 1 (input = Button B, output = green LED)
   * @param dio2 Mode for DIO 2 (input = Button C, output = red LED)
   * @param s_Drivetrain drivetrain for io
   */
  public OnBoardIO(ChannelMode dio1, ChannelMode dio2, Drivetrain s_Drivetrain) {
    this.s_Drivetrain = s_Drivetrain;
    initializeDios(dio1, dio2);
  }
  private void initializeDios(ChannelMode dio1, ChannelMode dio2){
    if (dio1 == ChannelMode.INPUT) {
      buttonB = new DigitalInput(1);
    } else {
      greenLed = new DigitalOutput(1);
    }

    if (dio2 == ChannelMode.INPUT) {
      buttonC = new DigitalInput(2);
    } else {
      redLed = new DigitalOutput(2);
    }
  }
  public void setDriveTrain(Drivetrain s_Drivetrain){
    this.s_Drivetrain = s_Drivetrain;
  }
  public Drivetrain getDrivetrain(){
    return s_Drivetrain;
  }

  /** Gets if the A button is pressed. */
  public boolean getButtonAPressed() {
    return buttonA.get();
  }

  /** Gets if the B button is pressed. */
  public boolean getButtonBPressed() {
    if (buttonB != null) {
      return buttonB.get();
    }

    double currentTime = Timer.getFPGATimestamp();
    if (currentTime > nextMessageTime) {
      DriverStation.reportError("Button B was not configured", true);
      nextMessageTime = currentTime + MESSAGE_INTERVAL;
    }
    return false;
  }

  /** Gets if the C button is pressed. */
  public boolean getButtonCPressed() {
    if (buttonC != null) {
      return buttonC.get();
    }

    double currentTime = Timer.getFPGATimestamp();
    if (currentTime > nextMessageTime) {
      DriverStation.reportError("Button C was not configured", true);
      nextMessageTime = currentTime + MESSAGE_INTERVAL;
    }
    return false;
  }

  /** Sets the green LED. */
  public void setGreenLed(boolean value) {
    if (greenLed != null) {
      greenLed.set(value);
    } else {
      double currentTime = Timer.getFPGATimestamp();
      if (currentTime > nextMessageTime) {
        DriverStation.reportError("Green LED was not configured", true);
        nextMessageTime = currentTime + MESSAGE_INTERVAL;
      }
    }
  }

  /** Sets the red LED. */
  public void setRedLed(boolean value) {
    if (redLed != null) {
      redLed.set(value);
    } else {
      double currentTime = Timer.getFPGATimestamp();
      if (currentTime > nextMessageTime) {
        DriverStation.reportError("Red LED was not configured", true);
        nextMessageTime = currentTime + MESSAGE_INTERVAL;
      }
    }
  }

  /** Sets the yellow LED. */
  public void setYellowLed(boolean value) {
    yellowLed.set(value);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
