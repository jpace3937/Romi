// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.Supplier;

public class ArcadeDriveCutPower extends CommandBase {
  private final Drivetrain s_Drivetrain;
  private final Supplier<Double> xAxisSpeedSupplier;
  private final Supplier<Double> zAxisRotateSupplier;
  private final Supplier<Boolean> cutPowerModeSupplier;

  /**
   * Creates a new ArcadeDriveCutPower. This command will drive your robot according to the speed supplier
   * lambdas. This command does not terminate.
   *
   * @param drivetrain The drivetrain subsystem on which this command will run
   * @param xaxisSpeedSupplier Lambda supplier of forward/backward speed
   * @param zaxisRotateSupplier Lambda supplier of rotational speed
   * @param cutPowerModeSupplier Lambda supplier of power cut
   */
  public ArcadeDriveCutPower(
      Drivetrain drivetrain,
      Supplier<Double> xaxisSpeedSupplier,
      Supplier<Double> zaxisRotateSupplier,
      Supplier<Boolean> cutPowerModeSupplier) {
    s_Drivetrain = drivetrain;
    xAxisSpeedSupplier = xaxisSpeedSupplier;
    zAxisRotateSupplier = zaxisRotateSupplier;
    this.cutPowerModeSupplier = cutPowerModeSupplier;
    addRequirements(s_Drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    s_Drivetrain.arcadeDriveCutPower(xAxisSpeedSupplier.get(), zAxisRotateSupplier.get(), cutPowerModeSupplier.get());
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
