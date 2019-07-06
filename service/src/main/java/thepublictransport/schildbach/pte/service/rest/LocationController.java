/*
 * Copyright 2012-2019 the original author or authors.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package thepublictransport.schildbach.pte.service.rest;

import java.util.EnumSet;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thepublictransport.schildbach.pte.NetworkProvider;
import thepublictransport.schildbach.pte.service.framework.source.SourceResolver;

import thepublictransport.schildbach.pte.dto.Location;
import thepublictransport.schildbach.pte.dto.LocationType;
import thepublictransport.schildbach.pte.dto.NearbyLocationsResult;
import thepublictransport.schildbach.pte.dto.SuggestLocationsResult;

/**
 * @author Andreas Schildbach & Tristan Marsell
 */
@RestController
public class LocationController {
    private SourceResolver resolver = new SourceResolver();

    @Cacheable(value = "requests", key = "#query + #source", sync = true)
    @RequestMapping(value = "/api/suggest", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SuggestLocationsResult> suggest(@RequestParam("q") final String query,
                                  @RequestParam(value = "source", defaultValue = "None", required = false) final String source) throws Exception {
        NetworkProvider provider = resolver.getSource(source);

        return ResponseEntity.ok().body(provider.suggestLocations(query, null, 10));
    }

    @Cacheable(value = "requests", key = "{#source + #lat.toString() + #lon.toString()}", sync = true)
    @RequestMapping(value = "/api/nearby", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<NearbyLocationsResult> nearby(@RequestParam(value = "source", defaultValue = "None", required = false) final String source,
                                        @RequestParam("lat") final int lat, @RequestParam("lon") final int lon) throws Exception {
        NetworkProvider provider = resolver.getSource(source);
        final Location coord = Location.coord(lat, lon);
        return ResponseEntity.ok().body(provider.queryNearbyLocations(EnumSet.of(LocationType.STATION, LocationType.POI), coord, 5000, 100));
    }
}
