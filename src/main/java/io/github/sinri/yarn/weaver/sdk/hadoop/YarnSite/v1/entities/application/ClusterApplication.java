package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.application;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.*;

/**
 * Note that depending on security settings a user might not be able to see all the fields.
 */
public class ClusterApplication extends YarnEntity {

    public ClusterApplication(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * @return The application id
     */
    public String id() {
        return readString("id");
    }

    /**
     * @return The user who started the application
     */
    public String user() {
        return readString("user");
    }

    /**
     * @return The application name
     */
    public String name() {
        return readString("name");
    }

    /**
     * @return The queue the application was submitted to
     */
    public String queue() {
        return readString("queue");
    }

    /**
     * @return The application state according to the ResourceManager - NEW, NEW_SAVING, SUBMITTED, ACCEPTED, RUNNING, FINISHED, FAILED, KILLED
     */
    public ApplicationState state() {
        return ApplicationState.valueOf(readString("state"));
    }

    /**
     * @return The final status of the application if finished - reported by the application itself - valid values are the members of the FinalApplicationStatus enum: UNDEFINED, SUCCEEDED, FAILED, KILLED
     */
    public FinalApplicationStatus finalStatus() {
        return FinalApplicationStatus.valueOf(readString("finalStatus"));
    }

    /**
     * @return The progress of the application as a percent
     */
    public Float progress() {
        return readFloat("progress");
    }

    /**
     * @return Where the tracking url is currently pointing - History (for history server) or ApplicationMaster
     */
    public String trackingUI() {
        return readString("trackingUI");
    }

    /**
     * @return The web URL that can be used to track the application
     */
    public String trackingUrl() {
        return readString("trackingUrl");
    }

    /**
     * @return Detailed diagnostics information
     */
    public String diagnostics() {
        return readString("diagnostics");
    }

    /**
     * @return The cluster id
     */
    public Long clusterId() {
        return readLong("clusterId");
    }

    /**
     * @return The application type
     */
    public String applicationType() {
        return readString("applicationType");
    }

    /**
     * @return tag set parsed from Comma separated tags of an application
     */
    public Set<String> applicationTags() {
        Set<String> set = new HashSet<>();
        String applicationTags = readString("applicationTags");
        if (applicationTags != null) {
            String[] split = applicationTags.split(",");
            Collections.addAll(set, split);
        }
        return set;
    }

    /**
     * @return Priority of the submitted application
     */
    public String priority() {
        return readString("priority");
    }

    /**
     * @return The time in which application started (in ms since epoch)
     */
    public Long startedTime() {
        return readLong("startedTime");
    }

    /**
     * @return The time in which the application finished (in ms since epoch)
     */
    public Long finishedTime() {
        return readLong("finishedTime");
    }

    /**
     * @return The elapsed time since the application started (in ms)
     */
    public Long elapsedTime() {
        return readLong("elapsedTime");
    }

    /**
     * @return The URL of the application master container logs
     */
    public String amContainerLogs() {
        return readString("amContainerLogs");
    }

    /**
     * @return The nodes http address of the application master
     */
    public String amHostHttpAddress() {
        return readString("amHostHttpAddress");
    }

    /**
     * @return The RPC address of the application master
     */
    public String amRPCAddress() {
        return readString("amRPCAddress");
    }

    /**
     * @return The sum of memory in MB allocated to the application’s running containers
     */
    public Integer allocatedMB() {
        return readInteger("allocatedMB");
    }

    /**
     * @return The sum of virtual cores allocated to the application’s running containers
     */
    public Integer allocatedVCores() {
        return readInteger("allocatedVCores");
    }

    /**
     * @return The number of containers currently running for the application
     */
    public Integer runningContainers() {
        return readInteger("runningContainers");
    }

    /**
     * @return The amount of memory the application has allocated (megabyte-seconds)
     */
    public Long memorySeconds() {
        return readLong("memorySeconds");
    }

    /**
     * @return The amount of CPU resources the application has allocated (virtual core-seconds)
     */
    public Long vCoreSeconds() {
        return readLong("vcoreSeconds");
    }

    /**
     * @return The percentage of resources of the queue that the app is using
     */
    public Float queueUsagePercentage() {
        return readFloat("queueUsagePercentage");
    }

    /**
     * @return The percentage of resources of the cluster that the app is using.
     */
    public Float clusterUsagePercentage() {
        return readFloat("clusterUsagePercentage");
    }

    /**
     * @return Memory used by preempted container
     */
    public Long preemptedResourceMB() {
        return readLong("preemptedResourceMB");
    }

    /**
     * @return Number of virtual cores used by preempted container
     */
    public Long preemptedResourceVCores() {
        return readLong("preemptedResourceVCores");
    }

    /**
     * @return Number of standard containers preempted
     */
    public Integer numNonAMContainerPreempted() {
        return readInteger("numNonAMContainerPreempted");
    }

    /**
     * @return Number of application master containers preempted
     */
    public Integer numAMContainerPreempted() {
        return readInteger("numAMContainerPreempted");
    }

    /**
     * @return Status of log aggregation - valid values are the members of the LogAggregationStatus enum: DISABLED, NOT_START, RUNNING, RUNNING_WITH_FAILURE, SUCCEEDED, FAILED, TIME_OUT
     */
    public LogAggregationStatus logAggregationStatus() {
        return LogAggregationStatus.valueOf(readString("logAggregationStatus"));
    }

    /**
     * @return Is the application unmanaged.
     */
    public Boolean unmanagedApplication() {
        return readBoolean("unmanagedApplication");
    }

    /**
     * @return Node Label expression which is used to identify the nodes on which application’s containers are expected to run by default.
     */
    public String appNodeLabelExpression() {
        return readString("appNodeLabelExpression");
    }

    /**
     * @return Node Label expression which is used to identify the node on which application’s AM container is expected to run.
     */
    public String amNodeLabelExpression() {
        return readString("amNodeLabelExpression");
    }

    public List<ClusterApplicationResourceRequest> resourceRequests() {
        List<ClusterApplicationResourceRequest> list = new ArrayList<>();
        JsonArray array = readJsonArray("resourceRequests");
        if (array != null) {
            array.forEach(item -> {
                list.add(new ClusterApplicationResourceRequest((JsonObject) item));
            });
        }
        return list;
    }
}
