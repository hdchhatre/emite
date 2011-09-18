/*
 * ((e)) emite: A pure Google Web Toolkit XMPP library
 * Copyright (c) 2008-2011 The Emite development team
 * 
 * This file is part of Emite.
 *
 * Emite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * Emite is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with Emite.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.calclab.emite.xep.storage.client;

import com.calclab.emite.core.client.session.IQCallback;
import com.calclab.emite.core.client.session.XmppSession;
import com.calclab.emite.core.client.stanzas.IQ;
import com.calclab.emite.core.client.stanzas.IQ.Type;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Implements http://xmpp.org/extensions/xep-0049.html
 */
@Singleton
public class PrivateStorageManagerImpl implements PrivateStorageManager {
	private static final String XMLNS = "jabber:iq:private";
	private static final String ID = "priv";

	private final XmppSession session;

	@Inject
	public PrivateStorageManagerImpl(final XmppSession session) {
		this.session = session;
	}

	public void retrieve(final SimpleStorageData data, final PrivateStorageResponseEvent.Handler handler) {
		final IQ iq = new IQ(Type.get);
		iq.addQuery(XMLNS).addChild(data);

		session.sendIQ(ID, iq, new IQCallback() {
			@Override
			public void onIQ(final IQ iq) {
				if (IQ.isSuccess(iq)) {
					handler.onStorageResponse(new PrivateStorageResponseEvent(iq));
				}
			}
		});
	}

	public void store(final SimpleStorageData data, final PrivateStorageResponseEvent.Handler handler) {
		final IQ iq = new IQ(Type.set);
		iq.addQuery(XMLNS).addChild(data);

		session.sendIQ(ID, iq, new IQCallback() {
			@Override
			public void onIQ(final IQ iq) {
				if (IQ.isSuccess(iq)) {
					handler.onStorageResponse(new PrivateStorageResponseEvent(iq));
				}
			}
		});
	}
}
