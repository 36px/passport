package net36px.passport.app.net;

import java.net.URI;

public class HttpException extends RuntimeException {

    public HttpException(int status_code, String status_text, String method, URI url) {
        super("HTTP " + status_code + ' ' + status_text + " while " + method + ' ' + url);
    }

}
