package net36px.passport.boot.ticket;

public class TicketInfo {

	private Runnable runnable;
	private int retry;
	private long timeFrom;
	private long timeTo;
	private boolean oneshot;

	public TicketInfo() {
	}

	public Runnable getRunnable() {
		return runnable;
	}

	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	public long getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(long timeFrom) {
		this.timeFrom = timeFrom;
	}

	public long getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(long timeTo) {
		this.timeTo = timeTo;
	}

	public boolean isOneshot() {
		return oneshot;
	}

	public void setOneshot(boolean oneshot) {
		this.oneshot = oneshot;
	}

}
