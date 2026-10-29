# Step 0 Seed Data

Enter the data in this order through the save endpoints. Each save returns only a message, so after each group call the matching GET /api/{path}/all endpoint and copy the generated ids into the next group.

## 1. Car types: POST /api/car-types/save

```json
{ "name": "Manual" }
{ "name": "Automatic" }
{ "name": "SUV" }
{ "name": "Pickup" }
```

## 2. Zones: POST /api/zones/save

```json
{ "zoneNumber": 1, "name": "Kimihurura" }
{ "zoneNumber": 2, "name": "Kacyiru" }
{ "zoneNumber": 3, "name": "Remera" }
{ "zoneNumber": 4, "name": "Nyamirambo" }
```

## 3. Customers: POST /api/customers/save

```json
{ "firstName": "Alice", "lastName": "Gatete", "phone": "0788123456", "homeAddress": "KG 7 Ave, Kacyiru" }
{ "firstName": "Emmanuel", "lastName": "Gatete", "phone": "0788234567", "homeAddress": "KN 3 Rd, Nyarugenge" }
{ "firstName": "Diane", "lastName": "Uwase", "phone": "0788345678", "homeAddress": "KG 11 Ave, Remera" }
{ "firstName": "Kevin", "lastName": "Nshuti", "phone": "0788456789", "homeAddress": "KK 15 Rd, Kicukiro" }
{ "firstName": "Grace", "lastName": "Mukamana", "phone": "0788567890", "homeAddress": "KG 9 Ave, Nyarutarama" }
```

## 4. Drivers: POST /api/drivers/save

Zone 4 stays without a driver. Nobody holds Pickup.

```json
{
  "firstName": "Jean Claude", "lastName": "Habimana", "licenseNumber": "RW-DL-1001",
  "zone": { "id": "ZONE_1_ID" },
  "carTypes": [ { "id": "MANUAL_ID" }, { "id": "AUTOMATIC_ID" } ]
}
{
  "firstName": "Eric", "lastName": "Ndayisaba", "licenseNumber": "RW-DL-1002",
  "zone": { "id": "ZONE_2_ID" },
  "carTypes": [ { "id": "SUV_ID" } ]
}
{
  "firstName": "Patrick", "lastName": "Mugisha", "licenseNumber": "RW-DL-1003",
  "zone": { "id": "ZONE_3_ID" },
  "carTypes": [ { "id": "AUTOMATIC_ID" } ]
}
```

## 5. Ride requests: POST /api/ride-requests/save

Alice has three ride requests. Driver 1 has two on 2026-10-20, one of them CANCELLED. Three ride requests are DROPPED_OFF.

```json
{ "rideDate": "2026-10-05", "status": "DROPPED_OFF", "pickupLocation": "Kimironko Bar Street", "carPlate": "RAD 123 A", "customer": { "id": "ALICE_ID" }, "driver": { "id": "DRIVER_1_ID" } }
{ "rideDate": "2026-10-12", "status": "DROPPED_OFF", "pickupLocation": "Kigali Heights", "carPlate": "RAC 456 B", "customer": { "id": "DIANE_ID" }, "driver": { "id": "DRIVER_2_ID" } }
{ "rideDate": "2026-10-20", "status": "ASSIGNED", "pickupLocation": "Kibagabaga Hill Lounge", "carPlate": "RAD 123 A", "customer": { "id": "ALICE_ID" }, "driver": { "id": "DRIVER_1_ID" } }
{ "rideDate": "2026-10-20", "status": "CANCELLED", "pickupLocation": "Nyamirambo Night Market", "carPlate": "RAE 789 C", "customer": { "id": "EMMANUEL_ID" }, "driver": { "id": "DRIVER_1_ID" } }
{ "rideDate": "2026-10-25", "status": "REQUESTED", "pickupLocation": "Remera Taxi Park Bar", "carPlate": "RAF 321 D", "customer": { "id": "KEVIN_ID" }, "driver": { "id": "DRIVER_3_ID" } }
{ "rideDate": "2026-10-31", "status": "REQUESTED", "pickupLocation": "Kigali Convention Centre", "carPlate": "RAG 654 E", "customer": { "id": "GRACE_ID" }, "driver": { "id": "DRIVER_2_ID" } }
{ "rideDate": "2026-11-03", "status": "ASSIGNED", "pickupLocation": "Gisozi Memorial Road", "carPlate": "RAD 123 A", "customer": { "id": "ALICE_ID" }, "driver": { "id": "DRIVER_3_ID" } }
{ "rideDate": "2026-11-08", "status": "REQUESTED", "pickupLocation": "Kicukiro Centre", "carPlate": "RAE 789 C", "customer": { "id": "EMMANUEL_ID" }, "driver": { "id": "DRIVER_2_ID" } }
{ "rideDate": "2026-11-14", "status": "CANCELLED", "pickupLocation": "Nyarutarama Golf Club", "carPlate": "RAC 456 B", "customer": { "id": "DIANE_ID" }, "driver": { "id": "DRIVER_3_ID" } }
{ "rideDate": "2026-11-21", "status": "DROPPED_OFF", "pickupLocation": "Kimihurura Roundabout", "carPlate": "RAF 321 D", "customer": { "id": "KEVIN_ID" }, "driver": { "id": "DRIVER_1_ID" } }
```
