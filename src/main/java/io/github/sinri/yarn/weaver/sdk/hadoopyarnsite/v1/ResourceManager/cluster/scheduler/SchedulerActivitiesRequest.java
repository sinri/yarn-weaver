package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.scheduler;

/**
 * The scheduler activities RESTful API is available if you are using capacity scheduler and can fetch scheduler activities info recorded in a scheduling cycle. The API returns a message that includes important scheduling activities info which has a hierarchical layout with following fields:
 * <p>
 * Activities - Activities is the root object of scheduler activities.
 * Allocations - Allocations are allocation attempts based on partition or reservation.
 * Hierarchical Queues - Hierarchical Queues where the scheduler have been tried to allocate containers to, each of them contains queue name, allocation state, optional diagnostic and optional children.
 * Applications - Applications are shown as children of leaf queue, each of them contains the basic info about the application.
 * Requests - Requests are shown as children of application, each of them contains the basic info about the request.
 * Nodes - Nodes are shown as children of request, each of them contains node id, allocation state, optional name which should appear after allocating or reserving a container on the node, and optional diagnostic which should present if failed to allocate or reserve a container on this node. For aggregated nodes grouped by allocation state and diagnostic, each of them contains allocation state, aggregated node IDs and optional diagnostic.
 */
public class SchedulerActivitiesRequest {
    // todo next wave
}
