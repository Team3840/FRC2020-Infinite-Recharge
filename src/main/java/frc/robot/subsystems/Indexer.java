/*
  FRC7068 Indexer Subsystem
  10 Feb 2020  -first committment
*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.Constants.ActuatorConstants;
import frc.robot.Constants.SensorContants;

public class Indexer extends SubsystemBase {

  // Motors
  private final WPI_VictorSPX indexerFront = new WPI_VictorSPX(ActuatorConstants.kIndexerFront);
  private final WPI_VictorSPX indexerBack = new WPI_VictorSPX(ActuatorConstants.kIndexerBack);
  // Sensors in the indexer holder
  //Local variables
  private final DigitalInput intakeSensor = new DigitalInput(SensorContants.kSensorInTake);
  private final DigitalInput sensorInput1 = new DigitalInput(SensorContants.kSensorIndex1);
  private final DigitalInput sensorInput2 = new DigitalInput(SensorContants.kSensorIndex2);
  private final DigitalInput sensorInput3 = new DigitalInput(SensorContants.kSensorIndex3);
  private final DigitalInput sensorInput4 = new DigitalInput(SensorContants.kSensorIndex4);
  private final DigitalInput sensorInput5 = new DigitalInput(SensorContants.kSensorIndex5);
  
  // SmartDashboard
  final String indexerSpeed = "Index Speed";
  final String shootSpeed = "Shoot Speed";
  final double SpeedIndexer = 0.2;
  final double SpeedShoot = 0.2;
  private double setSpeed;
  //local variables
  private final String [] snapShotSensors = new String[5];
  private boolean senIntakeStatus;
  private boolean sens1Status;
  private boolean sens2Status;
  private boolean sens3Status;
  private boolean sens4Status;
  private boolean sens5Status;
  private boolean isIndexing;
  private int locationToStop;

  // Indexer
  public Indexer() {
    
  }

  // Periodic
  @Override
  public void periodic() {
    // Monitors sensors inputs
    senIntakeStatus = intakeSensor.get();
    sens1Status = sensorInput1.get();
    sens2Status = sensorInput2.get();
    sens3Status = sensorInput3.get();
    sens4Status = sensorInput4.get();
    sens5Status = sensorInput5.get();
    // Put the status of the shuffleboard for the drivers
    SmartDashboard.putBoolean("Intake Sensor", senIntakeStatus);
    SmartDashboard.putBoolean("Sensor1", sens1Status);
    SmartDashboard.putBoolean("Sensor2", sens2Status);
    SmartDashboard.putBoolean("Sensor3", sens3Status);
    SmartDashboard.putBoolean("Sensor4", sens4Status);
    SmartDashboard.putBoolean("Sensor5", sens5Status);
    //Checks to see if the intake is running
    if (Robot.m_indexVariables.isIntakeActive == true) {
      //Check to see if the intake sensor is on...to start indexer
      if (senIntakeStatus == true && isIndexing == false) {
        isIndexing = true;
        this.IndexBalls();
      }
    }
  }

  // -------------------------------------------------------------
  // Indexing the balls into the mag.
  // -------------------------------------------------------------
  private void IndexBalls() {
    double backup = SpeedIndexer;

    // Get status of all the sensors inputs before indexer
    this.GetSensorStatus();
    
    // If the indexer is full to move
    if (!sens5Status) {
      // Finds Which location to stop
      this.WhichLocationToStop();
      // Get current speed setpoint from the preference tables
      setSpeed = getPreferencesDouble(indexerSpeed, backup);
      // Call the motor to move
      this.RunMotors(setSpeed);
      // Is the ball at the location?
      this.CheckBallLocation();
    }

    //reset isIndexing and recheck to see if another ball is present
    isIndexing = false;
  }

  // -------------------------------------------------------------
  // Get snap shot of the sensors
  // -------------------------------------------------------------
  private void GetSensorStatus() {
    // Fill the array with all the sensors inputs
    // 0 0 0 0 0
    // sensor 1
    if (sensorInput1.get()) {
      snapShotSensors[0] = "1";
    }
      else {
        snapShotSensors[0] = "0";
    }

     // sensor 2
     if (sensorInput2.get()) {
      snapShotSensors[1] = "1";
    }
      else {
        snapShotSensors[1] = "0";
    }

     // sensor 3
     if (sensorInput3.get()) {
      snapShotSensors[2] = "1";
    }
      else {
        snapShotSensors[2] = "0";
    }

     // sensor 4
     if (sensorInput4.get()) {
      snapShotSensors[3] = "1";
    }
      else {
        snapShotSensors[3] = "0";
    }

     // sensor 5
     if (sensorInput5.get()) {
      snapShotSensors[4] = "1";
    }
      else {
        snapShotSensors[4] = "0";
    }
  }

  // -------------------------------------------------------------
  // Runs the indexing motors until ball at location
  // -------------------------------------------------------------
  private void RunMotors(double speed) {
    indexerFront.set(speed);
    indexerBack.set(speed);
  }

  // ----------------------------------------------------------------------------------
  //This monitors the sensor states and nofity which sensor we are we need to monitor
  // ----------------------------------------------------------------------------------
  private void WhichLocationToStop() {
   int intcounter = 1;
   
    for (String xLoc : snapShotSensors) {
      System.out.println("Loc: " + intcounter + "L" + xLoc);
      
      //check the off sensor    
      if (xLoc == "0") {
        locationToStop = intcounter;
        break;
      }
      //increment counter
     intcounter = intcounter + 1;
    }

  }

  private void CheckBallLocation() {
    System.out.println("Check Ball Location");
    switch (locationToStop) {
      case 1: this.StopLocation1();
      System.out.println("Check Ball Location 1");
        break;
      case 2: this.StopLocation2();
        break;
      case 3: this.StopLocation3();
        break;
      case 4: this.StopLocation4();
        break;
      case 5: this.StopLocation5();
        break;
    }
  }

    // -------------------------------------------------------------
    // monitors to the ball location #1
    // -------------------------------------------------------------
    private void StopLocation1() {
      System.out.println("Stop Loc1");

      do {
        System.out.println("While Loc 1");
      } while (sens1Status == false && sensorInput1.get() == false);

      System.out.println("Loc1 Stop");
      this.StopMotion();
    }
  
  // -------------------------------------------------------------
  // monitors to the ball location #2
  // -------------------------------------------------------------
  private void StopLocation2() {
    while (sens2Status == false) {
      // Nothing
      if (sens2Status == true) {
        break;
      }
    }
    this.StopMotion();
  }

  // -------------------------------------------------------------
  // monitors to the ball location #3
  // -------------------------------------------------------------
  private void StopLocation3() {
    while (sens3Status == false) {
      // Nothing
      if (sens3Status == true) {
        break;
      }
    }
    this.StopMotion();
  }

  // -------------------------------------------------------------
  // monitors to the ball location #4
  // -------------------------------------------------------------
  private void StopLocation4() {
    while (sens4Status == false) {
      // Nothing
      if (sens4Status == true) {
        break;
      }
    }
    this.StopMotion();
  }

  // -------------------------------------------------------------
  // monitors to the ball location #5
  // -------------------------------------------------------------
  private void StopLocation5() {
    while (sens5Status == false) {
      //Nothing
      if (sens5Status == true) {
        break;
      }
    }
    this.StopMotion();
  }

  //--------------------------------------------------------------
  // This call as default command from the robot container
  //--------------------------------------------------------------
  public void SetDefaultCall() {

  }

  // -------------------------------------------------------------
  // Moves all balls to the flywheel when shooting
  // -------------------------------------------------------------
  public void ShotBalls() {
    final double backup = SpeedShoot;
    // Get current speed setpoint from the preference tables
    setSpeed = getPreferencesDouble(shootSpeed, backup);
    // Call the motor to move
    this.RunMotors(setSpeed);
  }
    
  // -------------------------------------------------------------
  // Stop Motion
  // -------------------------------------------------------------
  public void StopMotion() {
    indexerFront.set(0.0);
    indexerBack.set(0.0);
    isIndexing = false;
  }

  // -------------------------------------------------------------
  // Preferences from network tables
  // -------------------------------------------------------------
  private static double getPreferencesDouble(final String key, final double backup) {
    final Preferences preferences = Preferences.getInstance();
    if(!preferences.containsKey(key)) {
      preferences.putDouble(key, backup);
    }
    return preferences.getDouble(key, backup);
  }
}

