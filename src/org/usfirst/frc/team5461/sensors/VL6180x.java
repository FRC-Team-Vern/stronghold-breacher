package org.usfirst.frc.team5461.sensors;

import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.hal.I2CJNI;
import edu.wpi.first.wpilibj.util.BoundaryException;
public class VL6180x extends I2C 
{
	private Port m_port;
	  //Store address given when the class is initialized.
	  //This value can be changed by the changeAddress() function
	  private int _i2caddress;
	
  //Initalize library with default address
  public VL6180x(int deviceAddress)
  {
	  super(Port.kOnboard, deviceAddress);
	  m_port = Port.kOnboard;
	  _i2caddress = deviceAddress;
  }
  
  @Override
  public boolean read(int registerAddress, int count, ByteBuffer buffer) {
	    if (count < 1) {
	      throw new BoundaryException("Value must be at least 1, " + count +
	                                  " given");
	    }

	    if (!buffer.isDirect())
	      throw new IllegalArgumentException("must be a direct buffer");
	    if (buffer.capacity() < count)
	      throw new IllegalArgumentException("buffer is too small, must be at least " + count);

	    ByteBuffer dataToSendBuffer = ByteBuffer.allocateDirect(2);
	    dataToSendBuffer.putShort((short)registerAddress);

	    return transaction(dataToSendBuffer, 2, buffer, count);
	  }

  @Override
  public synchronized boolean write(int registerAddress, int data) {
	    ByteBuffer dataToSendBuffer = ByteBuffer.allocateDirect(3);
	    dataToSendBuffer.putShort((short)registerAddress);
	    dataToSendBuffer.put(2, (byte)data);

	    return I2CJNI.i2CWrite((byte) m_port.getValue(), (byte) _i2caddress, dataToSendBuffer,
	        (byte) 3) < 0;
	  }

  public synchronized boolean write16(int registerAddress, int data) {
	    ByteBuffer dataToSendBuffer = ByteBuffer.allocateDirect(4);
	    dataToSendBuffer.putShort((short)registerAddress);
	    dataToSendBuffer.putShort(2, (short)data);

	    return I2CJNI.i2CWrite((byte) m_port.getValue(), (byte) _i2caddress, dataToSendBuffer,
	        (byte) 4) < 0;
	  }


  //Send manditory settings as stated in ST datasheet.
  // http://www.st.com/st-web-ui/static/active/en/resource/technical/document/application_note/DM00122600.pdf (Section 1.3)
  public final int VL6180xInit()
  {
	  ByteBuffer resetCheck = ByteBuffer.allocateDirect(1);
	  read(Constants.VL6180X_SYSTEM_FRESH_OUT_OF_RESET, Constants.SINGLE_BYTE, resetCheck);
	  int checkResult = resetCheck.get() & 0xFF;
	  
	if (checkResult != 1)
	{
		return Constants.VL6180x_FAILURE_RESET;
	}

	//Required by datasheet
	//http://www.st.com/st-web-ui/static/active/en/resource/technical/document/application_note/DM00122600.pdf
	write(0x0207, 0x01);
	write(0x0208, 0x01);
	write(0x0096, 0x00);
	write(0x0097, 0xfd);
	write(0x00e3, 0x00);
	write(0x00e4, 0x04);
	write(0x00e5, 0x02);
	write(0x00e6, 0x01);
	write(0x00e7, 0x03);
	write(0x00f5, 0x02);
	write(0x00d9, 0x05);
	write(0x00db, 0xce);
	write(0x00dc, 0x03);
	write(0x00dd, 0xf8);
	write(0x009f, 0x00);
	write(0x00a3, 0x3c);
	write(0x00b7, 0x00);
	write(0x00bb, 0x3c);
	write(0x00b2, 0x09);
	write(0x00ca, 0x09);
	write(0x0198, 0x01);
	write(0x01b0, 0x17);
	write(0x01ad, 0x00);
	write(0x00ff, 0x05);
	write(0x0100, 0x05);
	write(0x0199, 0x05);
	write(0x01a6, 0x1b);
	write(0x01ac, 0x3e);
	write(0x01a7, 0x1f);
	write(0x0030, 0x00);

	return 1;
  }
  // Use default settings from ST data sheet section 9.
  // http://www.st.com/st-web-ui/static/active/en/resource/technical/document/application_note/DM00122600.pdf
  public final void defaultSettings()
  {
	//Recommended settings from datasheet
	//http://www.st.com/st-web-ui/static/active/en/resource/technical/document/application_note/DM00122600.pdf

	//Enable Interrupts on Conversion Complete (any source)
	write(Constants.VL6180X_SYSTEM_INTERRUPT_CONFIG_GPIO, (4 << 3) | (4)); // Set GPIO1 high when sample complete
	write(Constants.VL6180X_SYSTEM_MODE_GPIO1, 0x10); // Set GPIO1 high when sample complete
	write(Constants.VL6180X_READOUT_AVERAGING_SAMPLE_PERIOD, 0x30); //Set Avg sample period
	write(Constants.VL6180X_SYSALS_ANALOGUE_GAIN, 0x46); // Set the ALS gain
	write(Constants.VL6180X_SYSRANGE_VHV_REPEAT_RATE, 0xFF); // Set auto calibration period (Max = 255)/(OFF = 0)
	write(Constants.VL6180X_SYSRANGE_VHV_RECALIBRATE, 0x01); // perform a single temperature calibration
	//Optional settings from datasheet
	//http://www.st.com/st-web-ui/static/active/en/resource/technical/document/application_note/DM00122600.pdf
	write(Constants.VL6180X_SYSRANGE_INTERMEASUREMENT_PERIOD, 0x09); // Set default ranging inter-measurement period to 100ms
	write(Constants.VL6180X_SYSALS_INTERMEASUREMENT_PERIOD, 0x0A); // Set default ALS inter-measurement period to 100ms
	write(Constants.VL6180X_SYSTEM_INTERRUPT_CONFIG_GPIO, 0x24); // Configures interrupt on ‘New Sample Ready threshold event’
	//Additional settings defaults from community
	write(Constants.VL6180X_SYSRANGE_MAX_CONVERGENCE_TIME, 0x32);
	write(Constants.VL6180X_SYSRANGE_RANGE_CHECK_ENABLES, 0x10 | 0x01);
	write16(Constants.VL6180X_SYSRANGE_EARLY_CONVERGENCE_ESTIMATE, 0x007B);
	write16(Constants.VL6180X_SYSALS_INTEGRATION_PERIOD, 0x0064);
	write(Constants.VL6180X_READOUT_AVERAGING_SAMPLE_PERIOD, 0x30);
	write(Constants.VL6180X_SYSALS_ANALOGUE_GAIN, 0x40);
	write(Constants.VL6180X_FIRMWARE_RESULT_SCALER, 0x01);
  }

