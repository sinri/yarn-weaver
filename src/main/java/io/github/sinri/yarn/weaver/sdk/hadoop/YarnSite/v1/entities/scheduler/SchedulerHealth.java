package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.scheduler;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class SchedulerHealth extends YarnEntity {

    public SchedulerHealth(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * @return The time in which application started (in ms since epoch)
     */
    public Long lastRun() {
        return this.readLong("lastrun");
    }

    /**
     * @return A collection of operation objects
     */
    public List<SchedulerHealthOperationInfo> operationsInfo() {
        List<SchedulerHealthOperationInfo> list = new ArrayList<>();
        List<JsonObject> jsonObjectList = this.readJsonObjectArray("operationsInfo");
        if (jsonObjectList != null) {
            jsonObjectList.forEach(jsonObject -> {
                list.add(new SchedulerHealthOperationInfo(jsonObject));
            });
        }
        return list;
    }

    /**
     * @return A collection of lastRunDetail objects
     */
    public List<SchedulerHealthLastRunDetail> lastRunDetails() {
        List<SchedulerHealthLastRunDetail> list = new ArrayList<>();
        List<JsonObject> jsonObjectList = this.readJsonObjectArray("lastRunDetails");
        if (jsonObjectList != null) {
            jsonObjectList.forEach(jsonObject -> {
                list.add(new SchedulerHealthLastRunDetail(jsonObject));
            });
        }
        return list;
    }
}
