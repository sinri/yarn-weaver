package io.github.sinri.yarn.weaver.test.p1;

import io.github.sinri.yarn.weaver.YarnWeaver;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.ClusterNewApplicationRequest;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.ClusterSubmitApplicationRequest;
import io.vertx.core.Future;

public class CreateApplicationTest extends P1Test {

    public static void main(String[] args) {
        new CreateApplicationTest().mainBody();
    }

    @Override
    protected Future<Void> run() {
        return new ClusterNewApplicationRequest(YarnWeaver.yarnRMAddress())
                .request()
                .compose(meta -> {
                    getLogger().info(eventLog -> eventLog
                            .message("applied an application")
                            .put("application_id", meta.applicationId())
                            .put("meta.maximumResourceCapabilities", meta.maximumResourceCapabilities().toJsonObject())
                    );

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
