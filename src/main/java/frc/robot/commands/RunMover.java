
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Mover;


public class RunMover extends CommandBase {
  
  private final Mover m_mover;

  // Requirements
  public RunMover(Mover subsystem) {
    m_mover = subsystem;
    addRequirements(subsystem);
  }

  // Init
  @Override
  public void initialize() {
  }

  // Run
  @Override
  public void execute() {
    m_mover.Run();
  }

  // End
  @Override
  public void end(boolean interrupted) {
    m_mover.stopMotion();
  }

  // Finish
  @Override
  public boolean isFinished() {
    return false;
  }
}