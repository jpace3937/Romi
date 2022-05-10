// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.OnBoardIO;
import frc.robot.Constants;

public class ToggleLightsBasedOnGyro extends CommandBase {
  private final OnBoardIO onboardIO;
  /** Creates a new ToggleLightsBasedOnGyro. */
  public ToggleLightsBasedOnGyro(OnBoardIO onboardIO) {
    this.onboardIO = onboardIO;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(onboardIO);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double gyroAngle = onboardIO.getDrivetrain().getGyroAngleZ();
    if(Math.abs(gyroAngle) < Constants.IS_ORIENTED_STRAIGHT_DEGREE_THRESHOLD){
      setLightsGreen();
    }
    else{
      setLightsRed();
    }
  }

  private void setLightsGreen(){
    onboardIO.setGreenLed(true);
    onboardIO.setRedLed(false);
  }
  private void setLightsRed(){
    onboardIO.setGreenLed(false);
    onboardIO.setRedLed(true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
