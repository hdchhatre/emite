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

package com.calclab.emite.core.client.events;

import com.calclab.emite.core.client.stanzas.Message;
import com.google.web.bindery.event.shared.Event;

public class BeforeMessageSentEvent extends Event<BeforeMessageSentEvent.Handler> {
	
	public interface Handler {
		void onBeforeMessageSent(BeforeMessageSentEvent event);
	}

	public static final Type<Handler> TYPE = new Type<Handler>();
	
	private final Message message;

	public BeforeMessageSentEvent(final Message message) {
		this.message = message;
	}
	
	public Message getMessage() {
		return message;
	}
	
	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(final Handler handler) {
		handler.onBeforeMessageSent(this);
	}

	@Override
	public String toDebugString() {
		return super.toDebugString() + message;
	}

}
