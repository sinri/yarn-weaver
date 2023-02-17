package io.github.sinri.yarn.weaver.receptionist.story;

import io.github.sinri.keel.logger.event.KeelEventLogger;
import io.github.sinri.keel.logger.event.center.KeelOutputEventLogCenter;
import io.github.sinri.keel.web.http.prehandler.KeelPlatformHandler;
import io.github.sinri.keel.web.http.receptionist.KeelWebFutureReceptionist;
import io.vertx.ext.web.RoutingContext;

abstract public class StoryReceptionist extends KeelWebFutureReceptionist {

    public StoryReceptionist(RoutingContext routingContext) {
        super(routingContext);
    }

    @Override
    protected KeelEventLogger createLogger() {
        return KeelOutputEventLogCenter.getInstance()
                .createLogger(getClass().getSimpleName(), eventLog -> {
                    eventLog.put("request_id", getRoutingContext().get(KeelPlatformHandler.KEEL_REQUEST_ID));
                });
    }
}
