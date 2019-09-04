package net36px.passport.app.data.repository;

import java.util.Map;

public interface CredentialRepository {

    Map<String, String> loadCredentialTable();

    void saveCredentialTable(Map<String, String> table);

}
