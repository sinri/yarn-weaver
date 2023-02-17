package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.apps.app;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.YarnSiteV1Request;
import org.jetbrains.annotations.NotNull;

abstract public class ClusterCertainAppsRequestForCertainApp<R extends YarnEntity> extends YarnSiteV1Request<R> {
    private final @NotNull String appId;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterCertainAppsRequestForCertainApp(String serviceAddress, @NotNull String appId) {
        super(serviceAddress);
        this.appId = appId;
    }

    public @NotNull String getAppId() {
        return appId;
    }
}
