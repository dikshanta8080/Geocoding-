package com.dikshanta.geocoding.geocoding_service.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class LocationResponsePojo {
    public int place_id;
    public String licence;
    public String osm_type;
    public int osm_id;
    public String lat;
    public String lon;
    @JsonProperty("class")
    public String myclass;
    public String type;
    public int place_rank;
    public double importance;
    public String addresstype;
    public String name;
    public String display_name;
    public ArrayList<String> boundingbox;

}
