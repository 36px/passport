package net36px.passport.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net36px.passport.boot.data.objs.ObjsClient;
import net36px.passport.boot.data.objs.oss.OssObjsClientBean;

@Configuration

public class ConfigDataObjs {

	@Bean(destroyMethod = "destroy")
	public ObjsClient objs_client() {
		return new OssObjsClientBean();
	}

}
