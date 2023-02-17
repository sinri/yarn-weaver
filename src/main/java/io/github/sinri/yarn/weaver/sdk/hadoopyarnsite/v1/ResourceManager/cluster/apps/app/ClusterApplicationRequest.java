package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.apps.app;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.application.ClusterApplication;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;

/**
 * An application resource contains information about a particular application that was submitted to a cluster.
 */
public class ClusterApplicationRequest extends ClusterCertainAppsRequestForCertainApp<ClusterApplication> {


    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     * @param appId
     */
    public ClusterApplicationRequest(String serviceAddress, @NotNull String appId) {
        super(serviceAddress, appId);
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/apps/" + getAppId();
    }

    @Override
    protected Future<ClusterApplication> request() {
        return requestWithGet().compose(jsonObject -> {
            JsonObject app = jsonObject.getJsonObject("app");
            return Future.succeededFuture(new ClusterApplication(app));
        });
    }
}
