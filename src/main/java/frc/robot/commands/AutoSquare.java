// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoSquare extends SequentialCommandGroup {
  /** Creates a new AutoSquare. */
  public AutoSquare(Drivetrain s_Drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AutoSquareExecuteOne(s_Drivetrain),
      new AutoSquareExecuteTwo(s_Drivetrain),
      new AutoSquareExecuteThree(s_Drivetrain),
      new AutoSquareExecuteFour(s_Drivetrain)
    );
  }
}
