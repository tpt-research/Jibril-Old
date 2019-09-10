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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import thepublictransport.schildbach.pte.NetworkProvider;
import thepublictransport.schildbach.pte.dto.Location;
import thepublictransport.schildbach.pte.dto.LocationType;
import thepublictransport.schildbach.pte.dto.QueryTripsContext;
import thepublictransport.schildbach.pte.dto.QueryTripsResult;
import thepublictransport.schildbach.pte.service.framework.source.SourceResolver;
import thepublictransport.schildbach.pte.service.framework.tools.DateTools;
import thepublictransport.schildbach.pte.service.framework.tripoptions.TripOptionResolver;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Andreas Schildbach
 * @author Tristan Marsell
 * @author Felix Delattre
 */
@RestController
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public RedirectView main() {
        String correctUrl = "https://github.com/thepublictransport";
        RedirectView rv = new RedirectView(correctUrl);
        rv.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return rv;
    }
}
