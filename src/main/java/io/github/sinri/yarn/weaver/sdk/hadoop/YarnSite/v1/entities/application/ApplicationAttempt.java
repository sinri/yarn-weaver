package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.application;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.vertx.core.json.JsonObject;

public class ApplicationAttempt extends YarnEntity {
    public ApplicationAttempt(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * The app attempt id
     */
    public String id() {
        return readString("id");
    }

    /**
     * The node id of the node the attempt ran on
     */
    public String nodeId() {
        return readString("nodeId");
    }

    /**
     * The node http address of the node the attempt ran on
     */
    public String nodeHttpAddress() {
        return readString("nodeHttpAddress");
    }

    /**
     * The http link to the app attempt logs
     */
    public String logsLink() {
        return readString("logsLink");
    }

    /**
     * The id of the container for the app attempt
     */
    public String containerId() {
        return readString("containerId");
    }

    /**
     * The start time of the attempt (in ms since epoch)
     */
    public Long startTime() {
        return readLong("startTime");
    }
}
