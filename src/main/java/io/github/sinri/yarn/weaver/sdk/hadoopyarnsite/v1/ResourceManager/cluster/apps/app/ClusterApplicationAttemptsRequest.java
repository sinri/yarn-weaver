package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.apps.app;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.application.ApplicationAttempt;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * With the application attempts API,
 * you can obtain a collection of resources that represent an application attempt.
 * When you run a GET operation on this resource, you obtain a collection of App Attempt Objects.
 */
public class ClusterApplicationAttemptsRequest extends ClusterCertainAppsRequestForCertainApp<ClusterApplicationAttemptsRequest.AppAttempts> {

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     * @param appId
     */
    public ClusterApplicationAttemptsRequest(String serviceAddress, @NotNull String appId) {
        super(serviceAddress, appId);
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/apps/" + Objects.requireNonNull(getAppId()) + "/appattempts";
    }

    @Override
    protected Future<AppAttempts> request() {
        return requestWithGet().compose(jsonObject -> {
            JsonObject appAttempts = jsonObject.getJsonObject("appAttempts");
            return Future.succeededFuture(new AppAttempts(appAttempts));
        });
    }

    public static class AppAttempts extends YarnEntity {

        public AppAttempts(JsonObject jsonObject) {
            super(jsonObject);
        }

        public List<ApplicationAttempt> appAttempts() {
            List<ApplicationAttempt> list = new ArrayList<>();
            List<JsonObject> x = this.readJsonObjectArray("appAttempt");
            if (x != null) {
                x.forEach(item -> {
                    list.add(new ApplicationAttempt(item));
                });
            }
            return list;
        }
    }
}
