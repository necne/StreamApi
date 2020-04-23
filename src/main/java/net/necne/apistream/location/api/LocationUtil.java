package net.necne.apistream.location.api;

import net.necne.apistream.location.Location;

public class LocationUtil {
    private static final String LEGACY_NAME_LABEL = "%s: %s";

    static String getLegacyNameLabel(final Location location) {
        return String.format(LEGACY_NAME_LABEL, location.getName(), location.getState().getLabel());
    }    

}