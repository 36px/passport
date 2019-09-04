package net36px.passport.app.net;

import android.view.textclassifier.TextLinks;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import net36px.passport.app.context.ClientContext;
import net36px.passport.app.utils.IOTools;
import net36px.passport.app.utils.KeyValueMap;
import net36px.passport.app.utils.collection.PropertiesKVM;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkRestClient implements RestClient {

    private final OkHttpClient client;
    private final ClientContext context;

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public OkRestClient(ClientContext context) {
        this.client = new OkHttpClient();
        this.context = context;
    }

    @Override
    public <T> T get(String type, String id, Map<String, String> query, Class<T> doc_type) {
        String url = this.makeRestUrl(type, id, query);
        Request request = (new Request.Builder()).get().url(url).build();
        return (T) this.execute(request, doc_type);
    }

    @Override
    public <T> T post(String type, String id, T doc) {
        RequestBody request_body = this.makeRequestBody(doc);
        String url = this.makeRestUrl(type, id, null);
        Request request = (new Request.Builder()).post(request_body).url(url).build();
        return (T) this.execute(request, doc.getClass());
    }

    @Override
    public <T> T put(String type, String id, T doc) {
        RequestBody request_body = this.makeRequestBody(doc);
        String url = this.makeRestUrl(type, id, null);
        Request request = (new Request.Builder()).put(request_body).url(url).build();
        return (T) this.execute(request, doc.getClass());
    }

    @Override
    public <T> T delete(String type, String id, Class<T> doc_type) {
        String url = this.makeRestUrl(type, id, null);
        Request request = (new Request.Builder()).delete().url(url).build();
        return (T) this.execute(request, doc_type);
    }

    private String makeRestUrl(String type, String id, Map<String, String> query) {
        StringBuilder builder = new StringBuilder();
        String service_base_url = this.getRestServiceBaseUrl();
        builder.append(service_base_url);
        // type
        if (service_base_url.endsWith("/")) {
            builder.append(type);
        } else {
            builder.append('/').append(type);
        }
        // id
        if (id != null) {
            builder.append('/').append(id);
        }
        // query
        if (query != null) {
            int index = 0;
            Set<Map.Entry<String, String>> kv_list = query.entrySet();
            for (Map.Entry<String, String> kv : kv_list) {
                final int i = (index++);
                builder.append((i == 0) ? '?' : '&');
                builder.append(kv.getKey());
                builder.append('=');
                builder.append(kv.getValue());
            }
        }
        return builder.toString();
    }

    private String getRestServiceBaseUrl() {
        URI uri = context.getRestServiceURI();
        if (uri == null) {
            uri = this.loadRestServiceBaseUri();
            context.setRestServiceURI(uri);
        }
        return uri.toString();
    }

    private URI loadRestServiceBaseUri() {
        Reader input = null;
        try {
            String online_config_url = context.getConfiguration().getOnlineConfigUrl().toString();
            Request request = (new Request.Builder()).get().url(online_config_url).build();
            Response response = this.client.newCall(request).execute();
            if (response.code() != 200) {
                String url = request.url().toString();
                throw new HttpException(response.code(), response.message(), request.method(), URI.create(url));
            }
            input = response.body().charStream();
            Properties props = new Properties();
            props.load(input);
            KeyValueMap kvm = PropertiesKVM.create(props);
            String url = kvm.get("service.passport.uri");
            return URI.create(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOTools.close(input);
        }
    }

    private Object execute(Request request, Class<?> doc_type) {
        try {
            Response response = this.client.newCall(request).execute();
            int status_code = response.code();
            if (status_code != 200) {
                String status_text = response.message();
                URI uri = request.url().uri();
                throw new HttpException(status_code, status_text, request.method(), uri);
            }
            ResponseBody body = response.body();
            MediaType media_type = body.contentType();
            if (!this.isTypeOfJson(media_type)) {
                throw new RestException("bad body type: " + media_type);
            }
            String json = body.string();
            Gson gson = new Gson();
            return gson.fromJson(json, doc_type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isTypeOfJson(MediaType media_type) {
        String type1 = media_type.type();
        String type2 = media_type.subtype();
        return "application/json".equals(type1 + "/" + type2);
    }

    private <T> RequestBody makeRequestBody(T doc) {
        Gson gson = new Gson();
        String json = gson.toJson(doc);
        return RequestBody.create(json, JSON);
    }

}
