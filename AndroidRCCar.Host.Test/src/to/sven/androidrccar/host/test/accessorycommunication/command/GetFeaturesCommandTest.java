/*******************************************************************************
 * Copyright (C) 2012 Sven Nobis
 * 
 * This file is part of AndroidRCCar (http://androidrccar.sven.to)
 * 
 * AndroidRCCar is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This source code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *  
 * You should have received a copy of the GNU General Public License
 * along with this source code; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 ******************************************************************************/
package to.sven.androidrccar.host.test.accessorycommunication.command;

import org.easymock.Capture;

import to.sven.androidrccar.host.accessorycommunication.command.AbstractCommand;
import to.sven.androidrccar.host.accessorycommunication.command.GetFeaturesCommand;
import to.sven.androidrccar.host.accessorycommunication.model.CarFeatures;
import to.sven.androidrccar.host.accessorycommunication.model.RequestCommand;
import to.sven.androidrccar.host.accessorycommunication.model.ResponseMessage;

import com.google.android.testing.mocking.AndroidMock;

/**
 * This class should test the behavior of the {@link GetFeaturesCommand}.
 * This class also tests some(!) behavior of 
 * the abstract implementation of {@link AbstractCommand}.
 * 
 * @author sven
 * @see NoopCommandTest NoopCommandTest for tests of {@link AbstractCommand}.
 */
public class GetFeaturesCommandTest extends AbstractCommandTestCase {
	
	/**
	 * Contains the CarFeatures generated by the test object. 
	 * @see Capture
	 */
	private Capture<CarFeatures> featureCapture;
	
	/**
	 * Common verification for test.
	 */
	protected void verifyTest() {
		byte[] expectedRequest = createArray(new byte[] { 0x03 }, (byte) 0xD8);
		verifyTest(expectedRequest);
	}	
	
	/**
	 * Common configure and run test
	 * @param mockRespone The mock response that should "send from the µController".
	 */
	private void configureAndRunTest(byte[] mockRespone) {
		// Configure Test 
		prepareStreams(mockRespone);
		featureCapture = new Capture<CarFeatures>();
		listenerMock.setCarFeatures(AndroidMock.capture(featureCapture));
		listenerMock.connectionInitialized();

		// Run Test
		replay();
		GetFeaturesCommand target = new GetFeaturesCommand(listenerMock);
		target.run();
	}
	
	/**
	 * Send a {@link RequestCommand#GET_FEATURES} 
	 * and receive a {@link ResponseMessage#FEATURES}
	 * with minimal features.
	 */
	public void testRun_minimalFeatures() {
		byte[] mockRespone = createArray(new byte[] { 0x05, 0x00,
				   			   						  0x00, 0x00,   // cameraPanMin
				   			   						  0x00, 0x00,   // cameraPanMax
				   			   						  0x00, 0x00,   // cameraTiltMin
				   			   						  0x00, 0x00 }, // cameraTiltMax
										   			  (byte) 0x6F); 
		configureAndRunTest(mockRespone);
		
		// Verify Test
		CarFeatures features = featureCapture.getValue();
		assertFalse(features.adjustableSpeed);
		assertFalse(features.batteryPower);
		assertFalse(features.driveBackward);
		assertFalse(features.supportPanCamera());
		assertFalse(features.supportTiltCamera());
		
		verifyTest();
	}
	
	/**
	 * Send a {@link RequestCommand#GET_FEATURES} 
	 * and receive a {@link ResponseMessage#FEATURES}
	 * with adjustable speed feature.
	 */
	public void testRun_adjustableSpeed() {
		byte[] mockRespone = createArray(new byte[] { 0x05, 0x01,
				   			   						  0x00, 0x00,   // cameraPanMin
				   			   						  0x00, 0x00,   // cameraPanMax
				   			   						  0x00, 0x00,   // cameraTiltMin
				   			   						  0x00, 0x00 }, // cameraTiltMax
										   			  (byte) 0x8A); 
		configureAndRunTest(mockRespone);
		
		// Verify Test
		CarFeatures features = featureCapture.getValue();
		assertTrue(features.adjustableSpeed);
		assertFalse(features.batteryPower);
		assertFalse(features.driveBackward);
		assertFalse(features.supportPanCamera());
		assertFalse(features.supportTiltCamera());
		
		verifyTest();
	}
	
