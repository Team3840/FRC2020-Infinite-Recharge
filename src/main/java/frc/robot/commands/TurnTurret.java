
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;


public class TurnTurret extends CommandBase {
 
  private final Turret m_turret;

  // Requirements
  public TurnTurret(Turret subsystem) {
    m_turret = subsystem;
    addRequirements(subsystem);
  }

  // Init
  @Override
  public void initialize() {
  }

  // Run
  @Override
  public void execute() {
    m_turret.Run();
  }

  // End
  @Override
  public void end(boolean interrupted) {
    m_turret.stopMotion();
  }

  // Finish
  @Override
  public boolean isFinished() {
    return false;
  }
}