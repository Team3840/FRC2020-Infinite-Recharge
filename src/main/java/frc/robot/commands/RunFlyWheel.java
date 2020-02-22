
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlyWheel;


public class RunFlyWheel extends CommandBase {
  
  private final FlyWheel m_flyWheel;
  
  // Requirements
  public RunFlyWheel(FlyWheel subsystem) {
    m_flyWheel = subsystem;
      addRequirements(subsystem);
  }

  // Init
  @Override
  public void initialize() {
  }

  // Run
  @Override
  public void execute() {
    m_flyWheel.Run();
  }

  // End
  @Override
  public void end(boolean interrupted) {
    m_flyWheel.stopMotion();
  }

  // Finish
  @Override
  public boolean isFinished() {
    return false;
  }
}