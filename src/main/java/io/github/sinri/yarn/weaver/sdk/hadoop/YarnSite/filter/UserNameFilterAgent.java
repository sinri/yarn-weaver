package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.filter;

import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpRequest;

public class UserNameFilterAgent implements YarnSiteFilterAgent {
    private final String userName;

    public UserNameFilterAgent(String userName) {
        this.userName = userName;
    }

    @Override
    public void handle(HttpRequest<Buffer> request) {
        request.addQueryParam("user.name", this.userName);
    }
}
