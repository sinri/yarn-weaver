package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.scheduler.fair;

import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class FairSchedulerParentQueue extends FairSchedulerQueue {

    public FairSchedulerParentQueue(JsonObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public boolean isParentQueue() {
        return true;
    }


    /**
     * @return A collection of sub-queue information.
     * Omitted if the queue has no childQueues or is a leaf queue.
     */
    public List<FairSchedulerQueue> childQueues() {
        List<FairSchedulerQueue> list = new ArrayList<>();
        List<JsonObject> jsonObjects = this.readJsonObjectArray("childQueues", "queue");
        if (jsonObjects != null) {
            jsonObjects.forEach(jsonObject -> {
                list.add(factory(jsonObject));
            });
        }
        return list;
    }
}
