
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class FlyWheel extends SubsystemBase {
  
  // Motor
  private final WPI_TalonSRX flyWheel = new WPI_TalonSRX(Constants.ActuatorConstants.kFlyWheel);

  // SmartDashboard
  final String IntakeSpeed ="FlyWheelSpeed";
  final double SpeedIn = 
  0.5;
  private  double setSpeed;

  // Fly Wheel
  public FlyWheel() {
  }

  // Periodic
  @Override
  public void periodic() {
  }

  // Run
  public void Run() {
    double backup = SpeedIn;
    setSpeed = getPreferencesDouble(IntakeSpeed ,backup);
    flyWheel.set(setSpeed);
  }

  // Stop Motion
  public void stopMotion() {
    flyWheel.set(0.0);
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