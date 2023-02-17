package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.nodes;

import io.github.sinri.keel.helper.KeelHelpers;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.YarnSiteV1Request;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.node.Node;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.node.NodeState;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * With the Nodes API, you can obtain a collection of resources, each of which represents a node.
 * When you run a GET operation on this resource, you obtain a collection of Node Objects.
 */
public class ClusterNodesRequest extends YarnSiteV1Request<ClusterNodesRequest.ClusterNodes> {

    private @Nullable Collection<NodeState> states;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterNodesRequest(String serviceAddress) {
        super(serviceAddress);
    }

    public Collection<NodeState> getStates() {
        return states;
    }

    public ClusterNodesRequest setStates(Collection<NodeState> states) {
        this.states = states;
        return this;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/nodes";
    }

    @Override
    protected Future<ClusterNodes> request() {
        @Nullable Map<String, String> map = null;
        if (this.states != null) {
            map = new HashMap<>();
            map.put("states", KeelHelpers.stringHelper().joinStringArray(this.states, ","));
        }
        return requestWithGet(map).compose(jsonObject -> {
            return Future.succeededFuture(new ClusterNodes(jsonObject.getJsonObject("nodes")));
        });
    }

    public static class ClusterNodes extends YarnEntity {

        public ClusterNodes(JsonObject jsonObject) {
            super(jsonObject);
        }

        public List<Node> nodes() {
            List<Node> list = new ArrayList<>();
            var x = this.readJsonObjectArray("node");
            if (x != null) {
                x.forEach(item -> {
                    list.add(new Node(item));
                });
            }
            return list;
        }
    }
}
