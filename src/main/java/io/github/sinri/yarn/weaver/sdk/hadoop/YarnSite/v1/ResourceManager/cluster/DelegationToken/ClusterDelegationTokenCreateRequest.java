package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.DelegationToken;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.YarnSiteV1Request;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.cluster.DelegationToken;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/**
 * The Delegation Tokens API can be used to create, renew and cancel YARN ResourceManager delegation tokens.
 * All delegation token requests must be carried out on a Kerberos authenticated connection(using SPNEGO).
 * Carrying out operations on a non-kerberos connection will result in a FORBIDDEN response.
 * In case of renewing a token, only the renewer specified when creating the token can renew the token.
 * Other users(including the owner) are forbidden from renewing tokens.
 * It should be noted that when cancelling or renewing a token, the token to be cancelled or renewed is specified by setting a header.
 * <p>
 * This feature is currently in the alpha stage and may change in the future.
 * <p>
 * LATER TO USE TOKEN AS HEADER `X-Hadoop-Delegation-Token: MgASY2xpZW50QEVYQU1QTEUuQ09NDHRlc3QtcmVuZXdlcgCKAUbjqcHHigFHB7ZFxwQCFKWD3znCkDSy6SQIjRCLDydxbxvgE1JNX0RFTEVHQVRJT05fVE9LRU4A`
 */
public class ClusterDelegationTokenCreateRequest extends YarnSiteV1Request<DelegationToken> {
    private final String renewer;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterDelegationTokenCreateRequest(String serviceAddress, String renewer) {
        super(serviceAddress);
        this.renewer = renewer;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/delegation-token";
    }

    @Override
    public Future<DelegationToken> request() {
        return requestWithPost(new JsonObject().put("renewer", renewer))
                .compose(jsonObject -> {
                    return Future.succeededFuture(new DelegationToken(jsonObject));
                });
    }

}
