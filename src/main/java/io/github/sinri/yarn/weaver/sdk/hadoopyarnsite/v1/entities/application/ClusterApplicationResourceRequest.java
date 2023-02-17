package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.application;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.vertx.core.json.JsonObject;

public class ClusterApplicationResourceRequest extends YarnEntity {

    public ClusterApplicationResourceRequest(JsonObject jsonObject) {
        super(jsonObject);
    }

    public ClusterApplicationResourceRequestCapability capability() {
        return new ClusterApplicationResourceRequestCapability(readJsonObject("capability"));
    }

    public String nodeLabelExpression() {
        return readString("nodeLabelExpression");
    }

    public Integer numContainers() {
        return readInteger("numContainers");
    }

    public ClusterApplicationResourceRequestPriority priority() {
        return new ClusterApplicationResourceRequestPriority(readJsonObject("priority"));
    }

    public Boolean relaxLocality() {
        return readBoolean("relaxLocality");
    }

    public String resourceName() {
        return readString("resourceName");
    }
}
