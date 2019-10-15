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

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Andreas Schildbach
 * @author Tristan Marsell
 * @author Felix Delattre
 */
@RestController
public class ErrorShowController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        System.err.println("========BEGIN========");
        System.err.println("Date: " + new Date().toString());
        System.err.println("IP: " + request.getRemoteAddr());
        System.err.println("Message: " + "Weird Request. Please check NGINX logs!");
        System.err.println("Host: " + request.getRemoteHost());
        System.err.println("Cookies:" + prepareCookies(request.getCookies()));
        System.err.println("UserAgent: " + request.getHeader("User-Agent"));
        System.err.println("=========END=========");
        return  "That did not work. Reported to the admins. \n" +
                "If that wasn't a hacking attempt, you have nothing to worry about. :D \n" +
                "Otherwise GTFO :)";
    }

    public String prepareCookies(Cookie[] cookies) {
        String cookieString = "";

        for(int i = 0; i != cookies.length; i++) {
            cookieString += " Cookie" + i + ": Name/" + cookies[i].getName() + " Domain/" + cookies[i].getDomain() + " Value/" + cookies[i].getValue();
        }

        return cookieString;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