	/**
	 * Send a {@link RequestCommand#GET_FEATURES} 
	 * and receive a {@link ResponseMessage#FEATURES}
	 * with drive backward feature.
	 */
	public void testRun_driveBackward() {
		byte[] mockRespone = createArray(new byte[] { 0x05, 0x02,
				   			   						  0x00, 0x00,   // cameraPanMin
				   			   						  0x00, 0x00,   // cameraPanMax
				   			   						  0x00, 0x00,   // cameraTiltMin
				   			   						  0x00, 0x00 }, // cameraTiltMax
										   			  (byte) 0xA2); 
		configureAndRunTest(mockRespone);
		
		// Verify Test
		CarFeatures features = featureCapture.getValue();
		assertFalse(features.adjustableSpeed);
		assertFalse(features.batteryPower);
		assertTrue(features.driveBackward);
		assertFalse(features.supportPanCamera());
		assertFalse(features.supportTiltCamera());
		
		verifyTest();
	}
	
	/**
	 * Send a {@link RequestCommand#GET_FEATURES} 
	 * and receive a {@link ResponseMessage#FEATURES}
	 * with battery power feature.
	 */
	public void testRun_batteryPower() {
		byte[] mockRespone = createArray(new byte[] { 0x05, 0x04,
				   			   						  0x00, 0x00,   // cameraPanMin
				   			   						  0x00, 0x00,   // cameraPanMax
				   			   						  0x00, 0x00,   // cameraTiltMin
				   			   						  0x00, 0x00 }, // cameraTiltMax
										   			  (byte) 0xF2); 
		configureAndRunTest(mockRespone);
		
		// Verify Test
		CarFeatures features = featureCapture.getValue();
		assertFalse(features.adjustableSpeed);
		assertTrue(features.batteryPower);
		assertFalse(features.driveBackward);
		assertFalse(features.supportPanCamera());
		assertFalse(features.supportTiltCamera());
		
		verifyTest();
	}
	
	/**
	 * Send a {@link RequestCommand#GET_FEATURES} 
	 * and receive a {@link ResponseMessage#FEATURES}
	 * with minimal camera pan only.
	 */
	public void testRun_cameraPanMin() {
		byte[] mockRespone = createArray(new byte[] { 0x05, 0x00,
							   						  0x7F, -0x01,   // cameraPanMin
							   						  0x00, 0x00,   // cameraPanMax
							   						  0x00, 0x00,   // cameraTiltMin
							   						  0x00, 0x00 }, // cameraTiltMax
										   			  (byte) 0x0B); 
		configureAndRunTest(mockRespone);
		
		// Verify Test
		CarFeatures features = featureCapture.getValue();
		assertFalse(features.adjustableSpeed);
		assertFalse(features.batteryPower);
		assertFalse(features.driveBackward);
		assertTrue(features.supportPanCamera());
		assertEquals(180f, features.cameraPanMin);
		assertEquals(0f, features.cameraPanMax);
		assertFalse(features.supportTiltCamera());
		
		verifyTest();
	}
	
	/**
	 * Send a {@link RequestCommand#GET_FEATURES} 
	 * and receive a {@link ResponseMessage#FEATURES}
	 * with maximal camera pan only.
	 */
	public void testRun_cameraPanMax() {
		byte[] mockRespone = createArray(new byte[] { 0x05, 0x00,
					  								  0x00, 0x00,   // cameraPanMin
					  								  0x7F, -0x01,  // cameraPanMax
					  								  0x00, 0x00,   // cameraTiltMin
					  								  0x00, 0x00 }, // cameraTiltMax
										   			  (byte) 0x2C); 
		configureAndRunTest(mockRespone);
		
		// Verify Test
		CarFeatures features = featureCapture.getValue();
		assertFalse(features.adjustableSpeed);
		assertFalse(features.batteryPower);
		assertFalse(features.driveBackward);
		assertTrue(features.supportPanCamera());
		assertEquals(0f, features.cameraPanMin);
		assertEquals(180f, features.cameraPanMax);
		assertFalse(features.supportTiltCamera());
		
		verifyTest();
	}
	
