/*
 * Copyright the original author or authors.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package thepublictransport.schildbach.pte;

import okhttp3.HttpUrl;
import thepublictransport.schildbach.pte.dto.Product;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provider implementation for Deutsche Bahn (Germany).
 * 
 * @author Tristan Marsell
 * WIP
 */
public final class SncfProvider extends AbstractHafasClientInterfaceProvider {
    private static final HttpUrl API_BASE = HttpUrl.parse("https://sncf-maps.hafas.de/bin/");
    private static final Product[] PRODUCTS_MAP = { Product.HIGH_SPEED_TRAIN, // ICE-Züge
            Product.HIGH_SPEED_TRAIN, // Intercity- und Eurocityzüge
            Product.HIGH_SPEED_TRAIN, // Interregio- und Schnellzüge
            Product.REGIONAL_TRAIN, // Nahverkehr, sonstige Züge
            Product.SUBURBAN_TRAIN, // S-Bahn
            Product.BUS, // Busse
            Product.FERRY, // Schiffe
            Product.SUBWAY, // U-Bahnen
            Product.TRAM, // Straßenbahnen
            Product.ON_DEMAND, // Anruf-Sammeltaxi
            null, null, null, null };
    private static final String DEFAULT_API_CLIENT = "{\"id\":\"SNCF_LIVEMAP\",\"type\":\"WEB\",\"name\":\"webapp\",\"l\":\"vs_webapp\"}";

    public SncfProvider(final String apiAuthorization, final byte[] salt) {
        this(DEFAULT_API_CLIENT, apiAuthorization, salt);
    }

    public SncfProvider(final String apiClient, final String apiAuthorization, final byte[] salt) {
        super(NetworkId.SNCF, API_BASE, PRODUCTS_MAP);
        setApiVersion("1.18");
        setApiClient(apiClient);
        setApiAuthorization(apiAuthorization);
        setRequestChecksumSalt(salt);
    }
}
