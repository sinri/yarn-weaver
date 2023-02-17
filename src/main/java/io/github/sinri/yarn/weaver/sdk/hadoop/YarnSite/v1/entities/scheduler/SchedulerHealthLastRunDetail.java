package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.scheduler;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.ResourcesUsed;
import io.vertx.core.json.JsonObject;

public class SchedulerHealthLastRunDetail extends YarnEntity {

    public SchedulerHealthLastRunDetail(JsonObject jsonObject) {
        super(jsonObject);
    }


    /**
     * @return The type of operation
     */
    public String operation() {
        return this.readString("operation");
    }

    /**
     * @return The id of the node to which the operation relates
     */
    public Long count() {
        return this.readLong("count");
    }

    /**
     * @return The resources to which the operation relates
     */
    public ResourcesUsed resources() {
        return new ResourcesUsed(this.readJsonObject("resources"));
    }
}
