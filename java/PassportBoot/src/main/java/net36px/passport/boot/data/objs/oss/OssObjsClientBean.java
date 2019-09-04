package net36px.passport.boot.data.objs.oss;

import org.springframework.beans.factory.annotation.Autowired;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import net36px.passport.boot.data.objs.ObjsClient;
import net36px.passport.boot.data.objs.ObjsSession;
import net36px.passport.boot.data.service.SettingService;
import net36px.passport.boot.utils.IOTools;
import net36px.passport.boot.utils.Settings;

public class OssObjsClientBean implements ObjsClient {

	@Autowired
	private SettingService settingService;

	private OssObjsClientImpl implement;
	private OssObjsClientImpl implement_closeable;

	@Override
	public ObjsSession openSession(String path) {
		ObjsClient impl = this.getImpl();
		return impl.openSession(path);
	}

	private ObjsClient getImpl() {
		ObjsClient impl = this.implement;
		if (impl == null) {
			impl = this.createImpl();
		}
		return impl;
	}

	public void destroy() {
		IOTools.close(this.implement_closeable);
		this.implement_closeable = null;
	}

	private synchronized ObjsClient createImpl() {

		OssObjsClientImpl impl = this.implement;

		if (impl != null) {
			return impl;
		}

		Settings settings = this.settingService.getSettings();
		String endpoint = settings.getOssEndpoint();
		String accessKeyId = settings.getOssAccessKeyId();
		String accessKeySecret = settings.getOssAccessKeySecret();
		String bucketName = settings.getOssBucketName();

		OSSClientBuilder builder = new OSSClientBuilder();
		OSS oss_client = builder.build(endpoint, accessKeyId, accessKeySecret);
		impl = new OssObjsClientImpl(oss_client, bucketName);
		this.implement = impl;
		this.implement_closeable = impl;
		return impl;
	}

}
