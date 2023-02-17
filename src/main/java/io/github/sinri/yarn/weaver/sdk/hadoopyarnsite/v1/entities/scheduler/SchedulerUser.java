package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.scheduler;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.vertx.core.json.JsonObject;

public class SchedulerUser extends YarnEntity {

    public SchedulerUser(JsonObject jsonObject) {
        super(jsonObject);
    }

    // todo
    // username	String	The username of the user using the resources
    //numActiveApplications	int	The number of active applications for this user in this queue
    //numPendingApplications	int	The number of pending applications for this user in this queue

    /**
     * @return The amount of resources used by the user in this queue
     */
    public SchedulerHealth resourcesUsed() {
        return new SchedulerHealth(this.readJsonObject("resourcesUsed"));
    }
}
