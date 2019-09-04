package net36px.passport.boot.data.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import net36px.passport.boot.data.service.RandomStringService;
import net36px.passport.boot.data.service.TicketService;
import net36px.passport.boot.ticket.Ticket;
import net36px.passport.boot.ticket.TicketInfo;

public class TicketServiceImpl implements TicketService {

	@Autowired
	private RandomStringService randomStringService;

	private final Map<String, TicketCore> table;
	private long openCounterMaster;
	private long openCounterSlave;

	public TicketServiceImpl() {
		Map<String, TicketCore> tab = new HashMap<>();
		table = Collections.synchronizedMap(tab);
	}

	@Override
	public synchronized Ticket open(TicketInfo info) {

		this.openCounterMaster++;
		this.cleanup();

		TicketCore core = new TicketCore();
		core.ticket = new TicketImpl(core);
		core.info = info;
		core.id = this.generateId(core);
		core.init();
		table.put(core.id, core);
		return core.ticket;
	}

	@Override
	public Ticket get(String id) {
		TicketCore core = table.get(id);
		if (core == null) {
			return null;
		} else {
			return core.ticket;
		}
	}

	private static class TicketCore {

		public TicketInfo info;
		String id;
		Ticket ticket;
		int retryTTL;
		boolean oneshot;
		public boolean done;
		public boolean fired;

		public void init() {

			this.oneshot = info.isOneshot();
			this.retryTTL = info.getRetry();

		}

	}

	private class TicketImpl implements Ticket {

		private final TicketCore core;

		public TicketImpl(TicketCore core) {
			this.core = core;
		}

		@Override
		public void close() throws IOException {
			TicketServiceImpl.this.close(this.core);
		}

		@Override
		public String getId() {
			return this.core.id;
		}

		@Override
		public boolean execute() {

			TicketInfo info = this.core.info;
			Runnable runnable = info.getRunnable();
			long now = System.currentTimeMillis();

			if ((info.getTimeFrom() <= now) && (now <= info.getTimeTo())) {
				// continue
			} else {
				throw new RuntimeException("out of time");
			}

			if (core.retryTTL < 1) {
				throw new RuntimeException("out of time");
			}

			if (core.done && core.oneshot && core.fired) {
				throw new RuntimeException("oneshot done");
			}

			try {
				runnable.run();
				this.core.done = true;
				return true;
			} catch (Exception e) {
				this.core.retryTTL--;
			} finally {
				this.core.fired = true;
			}

			return false;
		}

		@Override
		public TicketInfo getInfo() {
			return this.core.info;
		}
	}

	private void close(TicketCore core) {
		String id = core.id;
		table.remove(id);
	}

	private String generateId(TicketCore core) {
		List<String> seed = new ArrayList<>();
		seed.add(core.info.toString());
		return this.randomStringService.nextString(64, seed);
	}

	private void cleanup() {
		long diff = Math.abs(this.openCounterMaster - this.openCounterSlave);
		if (diff < 1000) {
			return;
		} else {
			this.openCounterSlave = this.openCounterMaster;
		}
		// do cleanup
		long now = System.currentTimeMillis();
		List<String> ids = new ArrayList<>();
		Set<Entry<String, TicketCore>> all = table.entrySet();
		for (Entry<String, TicketCore> item : all) {
			if (this.isOutOfTime(item.getValue(), now)) {
				ids.add(item.getKey());
			}
		}
		for (String id : ids) {
			table.remove(id);
		}
	}

	private boolean isOutOfTime(TicketCore core, long now) {
		long t1 = core.info.getTimeFrom();
		long t2 = core.info.getTimeTo();
		return ((now < t1) || (t2 < now));
	}

}
