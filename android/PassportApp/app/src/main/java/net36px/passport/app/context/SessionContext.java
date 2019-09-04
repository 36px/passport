package net36px.passport.app.context;

import java.io.File;

import net36px.passport.app.client.PassportSession;
import net36px.passport.app.data.security.SecurityKeysHolder;
import net36px.passport.app.data.security.SecuritySpace;

public class SessionContext {

    private final ClientContext parent;

    private PassportSession session;
    private String account;
    private File userDirectory;
    private SecuritySpace securitySpace;

    private final SecurityKeysHolder userKeysHolder;
    private final RestServiceSet restServiceSet;
    private final DataServiceSet dataServiceSet;
    private final DataRepositorySet dataRepositorySet;

    public SessionContext(ClientContext cc) {
        this.parent = cc;
        this.userKeysHolder = new SecurityKeysHolder();
        this.restServiceSet = new RestServiceSet(cc.getRestServiceSet());
        this.dataServiceSet = new DataServiceSet(cc.getDataServiceSet());
        this.dataRepositorySet = new DataRepositorySet(cc.getDataRepositorySet());
    }

    public File getUserDirectory() {
        return userDirectory;
    }

    public void setUserDirectory(File userDirectory) {
        this.userDirectory = userDirectory;
    }

    public SecuritySpace getSecuritySpace() {
        return securitySpace;
    }

    public void setSecuritySpace(SecuritySpace securitySpace) {
        this.securitySpace = securitySpace;
    }

    public RestServiceSet getRestServiceSet() {
        return restServiceSet;
    }

    public DataServiceSet getDataServiceSet() {
        return dataServiceSet;
    }

    public DataRepositorySet getDataRepositorySet() {
        return dataRepositorySet;
    }

    public ClientContext getParent() {
        return parent;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public PassportSession getSession() {
        return session;
    }

    public void setSession(PassportSession session) {
        this.session = session;
    }

    public SecurityKeysHolder getUserKeysHolder() {
        return userKeysHolder;
    }


}
