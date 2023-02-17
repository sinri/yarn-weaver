package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.cluster;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.vertx.core.json.JsonObject;

public class ClusterMetrics extends YarnEntity {

    public ClusterMetrics(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * The number of applications submitted
     */
    public Integer appsSubmitted() {
        return readInteger("appsSubmitted");
    }

    /**
     * The number of applications completed
     */
    public Integer appsCompleted() {
        return readInteger("appsCompleted");
    }

    /**
     * The number of applications pending
     */
    public Integer appsPending() {
        return readInteger("appsPending");
    }

    /**
     * The number of applications running
     */
    public Integer appsRunning() {
        return readInteger("appsRunning");
    }

    /**
     * The number of applications failed
     */
    public Integer appsFailed() {
        return readInteger("appsFailed");
    }

    /**
     * The number of applications killed
     */
    public Integer appsKilled() {
        return readInteger("appsKilled");
    }

    /**
     * The amount of memory reserved in MB
     */
    public Long reservedMB() {
        return readLong("reservedMB");
    }

    /**
     * The amount of memory available in MB
     */
    public Long availableMB() {
        return readLong("availableMB");
    }

    /**
     * The amount of memory allocated in MB
     */
    public Long allocatedMB() {
        return readLong("allocatedMB");
    }

    /**
     * The amount of total memory in MB
     */
    public Long totalMB() {
        return readLong("totalMB");
    }

    /**
     * The number of reserved virtual cores
     */
    public Long reservedVirtualCores() {
        return readLong("reservedVirtualCores");
    }

    /**
     * The number of available virtual cores
     */
    public Long availableVirtualCores() {
        return readLong("availableVirtualCores");
    }

    /**
     * The number of allocated virtual cores
     */
    public Long allocatedVirtualCores() {
        return readLong("allocatedVirtualCores");
    }

    /**
     * The total number of virtual cores
     */
    public Long totalVirtualCores() {
        return readLong("totalVirtualCores");
    }

    /**
     * The number of containers allocated
     */
    public Integer containersAllocated() {
        return readInteger("containersAllocated");
    }

    /**
     * The number of containers reserved
     */
    public Integer containersReserved() {
        return readInteger("containersReserved");
    }

    /**
     * The number of containers pending
     */
    public Integer containersPending() {
        return readInteger("containersPending");
    }

    /**
     * The total number of nodes
     */
    public Integer totalNodes() {
        return readInteger("totalNodes");
    }

    /**
     * The number of active nodes
     */
    public Integer activeNodes() {
        return readInteger("activeNodes");
    }

    /**
     * The number of lost nodes
     */
    public Integer lostNodes() {
        return readInteger("lostNodes");
    }

    /**
     * The number of unhealthy nodes
     */
    public Integer unhealthyNodes() {
        return readInteger("unhealthyNodes");
    }

    /**
     * The number of nodes being decommissioned
     */
    public Integer decommissioningNodes() {
        return readInteger("decommissioningNodes");
    }

    /**
     * The number of nodes decommissioned
     */
    public Integer decommissionedNodes() {
        return readInteger("decommissionedNodes");
    }

    /**
     * The number of nodes rebooted
     */
    public Integer rebootedNodes() {
        return readInteger("rebootedNodes");
    }

    /**
     * The number of nodes shut down
     */
    public Integer shutdownNodes() {
        return readInteger("shutdownNodes");
    }
}
