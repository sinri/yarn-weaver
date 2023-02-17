package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.node;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.vertx.core.json.JsonObject;

public class NodeResourceUtilization extends YarnEntity {
    public NodeResourceUtilization(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Node physical memory utilization
     */
    public Integer nodePhysicalMemoryMB() {
        return readInteger("nodePhysicalMemoryMB");
    }

    /**
     * Node virtual memory utilization
     */
    public Integer nodeVirtualMemoryMB() {
        return readInteger("nodeVirtualMemoryMB");
    }

    /**
     * Node CPU utilization
     */
    public Double nodeCPUUsage() {
        return readDouble("nodeCPUUsage");
    }

    /**
     * The aggregated physical memory utilization of the containers
     */
    public Integer aggregatedContainersPhysicalMemoryMB() {
        return readInteger("aggregatedContainersPhysicalMemoryMB");
    }

    /**
     * The aggregated virtual memory utilization of the containers
     */
    public Integer aggregatedContainersVirtualMemoryMB() {
        return readInteger("aggregatedContainersVirtualMemoryMB");
    }

    /**
     * The aggregated CPU utilization of the containers
     */
    public Double containersCPUUsage() {
        return readDouble("containersCPUUsage");
    }
}
