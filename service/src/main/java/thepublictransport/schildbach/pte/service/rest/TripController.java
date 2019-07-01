/*
 * Copyright the original author or authors.
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import thepublictransport.schildbach.pte.NetworkProvider;
import thepublictransport.schildbach.pte.dto.*;
import thepublictransport.schildbach.pte.service.framework.source.SourceResolver;
import thepublictransport.schildbach.pte.service.framework.tripoptions.TripOptionResolver;

/**
 * @author Andreas Schildbach & Tristan Marsell
 */
@RestController
public class TripController {
    private SourceResolver resolver = new SourceResolver();

    @RequestMapping(value = "/trip", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String trip(
            @RequestParam(value = "fromType", required = false, defaultValue = "ANY") final LocationType fromType,
            @RequestParam(value = "from", required = false) final String from,
            @RequestParam(value = "fromId", required = false) final String fromId,
            @RequestParam(value = "toType", required = false, defaultValue = "ANY") final LocationType toType,
            @RequestParam(value = "to", required = false) final String to,
            @RequestParam(value = "toId", required = false) final String toId,
            @RequestParam(value = "when", required = false) final String when,
            @RequestParam(value = "accessibility", required = false, defaultValue = "NEUTRAL") final String accessibility,
            @RequestParam(value = "optimization", required = false, defaultValue = "LEAST_DURATION") final String optimization,
            @RequestParam(value = "walkspeed", required = false, defaultValue = "NORMAL") final String walkspeed,
            @RequestParam(value = "source", defaultValue = "None") final String source) throws IOException {
        NetworkProvider provider = resolver.getSource(source);
        final Location fromLocation = new Location(fromType, fromId, null, from);
        final Location toLocation = new Location(toType, toId, null, to);

        Date date;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy'T'HH:mm:ss");

        try {
            date = format.parse(when);
        } catch (ParseException e) {
            date = new Date();
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.writeValueAsString(provider.queryTrips(fromLocation, null, toLocation, date, true, TripOptionResolver.INSTANCE.optionBuilder(accessibility, optimization, walkspeed)));
    }

}