	/**
	 * Send a {@link RequestCommand#GET_FEATURES} 
	 * and receive a {@link ResponseMessage#FEATURES}
	 * with minimal camera tilt only.
	 */
	public void testRun_cameraTiltMin() {
		byte[] mockRespone = createArray(new byte[] { 0x05, 0x00,
				   			   						  0x00, 0x00,   // cameraPanMin
				   			   						  0x00, 0x00,   // cameraPanMax
				   			   						  0x7F, -0x01,  // cameraTiltMin
				   			   						  0x00, 0x00 }, // cameraTiltMax
										   			  (byte) 0x8F); 
		configureAndRunTest(mockRespone);
		
		// Verify Test
		CarFeatures features = featureCapture.getValue();
		assertFalse(features.adjustableSpeed);
		assertFalse(features.batteryPower);
		assertFalse(features.driveBackward);
		assertFalse(features.supportPanCamera());
		assertTrue(features.supportTiltCamera());
		assertEquals(180f, features.cameraTiltMin);
		assertEquals(0f, features.cameraTiltMax);
		
		verifyTest();
	}
	
	/**
	 * Send a {@link RequestCommand#GET_FEATURES} 
	 * and receive a {@link ResponseMessage#FEATURES}
	 * with maximal camera tilt only.
	 */
	public void testRun_cameraTiltMax() {
		byte[] mockRespone = createArray(new byte[] { 0x05, 0x00,
				   			   						  0x00, 0x00,   // cameraPanMin
				   			   						  0x00, 0x00,   // cameraPanMax
				   			   						  0x00, 0x00,   // cameraTiltMin
				   			   						  0x7F, -0x01 },// cameraTiltMax
										   			  (byte) 0x0E); 
		configureAndRunTest(mockRespone);
		
		// Verify Test
		CarFeatures features = featureCapture.getValue();
		assertFalse(features.adjustableSpeed);
		assertFalse(features.batteryPower);
		assertFalse(features.driveBackward);
		assertFalse(features.supportPanCamera());
		assertTrue(features.supportTiltCamera());
		assertEquals(0f, features.cameraTiltMin);
		assertEquals(180f, features.cameraTiltMax);
		
		verifyTest();
	}
	
	/**
	 * Send a {@link RequestCommand#GET_FEATURES} 
	 * and receive a {@link ResponseMessage#FEATURES}
	 * with all features.
	 */
	public void testRun_allFeatures() {
		byte[] mockRespone = createArray(new byte[] { 0x05, 0x07,
										   			  0x40, 0x00,   // cameraPanMin
										   			  0x40, 0x00,   // cameraPanMax
										   			  0x40, 0x00,   // cameraTiltMin
										   			  0x40, 0x00 }, // cameraTiltMax
										   			  (byte) 0xAB); 
		configureAndRunTest(mockRespone);
		
		// Verify Test
		CarFeatures features = featureCapture.getValue();
		assertTrue(features.adjustableSpeed);
		assertTrue(features.batteryPower);
		assertTrue(features.driveBackward);
		assertTrue(features.supportPanCamera());
		assertEquals(90f, features.cameraPanMin, 0.01f);
		assertEquals(90f, features.cameraPanMax, 0.01f);
		assertTrue(features.supportTiltCamera());
		assertEquals(90f, features.cameraTiltMin, 0.01f);
		assertEquals(90f, features.cameraTiltMax, 0.01f);
		
		verifyTest();
	}
}
