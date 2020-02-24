
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrain extends SubsystemBase {
  
  // Drive Motors
  private final WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(DriveConstants.kFrontLeftMotor);
  private final WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(DriveConstants.kBackLeftMotor);
  private final WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(DriveConstants.kFrontRightMotor);
  private final WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(DriveConstants.kBackRightMotor);
  
  double joyThreshold = 0.05; // Default threshold value from XboxController
  
  // Robot Drive
  MecanumDrive m_drive = new MecanumDrive(leftFrontMotor, leftBackMotor,rightFrontMotor, rightBackMotor);

  // Drive Train
  public DriveTrain() {
  }

  // Periodic
  @Override
  public void periodic() {
  }
  
  /**
   * Drives the robot at given x, y and theta speeds. Speeds range from [-1, 1] and the linear
   * speeds have no effect on the angular speed.
   *
   * @param xSpeed        Speed of the robot in the x direction (forward/backwards).
   * @param ySpeed        Speed of the robot in the y direction (sideways).
   * @param rot           Angular rate of the robot.
   * 
   */
  public void drive(double xSpeed, double ySpeed, double rot ) {
    
    if(Math.abs(xSpeed) > joyThreshold || Math.abs(ySpeed) > joyThreshold || Math.abs(rot) > joyThreshold ) {
      m_drive.driveCartesian(xSpeed, ySpeed, rot, 0.0 );

      m_drive.driveCartesian(ySpeed*-1.0, xSpeed*1.0, rot*-0.6);

    }
  }
}