package net.necne.apistream.springboot.rest.location;

import java.util.List;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.necne.apistream.location.Location;
import net.necne.apistream.location.LocationDao;
import net.necne.apistream.location.api.GsonDownstream;
import net.necne.apistream.location.api.GsonUpstream;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/location")
public class LocationController {

    private static final int API_VERSION_MAX = 3;

    @RequestMapping(value="/compare", method=RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getStreams() {
        StringEscapeUtils.Builder sbHtml = StringEscapeUtils.builder(StringEscapeUtils.ESCAPE_HTML4);
        sbHtml.append("<html><body><table cellpadding=\"20\"><tr><th>Version</th><th>Downstream</th><th>Upstream</th></tr>");

        List<Location> locations = LocationDao.find();
        for(int version = API_VERSION_MAX; version > 0; --version) {
            sbHtml.append("<tr><th>")
            .escape(Integer.toString(version))
            .append("</th><td><pre>")
            .escape(GsonDownstream.getGson(version).toJson(locations))
            .append("</pre></td><td><pre>")
            .escape(GsonUpstream.getGson(version).toJson(locations))
            .append("</pre></td>")
            ;
        }
        sbHtml.append("</table></body></html>");
        return sbHtml.toString();
    }

    @RequestMapping(value="/v{apiVersion}/ds", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDownstream(@PathVariable int apiVersion) {
        return GsonDownstream.getGson(apiVersion).toJson(LocationDao.find());
    }

    @RequestMapping(value="/v{apiVersion}/us", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUpstream(@PathVariable int apiVersion) {
        return GsonDownstream.getGson(apiVersion).toJson(LocationDao.find());
    }

    
}