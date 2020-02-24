
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Climber extends SubsystemBase {
  
  // Motor
  private final WPI_VictorSPX climbMotor = new WPI_VictorSPX(Constants.ActuatorConstants.kClimbMotor);
  private final Spark latchMotor = new Spark(Constants.ActuatorConstants.kLatchMotor);

  // SmartDashboard
  final String ClimberSpeed ="ClimbSpeed";
  final double SpeedIn = -0.5;
  private  double setSpeed;
  
  // Climber
  public Climber() {
  }

  // Periodic
  @Override
  public void periodic() {
  }

  // Run
  public void Run() {
    double backup = SpeedIn;
    setSpeed = getPreferencesDouble(ClimberSpeed ,backup);
    climbMotor.set(setSpeed);
  }

  // Stop Motion
  public void stopMotion() {
    climbMotor.set(0.0);
  }

  private void Latch() {
    latchMotor.set(0.2);
    wait(0.1);
    latchMotor.set(0.0);
  }

  private void UnLatch() {
    latchMotor.set(-0.2);
    wait(0.1);
    latchMotor.set(0.0);
  }

  // Preferences
  private static double getPreferencesDouble(String key, double backup) {
    Preferences preferences = Preferences.getInstance();
    if(!preferences.containsKey(key)) {
      preferences.putDouble(key, backup);
    }
    return preferences.getDouble(key, backup);
  }
}