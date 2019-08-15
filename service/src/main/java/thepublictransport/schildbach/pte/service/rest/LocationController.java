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
import thepublictransport.schildbach.pte.dto.*;
import thepublictransport.schildbach.pte.service.framework.source.SourceResolver;

/**
 * @author Andreas Schildbach
 * @author Tristan Marsell
 * @author Felix Delattre
 */
@RestController
public class LocationController {
    private SourceResolver resolver = new SourceResolver();

    @Cacheable(value = "requests", key = "#query + #source + #maxLocations", sync = true)
    @RequestMapping(value = "/location/suggest", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SuggestLocationsResult> suggest(
            @RequestParam("q") final CharSequence query,
            @RequestParam(value = "source", defaultValue = "None", required = false) final String source,
            @RequestParam(value = "types", required = false, defaultValue = "ANY") final EnumSet<LocationType> types,
            @RequestParam(value = "maxLocations", required = false, defaultValue = "25") final Integer maxLocations

    ) throws Exception {
        NetworkProvider provider = resolver.getSource(source);

        return ResponseEntity.ok().body(provider.suggestLocations(query, types, maxLocations));
    }

    @Cacheable(value = "requests", key = "{#source + #lat.toString() + #lon.toString() + #maxDistance + #maxLocations}", sync = true)
    @RequestMapping(value = "/location/nearby", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<NearbyLocationsResult> nearby(
            @RequestParam(value = "source", defaultValue = "None", required = false) final String source,
            @RequestParam(value = "types", required = false, defaultValue = "ANY") final EnumSet<LocationType> types,
            @RequestParam("lat") final double lat,
            @RequestParam("lon") final double lon,
            @RequestParam(value = "maxDistance", required = false, defaultValue = "5000") final Integer maxDistance,
            @RequestParam(value = "maxLocations", required = false, defaultValue = "100") final Integer maxLocations

    ) throws Exception {

        NetworkProvider provider = resolver.getSource(source);
        final Location coord = Location.coord(Point.fromDouble(lat, lon));

        return ResponseEntity.ok().body(provider.queryNearbyLocations(types, coord, maxDistance, maxLocations));

    }
}
