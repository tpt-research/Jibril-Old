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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thepublictransport.schildbach.pte.NetworkProvider;
import thepublictransport.schildbach.pte.dto.*;
import thepublictransport.schildbach.pte.service.framework.source.SourceResolver;
import thepublictransport.schildbach.pte.service.framework.tools.DateTools;
import thepublictransport.schildbach.pte.service.framework.tripoptions.TripOptionResolver;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * @author Andreas Schildbach
 * @author Tristan Marsell
 * @author Felix Delattre
 */
@RestController
public class TripController {
    private SourceResolver resolver = new SourceResolver();

     Set<Product> allProducts = Product.ALL;

    @Cacheable(value = "requests", key = "{#from + #to + #when + #products.toArray().toString() + #accessibility + #optimization + #walkspeed + #source}", sync = true)
    @RequestMapping(value = "/trips/name", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResponseEntity<String> trip(
            @RequestParam(value = "from", required = false) final String from,
            @RequestParam(value = "to", required = false) final String to,
            @RequestParam(value = "when", required = false) final String when,
            @RequestParam(value = "products", required = false, defaultValue = "") final Set<Product> products,
            @RequestParam(value = "accessibility", required = false, defaultValue = "NEUTRAL") final String accessibility,
            @RequestParam(value = "optimization", required = false, defaultValue = "LEAST_DURATION") final String optimization,
            @RequestParam(value = "walkspeed", required = false, defaultValue = "NORMAL") final String walkspeed,
            @RequestParam(value = "source", defaultValue = "None") final String source
    ) throws IOException {

        NetworkProvider provider = resolver.getSource(source);
        final Location fromLocation = new Location(LocationType.ANY, null, null, from);
        final Location toLocation = new Location(LocationType.ANY, null, null, to);

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

        return ResponseEntity.ok().body(
                mapper.writeValueAsString(
                        provider.queryTrips(
                                fromLocation,
                                null,
                                toLocation,
                                date,
                                true,
                                TripOptionResolver.INSTANCE.optionBuilder(
                                        !products.isEmpty() ? products : Product.ALL,
                                        accessibility,
                                        optimization,
                                        walkspeed
                                )
                        )
                )
        );
    }

    @Cacheable(value = "requests", key = "{#from + #to + #when + #products.toArray().toString() + #accessibility + #optimization + #walkspeed + #source}", sync = true)
    @RequestMapping(value = "/trips/id", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResponseEntity<String> tripId(
            @RequestParam(value = "from", required = false) final String from,
            @RequestParam(value = "to", required = false) final String to,
            @RequestParam(value = "via", required = false) final String via,
            @RequestParam(value = "when", required = false) final String when,
            @RequestParam(value = "products", required = false, defaultValue = "") final Set<Product> products,
            @RequestParam(value = "accessibility", required = false, defaultValue = "NEUTRAL") final String accessibility,
            @RequestParam(value = "optimization", required = false, defaultValue = "LEAST_DURATION") final String optimization,
            @RequestParam(value = "walkspeed", required = false, defaultValue = "NORMAL") final String walkspeed,
            @RequestParam(value = "source", defaultValue = "None") final String source
    ) throws IOException {

        NetworkProvider provider = resolver.getSource(source);

        final Location fromLocation = new Location(LocationType.STATION, from, null, null);
        final Location toLocation = new Location(LocationType.STATION, to, null, null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return ResponseEntity.ok().body(
                mapper.writeValueAsString(
                        provider.queryTrips(
                            fromLocation,
                            null,
                            toLocation,
                            DateTools.INSTANCE.parseDate(when),
                            true,
                            TripOptionResolver.INSTANCE.optionBuilder(
                                    !products.isEmpty() ? products : Product.ALL,
                                    accessibility,
                                    optimization,
                                    walkspeed
                            )
                        )
                )
        );
    }

    @RequestMapping(value = "/trips/more", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<QueryTripsResult> more(
            @RequestParam(value = "source", defaultValue = "None", required = true) final String source,
            @RequestParam(value = "context", required = true) final QueryTripsContext context,
            @RequestParam(value = "later", required = false, defaultValue = "true") final Boolean later
    ) throws IOException {
        NetworkProvider provider = resolver.getSource(source);

        return ResponseEntity.ok(provider.queryMoreTrips(context, later));
    }


}
