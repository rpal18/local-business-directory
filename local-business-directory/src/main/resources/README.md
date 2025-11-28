

🌍 GeoSearch: Hyper-Local Business Directory API
GeoSearch is a location-aware backend system designed to perform high-performance geospatial queries. Unlike standard directories that filter by city or zip code, GeoSearch uses PostGIS spatial indexing to calculate real-time distances between users and businesses with meter-level precision.

The system handles automatic Geocoding (converting addresses to GPS coordinates) during registration and supports radius-based filtering (e.g., "Find all coffee shops within 2km of my current location").

-----------------------------------------------
This README.md is designed to make you look like a Software Engineer, not just a student. It highlights Architecture, Data consistency, and Geospatial Logic—the exact things hiring managers look for.

Copy the content below into a file named README.md in your project root.

🌍 GeoSearch: Hyper-Local Business Directory API
GeoSearch is a location-aware backend system designed to perform high-performance geospatial queries. Unlike standard directories that filter by city or zip code, GeoSearch uses PostGIS spatial indexing to calculate real-time distances between users and businesses with meter-level precision.

The system handles automatic Geocoding (converting addresses to GPS coordinates) during registration and supports radius-based filtering (e.g., "Find all coffee shops within 2km of my current location").

🚀 Key Features
📍 Spatial Radius Search: Utilizes the ST_DWithin and ST_Distance PostGIS functions to filter businesses within a dynamic radius (in meters) efficiently.

🗺️ Automated Geocoding: Integrated OpenStreetMap (Nominatim) service to automatically convert text addresses (e.g., "Civil Lines, Kanpur") into POINT(Longitude, Latitude) upon registration.

🛡️ Fail-Fast Validation: Implemented robust input validation using Jakarta Validation to ensure data integrity (Regex for phone numbers, MX checks for emails).

⚡ Performance Optimized: Uses Pagination (Pageable) to handle large datasets prevents memory overhead during "Get All" operations.

🔧 Global Exception Handling: Centralized error handling (@RestControllerAdvice) returning standardized RFC-7807 compliant JSON error responses.

----
📂 Project Structure
The project follows a clean Layered Architecture (Controller -> Service -> Repository) to ensure separation of concerns and testability.

![](E:\Learning-projects\local -business-directory\local-business-directory\Screenshot\Project Structure.png)

----------------------------------
🔌 API Documentation & Usage
1. User Registration (with Auto-Geocoding)
   Endpoint: POST /api/v1/users/register

The system accepts a text address and automatically calculates longitude and latitude.

Request:

{
"name": "Ramdeen",
"email": "raamdeen@gmail.com",
"password": "strongPassword123",
"contactNumber": "9876543210",
"address": "Kanpur Central Railway Station",
"dob": "15-08-2000"
}

Response:

{
"userId": 2,
"userName": "Ramdeen",
"address": "Kanpur Central Railway Station",
"email": "raamdeen@gmail.com",
"longitude": 80.3512433,  <-- Automatically Generated
"latitude": 26.4538613    <-- Automatically Generated
}

![](E:\Learning-projects\local -business-directory\local-business-directory\Screenshot\registration with email verifaction.png)

---------------------------

2. Find Nearby BusinessesEndpoint: GET /api/v1/businesses/nearbyQuery Params: userId=1 (Uses user's home location) OR lat=...&lon=... (Uses GPS).Response:Returns a list of businesses sorted by proximity.

[
{
"businessId": 13,
"businessName": "Haveli Restaurant",
"category": "RESTAURANT",
"address": "Civil Lines, Kanpur",
"distance": 59.62  <-- Distance in Meters
},
{
"businessId": 6,
"businessName": "Royal Enfield Showroom",
"category": "BIKE_SHOWROOM",
"address": "Civil Lines, Kanpur",
"distance": 128.27
},
{
"businessId": 3,
"businessName": "Z Square Electronic Hub",
"category": "ELECTRONIC_STORE",
"distance": 332.32
}
]

![](E:\Learning-projects\local -business-directory\local-business-directory\Screenshot\nearby business.png)

-----------------------------------------------------------------------------------------------------------------

3. Robust Error Handling
   The API never crashes with a stack trace. It returns meaningful errors for the frontend.

![](E:\Learning-projects\local -business-directory\local-business-directory\Screenshot\wrong domain(email).png)

![](E:\Learning-projects\local -business-directory\local-business-directory\Screenshot\result for wrong domain.png)

--------------------------------------------------------------------------------------------------------------------

🔮 Future Roadmap
Dockerization: Containerize App and Database for easy deployment.

Redis Caching: Cache results for frequent "Nearby" queries to reduce DB load.

Security: Implement JWT (Stateless Authentication) for secure access.



👨‍💻 Author
[Rohit]


