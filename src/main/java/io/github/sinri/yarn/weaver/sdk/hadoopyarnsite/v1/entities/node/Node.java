package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.node;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.ResourcesUsed;
import io.vertx.core.json.JsonObject;

public class Node extends YarnEntity {
    public Node(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * The rack location of this node
     */
    public String rack() {
        return readString("rack");
    }

    /**
     * State of the node - valid values are: NEW, RUNNING, UNHEALTHY, DECOMMISSIONING, DECOMMISSIONED, LOST, REBOOTED, SHUTDOWN
     */
    public String state() {
        return readString("state");
    }

    /**
     * The node id
     */
    public String id() {
        return readString("id");
    }

    /**
     * The host name of the node
     */
    public String nodeHostName() {
        return readString("nodeHostName");
    }

    /**
     * The nodes HTTP address
     */
    public String nodeHTTPAddress() {
        return readString("nodeHTTPAddress");
    }

    /**
     * The last time the node reported its health (in ms since epoch)
     */
    public Long lastHealthUpdate() {
        return readLong("lastHealthUpdate");
    }

    /**
     * Version of hadoop running on node
     */
    public String version() {
        return readString("version");
    }

    /**
     * A detailed health report
     */
    public String healthReport() {
        return readString("healthReport");
    }

    /**
     * The total number of containers currently running on the node
     */
    public Integer numContainers() {
        return readInteger("numContainers");
    }

    /**
     * The total amount of memory currently used on the node (in MB)
     */
    public Long usedMemoryMB() {
        return readLong("usedMemoryMB");
    }

    /**
     * The total amount of memory currently available on the node (in MB)
     */
    public Long availMemoryMB() {
        return readLong("availMemoryMB");
    }

    /**
     * The total number of vCores currently used on the node
     */
    public Long usedVirtualCores() {
        return readLong("usedVirtualCores");
    }

    /**
     * The total number of vCores available on the node
     */
    public Long availableVirtualCores() {
        return readLong("availableVirtualCores");
    }

    /**
     * Resource utilization on the node
     */
    public NodeResourceUtilization resourceUtilization() {
        return new NodeResourceUtilization(readJsonObject("resourceUtilization"));
    }

    /**
     * Resources on the node
     */
    public ResourcesUsed totalResource() {
        return new ResourcesUsed(readJsonObject("totalResource"));
    }
}
