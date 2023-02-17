package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.scheduler.fair;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.scheduler.SchedulerInfo;
import io.vertx.core.json.JsonObject;

import java.util.Objects;

public class FairSchedulerInfo extends SchedulerInfo {

    public FairSchedulerInfo(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * @return A collection of root queue resources
     */
    public FairSchedulerQueue rootQueue() {
        JsonObject rootQueue = this.readJsonObject("rootQueue");
        Objects.requireNonNull(rootQueue);
        return FairSchedulerQueue.factory(rootQueue);
    }
}
