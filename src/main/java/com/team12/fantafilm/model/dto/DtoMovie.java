package com.team12.fantafilm.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team12.fantafilm.model.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Data

public class DtoMovie {
    private Long id;
    private String name;
    private Integer movieDuration;
    private String trailer;
    private Date premiereDate;
    private String image;
    private Rated rated;
    private List<MovieType> movieTypeList = new ArrayList<>();
    private List<Language> languageList;
    private List<Performer> performerList;
    private List<Director> directorList;

}
