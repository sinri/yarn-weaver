package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.filter;

import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpRequest;

public interface YarnSiteFilterAgent {
    void handle(HttpRequest<Buffer> request);
}
