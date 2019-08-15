# Documentation

## Preamble 

Welcome from the "The Public Transport" team. We are happy you considered using our service. 

For more or specific questions, please open an issue, or mail to tristan@sqrls.de !

## Functions

#### /departure

| Parameter            | Description                                              |
|----------------------|----------------------------------------------------------|
| stationId (Required) | ID of your station.                                      |
| source (Required)    | Source where your data comes from (default is Railteam). |
| hourshift            | Set how many hours your timeshift is, is existent.       |
| limit                | Limiting results.                                        |
| equiv                | Show equivalent stations                                 |

Example: `https://thepublictransport.de/api/departure?stationId=906606&source=DB&when=06.01.2019T06`


#### /location/suggest

| Parameter         | Description                                                                                                |
|-------------------|------------------------------------------------------------------------------------------------------------|
| query (Required)  | Query of your search, for example 'Münster(Westf)Hbf'.                                                     |
| source (Required) | Source where your data comes from (default is Railteam).                                                   |
| types             | Set types of stops you want to get. You can decide between ANY (default), ADDRESS, STATION, POI and COORDS |
| maxLocations      | Limiting location results.                                                                                 |

Example: `https://thepublictransport.de/location/suggest?q=Heroldstrasse,Muenster&types=STATION&maxLocations=10&source=DB`


#### /location/nearby

This is sometimes very broken, if you don't get results, try to combine /location/suggest with OSM Nominatim.

| Parameter         | Description                                                                                                |
|-------------------|------------------------------------------------------------------------------------------------------------|
| lat (Required)    | Latitude of search point.                                                                                  |
| lon (Required)    | Longitude of search point.                                                                                 |
| source (Required) | Source where your data comes from (default is Railteam).                                                   |
| types             | Set types of stops you want to get. You can decide between ANY (default), ADDRESS, STATION, POI and COORDS |
| maxDistance       | Define range of search                                                                                     |
| maxLocations      | Limiting location results.                                                                                 |

Example: `https://thepublictransport.de/location/nearby?lat=50.106529&lon=8.6599731&maxLocations=2&maxDistance=1000&source=DB`


#### /trips/name && /trips/id

Self explanatory: /name is for station names and /id for station IDs

| Parameter         | Description                                                                                         |
|-------------------|-----------------------------------------------------------------------------------------------------|
| from (Required)   | Name or ID of your start.                                                                           |
| to (Required)     | Name or ID of your end.                                                                             |
| source (Required) | Source where your data comes from (default is Railteam).                                            |
| when (Required)   | Time where the trip should start.                                                                   |
| accessibility     | For disabled people a way to make your way easier. You can choose between LIMITED and BARRIER_FREE. |
| optimization      | Optimizes your trip search. You can choose between LEAST_CHANGES, LEAST_DURATION and LEAST_WALKING. |
| walkspeed         | Defines your walkspeed. You can choose between SLOW, NORMAL and FAST.                               |

Example 1: `https://thepublictransport.de/trips/id?from=906606&to=693448&when=06.01.2019T06:00:00&walkspeed=NORMAL&optimization_LEAST_DURATION&source=DB`
Example 2: `https://thepublictransport.de/trips/name?from=Heroldstrasse,Muenster&to=Frankfurt&when=06.01.2019T06:00:00&walkspeed=NORMAL&optimization_LEAST_DURATION&&source=DB`


#### /trips/more

This is for pagination.

| Parameter          | Description                                                                          |
|--------------------|--------------------------------------------------------------------------------------|
| context (Required) | Context of your previous search. Always an included string in your previous request. |
| later              | Set to true if you want trips later than your search.                                |
| source (Required)  | Source where your data comes from (default is Railteam).                             |




## Warnings

All provides dates are Unix timestamps you have to divide by 1000. 

As this is a new API, changes will and can come, maybe some critical changes too.

All data provides are from another servers. If your request shows SERVICE_DOWN, then the providers have to fix it.