package net36px.passport.boot.utils;

public class Settings {

	public interface Name {

		String MAIL_SENDER_ADDRESS = "mail.sender.address";
		String MAIL_SENDER_USER = "mail.sender.user";
		String MAIL_SENDER_PASSWORD = "mail.sender.password";
		String MAIL_SMTP_HOST = "mail.smtp.host";

		String OSS_ENDPOINT = "oss.endpoint";
		String OSS_ACCESS_KEY_ID = "oss.accesskey.id";
		String OSS_ACCESS_KEY_SECRET = "oss.accesskey.secret";
		String OSS_BUCKET_NAME = "oss.bucket.name";

	}

	private String mailSenderAddress;
	private String mailSenderPassword;
	private String mailSenderUser;
	private String mailSmtpHost;

	private String ossEndpoint;
	private String ossAccessKeyId;
	private String ossAccessKeySecret;
	private String ossBucketName;

	public Settings() {
	}

	public String getMailSenderAddress() {
		return mailSenderAddress;
	}

	public void setMailSenderAddress(String mailSenderAddress) {
		this.mailSenderAddress = mailSenderAddress;
	}

	public String getMailSenderPassword() {
		return mailSenderPassword;
	}

	public void setMailSenderPassword(String mailSenderPassword) {
		this.mailSenderPassword = mailSenderPassword;
	}

	public String getMailSenderUser() {
		return mailSenderUser;
	}

	public void setMailSenderUser(String mailSenderUser) {
		this.mailSenderUser = mailSenderUser;
	}

	public String getMailSmtpHost() {
		return mailSmtpHost;
	}

	public void setMailSmtpHost(String mailSmtpHost) {
		this.mailSmtpHost = mailSmtpHost;
	}

	public String getOssEndpoint() {
		return ossEndpoint;
	}

	public void setOssEndpoint(String ossEndpoint) {
		this.ossEndpoint = ossEndpoint;
	}

	public String getOssAccessKeyId() {
		return ossAccessKeyId;
	}

	public void setOssAccessKeyId(String ossAccessKeyId) {
		this.ossAccessKeyId = ossAccessKeyId;
	}

	public String getOssAccessKeySecret() {
		return ossAccessKeySecret;
	}

	public void setOssAccessKeySecret(String ossAccessKeySecret) {
		this.ossAccessKeySecret = ossAccessKeySecret;
	}

	public String getOssBucketName() {
		return ossBucketName;
	}

	public void setOssBucketName(String ossBucketName) {
		this.ossBucketName = ossBucketName;
	}

}
