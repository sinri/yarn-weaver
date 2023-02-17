package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.container;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.YarnSiteV1Request;
import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.container.ContainerSignal;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;

/**
 * With the Container Signal API,
 * you can send a signal to a specified container with one of the following commands:
 * OUTPUT_THREAD_DUMP, GRACEFUL_SHUTDOWN and FORCEFUL_SHUTDOWN.
 */
public class ClusterContainerSignalRequest extends YarnSiteV1Request<ClusterContainerSignalRequest.SignalDone> {
    public final @NotNull String containerId;
    public final @NotNull ContainerSignal command;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterContainerSignalRequest(String serviceAddress, @NotNull String containerId, @NotNull ContainerSignal command) {
        super(serviceAddress);
        this.containerId = containerId;
        this.command = command;
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/containers/" + containerId + "/signal/" + command.name();
    }

    @Override
    protected Future<SignalDone> request() {
        return requestWithPost(new JsonObject())
                .compose(jsonObject -> {
                    return Future.succeededFuture(new SignalDone(new JsonObject()));
                });
    }

    public static class SignalDone extends YarnEntity {

        public SignalDone(JsonObject jsonObject) {
            super(jsonObject);
        }
    }
}
