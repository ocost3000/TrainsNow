package com.ocost.trainsnow.api

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
import kotlin.Result

/**
 * Transiter API client for accessing transit system data.
 *
 * This class provides a Ktor-based HTTP client for interacting with Transiter API instances.
 * All methods return [Result] types for safe error handling.
 *
 * Documentation: https://docs.transiter.dev/api/public_endpoints/
 *
 * ## Usage Example:
 * ```kotlin
 * // Create the API client
 * val api = createTransiterApi(
 *     baseUrl = "https://demo.transiter.dev",
 *     enableLogging = true
 * )
 *
 * // Fetch all systems
 * api.getSystems()
 *     .onSuccess { systems -> println("Found ${systems.size} systems") }
 *     .onFailure { error -> println("Error: ${error.message}") }
 *
 * // Fetch routes for a specific system
 * api.getRoutes(systemId = "us-ny-nycsubway")
 *     .onSuccess { reply ->
 *         reply.routes.forEach { route ->
 *             println("Route: ${route.id}")
 *         }
 *     }
 *     .onFailure { error -> println("Error fetching routes: ${error.message}") }
 * ```
 *
 * @property client The Ktor HttpClient instance for making requests
 * @property baseUrl The base URL of the Transiter API instance (defaults to demo instance)
 */
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
    suspend fun getSystems(): Result<List<System>> = runCatching {
        client.get("$baseUrl/systems").body()
    }

    /**
     * Fetches a transit system by ID.
     *
     * @param systemId The ID of the system to fetch.
     * @return The [System] object corresponding to the provided ID.
     */
    suspend fun getSystem(systemId: String): Result<System> = runCatching {
        client.get(baseUrl) {
            url {
                appendPathSegments("systems", systemId)
            }
        }.body()
    }

    // Agencies
    /**
     * Fetches all agencies in a transit system.
     *
     * @param systemId The ID of the system to fetch agencies from.
     * @return A list of [Agency] objects.
     */
    suspend fun getAgencies(systemId: String): Result<List<Agency>> = runCatching {
        client.get(baseUrl) {
            url {
                appendPathSegments("systems", systemId, "agencies")
            }
        }.body()
    }

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
    ): Result<Agency> = runCatching {
        client.get(baseUrl) {
            url {
                appendPathSegments("systems", systemId, "agencies", agencyId)
            }
        }.body()
    }

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
    ): Result<ListStopsReply> = runCatching {
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
        }.body()
    }

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
    ): Result<Stop> = runCatching {
        client.get(baseUrl) {
            url {
                appendPathSegments("systems", systemId, "stops", stopId)
                parameter("skip_stop_times", skipStopTimes)
                parameter("skip_service_maps", skipServiceMaps)
                parameter("skip_alerts", skipAlerts)
                parameter("skip_transfers", skipTransfers)
            }
        }.body()
    }

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
    ): Result<ListRoutesReply> = runCatching {
        client.get(baseUrl) {
            url {
                appendPathSegments("systems", systemId, "routes")
                parameter("skip_estimated_headways", skipEstimatedHeadways)
                parameter("skip_service_maps", skipServiceMaps)
                parameter("skip_alerts", skipAlerts)
            }
        }.body()
    }

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
    ): Result<Route> = runCatching {
        client.get(baseUrl) {
            url {
                appendPathSegments("systems", systemId, "routes", routeId)
                parameter("skip_estimated_headways", skipEstimatedHeadways)
                parameter("skip_service_maps", skipServiceMaps)
                parameter("skip_alerts", skipAlerts)
            }
        }.body()
    }

    // Trips
//    suspend fun getTrips(systemId: String): Result<Unit> = runCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "trips")
//            }
//        }.body()
//    }
//
//    suspend fun getTrip(
//        systemId: String,
//        tripId: String
//    ): Result<Unit> = runCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "trips", tripId)
//            }
//        }.body()
//    }
//
//    // Alerts
//    suspend fun getAlerts(systemId: String): Result<Unit> = runCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "alerts")
//            }
//        }.body()
//    }
//
//    suspend fun getAlert(
//        systemId: String,
//        alertId: String
//    ): Result<Unit> = runCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "alerts", alertId)
//            }
//        }.body()
//    }
//
//    // Transfers
//    suspend fun getTransfers(systemId: String): Result<Unit> = runCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "transfers")
//            }
//        }.body()
//    }
//
//    suspend fun getTransfer(
//        systemId: String,
//        transferId: String
//    ): Result<Unit> = runCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "transfers", transferId)
//            }
//        }.body()
//    }
//
//    // Feeds
//    suspend fun getFeeds(systemId: String): Result<Unit> = runCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "feeds")
//            }
//        }.body()
//    }
//
//    suspend fun getFeed(
//        systemId: String,
//        feedId: String
//    ): Result<Unit> = runCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "feeds", feedId)
//            }
//        }.body()
//    }
//
//    // Vehicles
//    suspend fun getVehicles(systemId: String): Result<Unit> = runCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "vehicles")
//            }
//        }.body()
//    }
//
//    suspend fun getVehicle(
//        systemId: String,
//        vehicleId: String
//    ): Result<Unit> = runCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "vehicles", vehicleId)
//            }
//        }.body()
//    }
//
//    // Shapes
//    suspend fun getShapes(systemId: String): Result<Unit> = runCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "shapes")
//            }
//        }.body()
//    }
//
//    suspend fun getShape(
//        systemId: String,
//        shapeId: String
//    ): Result<Unit> = runCatching {
//        client.get(baseUrl) {
//            url {
//                appendPathSegments("systems", systemId, "shapes", shapeId)
//            }
//        }.body()
//    }
}