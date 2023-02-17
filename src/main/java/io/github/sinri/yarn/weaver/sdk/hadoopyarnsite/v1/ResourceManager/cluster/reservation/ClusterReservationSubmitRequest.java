package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster.reservation;

/**
 * The Cluster Reservation API can be used to submit reservations. When submitting a reservation the user specifies the constraints in terms of resources, and time that is required. The resulting response is successful if the reservation can be made. If a reservation-id is used to submit a reservation multiple times, the request will succeed if the reservation definition is the same, but only one reservation will be created. If the reservation definition is different, the server will respond with an error response. When the reservation is made, the user can use the reservation-id used to submit the reservation to get access to the resources by specifying it as part of Cluster Submit Applications API.
 */
public class ClusterReservationSubmitRequest {
    // todo next wave
}
