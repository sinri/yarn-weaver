package io.github.sinri.yarn.weaver;

import io.github.sinri.keel.facade.Keel;
import io.github.sinri.keel.logger.event.center.KeelOutputEventLogCenter;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.VertxOptions;

public class YarnWeaver {


    public static void main(String[] args) {
        Keel.getConfiguration().loadPropertiesFile("config.properties");
        Keel.initializeVertx(new VertxOptions())
                .onSuccess(v -> {
                    KeelOutputEventLogCenter.instantLogger().notice("YarnWeaver initialized");
                    new YarnWeaverServer()
                            .deployMe(new DeploymentOptions()
                                    .setConfig(YarnWeaverServer.generateConfig())
                            )
                            .onSuccess(serverStarted -> {
                                KeelOutputEventLogCenter.instantLogger().notice("YarnWeaverServer started, deployment id is " + serverStarted);
                            })
                            .onFailure(serverFailure -> {
                                KeelOutputEventLogCenter.instantLogger().exception(serverFailure, "YarnWeaverServer failed to start");
                            });
                })
                .onFailure(throwable -> {
                    KeelOutputEventLogCenter.instantLogger().exception(throwable, "YarnWeaver failed to initialize");
                    System.exit(1);
                });
    }

    public static String yarnRMAddress() {
        return Keel.config("yarn.ResourceManager.address");
    }
}