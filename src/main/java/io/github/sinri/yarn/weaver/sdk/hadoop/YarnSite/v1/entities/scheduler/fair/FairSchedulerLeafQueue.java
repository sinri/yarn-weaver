package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.scheduler.fair;

import io.vertx.core.json.JsonObject;

public class FairSchedulerLeafQueue extends FairSchedulerQueue {

    public FairSchedulerLeafQueue(JsonObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public boolean isParentQueue() {
        return false;
    }

    /**
     * The type of the queue - fairSchedulerLeafQueueInfo
     */
    public String type() {
        return readString("type");
    }

    /**
     * The number of active applications in this queue
     */
    public Integer numActiveApps() {
        return readInteger("numActiveApps");
    }

    /**
     * The number of pending applications in this queue
     */
    public Integer numPendingApps() {
        return readInteger("numPendingApps");
    }
}
