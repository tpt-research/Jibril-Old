/*
 * Copyright 2012-2015 the original author or authors.
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

import java.io.IOException;
import java.util.EnumSet;

import thepublictransport.schildbach.pte.NetworkProvider;
import thepublictransport.schildbach.pte.service.framework.source.SourceResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import thepublictransport.schildbach.pte.dto.Location;
import thepublictransport.schildbach.pte.dto.LocationType;
import thepublictransport.schildbach.pte.dto.NearbyLocationsResult;
import thepublictransport.schildbach.pte.dto.SuggestLocationsResult;

/**
 * @author Andreas Schildbach & Tristan Marsell
 */
@Controller
public class LocationController {
    private SourceResolver resolver = new SourceResolver();

    @RequestMapping(value = "/api/suggest", method = RequestMethod.GET)
    @ResponseBody
    public SuggestLocationsResult suggest(@RequestParam("q") final String query, @RequestParam(value = "source", defaultValue = "None", required = false) final String source) throws IOException {
        NetworkProvider provider = resolver.getSource(source);
        return provider.suggestLocations(query, null, 10);
    }

    @RequestMapping(value = "/api/nearby", method = RequestMethod.GET)
    @ResponseBody
    public NearbyLocationsResult nearby(@RequestParam(value = "source", defaultValue = "None", required = false) final String source, @RequestParam("lat") final int lat, @RequestParam("lon") final int lon)
            throws IOException {
        NetworkProvider provider = resolver.getSource(source);
        final Location coord = Location.coord(lat, lon);
        return provider.queryNearbyLocations(EnumSet.of(LocationType.STATION, LocationType.POI), coord, 5000, 100);
    }
}
