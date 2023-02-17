package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.YarnSiteV1Request;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.cluster.ClusterInfo;
import io.vertx.core.Future;

/**
 * The cluster information resource provides overall information about the cluster.
 */
public class ClusterInformationRequest extends YarnSiteV1Request<ClusterInfo> {
    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterInformationRequest(String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * Both of the following URI’s give you the cluster information.
     *
     * <p>
     * `<a href="http://rm-http-address:port/ws/v1/cluster">...</a>`
     * `<a href="http://rm-http-address:port/ws/v1/cluster/info">...</a>`
     * </p>
     *
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/info";
    }

    @Override
    public Future<ClusterInfo> request() {
        return this.requestWithGet()
                .compose(jsonObject -> {
                    ClusterInfo clusterInfo = new ClusterInfo(jsonObject.getJsonObject("clusterInfo"));
                    return Future.succeededFuture(clusterInfo);
                });
    }

}
