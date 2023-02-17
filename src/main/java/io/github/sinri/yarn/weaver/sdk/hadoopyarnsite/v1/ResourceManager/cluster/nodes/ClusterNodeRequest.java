package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.nodes;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.YarnSiteV1Request;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.node.Node;
import io.vertx.core.Future;

import java.util.Objects;

/**
 * A node resource contains information about a node in the cluster.
 */
public class ClusterNodeRequest extends YarnSiteV1Request<Node> {
    private String nodeId;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterNodeRequest(String serviceAddress) {
        super(serviceAddress);
    }

    public String getNodeId() {
        return nodeId;
    }

    public ClusterNodeRequest setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/nodes/" + Objects.requireNonNull(nodeId);
    }

    @Override
    protected Future<Node> request() {
        return requestWithGet().compose(jsonObject -> {
            return Future.succeededFuture(new Node(jsonObject.getJsonObject("node")));
        });
    }
}
