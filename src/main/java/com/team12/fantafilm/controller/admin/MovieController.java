package com.team12.fantafilm.controller.admin;

import com.team12.fantafilm.model.*;
import com.team12.fantafilm.service.impl.*;
import com.team12.fantafilm.util.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/admin/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private UploadImage uploadImage;
    @Autowired
    private RatedService ratedService;
    @Autowired
    private DirectorService directorService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private  MovieTypeService movieTypeService;
    @Autowired
    private  PerformerService performerService;

    @GetMapping("/view-all/page/{pageNumber}")
    public String findAll(Model model, @PathVariable("pageNumber") Integer pageNumber,
                          @RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "status", required = false) String status,
                          @RequestParam(value = "director", required = false) String directors,
                          @RequestParam(value = "movieType", required = false) String movieTypes,
                          @RequestParam(value = "language", required = false) String languages,
                          @RequestParam(value = "performer", required = false) String performers) {
        Page<Movie> page;
        page = movieService.filterMovies(pageNumber, directors, languages, movieTypes, performers, status, keyword);
        List<Rated> ratedId = ratedService.findAll();
        List<Director> directorId = directorService.findAll();
        List<Language> languageId = languageService.findAll();
        List<MovieType> movieTypeId = movieTypeService.findAll();
        List<Performer> performerId = performerService.findAll();

        model.addAttribute("ratedId", ratedId);
        model.addAttribute("languages", languageId);
        model.addAttribute("movieTypes", movieTypeId);
        model.addAttribute("directors", directorId);
        model.addAttribute("performers", performerId);
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listMovie", page.getContent());
        return "redirect:/admin/movie/view-all";
    }
}
