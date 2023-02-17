package io.github.sinri.yarn.weaver;

import io.github.sinri.keel.facade.Keel;
import io.github.sinri.keel.web.http.KeelHttpServer;
import io.github.sinri.keel.web.http.receptionist.KeelWebReceptionistKit;
import io.github.sinri.yarn.weaver.receptionist.story.StoryReceptionist;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

import java.util.Objects;

public class YarnWeaverServer extends KeelHttpServer {
    public static JsonObject generateConfig() {
        int port = Integer.parseInt(
                Objects.requireNonNullElse(
                        Keel.config("weaver.http.port"),
                        "8080"
                )
        );
        return new JsonObject()
                .put(KeelHttpServer.CONFIG_HTTP_SERVER_PORT, port)
                .put(KeelHttpServer.CONFIG_IS_MAIN_SERVICE, true);
    }

    @Override
    protected void configureRoutes(Router router) {
        new KeelWebReceptionistKit<StoryReceptionist>(StoryReceptionist.class, router)
                .loadPackage("io.github.sinri.yarn.weaver.receptionist.story");
    }
}
