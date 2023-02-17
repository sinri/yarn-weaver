package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.container;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container extends YarnEntity {
    public Container(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * The container id
     */
    public String containerId() {
        return readString("containerId");
    }

    /**
     * The amount of memory allocated for the container in MB
     */
    public Long allocatedMB() {
        return readLong("allocatedMB");
    }

    /**
     * The amount of virtual cores allocated for the container
     */
    public Integer allocatedVCores() {
        return readInteger("allocatedVCores");
    }

    /**
     * The node id of the node the attempt ran on
     */
    public String assignedNodeId() {
        return readString("assignedNodeId");
    }

    /**
     * Allocated priority of the container
     */
    public Integer priority() {
        return readInteger("priority");
    }

    /**
     * The start time of the attempt (in ms since epoch)
     */
    public Long startedTime() {
        return readLong("startedTime");
    }

    /**
     * The finish time of the attempt (in ms since epoch) 0 if not finished
     */
    public Long finishedTime() {
        return readLong("finishedTime");
    }

    /**
     * The elapsed time in ms since the startedTime
     */
    public Long elapsedTime() {
        return readLong("elapsedTime");
    }

    /**
     * The web URL that can be used to check the log for the container
     */
    public String logUrl() {
        return readString("logUrl");
    }

    /**
     * Final exit status of the container
     */
    public Integer containerExitStatus() {
        return readInteger("containerExitStatus");
    }

    /**
     * State of the container, can be NEW, RUNNING, or COMPLETE
     */
    public String containerState() {
        return readString("containerState");
    }

    /**
     * The node http address of the node the attempt ran on
     */
    public String nodeHttpAddress() {
        return readString("nodeHttpAddress");
    }

    /**
     * The node id of the node the attempt ran on
     */
    public String nodeId() {
        return readString("nodeId");
    }

    /**
     * Allocated resources for the container;
     * `memory`: string wrapped int,	The maximum memory for the container
     * `vCores`: string wrapped int,	The maximum number of vcores for the container
     */
    public Map<String, String> allocatedResources() {
        Map<String, String> map = new HashMap<>();
        List<JsonObject> list = readJsonObjectArray("allocatedResources");
        if (list != null) {
            list.forEach(entry -> {
                map.put(entry.getString("key"), entry.getString("value"));
            });
        }
        return map;
    }
}
