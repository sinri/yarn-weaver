package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.app;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * With the application priority API,
 * you can query the priority of a submitted app
 * as well update priority of a running or accepted app
 * using a PUT request specifying the target priority.
 * To perform the PUT operation, authentication has to be setup for the RM web services.
 * In addition, you must be authorized to update the app priority.
 * Currently, you can only update the app priority if you’re using the Capacity scheduler.
 * <p>
 * Please note that in order to update priority of an app,
 * you must have an authentication filter setup for the HTTP interface.
 * The functionality requires that a username is set in the HttpServletRequest.
 * If no filter is setup, the response will be an “UNAUTHORIZED” response.
 * <p>
 * This feature is currently in the alpha stage and may change in the future.
 */
public class ClusterApplicationPriorityRequest extends ClusterCertainAppsRequestForCertainApp<ClusterApplicationPriorityRequest.ApplicationPriorityResult> {
    private @Nullable Integer priorityToBe = null;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     * @param appId
     */
    public ClusterApplicationPriorityRequest(String serviceAddress, @NotNull String appId) {
        super(serviceAddress, appId);
    }


    public @Nullable Integer getPriorityToBe() {
        return priorityToBe;
    }


    public ClusterApplicationPriorityRequest setPriorityToBe(@Nullable Integer priorityToBe) {
        this.priorityToBe = priorityToBe;
        return this;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/apps/" + Objects.requireNonNull(getAppId()) + "/priority";
    }

    @Override
    public Future<ApplicationPriorityResult> request() {
        return Future.succeededFuture()
                .compose(v -> {
                    if (this.priorityToBe != null) {
                        return this.requestWithPut(new JsonObject().put("priority", this.priorityToBe), 200);
                    } else {
                        return this.requestWithGet();
                    }
                })
                .compose(jsonObject -> {
                    return Future.succeededFuture(new ApplicationPriorityResult(jsonObject));
                });
    }

    public static class ApplicationPriorityResult extends YarnEntity {

        public ApplicationPriorityResult(JsonObject jsonObject) {
            super(jsonObject);
        }

        public Integer priority() {
            return this.readInteger("priority");
        }
    }
}
