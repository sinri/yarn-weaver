package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.scheduler.capacity;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class CapacitySchedulerParentQueue extends CapacitySchedulerQueue {

    public CapacitySchedulerParentQueue(JsonObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public boolean isParentQueue() {
        return true;
    }

    /**
     * Parsed from array of queues(JSON)/zero or more queue objects(XML).
     *
     * @return A collection of sub-queue information. Omitted if the queue has no sub-queues.
     */
    public List<CapacitySchedulerQueue> queues() {
        List<CapacitySchedulerQueue> list = new ArrayList<>();
        JsonArray queueArray = this.readJsonArray("queues", "queue");
        if (queueArray != null) {
            queueArray.forEach(queueJsonObject -> {
                CapacitySchedulerQueue schedulerQueue = CapacitySchedulerQueue.factory((JsonObject) queueJsonObject);
                list.add(schedulerQueue);
            });
        }
        return list;
    }
}
