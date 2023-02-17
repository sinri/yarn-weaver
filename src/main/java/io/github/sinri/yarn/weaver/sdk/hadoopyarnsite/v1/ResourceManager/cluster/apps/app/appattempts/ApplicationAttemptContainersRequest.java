package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.apps.app.appattempts;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.apps.app.ClusterCertainAppsRequestForCertainApp;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.container.Container;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * With Containers for an Application Attempt API you can obtain the list of containers, which belongs to an Application Attempt.
 */
public class ApplicationAttemptContainersRequest extends ClusterCertainAppsRequestForCertainApp<ApplicationAttemptContainersRequest.Containers> {
    private String appAttemptId;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     * @param appId
     */
    public ApplicationAttemptContainersRequest(String serviceAddress, @NotNull String appId) {
        super(serviceAddress, appId);
    }

    public String getAppAttemptId() {
        return appAttemptId;
    }

    public ApplicationAttemptContainersRequest setAppAttemptId(String appAttemptId) {
        this.appAttemptId = appAttemptId;
        return this;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/apps/" + Objects.requireNonNull(getAppId()) + "/appattempts/" + Objects.requireNonNull(appAttemptId) + "/containers";
    }

    @Override
    protected Future<Containers> request() {
        return requestWithGet().compose(jsonObject -> {
            return Future.succeededFuture(new Containers(jsonObject.getJsonObject("containers")));
        });
    }

    public static class Containers extends YarnEntity {

        public Containers(JsonObject jsonObject) {
            super(jsonObject);
        }

        public List<Container> containers() {
            List<Container> list = new ArrayList<>();
            List<JsonObject> x = this.readJsonObjectArray("container");
            if (x != null) {
                x.forEach(item -> {
                    list.add(new Container(item));
                });
            }
            return list;
        }
    }
}
