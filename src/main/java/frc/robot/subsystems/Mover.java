
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Mover extends SubsystemBase {
  
  // Motor
  private final WPI_VictorSPX moverMotor = new WPI_VictorSPX(Constants.ActuatorConstants.kMoverMotor);

  // SmartDashboard
  final String IntakeSpeed ="MoverSpeed";
  final double SpeedIn = -0.5;
  private  double setSpeed;
  
  // Mover
  public Mover() {
  }

  // Periodic
  @Override
  public void periodic() {
  }

  // Run
  public void Run() {
    double backup = SpeedIn;
    setSpeed = getPreferencesDouble(IntakeSpeed ,backup);
    moverMotor.set(setSpeed);
  }
  
  // Stop Motion
  public void stopMotion() {
    moverMotor.set(0.0);
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