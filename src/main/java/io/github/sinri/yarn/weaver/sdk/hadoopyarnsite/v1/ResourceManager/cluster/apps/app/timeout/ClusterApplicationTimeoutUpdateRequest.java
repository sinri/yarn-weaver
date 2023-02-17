package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.apps.app.timeout;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.apps.app.ClusterCertainAppsRequestForCertainApp;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.application.ApplicationTimeout;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.application.ApplicationTimeoutType;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Update timeout of an application for given timeout type.
 */
public class ClusterApplicationTimeoutUpdateRequest extends ClusterCertainAppsRequestForCertainApp<ApplicationTimeout> {
    private final @NotNull ApplicationTimeoutType type;
    private final long expiryTime;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     * @param appId
     * @param type           Timeout type. Valid values are the members of the ApplicationTimeoutType enum. LIFETIME is currently the only valid value.
     * @param expiryTime     Time at which the application will expire in ISO8601 yyyy-MM-dd’T’HH:mm:ss.SSSZ format.
     */
    public ClusterApplicationTimeoutUpdateRequest(
            String serviceAddress,
            @NotNull String appId,
            @NotNull ApplicationTimeoutType type,
            long expiryTime
    ) {
        super(serviceAddress, appId);
        this.type = type;
        this.expiryTime = expiryTime;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/apps/" + getAppId() + "/timeout";
    }

    @Override
    protected Future<ApplicationTimeout> request() {
        return requestWithPut(new JsonObject()
                        .put("timeout", new JsonObject()
                                .put("type", type.name())
                                .put("expiryTime", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                                        .format(new Date(expiryTime))
                                )
                        ),
                200
        ).compose(jsonObject -> {
            JsonObject timeout = Objects.requireNonNull(jsonObject).getJsonObject("timeout");
            return Future.succeededFuture(new ApplicationTimeout(timeout));
        });
    }
}
