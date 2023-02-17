package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.application;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.vertx.core.json.JsonObject;

public class ClusterApplicationResourceRequestPriority extends YarnEntity {

    public ClusterApplicationResourceRequestPriority(JsonObject jsonObject) {
        super(jsonObject);
    }

    public Integer priority() {
        return readInteger("priority");
    }
}
