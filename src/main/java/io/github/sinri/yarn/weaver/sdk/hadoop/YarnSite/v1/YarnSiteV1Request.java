package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnSiteRequest;

abstract public class YarnSiteV1Request<R extends YarnEntity> extends YarnSiteRequest<R> {

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public YarnSiteV1Request(String serviceAddress) {
        super(serviceAddress, "v1");
    }
}
