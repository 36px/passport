package net36px.passport.boot.ticket;

import java.io.Closeable;

public interface Ticket extends Closeable {

	String getId();

	TicketInfo getInfo();

	boolean execute();

}
