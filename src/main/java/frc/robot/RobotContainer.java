// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ArcadeDriveCutPower;
import frc.robot.commands.AutonomousDistance;
import frc.robot.commands.AutonomousTime;
import frc.robot.commands.GyroStraightDrive;
import frc.robot.commands.GyroStraightDriveKeyboard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.OnBoardIO;
import frc.robot.subsystems.OnBoardIO.ChannelMode;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain s_Drivetrain = new Drivetrain();
  private final OnBoardIO onboardIO = new OnBoardIO(ChannelMode.OUTPUT, ChannelMode.OUTPUT, s_Drivetrain);

  // Assumes a gamepad plugged into channnel 0
  private final Joystick xboxController = new Joystick(0);
  private final Joystick keyboard = new Joystick(1);
  private final JoystickButton zButton = new JoystickButton(Constants.KEYBOARD, 1);
  private final JoystickButton xButton = new JoystickButton(Constants.KEYBOARD, 2);
  private final JoystickButton cButton = new JoystickButton(Constants.KEYBOARD, 3);
  private final JoystickButton vButton = new JoystickButton(Constants.KEYBOARD, 4);
  private final JoystickButton aButton = new JoystickButton(Constants.JOYSTICK, 1);
  //TESTING

  // Create SmartDashboard chooser for autonomous routines
  private final SendableChooser<Command> chooser = new SendableChooser<>();

  private final Command joystickDrive = getJoystickArcadeDriveCommand();
  private final Command keyboardDrive = getKeyboardArcadeDriveCommand();
  private final Command joystickGyroStraightDrive = getJoystickGyroStraightDriveCommand();
  private final Command keyboardGyroStraightDrive = getKeyboardGyroStraightDriveCommand();
  private final Command arcadeDriveJoystickCutPower = getArcadeDriveJoystickCutPowerCommand();

  // Example of how to use the onboard IO
  private final Button onboardButtonA = new Button(onboardIO::getButtonAPressed);
  private final Button onboardButtonB = new Button(onboardIO::getButtonBPressed);
  private final Button onboardButtonC = new Button(onboardIO::getButtonCPressed);


  // NOTE: The I/O pin functionality of the 5 exposed I/O pins depends on the hardware "overlay"
  // that is specified when launching the wpilib-ws server on the Romi raspberry pi.
  // By default, the following are available (listed in order from inside of the board to outside):
  // - DIO 8 (mapped to Arduino pin 11, closest to the inside of the board)
  // - Analog In 0 (mapped to Analog Channel 6 / Arduino Pin 4)
  // - Analog In 1 (mapped to Analog Channel 2 / Arduino Pin 20)
  // - PWM 2 (mapped to Arduino Pin 21)
  // - PWM 3 (mapped to Arduino Pin 22)
  //
  // Your subsystem configuration should take the overlays into account

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Setup SmartDashboard options
    chooser.setDefaultOption("Auto Routine Distance", new AutonomousDistance(s_Drivetrain));
    chooser.addOption("Auto Routine Time", new AutonomousTime(s_Drivetrain));
    Shuffleboard.getTab("Chooser").add(chooser);
    s_Drivetrain.setDefaultCommand(keyboardGyroStraightDrive);
  
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    vButton.whenPressed(keyboardDrive);
    cButton.whenPressed(joystickDrive);
    xButton.whenPressed(joystickGyroStraightDrive);
    zButton.whenPressed(keyboardGyroStraightDrive);
    aButton.whenPressed(arcadeDriveJoystickCutPower);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }

  /**
   * Use this to pass the teleop command with joysticks to the main {@link Robot} class.
   *
   * @return the command to run in teleop with joysticks
   */
  public Command getJoystickArcadeDriveCommand() {
    return new ArcadeDrive(
        s_Drivetrain, () -> -xboxController.getRawAxis(1), () -> xboxController.getRawAxis(4));
  }
  /**
   * Use this to pass the teleop command with keyboard to the main {@link Robot} class.
   *
   * @return the command to run in teleop with keyboard
   */
  public Command getKeyboardArcadeDriveCommand() {
    return new ArcadeDrive(
        s_Drivetrain, () -> -keyboard.getRawAxis(1), () -> keyboard.getRawAxis(0));
  }
  /**
   * Use this to pass the teleop command with joystick gyro to the main {@link Robot} class.
   *
   * @return the command to run in teleop with joystick gyro
   */
  public Command getJoystickGyroStraightDriveCommand(){
    return new GyroStraightDrive(s_Drivetrain, xboxController);
  }
  /**
   * Use this to pass the teleop command with keyboard gyro to the main {@link Robot} class.
   *
   * @return the command to run in teleop with keyboard gyro
   */
  public Command getKeyboardGyroStraightDriveCommand(){
    return new GyroStraightDriveKeyboard(s_Drivetrain, () -> -keyboard.getRawAxis(1), () -> keyboard.getRawAxis(0));
  }
  /**
   * Use this to pass the teleop command with joystick cut power to the main {@link Robot} class.
   *
   * @return the command to run in teleop with joystick cut power
   */
  public Command getArcadeDriveJoystickCutPowerCommand(){
    return new ArcadeDriveCutPower(s_Drivetrain, () -> -xboxController.getRawAxis(1), () -> xboxController.getRawAxis(4), () -> xboxController.getRawButton(1));
  }
}
