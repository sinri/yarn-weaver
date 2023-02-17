package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.scheduler;

/**
 * Application activities RESTful API is available if you are using capacity scheduler and can fetch useful scheduling info for a specified application, the response has a hierarchical layout with following fields:
 * <p>
 * AppActivities - AppActivities are root element of application activities within basic information.
 * Allocations - Allocations are allocation attempts at app level queried from the cache.
 * Requests - Requests are shown as children of allocation, each of them contains request name, request priority, allocation request id, allocation state and optional children.
 * Nodes - Nodes are shown as children of request, each of them contains node id, allocation state, optional name which should appear after allocating or reserving a container on the node, and optional diagnostic which should appear if failed to allocate or reserve a container on the node. For aggregated nodes grouped by allocation state and diagnostic, each of them contains allocation state, aggregated node IDs and optional diagnostic.
 */
public class SchedulerApplicationActivitiesRequest {
}
