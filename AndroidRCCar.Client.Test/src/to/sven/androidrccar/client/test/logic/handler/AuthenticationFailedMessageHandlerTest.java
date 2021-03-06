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
package to.sven.androidrccar.client.test.logic.handler;

import to.sven.androidrccar.client.logic.handler.AuthenticationFailedMessageHandler;
import to.sven.androidrccar.common.communication.model.AuthenticationFailedMessage;

/**
 * This class should test the behavior of the {@link AuthenticationFailedMessageHandler}.
 * @author sven
 *
 */
public class AuthenticationFailedMessageHandlerTest extends
		AbstractTestCaseForClientMessageHandler<AuthenticationFailedMessageHandler> {
	
	/**
	 * Tests {@link AuthenticationFailedMessageHandler#handleMessage}.
	 */
	public void testHandleMessage_Success() {
		// Configure Test
		String reason = "The reason is that this is a test.";
		logicMock.clearMessageHandlers();
		logicListenerMock.authenticationFalied(reason);
		logicMock.close();
		
		// Run Test
		AuthenticationFailedMessageHandler handler = aHandlerForTest();
		handler.handleMessage(new AuthenticationFailedMessage(reason));
		
		// Verify Test
		verifyFieldMocks();
	}
}
