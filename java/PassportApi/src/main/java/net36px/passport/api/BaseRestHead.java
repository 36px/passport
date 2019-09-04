package net36px.passport.api;

import net36px.passport.api.element.EndpointElement;
import net36px.passport.api.element.SignatureElement;

public class BaseRestHead {

	private long account; // account - id
	private String device; // device - id
	private SignatureElement signature;
	private EndpointElement server;
	private EndpointElement client;

	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public SignatureElement getSignature() {
		return signature;
	}

	public void setSignature(SignatureElement signature) {
		this.signature = signature;
	}

	public EndpointElement getServer() {
		return server;
	}

	public void setServer(EndpointElement server) {
		this.server = server;
	}

	public EndpointElement getClient() {
		return client;
	}

	public void setClient(EndpointElement client) {
		this.client = client;
	}

}
