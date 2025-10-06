package com.ocost.trainsnow.temp.core.api

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.runSuspendCatching
import com.github.michaelbull.result.mapError
import com.quicktrain.core.model.data.Agency
import com.quicktrain.core.model.data.Route
import com.quicktrain.core.model.data.Stop
import com.quicktrain.core.model.data.System
import com.quicktrain.core.model.reply.ListRoutesReply
import com.quicktrain.core.model.reply.ListStopsReply
import com.quicktrain.core.model.request.ListStopsRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments

class TransiterApi(
    private val client: HttpClient,
    private val baseUrl: String = "https://demo.transiter.dev"
) {

    // Systems
    /**
     * Fetches all transit systems available on the Transiter API instance.
     *
     * @return A list of [System] objects.
     */
    suspend fun getSystems(): Result<List<System>, ApiError> = runSuspendCatching {
        client.get("$baseUrl/systems").body<List<System>>()
    }.mapError { exceptionToApiError(it) }

    /**
     * Fetches a transit system by ID.
     *
     * @param systemId The ID of the system to fetch.
     * @return The [System] object corresponding to the provided ID.
     */
    suspend fun getSystem(systemId: String): Result<System, ApiError> = runSuspendCatching {
        client.get(baseUrl) {
            url {
                appendPathSegments("systems", systemId)
            }
        }.body<System>()
    }.mapError { exceptionToApiError(it) }

    // Agencies
    /**
     * Fetches all agencies in a transit system.
     *
     * @param systemId The ID of the system to fetch agencies from.
     * @return A list of [Agency] objects.
     */
    suspend fun getAgencies(systemId: String): Result<List<Agency>, ApiError> = runSuspendCatching {
        client.get(baseUrl) {
            url {
                appendPathSegments("systems", systemId, "agencies")
            }
        }.body<List<Agency>>()
    }.mapError { exceptionToApiError(it) }

    /**
     * Fetches an agency by ID in a transit system.
     *
     * @param systemId The ID of the system to fetch the agency from.
     * @param agencyId The ID of the agency to fetch.
     * @return The [Agency] object corresponding to the provided ID.
     */
    suspend fun getAgency(
        systemId: String,
        agencyId: String
    ): Result<Agency, ApiError> = runSuspendCatching {
        client.get(baseUrl) {
            url {
                appendPathSegments("systems", systemId, "agencies", agencyId)
            }
        }.body<Agency>()
    }.mapError { exceptionToApiError(it) }

    // Stops
    /**
     * Fetches stops in a transit system, with optional filtering and pagination.
     *
     * @param systemId The ID of the system to fetch stops from.
     * @param searchMode The search mode to use (defaults to ID).
     * @param onlyReturnSpecifiedIds Whether to only return stops with specified IDs.
     * @param filterById Whether to filter stops by ID.
     * @param id The ID to filter by (if applicable).
     * @param filterByType Whether to filter stops by type.
     * @param type The type to filter by (if applicable).
     * @param firstId The first ID to return (for pagination).
     * @param limit The maximum number of stops to return.
     * @param skipStopTimes Whether to skip stop times in the response.
     * @param skipServiceMaps Whether to skip service maps in the response.
     * @param skipAlerts Whether to skip alerts in the response.
     * @param skipTransfers Whether to skip transfers in the response.
     * @param maxDistance The maximum distance to search (if applicable).
     * @param latitude The latitude to search around (if applicable).
     * @param longitude The longitude to search around (if applicable).
     * @return A [ListStopsReply] object containing the fetched stops.
     */
    suspend fun getStops(
        systemId: String,
        searchMode: ListStopsRequest.SearchMode = ListStopsRequest.SearchMode.ID,
        onlyReturnSpecifiedIds: Boolean = false,
        filterById: Boolean = false,
        id: String? = null,
        filterByType: Boolean = false,
        type: Stop.Type? = null,
        firstId: String? = null,
        limit: Int = 100,
        skipStopTimes: Boolean = false,
        skipServiceMaps: Boolean = false,
        skipAlerts: Boolean = false,
        skipTransfers: Boolean = false,
        maxDistance: Double? = null,
        latitude: Double? = null,
        longitude: Double? = null,
    ): Result<ListStopsReply, ApiError> = runSuspendCatching {
        client.get(baseUrl) {
            url {
                appendPathSegments("systems", systemId, "stops")
                parameter("search_mode", searchMode.name)
                parameter("only_return_specified_ids", onlyReturnSpecifiedIds)
                parameter("filter_by_id", filterById)
                id?.let { parameter("id", it) }
                parameter("filter_by_type", filterByType)
                type?.let { parameter("type", it.name) }
                firstId?.let { parameter("first_id", it) }
                parameter("limit", limit)
                parameter("skip_stop_times", skipStopTimes)
                parameter("skip_service_maps", skipServiceMaps)
                parameter("skip_alerts", skipAlerts)
                parameter("skip_transfers", skipTransfers)
                maxDistance?.let { parameter("max_distance", it) }
                latitude?.let { parameter("latitude", it) }
                longitude?.let { parameter("longitude", it) }
            }
        }.body<ListStopsReply>()
    }.mapError { exceptionToApiError(it) }

    /**
     * Fetches a stop by ID in a transit system.
     *
     * @param systemId The ID of the system to fetch the stop from.
     * @param stopId The ID of the stop to fetch.
     * @param skipStopTimes Whether to skip stop times in the response.
     * @param skipServiceMaps Whether to skip service maps in the response.
     * @param skipAlerts Whether to skip alerts in the response.
     * @param skipTransfers Whether to skip transfers in the response.
     * @return The [Stop] object corresponding to the provided ID.
     */
    suspend fun getStop(
        systemId: String,
        stopId: String,
        skipStopTimes: Boolean = false,
        skipServiceMaps: Boolean = false,
        skipAlerts: Boolean = false,
        skipTransfers: Boolean = false
    ): Result<Stop, ApiError> = runSuspendCatching {
        client.get(baseUrl) {
            url {
                appendPathSegments("systems", systemId, "stops", stopId)
                parameter("skip_stop_times", skipStopTimes)
                parameter("skip_service_maps", skipServiceMaps)
                parameter("skip_alerts", skipAlerts)
                parameter("skip_transfers", skipTransfers)
            }
        }.body<Stop>()
    }.mapError { exceptionToApiError(it) }

    // Routes
    /**
     * Fetches all routes in a transit system, with optional filtering.
     *
     * @param systemId The ID of the system to fetch routes from.
     * @param skipEstimatedHeadways Whether to skip estimated headways in the response.
     * @param skipServiceMaps Whether to skip service maps in the response.
     * @param skipAlerts Whether to skip alerts in the response.
     * @return A [ListRoutesReply] object containing the fetched routes.
     */
    suspend fun getRoutes(
        systemId: String,
        skipEstimatedHeadways: Boolean = false,
        skipServiceMaps: Boolean = false,
        skipAlerts: Boolean = false
    ): Result<ListRoutesReply, ApiError> = runSuspendCatching {
        client.get(baseUrl) {
            url {
                appendPathSegments("systems", systemId, "routes")
                parameter("skip_estimated_headways", skipEstimatedHeadways)
                parameter("skip_service_maps", skipServiceMaps)
                parameter("skip_alerts", skipAlerts)
            }
        }.body<ListRoutesReply>()
    }.mapError { exceptionToApiError(it) }

    /**
     * Fetches a route by ID in a transit system.
     *
     * @param systemId The ID of the system to fetch the route from.
     * @param routeId The ID of the route to fetch.
     * @param skipEstimatedHeadways Whether to skip estimated headways in the response.
     * @param skipServiceMaps Whether to skip service maps in the response.
     * @param skipAlerts Whether to skip alerts in the response.
     * @return The [Route] object corresponding to the provided ID.
     */
    suspend fun getRoute(
        systemId: String,
        routeId: String,
        skipEstimatedHeadways: Boolean = false,
        skipServiceMaps: Boolean = false,
        skipAlerts: Boolean = false
    ): Result<Route, ApiError> = runSuspendCatching {
        client.get(baseUrl) {
            url {
                appendPathSegments("systems", systemId, "routes", routeId)
                parameter("skip_estimated_headways", skipEstimatedHeadways)
                parameter("skip_service_maps", skipServiceMaps)
                parameter("skip_alerts", skipAlerts)
            }
        }.body<Route>()
    }.mapError { exceptionToApiError(it) }

    // Trips
//    suspend fun getTrips(systemId: String): Result<Unit, ApiError> = runSuspendCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "trips")
//            }
//        }.body()
//    }.mapError { apiExceptionToError(it) }
//
//    suspend fun getTrip(
//        systemId: String,
//        tripId: String
//    ): Result<Unit, ApiError> = runSuspendCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "trips", tripId)
//            }
//        }.body()
//    }.mapError { apiExceptionToError(it) }
//
//    // Alerts
//    suspend fun getAlerts(systemId: String): Result<Unit, ApiError> = runSuspendCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "alerts")
//            }
//        }.body()
//    }.mapError { apiExceptionToError(it) }
//
//    suspend fun getAlert(
//        systemId: String,
//        alertId: String
//    ): Result<Unit, ApiError> = runSuspendCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "alerts", alertId)
//            }
//        }.body()
//    }.mapError { apiExceptionToError(it) }
//
//    // Transfers
//    suspend fun getTransfers(systemId: String): Result<Unit, ApiError> = runSuspendCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "transfers")
//            }
//        }.body()
//    }.mapError { apiExceptionToError(it) }
//
//    suspend fun getTransfer(
//        systemId: String,
//        transferId: String
//    ): Result<Unit, ApiError> = runSuspendCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "transfers", transferId)
//            }
//        }.body()
//    }.mapError { apiExceptionToError(it) }
//
//    // Feeds
//    suspend fun getFeeds(systemId: String): Result<Unit, ApiError> = runSuspendCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "feeds")
//            }
//        }.body()
//    }.mapError { apiExceptionToError(it) }
//
//    suspend fun getFeed(
//        systemId: String,
//        feedId: String
//    ): Result<Unit, ApiError> = runSuspendCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "feeds", feedId)
//            }
//        }.body()
//    }.mapError { apiExceptionToError(it) }
//
//    // Vehicles
//    suspend fun getVehicles(systemId: String): Result<Unit, ApiError> = runSuspendCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "vehicles")
//            }
//        }.body()
//    }.mapError { apiExceptionToError(it) }
//
//    suspend fun getVehicle(
//        systemId: String,
//        vehicleId: String
//    ): Result<Unit, ApiError> = runSuspendCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "vehicles", vehicleId)
//            }
//        }.body()
//    }.mapError { apiExceptionToError(it) }
//
//    // Shapes
//    suspend fun getShapes(systemId: String): Result<Unit, ApiError> = runSuspendCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "shapes")
//            }
//        }.body()
//    }.mapError { apiExceptionToError(it) }
//
//    suspend fun getShape(
//        systemId: String,
//        shapeId: String
//    ): Result<Unit, ApiError> = runSuspendCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "shapes", shapeId)
//            }
//        }.body()
//    }
}