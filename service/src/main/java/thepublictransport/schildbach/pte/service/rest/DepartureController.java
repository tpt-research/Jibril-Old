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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import thepublictransport.schildbach.pte.NetworkProvider;
import thepublictransport.schildbach.pte.dto.QueryDeparturesResult;
import thepublictransport.schildbach.pte.service.framework.source.SourceResolver;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tristan Marsell
 */
@Controller
public class DepartureController {
    private SourceResolver resolver = new SourceResolver();

    @RequestMapping(value = "/departure", method = RequestMethod.GET)
    @ResponseBody
    public QueryDeparturesResult suggest(@RequestParam("stationId") final String query,
                                         @RequestParam(value = "source", defaultValue = "None", required = false) final String source,
                                         @RequestParam(value = "when", required = false) final String when,
                                         @RequestParam(value = "limit", required = false) final int limit,
                                         @RequestParam(value = "equiv", required = false) final boolean equiv
    ) throws IOException {
        NetworkProvider provider = resolver.getSource(source);

        Date date;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy'T'HH:mm:ss");

        try {
            date = format.parse(when);
        } catch (ParseException e) {
            date = new Date();
        }

        return provider.queryDepartures(query, date, limit, equiv);
    }
}
