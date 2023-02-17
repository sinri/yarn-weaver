package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.DelegationToken;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.YarnSiteV1Request;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public class ClusterDelegationTokenRenewRequest extends YarnSiteV1Request<ClusterDelegationTokenRenewRequest.DelegationTokenRenewResult> {

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterDelegationTokenRenewRequest(String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/delegation-token/expiration";
    }

    @Override
    public Future<DelegationTokenRenewResult> request() {
        return requestWithPost(new JsonObject())
                .compose(jsonObject -> {
                    return Future.succeededFuture(new DelegationTokenRenewResult(jsonObject));
                });
    }

    public static class DelegationTokenRenewResult extends YarnEntity {

        public DelegationTokenRenewResult(JsonObject jsonObject) {
            super(jsonObject);
        }

        public Long expirationTime() {
            return readLong("expiration-time");
        }
    }
}
