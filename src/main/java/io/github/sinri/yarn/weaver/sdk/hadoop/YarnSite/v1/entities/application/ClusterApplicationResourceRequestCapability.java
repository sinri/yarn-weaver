package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.application;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.vertx.core.json.JsonObject;

public class ClusterApplicationResourceRequestCapability extends YarnEntity {

    public ClusterApplicationResourceRequestCapability(JsonObject jsonObject) {
        super(jsonObject);
    }

    public Integer memory() {
        return readInteger("memory");
    }

    public Integer virtualCores() {
        return readInteger("virtualCores");
    }
}
