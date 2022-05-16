// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.sensors.RomiGyro;
import frc.robot.subsystems.Drivetrain;

public class Turn extends CommandBase {
  private double angle;
  private Drivetrain s_Drivetrain;
  private double slowDownRange = 1;
  private double gain;
  private RomiGyro gyro = Robot.gyro;
  private final Timer timer = new Timer();
  /** Creates a new Turn. */
  public Turn(Drivetrain s_Drivetrain) {
    this.s_Drivetrain = s_Drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_Drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    angle = gyro.getAngleZ() + 90.0;
    s_Drivetrain.arcadeDrive(0, 0.5);
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(angle - gyro.getAngleZ() < slowDownRange && angle - gyro.getAngleZ() > -slowDownRange){
      gain = 0;
    }
    else if((angle - gyro.getAngleZ() > slowDownRange || angle - gyro.getAngleZ() < -slowDownRange)){
      gain = (angle - gyro.getAngleZ()) / 44.0;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_Drivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timer.get() > 2){
      return true;
    }
    else{
      return false;
    }
  }
}
