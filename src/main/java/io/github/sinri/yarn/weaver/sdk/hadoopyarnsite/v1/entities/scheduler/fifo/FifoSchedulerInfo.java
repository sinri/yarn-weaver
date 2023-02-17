package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.scheduler.fifo;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.scheduler.SchedulerInfo;
import io.vertx.core.json.JsonObject;

public class FifoSchedulerInfo extends SchedulerInfo {

    public FifoSchedulerInfo(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Queue capacity in percentage
     */
    public Float capacity() {
        return readFloat("capacity");
    }

    /**
     * Used queue capacity in percentage
     */
    public Float usedCapacity() {
        return readFloat("usedCapacity");
    }

    /**
     * State of the queue - valid values are: STOPPED, RUNNING
     */
    public String qstate() {
        return readString("qstate");
    }

    /**
     * Minimum queue memory capacity
     */
    public Integer minQueueMemoryCapacity() {
        return readInteger("minQueueMemoryCapacity");
    }

    /**
     * Maximum queue memory capacity
     */
    public Integer maxQueueMemoryCapacity() {
        return readInteger("maxQueueMemoryCapacity");
    }

    /**
     * The total number of nodes
     */
    public Integer numNodes() {
        return readInteger("numNodes");
    }

    /**
     * The used node capacity
     */
    public Integer usedNodeCapacity() {
        return readInteger("usedNodeCapacity");
    }

    /**
     * The available node capacity
     */
    public Integer availNodeCapacity() {
        return readInteger("availNodeCapacity");
    }

    /**
     * The total node capacity
     */
    public Integer totalNodeCapacity() {
        return readInteger("totalNodeCapacity");
    }

    /**
     * The number of containers
     */
    public Integer numContainers() {
        return readInteger("numContainers");
    }
}
