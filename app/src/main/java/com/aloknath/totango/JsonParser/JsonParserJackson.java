package com.aloknath.totango.JsonParser;

import android.util.Log;
import com.aloknath.totango.Objects.Hits;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


/**
 * Created by ALOKNATH on 3/9/2015.
 */
public class JsonParserJackson {

    private static Hits hit;

    public static Hits parse(String json)  {

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode rootNode = mapper.readValue(json.getBytes(), JsonNode.class);
            JsonNode hits = rootNode.get("hits");


            if (hits != null)
            {

                hit = mapper.treeToValue(hits , Hits.class);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return hit;

    }
}

