package net.necne.apistream.location;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Location {
    long id;
    String name;
    int sortCode;
    String status;
}