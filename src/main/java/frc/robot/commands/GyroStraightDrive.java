// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.sensors.RomiGyro;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class GyroStraightDrive extends CommandBase {
  private final Drivetrain s_Drivetrain;
  private final Joystick joystick;
  private double angle;
  private double gain;
  private final RomiGyro gyro;
  private double slowDownRange;
  private final boolean flag = false;


  /**
   * Creates a new GyroStraightDrive. This command will drive your robot according to the speed supplier
   * lambdas, but with gyro correction. This command does not terminate.
   *
   * @param s_Drivetrain The drivetrain subsystem on which this command will run
   * @param joystick Joystick of forward/backward speed
   */
  public GyroStraightDrive(Drivetrain s_Drivetrain, Joystick joystick) {
    this.s_Drivetrain = s_Drivetrain;
    this.joystick = joystick;
    addRequirements(s_Drivetrain);
    gyro = Robot.gyro;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    angle = gyro.getAngleZ();
    gain = 0;
    slowDownRange = 3;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
    while(Constants.getJoystickTurn(joystick) != 0 && !flag){
      s_Drivetrain.arcadeDrive(Constants.getJoystick(joystick), Constants.getJoystickTurn(joystick));
      if(Constants.getJoystickTurn(joystick) == 0){
        angle = gyro.getAngleZ();
        break;
      }
    }
    if(Constants.getJoystick(joystick) > 0 && gyro.getAngleZ() < angle){
      gain = (angle - gyro.getAngleZ()) / 13.5;
    }
    else if(Constants.getJoystick(joystick) > 0 && gyro.getAngleZ() > angle){
      gain = (angle - gyro.getAngleZ()) / 13.5;
    }
    else if(Constants.getJoystick(joystick) < 0 && gyro.getAngleZ() < angle){
      gain = (angle - gyro.getAngleZ()) / 13.5;
    }
    else if(Constants.getJoystick(joystick) < 0 && gyro.getAngleZ() > angle){
      gain = (angle - gyro.getAngleZ()) / 13.5;
    }
    if(Constants.getJoystick(joystick) == 0 && angle - gyro.getAngleZ() < slowDownRange && angle - gyro.getAngleZ() > -slowDownRange * 2){
      gain = 0;
    }
    else if(Constants.getJoystick(joystick) == 0 && (angle - gyro.getAngleZ() > slowDownRange || angle - gyro.getAngleZ() < -slowDownRange * 2)){
      gain = (angle - gyro.getAngleZ()) / 44.0;
    }
    s_Drivetrain.arcadeDrive(Constants.getJoystick(joystick), gain);
    System.out.println(Constants.getJoystick(joystick));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_Drivetrain.arcadeDrive(0, 0);
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
