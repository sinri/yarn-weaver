package io.github.sinri.yarn.weaver.test.p1;

import io.github.sinri.yarn.weaver.YarnWeaver;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.ClusterNewApplicationRequest;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.ClusterSubmitApplicationRequest;
import io.vertx.core.Future;

import java.util.Objects;

public class CreateApplicationTest extends P1Test {

    public static void main(String[] args) {
        new CreateApplicationTest().mainBody();
    }

    @Override
    protected Future<Void> run() {
        return new ClusterNewApplicationRequest(YarnWeaver.yarnRMAddress())
                .request()
                .compose(meta -> {
                    getLogger().info("applied an application", Objects.requireNonNull(meta, "applied meta is null").toJsonObject());

                    return new ClusterSubmitApplicationRequest(YarnWeaver.yarnRMAddress())
                            .setEditor(form -> {
                                form
                                        .setApplicationId(meta.applicationId())
                                        .setApplicationName("weaver-test-on-" + System.currentTimeMillis())
                                        .setAMContainerSpec(new ClusterSubmitApplicationRequest.ApplicationDataToSubmit.AMContainerSpec()
                                                .setLocalResources(null)
                                                .setCommand("/opt/sinri/OntarioSync-1.0.0.jar sql=\"SELECT VERSION() AS vs\"")
                                                .setEnvironment(null)
                                        )
                                        .setUnmanagedAM(false)
                                        .setMaxAppAttempts(2)
                                        .setResource(new ClusterSubmitApplicationRequest.ApplicationDataToSubmit.Resource(512, 1))
                                        .setApplicationType("SPARK")
                                        .setKeepContainersAcrossApplicationAttempts(false)
                                ;
                            })
                            .request()
                            .compose(done -> {
                                getLogger().info("submit done", meta.toJsonObject());
                                return Future.succeededFuture();
                            });
                });
    }
}
