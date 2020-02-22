
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.Climb;
import frc.robot.commands.EjectBall;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.RunFlyWheel;
import frc.robot.commands.RunMover;
import frc.robot.commands.TurnTurret;
import frc.robot.limelight.LimeLight;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Mover;
import frc.robot.subsystems.Turret;


public class RobotContainer {

  // Subsystems
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Intake m_intake = new Intake();
  private final Turret m_turret = new Turret();
  private final Indexer m_indexer = new Indexer();
  private final FlyWheel m_flyWheel = new FlyWheel();
  private final Climber m_climber = new Climber();
  private final Mover m_mover = new Mover();

  // The Xbox Controller
  XboxController m_driverController = new XboxController(OIConstants.kDriveJoyStick);
  XboxController m_actuatorController = new XboxController(OIConstants.kActuatorJoyStick);
  
  // Commands
  private final CommandBase m_intakeCommand = new IntakeBall(m_intake);
  private final CommandBase m_flywheelCommand = new RunFlyWheel(m_flyWheel);
  private final CommandBase m_climbCommand = new Climb(m_climber);
  private final CommandBase m_turretCommand = new TurnTurret(m_turret);
  private final CommandBase m_moverCommand = new RunMover(m_mover);
  private final CommandBase m_ejectBallCommand = new EjectBall(m_intake);

  // LimeLight
  private final LimeLight limeLight = new LimeLight();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // A split-stick drive train command, with forward/backward controlled by the
    // left
    // hand, and turning controlled by the right.
    m_driveTrain.setDefaultCommand(new RunCommand(
        () -> m_driveTrain.drive(m_driverController.getY(GenericHID.Hand.kRight),
            m_driverController.getX(GenericHID.Hand.kRight), m_driverController.getX(GenericHID.Hand.kLeft)),
        m_driveTrain));

    // Configure the button bindings
    configureButtonBindings();

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureButtonBindings() {

    // Run instant command intake when the 'Back' button is pressed
    new JoystickButton(m_actuatorController, Button.kBack.value).whileHeld(m_intakeCommand);
    // Run Flywheel command when the 'B' button is pressed
    new JoystickButton(m_actuatorController, Button.kB.value).toggleWhenPressed(m_flywheelCommand);
    // Run Climb command when the 'Y' button is pressed
    // new JoystickButton(m_actuatorController,
    // Button.kY.value).whileHeld(m_climbCommand);
    // Run Eject Ball command when the 'start' button is pressed
    new JoystickButton(m_actuatorController, Button.kA.value).whileHeld(m_ejectBallCommand);

    // new JoystickButton(m_actuatorController,
    // Button.kA.value).whenPressed(m_climbLow);
    // new JoystickButton(m_actuatorController,
    // Button.kB.value).whenPressed(m_climbHigh);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

  public LimeLight getLimeLight() {
    return limeLight;
  }
}
