package net36px.passport.boot.data.repository;

import org.springframework.data.repository.CrudRepository;

import net36px.passport.boot.data.entity.Setting;

public interface SettingRepository extends CrudRepository<Setting, String> {

}
