package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster;

import io.github.sinri.keel.helper.KeelHelpers;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.YarnSiteV1Request;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.application.ApplicationStatItem;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.*;

/**
 * With the Application Statistics API, you can obtain a collection of triples,
 * each of which contains the application type,
 * the application state
 * and the number of applications of this type
 * and this state in ResourceManager context.
 * Note that with the performance concern,
 * we currently only support at most one applicationType per query.
 * We may support multiple applicationTypes per query as well as more statistics in the future.
 * When you run a GET operation on this resource, you obtain a collection of statItem objects.
 */
public class ClusterApplicationStatisticsRequest extends YarnSiteV1Request<ClusterApplicationStatisticsRequest.AppStatInfo> {
    /**
     * states of the applications, specified as a comma-separated list.
     * If states is not provided, the API will enumerate all application states and return the counts of them.
     */
    public Collection<String> states;
    /**
     * types of the applications, specified as a comma-separated list.
     * If applicationTypes is not provided, the API will count the applications of any application type.
     * In this case, the response shows * to indicate any application type.
     * Note that we only support at most one applicationType temporarily.
     * Otherwise, users will expect an BadRequestException.
     */
    public Collection<String> applicationTypes;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterApplicationStatisticsRequest(String serviceAddress) {
        super(serviceAddress);
    }

    public Collection<String> getStates() {
        return states;
    }

    public ClusterApplicationStatisticsRequest setStates(Collection<String> states) {
        this.states = states;
        return this;
    }

    public Collection<String> getApplicationTypes() {
        return applicationTypes;
    }

    public ClusterApplicationStatisticsRequest setApplicationTypes(Collection<String> applicationTypes) {
        this.applicationTypes = applicationTypes;
        return this;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/appstatistics";
    }

    @Override
    protected Future<AppStatInfo> request() {
        Map<String, String> map = new HashMap<>();
        if (this.states != null) {
            map.put("states", KeelHelpers.stringHelper().joinStringArray(this.states, ","));
        }
        if (this.applicationTypes != null) {
            map.put("applicationTypes", KeelHelpers.stringHelper().joinStringArray(this.applicationTypes, ","));
        }
        return this.requestWithGet(map)
                .compose(jsonObject -> {
                    JsonObject appStatInfo = jsonObject.getJsonObject("appStatInfo");
                    return Future.succeededFuture(new AppStatInfo(appStatInfo));
                });
    }

    public static class AppStatInfo extends YarnEntity {

        public AppStatInfo(JsonObject jsonObject) {
            super(jsonObject);
        }

        public List<ApplicationStatItem> statItems() {
            List<ApplicationStatItem> list = new ArrayList<>();
            List<JsonObject> x = this.readJsonObjectArray("statItem");
            if (x != null) {
                x.forEach(jsonObject -> {
                    list.add(new ApplicationStatItem(jsonObject));
                });
            }
            return list;
        }
    }

}
