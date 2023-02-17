package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.apps.app.timeout;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.apps.app.ClusterCertainAppsRequestForCertainApp;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.application.ApplicationTimeout;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.application.ApplicationTimeoutType;
import io.vertx.core.Future;
import org.jetbrains.annotations.NotNull;

public class ClusterApplicationTimeoutForOneTypeRequest extends ClusterCertainAppsRequestForCertainApp<ApplicationTimeout> {
    private final @NotNull ApplicationTimeoutType type;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     * @param appId
     */
    public ClusterApplicationTimeoutForOneTypeRequest(String serviceAddress, @NotNull String appId, @NotNull ApplicationTimeoutType type) {
        super(serviceAddress, appId);
        this.type = type;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/apps/" + getAppId() + "/timeouts/" + type.name();
    }

    @Override
    protected Future<ApplicationTimeout> request() {
        return requestWithGet().compose(jsonObject -> {
            return Future.succeededFuture(new ApplicationTimeout(jsonObject.getJsonObject("timeout")));
        });
    }
}
