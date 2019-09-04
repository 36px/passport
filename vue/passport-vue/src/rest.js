import axios from 'axios'

const profiles =
{
    debug: {
        app_api: "/app.api",
        web_api: "/web.api",
    }
};

const profile = profiles.debug;
const default_service_name = 'web_api';

function makeRestRequestUrl(param) {

    var service = param.service;
    var type = param.type;
    var id = param.id;
    var query = param.query;

    // base url by service
    if (service == null) {
        service = default_service_name;
    }
    var url = profile[service];

    // type
    url = url + '/' + type;

    // id
    if (id != null) {
        url = url + '/' + id;
    }

    // query
    if (query == null) {
        return url;
    }
    var prefix = null;
    for (var key in query) {
        var value = query[key];
        prefix = (prefix == null) ? '?' : '&';
        url = url + prefix + key + '=' + value;
    }

}

function fireError(fn, err) {
    var result = {};
    result.content = err.data;
    result.status = err.status;
    result.statusText = err.statusText;
    if (fn == null) { return; }
    fn(result);
}

function fireOK(fn, resp) {
    var result = {};
    result.content = resp.data;
    result.status = resp.status;
    result.statusText = resp.statusText;
    if (fn == null) { return; }
    fn(result);
}


export default {

    execute(param) {

        var method = param.method;
        var data = param.content;
        var url = makeRestRequestUrl(param);
        var on_ok = param.ok;
        var on_error = param.error;

        axios({
            method, url, data
        }).then(response => {
            fireOK(on_ok, response);
        }).catch(error => {
            fireError(on_error, error);
        });

    }

}