package net36px.passport.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration

@Import({ ConfigDataObjs.class, ConfigDataService.class, ConfigAppREST.class, ConfigWebREST.class, ConfigShiro.class })

public class PassportConfig {

}
