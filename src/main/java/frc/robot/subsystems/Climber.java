
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Climber extends SubsystemBase {
  
  // Motor
  private final WPI_VictorSPX climbMotor = new WPI_VictorSPX(Constants.ActuatorConstants.kClimbMotor);

  // SmartDashboard
  final String IntakeSpeed ="ClimbSpeed";
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
    setSpeed = getPreferencesDouble(IntakeSpeed ,backup);
    climbMotor.set(setSpeed);
  }

  // Stop Motion
  public void stopMotion() {
    climbMotor.set(0.0);
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