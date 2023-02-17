package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.apps.app;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.application.ApplicationState;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * With the application state API,
 * you can query the state of a submitted app
 * as well kill a running app by modifying the state of a running app using a PUT request with the state set to “KILLED”.
 * To perform the PUT operation, authentication has to be setup for the RM web services.
 * In addition, you must be authorized to kill the app.
 * Currently, you can only change the state to “KILLED”;
 * an attempt to change the state to any other results in a 400 error response.
 * Examples of the unauthorized and bad request errors are below.
 * When you carry out a successful PUT, the initial response may be a 202.
 * You can confirm that the app is killed by repeating the PUT request until you get a 200,
 * querying the state using the GET method or querying for app information and checking the state.
 * In the examples below, we repeat the PUT request and get a 200 response.
 * <p>
 * Please note that in order to kill an app, you must have an authentication filter setup for the HTTP interface.
 * The functionality requires that a username is set in the HttpServletRequest.
 * If no filter is setup, the response will be an “UNAUTHORIZED” response.
 * <p>
 * This feature is currently in the alpha stage and may change in the future.
 */
public class ClusterApplicationStateRequest extends ClusterCertainAppsRequestForCertainApp<ClusterApplicationStateRequest.AppStateResult> {
    private @Nullable ApplicationState applicationStateToBe;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     * @param appId
     */
    public ClusterApplicationStateRequest(String serviceAddress, @NotNull String appId) {
        super(serviceAddress, appId);
    }

    public @Nullable ApplicationState getApplicationStateToBe() {
        return applicationStateToBe;
    }

    public ClusterApplicationStateRequest setApplicationStateToBe(@Nullable ApplicationState applicationStateToBe) {
        this.applicationStateToBe = applicationStateToBe;
        return this;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/apps/" + Objects.requireNonNull(getAppId()) + "/state";
    }

    @Override
    protected Future<AppStateResult> request() {
        return Future.succeededFuture()
                .compose(v -> {
                    if (this.applicationStateToBe == null) {
                        return this.requestWithGet();
                    } else {
                        return this.requestWithPut(new JsonObject().put("state", applicationStateToBe), 202);
                    }
                })
                .compose(jsonObject -> {
                    return Future.succeededFuture(new AppStateResult(jsonObject));
                });
    }

    public static class AppStateResult extends YarnEntity {

        public AppStateResult(JsonObject jsonObject) {
            super(jsonObject);
        }

        public ApplicationState getState() {
            return ApplicationState.valueOf(this.readString("state"));
        }
    }

}
