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
package to.sven.androidrccar.host.test.logic.handler;

import to.sven.androidrccar.common.communication.model.AdjustSpeedMessage;
import to.sven.androidrccar.common.exception.RangeException;
import to.sven.androidrccar.host.accessorycommunication.contract.IAccessoryCommunication;
import to.sven.androidrccar.host.logic.handler.AdjustSpeedMessageHandler;

import com.google.android.testing.mocking.AndroidMock;

/**
 * This class should test the behavior of the {@link AdjustSpeedMessageHandler}.
 * @author sven
 *
 */
public class AdjustSpeedMessageHandlerTest extends
		AbstractTestCaseForHostMessageHandler<AdjustSpeedMessageHandler> {

	/**
	 * Tests {@link AdjustSpeedMessageHandler#handleMessage}.
	 * Test with full forward and backward speed.
	 */
	public void testHandleMessage_success() {
		// Configure Test
		accessoryCommunicationMock.adjustSpeed(1.0f);
		accessoryCommunicationMock.adjustSpeed(-1.0f);
		
		// Run Test
		AdjustSpeedMessageHandler handler = aHandlerForTest();
		handler.handleMessage(new AdjustSpeedMessage(1.0f));
		handler.handleMessage(new AdjustSpeedMessage(-1.0f));
		
		// Verify Test
		verifyFieldMocks();
	}

	/**
	 * Tests {@link AdjustSpeedMessageHandler#handleMessage}.
	 * {@link IAccessoryCommunication} throws {@link RangeException}.
	 */
	public void testHandleMessage_rangeException() {
		// Configure Test
		accessoryCommunicationMock.adjustSpeed(1.1f);
		RangeException ex = new RangeException("Oh no!");
		AndroidMock.expectLastCall().andThrow(ex);
		logicMock.handleError(ex);
		
		// Run Test
		AdjustSpeedMessageHandler handler = aHandlerForTest();
		handler.handleMessage(new AdjustSpeedMessage(1.1f));
		
		// Verify Test
		verifyFieldMocks();
	}
}
