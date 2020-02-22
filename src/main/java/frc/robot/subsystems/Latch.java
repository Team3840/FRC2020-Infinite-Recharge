
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Latch extends SubsystemBase {
  
  // Motor
  private final Spark latchMotor = new Spark(Constants.ActuatorConstants.kLatchMotor);

  // Latch
  public Latch() {
  }

  // Periodic
  @Override
  public void periodic() {
  }
}
