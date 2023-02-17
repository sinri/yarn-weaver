package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps;

import io.github.sinri.keel.helper.KeelHelpers;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.YarnSiteV1Request;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.application.ClusterApplication;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * With the Applications API,
 * you can obtain a collection of resources, each of which represents an application.
 * When you run a GET operation on this resource, you obtain a collection of Application Objects.
 * <p>
 * Multiple parameters can be specified for GET operations.
 * The started and finished times have a begin and end parameter to allow you to specify ranges.
 * For example, one could request all applications that started between 1:00am and 2:00pm on 12/19/2011
 * with startedTimeBegin=1324256400&startedTimeEnd=1324303200.
 * If the Begin parameter is not specified, it defaults to 0,
 * and if the End parameter is not specified, it defaults to infinity.
 * All query parameters for this api will filter on all applications.
 * However the queue query parameter will only implicitly filter on unfinished applications that are currently in the given queue.
 */
public class ClusterApplicationsRequest extends YarnSiteV1Request<ClusterApplicationsRequest.ClusterApplications> {

    /**
     * [deprecated] state of the application
     */
    @Deprecated
    private @Nullable String state;
    /**
     * applications matching the given application states, specified as a comma-separated list.
     */
    private @Nullable Collection<String> states;
    /**
     * the final status of the application - reported by the application itself
     */
    private @Nullable String finalStatus;
    /**
     * user name
     */
    private @Nullable String user;
    /**
     * unfinished applications that are currently in this queue
     */
    private @Nullable String queue;
    /**
     * total number of app objects to be returned
     */
    private @Nullable Integer limit;
    /**
     * applications with start time beginning with this time, specified in ms since epoch
     */
    private @Nullable Long startedTimeBegin;
    /**
     * applications with start time ending with this time, specified in ms since epoch
     */
    private @Nullable Long startedTimeEnd;
    /**
     * applications with finish time beginning with this time, specified in ms since epoch
     */
    private @Nullable Long finishedTimeBegin;
    /**
     * applications with finish time ending with this time, specified in ms since epoch
     */
    private @Nullable Long finishedTimeEnd;
    /**
     * applications matching the given application types, specified as a comma-separated list.
     */
    private @Nullable Collection<String> applicationTypes;
    /**
     * applications matching any of the given application tags, specified as a comma-separated list.
     */
    private @Nullable Collection<String> applicationTags;
    /**
     * name of the application
     */
    private @Nullable String name;
    /**
     * a generic fields which will be skipped in the result.
     * Help requesters who don’t need certain information to reduce the overhead.
     * Current supported items:
     * - resouceRequests	comma separated string	Skip resource requests of application in return
     */
    private @Nullable Collection<String> deSelects;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterApplicationsRequest(String serviceAddress) {
        super(serviceAddress);
    }

    @Deprecated
    public String getState() {
        return state;
    }

    @Deprecated
    public ClusterApplicationsRequest setState(String state) {
        this.state = state;
        return this;
    }

    public Collection<String> getStates() {
        return states;
    }

    public ClusterApplicationsRequest setStates(Collection<String> states) {
        this.states = states;
        return this;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public ClusterApplicationsRequest setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
        return this;
    }

    public String getUser() {
        return user;
    }

    public ClusterApplicationsRequest setUser(String user) {
        this.user = user;
        return this;
    }

    public String getQueue() {
        return queue;
    }

    public ClusterApplicationsRequest setQueue(String queue) {
        this.queue = queue;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public ClusterApplicationsRequest setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Long getStartedTimeBegin() {
        return startedTimeBegin;
    }

    public ClusterApplicationsRequest setStartedTimeBegin(Long startedTimeBegin) {
        this.startedTimeBegin = startedTimeBegin;
        return this;
    }

    public Long getStartedTimeEnd() {
        return startedTimeEnd;
    }

    public ClusterApplicationsRequest setStartedTimeEnd(Long startedTimeEnd) {
        this.startedTimeEnd = startedTimeEnd;
        return this;
    }

    public Long getFinishedTimeBegin() {
        return finishedTimeBegin;
    }

    public ClusterApplicationsRequest setFinishedTimeBegin(Long finishedTimeBegin) {
        this.finishedTimeBegin = finishedTimeBegin;
        return this;
    }

    public Long getFinishedTimeEnd() {
        return finishedTimeEnd;
    }

    public ClusterApplicationsRequest setFinishedTimeEnd(Long finishedTimeEnd) {
        this.finishedTimeEnd = finishedTimeEnd;
        return this;
    }

    public Collection<String> getApplicationTypes() {
        return applicationTypes;
    }

    public ClusterApplicationsRequest setApplicationTypes(Collection<String> applicationTypes) {
        this.applicationTypes = applicationTypes;
        return this;
    }

    public Collection<String> getApplicationTags() {
        return applicationTags;
    }

    public ClusterApplicationsRequest setApplicationTags(Collection<String> applicationTags) {
        this.applicationTags = applicationTags;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClusterApplicationsRequest setName(String name) {
        this.name = name;
        return this;
    }

    public Collection<String> getDeSelects() {
        return deSelects;
    }

    public ClusterApplicationsRequest setDeSelects(Collection<String> deSelects) {
        this.deSelects = deSelects;
        return this;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/apps";
    }

    @Override
    public Future<ClusterApplications> request() {
        Map<String, String> map = new HashMap<>();
        if (this.state != null) {
            map.put("state", state);
        }
        if (this.states != null) {
            map.put("states", KeelHelpers.stringHelper().joinStringArray(states, ","));
        }
        if (this.finalStatus != null) {
            map.put("finalStatus", finalStatus);
        }
        if (this.user != null) {
            map.put("user", user);
        }
        if (this.queue != null) {
            map.put("queue", queue);
        }
        if (this.limit != null) {
            map.put("limit", String.valueOf(limit));
        }
        if (this.startedTimeBegin != null) {
            map.put("startedTimeBegin", String.valueOf(startedTimeBegin));
        }
        if (this.startedTimeEnd != null) {
            map.put("startedTimeEnd", String.valueOf(startedTimeEnd));
        }
        if (this.finishedTimeBegin != null) {
            map.put("finishedTimeBegin", String.valueOf(finishedTimeBegin));
        }
        if (this.finishedTimeEnd != null) {
            map.put("finishedTimeEnd", String.valueOf(finishedTimeEnd));
        }
        if (this.applicationTypes != null) {
            map.put("applicationTypes", KeelHelpers.stringHelper().joinStringArray(applicationTypes, ","));
        }
        if (this.applicationTags != null) {
            map.put("applicationTags", KeelHelpers.stringHelper().joinStringArray(applicationTags, ","));
        }
        if (this.name != null) {
            map.put("name", name);
        }
        if (this.deSelects != null) {
            map.put("deSelects", KeelHelpers.stringHelper().joinStringArray(deSelects, ","));
        }
        return this.requestWithGet(map)
                .compose(jsonObject -> {
                    JsonObject apps = jsonObject.getJsonObject("apps");
                    return Future.succeededFuture(new ClusterApplications(apps));
                });
    }

    public static class ClusterApplications extends YarnEntity {

        public ClusterApplications(JsonObject jsonObject) {
            super(jsonObject);
        }

        public List<ClusterApplication> apps() {
            List<ClusterApplication> list = new ArrayList<>();
            List<JsonObject> items = this.readJsonObjectArray("app");
            if (items != null) {
                items.forEach(item -> {
                    list.add(new ClusterApplication(item));
                });
            }
            return list;
        }
    }

}
