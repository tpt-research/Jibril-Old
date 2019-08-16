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

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thepublictransport.schildbach.pte.NetworkProvider;
import thepublictransport.schildbach.pte.dto.QueryDeparturesResult;
import thepublictransport.schildbach.pte.service.framework.source.SourceResolver;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Tristan Marsell
 */
@RestController
public class DepartureController {
    private SourceResolver resolver = new SourceResolver();

    @RequestMapping(value = "/departure", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<QueryDeparturesResult> suggest(@RequestParam("stationId") final String query,
                                         @RequestParam(value = "source", defaultValue = "None", required = false) final String source,
                                         @RequestParam(value = "hourshift", required = false, defaultValue = "0") final int hourshift,
                                         @RequestParam(value = "limit", required = false, defaultValue = "10") final int limit,
                                         @RequestParam(value = "equiv", required = false) final boolean equiv
    ) throws IOException {
        NetworkProvider provider = resolver.getSource(source);


        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, hourshift); // adds one hour

        return ResponseEntity.ok().body(provider.queryDepartures(query, cal.getTime(), limit, equiv));
    }
}
