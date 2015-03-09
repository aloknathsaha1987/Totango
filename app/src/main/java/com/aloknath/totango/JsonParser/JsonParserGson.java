package com.aloknath.totango.JsonParser;

import com.aloknath.totango.Objects.ParsedJsonListObject;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ALOKNATH on 3/6/2015.
 */
public class JsonParserGson {

    private static List<ParsedJsonListObject> listObjects = new ArrayList<>();

    public static List<ParsedJsonListObject> jsonParserGson(String jsonString){

        Map jsonJavaRootObject = new Gson().fromJson(jsonString, Map.class);

        int size = (
                (List)
                        (

                                (Map)
                                        (

                                                (Map)
                                                        (
                                                                jsonJavaRootObject.get("response")
                                                        )

                                        ).get("accounts")
                        ).get("hits")
        ).size();

        for(int i = 0; i < size; i++){

            ParsedJsonListObject newObject = new ParsedJsonListObject();
            newObject.setDisplayName((String) (
                    (Map)
                            (
                                    (List)
                                            (

                                                    (Map)
                                                            (

                                                                    (Map)
                                                                            (
                                                                                    jsonJavaRootObject.get("response")
                                                                            )

                                                            ).get("accounts")
                                            ).get("hits")
                            ).get(i)
            ).get("display_name"));

            Double salary = (Double) (
                    (List)
                            (
                                    (Map)
                                            (
                                                    (List)
                                                            (

                                                                    (Map)
                                                                            (

                                                                                    (Map)
                                                                                            (
                                                                                                    jsonJavaRootObject.get("response")
                                                                                            )

                                                                            ).get("accounts")
                                                            ).get("hits")
                                            ).get(i)
                            ).get("selected_fields")
            ).get(3);

            if(salary != null) {
                newObject.setSalary(salary);
            }

            Double engagedUsers = (Double) (
                    (List)
                            (
                                    (Map)
                                            (
                                                    (List)
                                                            (

                                                                    (Map)
                                                                            (

                                                                                    (Map)
                                                                                            (
                                                                                                    jsonJavaRootObject.get("response")
                                                                                            )

                                                                            ).get("accounts")
                                                            ).get("hits")
                                            ).get(i)
                            ).get("selected_fields")
            ).get(5) ;

            if(engagedUsers != null) {
                newObject.setEngagedUsers(engagedUsers);
            }

            Double time = (Double) (
                    (List)
                            (
                                    (Map)
                                            (
                                                    (List)
                                                            (

                                                                    (Map)
                                                                            (

                                                                                    (Map)
                                                                                            (
                                                                                                    jsonJavaRootObject.get("response")
                                                                                            )

                                                                            ).get("accounts")
                                                            ).get("hits")
                                            ).get(i)
                            ).get("selected_fields")
            ).get(2);

            if (time != null) {
                newObject.setTime(time);
            }

            listObjects.add(newObject);
        }

        return listObjects;

    }
}
