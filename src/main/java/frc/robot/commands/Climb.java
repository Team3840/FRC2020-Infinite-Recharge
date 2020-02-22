
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;


public class Climb extends CommandBase {
  
  private final Climber m_Climber;

  // Requirements
  public Climb(Climber subsystem) {
    m_Climber = subsystem;
    addRequirements(subsystem);
  }

  // Init
  @Override
  public void initialize() {
  }

  // Run
  @Override
  public void execute() {
    m_Climber.Run();
  }

  // End
  @Override
  public void end(boolean interrupted) {
    m_Climber.stopMotion();
  }

  // Finish
  @Override
  public boolean isFinished() {
    return false;
  }
}