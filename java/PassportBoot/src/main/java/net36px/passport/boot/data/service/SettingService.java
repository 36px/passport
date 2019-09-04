package net36px.passport.boot.data.service;

import net36px.passport.boot.utils.Settings;

public interface SettingService {

	Settings getSettings();

	String getValue(String name);

	void setValue(String name, String value);

}
