package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.scheduler.capacity;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.scheduler.SchedulerHealth;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.scheduler.SchedulerInfo;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class CapacitySchedulerInfo extends SchedulerInfo {
    public CapacitySchedulerInfo(JsonObject jsonObject) {
        super(jsonObject);
    }

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
     * Name of the queue
     */
    public String queueName() {
        return readString("queueName");
    }

    /**
     * @return A collection of queue resources
     */
    public List<CapacitySchedulerQueue> queues() {
        List<CapacitySchedulerQueue> list = new ArrayList<>();
        List<JsonObject> jsonObjects = this.readJsonObjectArray("queues", "queue");
        if (jsonObjects != null) {
            jsonObjects.forEach(jsonObject -> {
                list.add(CapacitySchedulerQueue.factory(jsonObject));
            });
        }
        return list;
    }

    /**
     * This metrics existed since 2.8.0, but the output was not well formatted.
     * Hence users can not make use of this field cleanly, this is optimized from 3.2.0 onwards.
     *
     * @return The health metrics of capacity scheduler.
     */
    public SchedulerHealth health() {
        return new SchedulerHealth(readJsonObject("health"));
    }
}
