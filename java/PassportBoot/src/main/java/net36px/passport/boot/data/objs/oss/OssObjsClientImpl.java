package net36px.passport.boot.data.objs.oss;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;

import net36px.passport.boot.data.objs.ObjsClient;
import net36px.passport.boot.data.objs.ObjsSession;
import net36px.passport.boot.utils.IOTools;

final class OssObjsClientImpl implements ObjsClient, Closeable, OssObjsSessionContext {

	private OSS ossClient;
	private String bucketName;

	public OssObjsClientImpl(OSS oss_client, String bucket_name) {
		this.ossClient = oss_client;
		this.bucketName = bucket_name;
	}

	@Override
	public ObjsSession openSession(String path) {
		return new OssObjsSessionImpl(this, path);
	}

	@Override
	public void close() throws IOException {
		if (this.ossClient == null) {
			return;
		}
		this.ossClient.shutdown();
		this.ossClient = null;
	}

	@Override
	public byte[] load(String path, boolean required) {

		InputStream in = null;
		Exception err = null;
		byte[] data = null;

		try {
			OSSObject oss_object = this.ossClient.getObject(this.bucketName, path);
			in = oss_object.getObjectContent();
			if (in != null) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				IOTools.pump(in, out, new byte[1024 * 4]);
				data = out.toByteArray();
			}
		} catch (Exception e) {
			err = e;
		} finally {
			IOTools.close(in);
		}

		if (required && (data == null)) {
			if (err != null) {
				throw new RuntimeException(err);
			} else {
				throw new RuntimeException("no object: " + path);
			}
		}

		return data;
	}

	@Override
	public void save(byte[] data, String path) {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		this.ossClient.putObject(this.bucketName, path, in);
	}

}
