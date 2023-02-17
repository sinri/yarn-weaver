package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.cluster;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.vertx.core.json.JsonObject;

public class ClusterInfo extends YarnEntity {

    public ClusterInfo(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * The cluster id
     */
    public Long id() {
        return readLong("id");
    }

    /**
     * The time the cluster started (in ms since epoch)
     */
    public Long startedOn() {
        return readLong("startedOn");
    }

    /**
     * The ResourceManager state - valid values are: NOTINITED, INITED, STARTED, STOPPED
     */
    public String state() {
        return readString("state");
    }

    /**
     * The ResourceManager HA state - valid values are: INITIALIZING, ACTIVE, STANDBY, STOPPED
     */
    public String haState() {
        return readString("haState");
    }

    /**
     * Fully qualified name of class that implements the storage of ResourceManager state
     */
    public String rmStateStoreName() {
        return readString("rmStateStoreName");
    }

    /**
     * Version of the ResourceManager
     */
    public String resourceManagerVersion() {
        return readString("resourceManagerVersion");
    }

    /**
     * ResourceManager build string with build version, user, and checksum
     */
    public String resourceManagerBuildVersion() {
        return readString("resourceManagerBuildVersion");
    }

    /**
     * Timestamp when ResourceManager was built (in ms since epoch)
     */
    public String resourceManagerVersionBuiltOn() {
        return readString("resourceManagerVersionBuiltOn");
    }

    /**
     * Version of hadoop common
     */
    public String hadoopVersion() {
        return readString("hadoopVersion");
    }

    /**
     * Hadoop common build string with build version, user, and checksum
     */
    public String hadoopBuildVersion() {
        return readString("hadoopBuildVersion");
    }

    /**
     * Timestamp when hadoop common was built(in ms since epoch)
     */
    public String hadoopVersionBuiltOn() {
        return readString("hadoopVersionBuiltOn");
    }

    /**
     * State of ZooKeeper connection of the high availability service
     */
    public String haZooKeeperConnectionState() {
        return readString("haZooKeeperConnectionState");
    }
}
