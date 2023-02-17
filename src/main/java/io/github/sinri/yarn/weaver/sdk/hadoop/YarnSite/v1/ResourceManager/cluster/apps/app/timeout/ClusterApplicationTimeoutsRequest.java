package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.app.timeout;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.app.ClusterCertainAppsRequestForCertainApp;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.application.ApplicationTimeout;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Cluster Application Timeouts API can be used to get all configured timeouts of an application.
 * When you run a GET operation on this resource, a collection of timeout objects is returned.
 * Each timeout object is composed of a timeout type, expiry-time and remaining time in seconds.
 */
public class ClusterApplicationTimeoutsRequest extends ClusterCertainAppsRequestForCertainApp<ClusterApplicationTimeoutsRequest.ApplicationTimeouts> {
    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     * @param appId
     */
    public ClusterApplicationTimeoutsRequest(String serviceAddress, @NotNull String appId) {
        super(serviceAddress, appId);
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/apps/" + apiUrl() + "/timeouts";
    }

    @Override
    public Future<ApplicationTimeouts> request() {
        return requestWithGet().compose(jsonObject -> {
            return Future.succeededFuture(new ApplicationTimeouts(jsonObject.getJsonObject("timeouts")));
        });
    }

    public static class ApplicationTimeouts extends YarnEntity {


        public ApplicationTimeouts(JsonObject jsonObject) {
            super(jsonObject);
        }

        public List<ApplicationTimeout> timeouts() {
            List<ApplicationTimeout> list = new ArrayList<>();
            List<JsonObject> items = readJsonObjectArray("timeout");
            if (items != null) {
                items.forEach(item -> {
                    list.add(new ApplicationTimeout(item));
                });
            }
            return list;
        }

    }

}
