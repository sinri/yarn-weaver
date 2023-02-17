package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.DelegationToken;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.YarnSiteV1Request;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public class ClusterDelegationTokenCancelRequest extends YarnSiteV1Request<ClusterDelegationTokenCancelRequest.CancelResult> {
    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterDelegationTokenCancelRequest(String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return null;
    }

    @Override
    public Future<CancelResult> request() {
        return null;
    }

    public static class CancelResult extends YarnEntity {

        public CancelResult(JsonObject jsonObject) {
            super(jsonObject);
        }
    }
}
