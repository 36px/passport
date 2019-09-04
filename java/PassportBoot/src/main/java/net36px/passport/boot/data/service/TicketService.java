package net36px.passport.boot.data.service;

import net36px.passport.boot.ticket.Ticket;
import net36px.passport.boot.ticket.TicketInfo;

public interface TicketService {

	Ticket open(TicketInfo info);

	Ticket get(String id);

}
