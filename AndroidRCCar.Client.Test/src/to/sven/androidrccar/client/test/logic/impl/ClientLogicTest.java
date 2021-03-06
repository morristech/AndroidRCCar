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
package to.sven.androidrccar.client.test.logic.impl;

import to.sven.androidrccar.client.logic.impl.ClientLogic;
import android.test.AndroidTestCase;

import com.google.android.testing.mocking.AndroidMock;

/**
 * This class should test the behavior of the {@link ClientLogic}.
 * 
 * @author sven
 */
//@UsesMocks({ClientDependencyContainer.class, IClientLogicListener.class, CommunicationPartner.class, Socket.class, CommunicationPartnerListener.class})
public class ClientLogicTest extends AndroidTestCase {
	
//	/**
//	 * Strict mock
//	 * @see AndroidMock#createStrictMock
//	 */
//	private CommunicationPartner cpMock;
//	
//	/**
//	 * Strict mock
//	 * @see AndroidMock#createStrictMock
//	 */
//	private IClientLogicListener listenerMock;
	
	/**
	 * Before Test:
	 * Set up new {@link LogicListener} and {@link CommunicationPartner} mocks. 
	 * @see AndroidMock#createStrictMock
	 */
	@Override
	protected void setUp() {
//		factoryMock = AndroidMock.createStrictMock(Factory.class);
//		listenerMock = AndroidMock.createStrictMock(IClientLogicListener.class);
//		// "Call the correct constructor"
//		Socket socket = AndroidMock.createStrictMock(Socket.class);
//		CommunicationPartnerListener listener = AndroidMock.createStrictMock(CommunicationPartnerListener.class);
//		cpMock = AndroidMock.createStrictMock(CommunicationPartner.class,
//											  socket,
//											  listener);
//		
//		// Set up mock factory:
//		AndroidMock.expect(factoryMock.createCommuncationPartner(AndroidMock.isA(Socket.class),
//															     AndroidMock.isA(CommunicationPartnerListener.class)))
//			       .andReturn(cpMock);
//		
//		cpMock.startMessageListener();
	}	
}
