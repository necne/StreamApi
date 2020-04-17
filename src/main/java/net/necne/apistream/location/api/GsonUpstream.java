package net.necne.apistream.location.api;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.necne.apistream.location.Location;

public class GsonUpstream {

    private static final Gson GSON_1;
    private static final Gson GSON_2;
    private static final Gson GSON_3;

    static {
        GsonBuilder gb = new GsonBuilder().setPrettyPrinting();

        gb.registerTypeHierarchyAdapter(Location.class, new JsonSerializer<Location>() {

            @Override
			public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject jo = new JsonObject();
                jo.addProperty("key", src.getId());
                jo.addProperty("name", src.getName());
                jo.addProperty("sortCode", src.getSortCode());
                jo.addProperty("status", src.getStatus());
				return jo;
			}

        });
        GSON_3 = gb.create();

        gb.registerTypeHierarchyAdapter(Location.class, new JsonSerializer<Location>() {

            @Override
			public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject jo = GSON_3.toJsonTree(src).getAsJsonObject();
                jo.addProperty("name", LocationUtil.getLegacyNameLabel(src));
                jo.remove("status");
				return jo;
			}

        });
        GSON_2 = gb.create();

        gb.registerTypeHierarchyAdapter(Location.class, new JsonSerializer<Location>() {

            @Override
			public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject jo = GSON_2.toJsonTree(src).getAsJsonObject();
                jo.remove("sortCode");
				return jo;
			}

        });
        GSON_1 = gb.create();

    }

    public static Gson getGson(int version) {
        switch(version){
            case 3: return GSON_3;
            case 2: return GSON_2;
            case 1: return GSON_1;
            default: 
                throw new IllegalArgumentException("Unsupported version " + version);
        }
    }

}