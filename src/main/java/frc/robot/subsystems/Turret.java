
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Turret extends SubsystemBase {
  
  // Motor
  private final WPI_TalonSRX turretMotor = new WPI_TalonSRX(Constants.ActuatorConstants.kTurretMotor);
  // SmartDashboard
  final String TurretSpeed ="TurretSpeed";
  final double SpeedIn = -0.5;
  private double setSpeed;
  // Vision data
  private double limeLightTx;
  private double limeLightTy;
  private double limeLightTr;
  double h1 = 6.0;
  double h2 = 36.0;
  double a1 = 0.0;

  // Turret
  public Turret() {

    final double iaccum = 0;

    turretMotor.setInverted(false);

    /* first choose the sensor- using the CAN Encoder */  
    turretMotor.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0, Constants.EncoderConstants.kPIDLoopIdx, Constants.EncoderConstants.kTimeoutMs);
    turretMotor.setSensorPhase(true);

    // Reset sensor position
    turretMotor.setIntegralAccumulator(iaccum, 0, 10);

    /* Set relevant frame periods to be at least as fast as periodic rate */
    turretMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.EncoderConstants.kTimeoutMs);
    turretMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.EncoderConstants.kTimeoutMs);

    /* set the peak and nominal outputs */
    turretMotor.configNominalOutputForward(0, Constants.EncoderConstants.kTimeoutMs);
    turretMotor.configNominalOutputReverse(0, Constants.EncoderConstants.kTimeoutMs);
    turretMotor.configPeakOutputForward(1, Constants.EncoderConstants.kTimeoutMs);
    turretMotor.configPeakOutputReverse(-1, Constants.EncoderConstants.kTimeoutMs);

    /* zero the sensor */
     turretMotor.setSelectedSensorPosition(0, Constants.EncoderConstants.kPIDLoopIdx, Constants.EncoderConstants.kTimeoutMs);
  }

  // Periodic
  @Override
  public void periodic() {
    //pushes values to the dashboard
    SmartDashboard.putNumber(" TurretSensorPosition", turretMotor.getSelectedSensorPosition(Constants.EncoderConstants.kPIDLoopIdx));
    limeLightTx = Robot.m_limeLight.getdegRotationToTarget();
    limeLightTy = Robot.m_limeLight.getdegVerticalToTarget();
    SmartDashboard.putNumber("Vision X", limeLightTx);
    SmartDashboard.putNumber("Vision Y", limeLightTy);
  }

  // Run
  public void Run() {
    double backup = SpeedIn;
    setSpeed = getPreferencesDouble(TurretSpeed ,backup);
    turretMotor.set(setSpeed);
  }
  
  // Stop Motion 
  public void stopMotion() {
    turretMotor.set(0.0);
  }
  
  //---------------------------------------------------------------------
  // Estimated Distance
  //---------------------------------------------------------------------
  private double EstimatedDistance(double a2) {
    return (h2-h1)/Math.tan(a1+a2);
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