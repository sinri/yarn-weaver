package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.YarnSiteV1Request;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.ResourcesUsed;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/**
 * With the New Application API, you can obtain an application-id
 * which can then be used as part of the Cluster Submit Applications API to submit applications.
 * The response also includes the maximum resource capabilities available on the cluster.
 * NOTE: This feature is currently in the alpha stage and may change in the future.
 */
public class ClusterNewApplicationRequest extends YarnSiteV1Request<ClusterNewApplicationRequest.NewApplicationMeta> {


    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterNewApplicationRequest(String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/apps/new-application";
    }

    @Override
    public Future<NewApplicationMeta> request() {
        return requestWithPost(new JsonObject()).compose(jsonObject -> {
            return Future.succeededFuture(new NewApplicationMeta(jsonObject));
        });
    }

    public static class NewApplicationMeta extends YarnEntity {

        public NewApplicationMeta(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * @return The newly created application id
         */
        public String applicationId() {
            return readString("application-id");
        }

        /**
         * Contains:
         * memory	int	The maximum memory available for a container
         * vCores	int	The maximum number of cores available for a container
         *
         * @return The maximum resource capabilities available on this cluster
         */
        public ResourcesUsed maximumResourceCapabilities() {
            return new ResourcesUsed(readJsonObject("maximum-resource-capabilities"));
        }
    }
}
