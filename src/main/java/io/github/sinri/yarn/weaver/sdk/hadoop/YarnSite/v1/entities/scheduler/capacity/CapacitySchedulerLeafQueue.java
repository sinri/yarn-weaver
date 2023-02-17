package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.scheduler.capacity;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.scheduler.SchedulerUser;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class CapacitySchedulerLeafQueue extends CapacitySchedulerQueue {

    public CapacitySchedulerLeafQueue(JsonObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public boolean isParentQueue() {
        return false;
    }

    /**
     * type of the queue - capacitySchedulerLeafQueueInfo
     */
    public String type() {
        return readString("type");
    }

    /**
     * The number of active applications in this queue
     */
    public Integer numActiveApplications() {
        return readInteger("numActiveApplications");
    }

    /**
     * The number of pending applications in this queue
     */
    public Integer numPendingApplications() {
        return readInteger("numPendingApplications");
    }

    /**
     * The number of containers being used
     */
    public Integer numContainers() {
        return readInteger("numContainers");
    }

    /**
     * The number of allocated containers in this queue
     */
    public Integer allocatedContainers() {
        return readInteger("allocatedContainers");
    }

    /**
     * The number of reserved containers in this queue
     */
    public Integer reservedContainers() {
        return readInteger("reservedContainers");
    }

    /**
     * The number of pending containers in this queue
     */
    public Integer pendingContainers() {
        return readInteger("pendingContainers");
    }

    /**
     * The maximum number of applications this queue can have
     */
    public Integer maxApplications() {
        return readInteger("maxApplications");
    }

    /**
     * The maximum number of applications per user this queue can have
     */
    public Integer maxApplicationsPerUser() {
        return readInteger("maxApplicationsPerUser");
    }

    /**
     * The maximum number of active applications this queue can have
     */
    public Integer maxActiveApplications() {
        return readInteger("maxActiveApplications");
    }

    /**
     * The maximum number of active applications per user this queue can have
     */
    public Integer maxActiveApplicationsPerUser() {
        return readInteger("maxActiveApplicationsPerUser");
    }

    /**
     * The minimum user limit percent set in the configuration
     */
    public Integer userLimit() {
        return readInteger("userLimit");
    }

    /**
     * The user limit factor set in the configuration
     */
    public Float userLimitFactor() {
        return readFloat("userLimitFactor");
    }

    /**
     * @return A collection of user objects containing resources used
     */
    public List<SchedulerUser> users() {
        List<SchedulerUser> list = new ArrayList<>();
        List<JsonObject> jsonObjects = this.readJsonObjectArray("users", "user");
        if (jsonObjects != null) {
            jsonObjects.forEach(jsonObject -> {
                list.add(new SchedulerUser(jsonObject));
            });
        }
        return list;
    }
}
