package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.scheduler;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.scheduler.capacity.CapacitySchedulerInfo;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.scheduler.fair.FairSchedulerInfo;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.scheduler.fifo.FifoSchedulerInfo;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;

/**
 * The capacity scheduler supports hierarchical queues.
 * This one request will print information about all the queues and any subqueues they have.
 * Queues that can actually have jobs submitted to them are referred to as leaf queues.
 * These queues have additional data associated with them.
 */
abstract public class SchedulerInfo extends YarnEntity {

    protected SchedulerInfo(JsonObject jsonObject) {
        super(jsonObject);
    }

    public static SchedulerInfo factory(@NotNull JsonObject jsonObject) {
        // It currently supports the Fifo, Capacity and Fair Scheduler.
        String type = jsonObject.getString("type");
        switch (type) {
            case "capacityScheduler":
                return new CapacitySchedulerInfo(jsonObject);
            case "fifoScheduler":
                return new FifoSchedulerInfo(jsonObject);
            case "fairScheduler":
                return new FairSchedulerInfo(jsonObject);
        }
        throw new UnsupportedOperationException();
    }

    /**
     * @return Scheduler type - capacityScheduler
     */
    public String type() {
        return this.readString("type");
    }
}
