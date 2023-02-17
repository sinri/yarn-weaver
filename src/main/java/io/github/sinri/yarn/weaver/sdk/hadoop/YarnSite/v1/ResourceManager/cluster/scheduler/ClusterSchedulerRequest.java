package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.scheduler;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.YarnSiteV1Request;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.scheduler.SchedulerInfo;
import io.vertx.core.Future;

/**
 * A scheduler resource contains information about the current scheduler configured in a cluster.
 * It currently supports the Fifo, Capacity and Fair Scheduler.
 * You will get different information depending on which scheduler is configured so be sure to look at the type information.
 */
public class ClusterSchedulerRequest extends YarnSiteV1Request<SchedulerInfo> {
    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterSchedulerRequest(String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/scheduler";
    }

    @Override
    public Future<SchedulerInfo> request() {
        return this.requestWithGet().compose(jsonObject -> {
            SchedulerInfo scheduler = SchedulerInfo.factory(jsonObject
                    .getJsonObject("scheduler")
                    .getJsonObject("schedulerInfo")
            );
            return Future.succeededFuture(scheduler);
        });
    }

}
