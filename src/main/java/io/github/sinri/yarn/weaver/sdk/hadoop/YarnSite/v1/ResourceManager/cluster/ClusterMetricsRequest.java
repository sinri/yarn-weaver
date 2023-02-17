package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.YarnSiteV1Request;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.cluster.ClusterMetrics;
import io.vertx.core.Future;

/**
 * The cluster metrics resource provides some overall metrics about the cluster.
 * More detailed metrics should be retrieved from the jmx interface.
 */
public class ClusterMetricsRequest extends YarnSiteV1Request<ClusterMetrics> {
    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterMetricsRequest(String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/metrics";
    }

    @Override
    public Future<ClusterMetrics> request() {
        return this.requestWithGet().compose(jsonObject -> {
            ClusterMetrics clusterMetrics = new ClusterMetrics(jsonObject.getJsonObject("clusterMetrics"));
            return Future.succeededFuture(clusterMetrics);
        });
    }


}
