package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.nodes;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.YarnSiteV1Request;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.ResourcesUsed;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.Objects;

/**
 * Update the total resources in a node.
 */
public class ClusterNodeUpdateResourceRequest extends YarnSiteV1Request<ResourcesUsed> {
    private String nodeId;

    /**
     * The total amount of memory to set on the node (in MB)
     */
    private long memory;
    /**
     * The total number of vCores to set on the node
     */
    private long vCores;
    /**
     * The timeout to preempt containers
     */
    private long overCommitTimeout;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterNodeUpdateResourceRequest(String serviceAddress) {
        super(serviceAddress);
    }

    public long getMemory() {
        return memory;
    }

    public ClusterNodeUpdateResourceRequest setMemory(long memory) {
        this.memory = memory;
        return this;
    }

    public long getOverCommitTimeout() {
        return overCommitTimeout;
    }

    public ClusterNodeUpdateResourceRequest setOverCommitTimeout(long overCommitTimeout) {
        this.overCommitTimeout = overCommitTimeout;
        return this;
    }

    public long getVCores() {
        return vCores;
    }

    public ClusterNodeUpdateResourceRequest setVCores(long vCores) {
        this.vCores = vCores;
        return this;
    }

    public String getNodeId() {
        return nodeId;
    }

    public ClusterNodeUpdateResourceRequest setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/nodes/" + Objects.requireNonNull(nodeId) + "/resource";
    }

    @Override
    public Future<ResourcesUsed> request() {
        return requestWithPost(new JsonObject()
                .put("resource", new JsonObject()
                        .put("memory", memory)
                        .put("vCores", vCores)
                )
                .put("overCommitTimeout", overCommitTimeout)
        ).compose(jsonObject -> {
            return Future.succeededFuture(new ResourcesUsed(jsonObject.getJsonObject("resourceInfo")));
        });
    }
}
