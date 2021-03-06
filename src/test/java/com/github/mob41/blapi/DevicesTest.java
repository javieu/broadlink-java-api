package com.github.mob41.blapi;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DevicesTest {

	private static final Logger log = LoggerFactory.getLogger(DevicesTest.class);

    @Test
    @Ignore
    public void testDevices() throws Exception {
        BLDevice[] devs = BLDevice.discoverDevices(0);
        log.info("BLDevice returned " + devs.length + " number of devices.");
        for(int i = 0; i < devs.length; i++) {
	        BLDevice dev = devs[i];
	
	        log.info("BLDevice mac is " + dev.getMac().getMacString());
	
	        dev.auth();
	        switch(dev.getDeviceType()) {
	        case BLDevice.DEV_A1:
	        	EnvironmentalSensor theData = ((A1Device)dev).checkSensors();
	        	log.info("A1Device Temp: " + theData.getTemperature() + ", Humidity: " + theData.getHumidity() + ", light: " + theData.getLight() + ", air_quality: " + theData.getAirquality() + ", noise: " + theData.getNoise());
	        	break;
	        case BLDevice.DEV_MP1:
	        	byte byteData = ((MP1Device)dev).checkPowerRaw();
	        	log.info("MP1 Device srtatus byte: " + byteData);
	        	break;
			case BLDevice.DEV_SP2:
			case BLDevice.DEV_SP2_HONEYWELL_ALT1:
			case BLDevice.DEV_SP2_HONEYWELL_ALT2:
			case BLDevice.DEV_SP2_HONEYWELL_ALT3:
			case BLDevice.DEV_SP2_HONEYWELL_ALT4:
			case BLDevice.DEV_SPMINI:
			case BLDevice.DEV_SP3:
			case BLDevice.DEV_SPMINI2:
			case BLDevice.DEV_SPMINI_OEM_ALT1:
			case BLDevice.DEV_SPMINI_OEM_ALT2:
			case BLDevice.DEV_SPMINI_PLUS:
	        	boolean state = ((SP2Device)dev).checkPower();
	        	log.info("SP2Device returned state: " + state);
	        	break;
			case BLDevice.DEV_SP1:
				((SP1Device)dev).setPower(true);
				log.info("SP1Device set power to true");
	        	break;
			case BLDevice.DEV_RM_2:
			case BLDevice.DEV_RM_MINI:
			case BLDevice.DEV_RM_PRO_PHICOMM:
			case BLDevice.DEV_RM_2_HOME_PLUS:
			case BLDevice.DEV_RM_2_2HOME_PLUS_GDT:
			case BLDevice.DEV_RM_2_PRO_PLUS:
			case BLDevice.DEV_RM_2_PRO_PLUS_2:
			case BLDevice.DEV_RM_2_PRO_PLUS_2_BL:
			case BLDevice.DEV_RM_MINI_SHATE:
	        	double theTemp = ((RMDevice)dev).getTemp();
	        	log.info("RMDevice get temperature: " + theTemp);
	        	break;
	        	
	        }
	        
        }
    }

}