  // Get Range distance in (mm)
  public final int getDistance()
  {
	write(Constants.VL6180X_SYSRANGE_START, 0x01); //Start Single shot mode
	try 
	{
		Thread.sleep(10);
	} 
	catch (InterruptedException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ByteBuffer data = ByteBuffer.allocateDirect(Constants.SINGLE_BYTE);
	read(Constants.VL6180X_RESULT_RANGE_VAL, Constants.SINGLE_BYTE, data);
	int value = data.get() & 0xFF;
		return value;
	
  }
  // Get ALS level in Lux
  public final float getAmbientLight(vl6180x_as_gain VL6180X_AS_GAIN)
  {
	//First load in Gain we are using, do it everytime incase someone changes it on us.
	//Note: Upper nibble shoudl be set to 0x4 i.e. for ALS gain of 1.0 write 0x46
	write(Constants.VL6180X_SYSALS_ANALOGUE_GAIN, (0x40 | VL6180X_AS_GAIN.getValue())); // Set the ALS gain

	//Start ALS Measurement
	write(Constants.VL6180X_SYSALS_START, 0x01);

	  try 
	  {
		Thread.sleep(100);
	} 
	  catch (InterruptedException e) 
	  {
		e.printStackTrace();
	} //give it time...

	write(Constants.VL6180X_SYSTEM_INTERRUPT_CLEAR, 0x07);
	ByteBuffer alsRaw = ByteBuffer.allocateDirect(Constants.SHORT_BYTE);
	read(Constants.VL6180X_RESULT_ALS_VAL, Constants.SHORT_BYTE, alsRaw);
	
	ByteBuffer alsIntegrationPeriodRaw = ByteBuffer.allocateDirect(Constants.SHORT_BYTE);
	read(Constants.VL6180X_SYSALS_INTEGRATION_PERIOD, Constants.SHORT_BYTE, alsIntegrationPeriodRaw);
	int integrationPeriod = alsIntegrationPeriodRaw.getShort() & 0x01FF;
	float alsIntegrationPeriod = (float) (100.0 / integrationPeriod);

	//Calculate actual LUX from Appnotes

	float alsGain = 0.0F;

	switch (VL6180X_AS_GAIN)
	{
	  case GAIN_20:
		  alsGain = 20.0F;
		  break;
	  case GAIN_10:
		  alsGain = 10.32F;
		  break;
	  case GAIN_5:
		  alsGain = 5.21F;
		  break;
	  case GAIN_2_5:
		  alsGain = 2.60F;
		  break;
	  case GAIN_1_67:
		  alsGain = 1.72F;
		  break;
	  case GAIN_1_25:
		  alsGain = 1.28F;
		  break;
	  case GAIN_1:
		  alsGain = 1.01F;
		  break;
	  case GAIN_40:
		  alsGain = 40.0F;
		  break;
	}

  //Calculate LUX from formula in AppNotes
	int als = alsRaw.getShort() & 0xFFFF;
	float alsCalculated = (float)0.32 * ( als/ alsGain) * alsIntegrationPeriod;

	return alsCalculated;
  }

  //Load structure provided by the user with identification info
  //Structure example:
  // struct VL6180xIdentification
  //  {
  //   uint8_t idModel;
  //   uint8_t idModelRevMajor;
  //   uint8_t idModelRevMinor;
  //   uint8_t idModuleRevMajor;
  //   uint8_t idModuleRevMinor;
  //   uint16_t idDate;
  //   uint16_t idTime;
  //   };
  public final void getIdentification(VL6180xIdentification temp)
  {
	ByteBuffer modelId = ByteBuffer.allocateDirect(Constants.SINGLE_BYTE);
	ByteBuffer modelRevMajorId = ByteBuffer.allocateDirect(Constants.SINGLE_BYTE);
	ByteBuffer modelRevMinorId = ByteBuffer.allocateDirect(Constants.SINGLE_BYTE);
	ByteBuffer moduleRevMajorId = ByteBuffer.allocateDirect(Constants.SINGLE_BYTE);
	ByteBuffer moduleRevMinorId = ByteBuffer.allocateDirect(Constants.SINGLE_BYTE);
	ByteBuffer dateId = ByteBuffer.allocateDirect(Constants.SHORT_BYTE);
	ByteBuffer timeId = ByteBuffer.allocateDirect(Constants.SHORT_BYTE);
	read(Constants.VL6180X_IDENTIFICATION_MODEL_ID, Constants.SINGLE_BYTE, modelId);
	read(Constants.VL6180X_IDENTIFICATION_MODEL_REV_MAJOR, Constants.SINGLE_BYTE, modelRevMajorId);
	read(Constants.VL6180X_IDENTIFICATION_MODEL_REV_MINOR, Constants.SINGLE_BYTE, modelRevMinorId);
	read(Constants.VL6180X_IDENTIFICATION_MODULE_REV_MAJOR, Constants.SINGLE_BYTE, moduleRevMajorId);
	read(Constants.VL6180X_IDENTIFICATION_MODULE_REV_MINOR, Constants.SINGLE_BYTE, moduleRevMinorId);
	temp.idModel = modelId.get() & 0xFF;
	temp.idModelRevMajor = modelRevMajorId.get() & 0xFF;
	temp.idModelRevMinor = modelRevMinorId.get() & 0xFF;
	temp.idModuleRevMajor = moduleRevMajorId.get() & 0xFF;
	temp.idModuleRevMinor = moduleRevMinorId.get() & 0xFF;
	read(Constants.VL6180X_IDENTIFICATION_DATE, Constants.SHORT_BYTE, dateId);
	read(Constants.VL6180X_IDENTIFICATION_TIME, Constants.SHORT_BYTE, timeId);
	temp.idDate = dateId.getShort() & 0xFFFF;
	temp.idTime = timeId.getShort() & 0xFFFF;
  }

  //Change the default address of the device to allow multiple
  //sensors on the bus.  Can use up to 127 sensors. New address
  //is saved in non-volatile device memory.
  public final int changeAddress(int old_address, int new_address)
  {

	//NOTICE:  IT APPEARS THAT CHANGING THE ADDRESS IS NOT STORED IN NON-VOLATILE MEMORY
	// POWER CYCLING THE DEVICE REVERTS ADDRESS BACK TO 0X29

	if (old_address == new_address)
	{
		return old_address;
	}
	if (new_address > 127)
	{
		return old_address;
	}

	 write(Constants.VL6180X_I2C_SLAVE_DEVICE_ADDRESS, new_address);
	 ByteBuffer deviceAddress = ByteBuffer.allocateDirect(Constants.SINGLE_BYTE);
	 read(Constants.VL6180X_I2C_SLAVE_DEVICE_ADDRESS, Constants.SINGLE_BYTE, deviceAddress);
	 return deviceAddress.get();
  }

  // --- Private Functions --- //

}