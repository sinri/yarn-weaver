package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.scheduler;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.vertx.core.json.JsonObject;

public class SchedulerHealthOperationInfo extends YarnEntity {

    public SchedulerHealthOperationInfo(JsonObject jsonObject) {
        super(jsonObject);
    }

    // todo
    // operation	string	The type of operation
    //nodeId	string	The id of the node to which the operation relates
    //containerId	string	The id of the container to which the operation relates
    //queue	string	The name of the queue to which the operation relates
}
