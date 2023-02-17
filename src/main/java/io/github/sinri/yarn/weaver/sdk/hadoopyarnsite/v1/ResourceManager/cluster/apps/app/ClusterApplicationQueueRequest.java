package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.apps.app;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * With the application queue API,
 * you can query the queue of a submitted app
 * as well move a running app to another queue using a PUT request specifying the target queue.
 * To perform the PUT operation, authentication has to be setup for the RM web services.
 * In addition, you must be authorized to move the app.
 * Currently, you can only move the app if you’re using the Capacity scheduler or the Fair scheduler.
 * <p>
 * Please note that in order to move an app,
 * you must have an authentication filter setup for the HTTP interface.
 * The functionality requires that a username is set in the HttpServletRequest.
 * If no filter is setup, the response will be an “UNAUTHORIZED” response.
 * <p>
 * This feature is currently in the alpha stage and may change in the future.
 */
public class ClusterApplicationQueueRequest extends ClusterCertainAppsRequestForCertainApp<ClusterApplicationQueueRequest.AppQueueResult> {

    private boolean toMoveAppToQueue;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     * @param appId
     */
    public ClusterApplicationQueueRequest(String serviceAddress, @NotNull String appId) {
        super(serviceAddress, appId);
    }

    public boolean isToMoveAppToQueue() {
        return toMoveAppToQueue;
    }

    public ClusterApplicationQueueRequest setToMoveAppToQueue(boolean toMoveAppToQueue) {
        this.toMoveAppToQueue = toMoveAppToQueue;
        return this;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/apps/" + Objects.requireNonNull(getAppId()) + "/queue";
    }

    @Override
    protected Future<AppQueueResult> request() {
        return Future.succeededFuture()
                .compose(v -> {
                    if (isToMoveAppToQueue()) {
                        return requestWithPut(200);
                    } else {
                        return requestWithGet();
                    }
                })
                .compose(jsonObject -> {
                    return Future.succeededFuture(new AppQueueResult(jsonObject));
                });
    }

    public static class AppQueueResult extends YarnEntity {

        public AppQueueResult(JsonObject jsonObject) {
            super(jsonObject);
        }

        public String getQueue() {
            return this.readString("queue");
        }
    }
}
