package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.scheduler.capacity;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.scheduler.SchedulerHealth;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;

public abstract class CapacitySchedulerQueue extends YarnEntity {

    protected CapacitySchedulerQueue(JsonObject jsonObject) {
        super(jsonObject);
    }

    public static CapacitySchedulerQueue factory(@NotNull JsonObject jsonObject) {
        if (jsonObject.containsKey("queues") && jsonObject.getJsonObject("queues") != null) {
            return new CapacitySchedulerParentQueue(jsonObject);
        } else {
            return new CapacitySchedulerLeafQueue(jsonObject);
        }
    }

    abstract public boolean isParentQueue();

    /**
     * Configured queue capacity in percentage relative to its parent queue
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
     * Configured maximum queue capacity in percentage relative to its parent queue
     */
    public Float maxCapacity() {
        return readFloat("maxCapacity");
    }

    /**
     * Absolute capacity percentage this queue can use of entire cluster
     */
    public Float absoluteCapacity() {
        return readFloat("absoluteCapacity");
    }

    /**
     * Absolute maximum capacity percentage this queue can use of the entire cluster
     */
    public Float absoluteMaxCapacity() {
        return readFloat("absoluteMaxCapacity");
    }

    /**
     * Absolute used capacity percentage this queue is using of the entire cluster
     */
    public Float absoluteUsedCapacity() {
        return readFloat("absoluteUsedCapacity");
    }

    /**
     * The number of applications currently in the queue
     */
    public Integer numApplications() {
        return readInteger("numApplications");
    }

    /**
     * A string describing the current resources used by the queue
     */
    public String usedResources() {
        return readString("usedResources");
    }

    /**
     * The name of the queue
     */
    public String queueName() {
        return readString("queueName");
    }

    /**
     * of QueueState	The state of the queue
     */
    public String state() {
        return readString("state");
    }

    /**
     * @return The total amount of resources used by this queue
     */
    public SchedulerHealth resourcesUsed() {
        return new SchedulerHealth(this.readJsonObject("resourcesUsed"));
    }
}
