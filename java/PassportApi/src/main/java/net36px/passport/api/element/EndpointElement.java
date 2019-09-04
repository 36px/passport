package net36px.passport.api.element;

public class EndpointElement {

	private String latestVersion;
	private String runtimeVersion;
	private String apiVersion;
	private String usingVersion;

	public String getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(String latestVersion) {
		this.latestVersion = latestVersion;
	}

	public String getRuntimeVersion() {
		return runtimeVersion;
	}

	public void setRuntimeVersion(String runtimeVersion) {
		this.runtimeVersion = runtimeVersion;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getUsingVersion() {
		return usingVersion;
	}

	public void setUsingVersion(String usingVersion) {
		this.usingVersion = usingVersion;
	}

}
