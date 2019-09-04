package net36px.passport.boot.data.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import net36px.passport.boot.data.entity.Setting;
import net36px.passport.boot.data.repository.SettingRepository;
import net36px.passport.boot.data.service.SettingService;
import net36px.passport.boot.utils.Settings;

public class SettingServiceImpl implements SettingService {

	@Autowired
	private SettingRepository settingRepo;

	private Settings cache;

	@Override
	public Settings getSettings() {
		Settings settings = this.cache;
		if (settings == null) {
			settings = this.loadSettings();
			this.cache = settings;
		}
		return settings;
	}

	@Override
	public String getValue(String name) {
		Setting item = this.settingRepo.findById(name).get();
		return item.getValue();
	}

	@Override
	public void setValue(String name, String value) {
		Setting setting = new Setting();
		setting.setName(name);
		setting.setValue(value);
		setting = this.settingRepo.save(setting);
		this.cache = null;
	}

	private Settings loadSettings() {

		Settings settings = new Settings();
		Map<String, String> table = new HashMap<>();
		Iterator<Setting> all = this.settingRepo.findAll().iterator();

		for (; all.hasNext();) {
			Setting item = all.next();
			table.put(item.getName(), item.getValue());
		}

		settings.setMailSenderAddress(this.getValue(Settings.Name.MAIL_SENDER_ADDRESS, table, null));
		settings.setMailSenderPassword(this.getValue(Settings.Name.MAIL_SENDER_PASSWORD, table, null));
		settings.setMailSenderUser(this.getValue(Settings.Name.MAIL_SENDER_USER, table, null));
		settings.setMailSmtpHost(this.getValue(Settings.Name.MAIL_SMTP_HOST, table, null));

		settings.setOssAccessKeyId(this.getValue(Settings.Name.OSS_ACCESS_KEY_ID, table, null));
		settings.setOssAccessKeySecret(this.getValue(Settings.Name.OSS_ACCESS_KEY_SECRET, table, null));
		settings.setOssBucketName(this.getValue(Settings.Name.OSS_BUCKET_NAME, table, null));
		settings.setOssEndpoint(this.getValue(Settings.Name.OSS_ENDPOINT, table, null));

		return settings;
	}

	private String getValue(String name, Map<String, String> table, String value_default) {
		String value = table.get(name);
		if (value == null) {
			value = value_default;
		}
		return value;
	}

}
