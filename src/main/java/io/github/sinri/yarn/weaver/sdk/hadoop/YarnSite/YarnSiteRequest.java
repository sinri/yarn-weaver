package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite;

import io.github.sinri.keel.facade.Keel;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.filter.YarnSiteFilterAgent;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.uritemplate.UriTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public abstract class YarnSiteRequest<R extends YarnEntity> {
    private final String schema;
    private final String serviceAddress;
    private final String version;
    private YarnSiteFilterAgent filterAgent = null;
    private String errorResponseText = null;

    /**
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     * @param version        The version of the APIs. In this release, the version is `v1`.
     */
    public YarnSiteRequest(String serviceAddress, String version) {
        this.schema = "http";
        this.serviceAddress = serviceAddress;
        this.version = version;
    }

    public YarnSiteRequest<R> setFilterAgent(YarnSiteFilterAgent filterAgent) {
        this.filterAgent = filterAgent;
        return this;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    abstract protected String resourcePath();

    public final String apiUrl() {
        return schema + "://" + this.serviceAddress + "/ws/" + this.version + "/" + this.resourcePath();
    }

    public abstract Future<R> request();

    protected Future<JsonObject> requestWithGet() {
        return this.requestWithGet(null);
    }

    protected Future<JsonObject> requestWithGet(@Nullable Map<String, String> query) {
        WebClient webClient = WebClient.create(Keel.getVertx());
        HttpRequest<Buffer> request;
        if (query != null) {
            request = webClient
                    .getAbs(UriTemplate.of(apiUrl() + "{?query*}"))
                    .setTemplateParam("query", query);
        } else {
            request = webClient.getAbs(apiUrl());
        }
        return request.putHeader("Content-Type", "application/json")
                .send()
                .compose(bufferHttpResponse -> {
                    if (bufferHttpResponse.statusCode() != 200) {
                        this.errorResponseText = bufferHttpResponse.bodyAsString();
                        return Future.failedFuture(new YarnSiteRemoteException(bufferHttpResponse.statusCode(), bufferHttpResponse.bodyAsJsonObject()));
                    }
                    JsonObject jsonObject = bufferHttpResponse.bodyAsJsonObject();
                    return Future.succeededFuture(jsonObject);
                });
    }

    protected Future<@Nullable JsonObject> requestWithPost(@NotNull JsonObject body) {
        return this.requestWithPost(body, 200);
    }

    protected Future<@Nullable JsonObject> requestWithPut(int expectedHttpStatusCode) {
        HttpRequest<Buffer> request = WebClient.create(Keel.getVertx())
                .putAbs(apiUrl());
        if (filterAgent != null) {
            filterAgent.handle(request);
            //request.putHeader("Hadoop-YARN-RM-Delegation-Token", delegationToken);
        }
        return request.send()
                .compose(bufferHttpResponse -> {
                    if (bufferHttpResponse.statusCode() != expectedHttpStatusCode) {
                        this.errorResponseText = bufferHttpResponse.bodyAsString();
                        return Future.failedFuture(new YarnSiteRemoteException(bufferHttpResponse.statusCode(), bufferHttpResponse.bodyAsJsonObject()));
                    }
                    JsonObject jsonObject = bufferHttpResponse.bodyAsJsonObject();
                    return Future.succeededFuture(jsonObject);
                });
    }

    protected Future<@Nullable JsonObject> requestWithPut(JsonObject body, int expectedHttpStatusCode) {
        HttpRequest<Buffer> request = WebClient.create(Keel.getVertx())
                .putAbs(apiUrl());
        if (filterAgent != null) {
            filterAgent.handle(request);
//            request.putHeader("Hadoop-YARN-RM-Delegation-Token", delegationToken);
        }
        return request.sendJsonObject(body)
                .compose(bufferHttpResponse -> {
                    if (bufferHttpResponse.statusCode() != expectedHttpStatusCode) {
                        this.errorResponseText = bufferHttpResponse.bodyAsString();
                        return Future.failedFuture(new YarnSiteRemoteException(bufferHttpResponse.statusCode(), bufferHttpResponse.bodyAsJsonObject()));
                    }
                    JsonObject jsonObject = bufferHttpResponse.bodyAsJsonObject();
                    return Future.succeededFuture(jsonObject);
                });
    }

    protected Future<@Nullable JsonObject> requestWithPost(@NotNull JsonObject body, int expectedHttpStatusCode) {
        HttpRequest<Buffer> request = WebClient.create(Keel.getVertx())
                .postAbs(apiUrl());
        if (filterAgent != null) {
            filterAgent.handle(request);
//            request.putHeader("Hadoop-YARN-RM-Delegation-Token", delegationToken);
        }
        return request.sendJsonObject(body)
                .compose(bufferHttpResponse -> {
                    if (bufferHttpResponse.statusCode() != expectedHttpStatusCode) {
                        this.errorResponseText = bufferHttpResponse.bodyAsString();
                        return Future.failedFuture(new YarnSiteRemoteException(bufferHttpResponse.statusCode(), bufferHttpResponse.bodyAsJsonObject()));
                    }
                    JsonObject jsonObject = bufferHttpResponse.bodyAsJsonObject();
                    return Future.succeededFuture(jsonObject);
                });
    }

}
