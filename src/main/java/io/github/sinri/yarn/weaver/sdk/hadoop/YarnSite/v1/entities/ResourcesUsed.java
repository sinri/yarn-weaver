package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.vertx.core.json.JsonObject;

public class ResourcesUsed extends YarnEntity {

    public ResourcesUsed(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * @return The amount of memory used (in MB)
     */
    public Integer memory() {
        return this.readInteger("memory");
    }

    /**
     * @return The number of virtual cores
     */
    public Integer vCores() {
        return this.readInteger("vCores");
    }

}
