package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.app.appattempts;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.app.ClusterCertainAppsRequestForCertainApp;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.container.Container;
import io.vertx.core.Future;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * With Specific Container for an Application Attempt API you can obtain information about a specific container,
 * which belongs to an Application Attempt and selected by the container id.
 */
public class ApplicationAttemptContainerRequest extends ClusterCertainAppsRequestForCertainApp<Container> {
    private String appAttemptId;
    private String containerId;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     * @param appId
     */
    public ApplicationAttemptContainerRequest(String serviceAddress, @NotNull String appId) {
        super(serviceAddress, appId);
    }


    public String getAppAttemptId() {
        return appAttemptId;
    }

    public ApplicationAttemptContainerRequest setAppAttemptId(String appAttemptId) {
        this.appAttemptId = appAttemptId;
        return this;
    }

    public String getContainerId() {
        return containerId;
    }

    public ApplicationAttemptContainerRequest setContainerId(String containerId) {
        this.containerId = containerId;
        return this;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/apps/" + Objects.requireNonNull(getAppId()) + "/appattempts/" + Objects.requireNonNull(appAttemptId) + "/containers/" + Objects.requireNonNull(containerId);
    }

    @Override
    public Future<Container> request() {
        return requestWithGet().compose(jsonObject -> {
            return Future.succeededFuture(new Container(jsonObject.getJsonObject("container")));
        });
    }
}
