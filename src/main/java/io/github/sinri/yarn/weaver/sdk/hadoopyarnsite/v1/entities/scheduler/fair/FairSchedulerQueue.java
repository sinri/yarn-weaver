package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.scheduler.fair;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.ResourcesUsed;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;

abstract public class FairSchedulerQueue extends YarnEntity {

    protected FairSchedulerQueue(JsonObject jsonObject) {
        super(jsonObject);
    }

    public static FairSchedulerQueue factory(@NotNull JsonObject jsonObject) {
        if (jsonObject.containsKey("childQueues") && jsonObject.getJsonObject("childQueues") != null) {
            return new FairSchedulerParentQueue(jsonObject);
        } else {
            return new FairSchedulerLeafQueue(jsonObject);
        }
    }

    abstract public boolean isParentQueue();

    /**
     * The maximum number of applications the queue can have
     */
    public Integer maxApps() {
        return readInteger("maxApps");
    }

    /**
     * The name of the queue
     */
    public String queueName() {
        return readString("queueName");
    }

    /**
     * The name of the scheduling policy used by the queue
     */
    public String schedulingPolicy() {
        return readString("schedulingPolicy");
    }

    /**
     * The number of allocated containers
     */
    public Integer allocatedContainers() {
        return readInteger("allocatedContainers");
    }

    /**
     * The number of pending containers
     */
    public Integer pendingContainers() {
        return readInteger("pendingContainers");
    }

    /**
     * true if containers in this queue can be preempted
     */
    public Boolean preemptable() {
        return readBoolean("preemptable");
    }

    /**
     * The number of reserved containers
     */
    public Integer reservedContainers() {
        return readInteger("reservedContainers");
    }

    /**
     * @return The steady fair share for the queue
     */
    public ResourcesUsed steadyFairResources() {
        return new ResourcesUsed(readJsonObject("steadyFairResources"));
    }

    /**
     * @return The resources that have been requested by containers in this queue which have not been fulfilled by the scheduler
     */
    public ResourcesUsed demandResources() {
        return new ResourcesUsed(readJsonObject("demandResources"));
    }

    /**
     * @return The capacity of the cluster
     */
    public ResourcesUsed clusterResources() {
        return new ResourcesUsed(readJsonObject("clusterResources"));
    }

    /**
     * @return The configured minimum resources that are guaranteed to the queue
     */
    public ResourcesUsed minResources() {
        return new ResourcesUsed(readJsonObject("minResources"));
    }

    /**
     * @return The configured maximum resources that are allowed to the queue
     */
    public ResourcesUsed maxResources() {
        return new ResourcesUsed(readJsonObject("maxResources"));
    }

    /**
     * @return The sum of resources allocated to containers within the queue
     */
    public ResourcesUsed usedResources() {
        return new ResourcesUsed(readJsonObject("usedResources"));
    }

    /**
     * @return The queue’s fair share of resources
     */
    public ResourcesUsed fairResources() {
        return new ResourcesUsed(readJsonObject("fairResources"));
    }
}
