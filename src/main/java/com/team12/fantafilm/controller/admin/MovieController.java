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


import java.text.SimpleDateFormat;
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
    public String findAll(Model model,
                          @PathVariable("pageNumber") Integer pageNumber,
                          @RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "status", required = false) String status,
                          @RequestParam(value = "director", required = false) String directors,
                          @RequestParam(value = "movieType", required = false) String movieTypes,
                          @RequestParam(value = "language", required = false) String languages,
                          @RequestParam(value = "performer", required = false) String performers) {
        movieService.checkStatusToday();
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

        return "admin/movie";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
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
        model.addAttribute("movie", new Movie());
        return "admin/form-add-movie";
    }
    @PostMapping("/save")
    public String save(Model model,
                       @RequestParam(name = "name") String name,
                       @RequestParam(name = "language") List<Language> languages,
                       @RequestParam(name = "movieType") List<MovieType> movieTypes,
                       @RequestParam(name = "trailer") String trailer,
                       @RequestParam(name = "performer") List<Performer> performers,
                       @RequestParam(name = "description") String description,
                       @RequestParam(name = "endDate") Date endDate,
                       @RequestParam(name = "premiereDate") Date premiereDate,
                       @RequestParam(name = "directors") List<Director> directors,
                       @RequestParam(name = "image") MultipartFile multipartFile,
                       @RequestParam(name = "movieDuration") Integer movieDuration,
                       @RequestParam(name = "ratedId") Rated rated,
                       @RequestParam(name = "id") Long id
    )
    {
        uploadImage.handerUpLoadFile(multipartFile);
        try {
            Movie movie = new Movie();
            if(id != -1)
            {
                movie= Movie.builder()
                        .id(id)
                        .movieDuration(movieDuration)
                        .name(name)
                        .description(description)
                        .trailer(trailer)
                        .endDate(endDate)
                        .premiereDate(premiereDate)
                        .image(multipartFile.getOriginalFilename())
                        .rated(rated)
                        .directors(directors)
                        .movieTypes(movieTypes)
                        .languages(languages)
                        .performers(performers)
                        .build();
            }
            else {
                 movie = Movie.builder()
                        .movieDuration(movieDuration)
                        .name(name)
                        .description(description)
                        .trailer(trailer)
                        .endDate(endDate)
                        .premiereDate(premiereDate)
                        .image(multipartFile.getOriginalFilename())
                        .rated(rated)
                        .directors(directors)
                        .movieTypes(movieTypes)
                        .languages(languages)
                        .performers(performers)
                        .build();
            }


            if (movieService.save(movie) instanceof Movie) {
                model.addAttribute("thanhCong", "Thêm thành công!");
            } else {
                model.addAttribute("thatBai", "Thêm thất bại");
            }
            model.addAttribute("movie", new Movie());
            return "redirect:/admin/movie/view-all/page/1?status=&keyword=";
        } catch (Exception e) {
            e.printStackTrace();
            return "admin/movie";
        }
    }
    @PostMapping("/add-director")
    public String addDirector(Model model,
                              @RequestParam(name="name") String name,
                              @RequestParam(name="image") MultipartFile multipartFile
    )
    {
        uploadImage.handerUpLoadFile(multipartFile);
        try
        {
            Director director = Director.builder()
                    .name(name)
                    .image(multipartFile.getOriginalFilename())
                    .build();
            directorService.addDirector(director);
            return  "redirect:/admin/movie/view-add";

        }
        catch (Exception e)
        {
            return null;
        }
    }
    @PostMapping("/add-movieType")
    public String addMovieType(@ModelAttribute("movieType") MovieType movieType)
    {
        if(movieTypeService.addMovieType(movieType))
        {
            return  "redirect:/admin/movie/view-add";
        }
        else {
            return "admin/movieType";
        }
    }
    @PostMapping("/add-language")
    public String addlanguage(@ModelAttribute("language") Language language)
    {
        if(languageService.addLanguage(language))
        {
            return  "redirect:/admin/movie/view-add";
        }
        else {
            return "admin/language/language";
        }
    }
    @PostMapping("/add-performer")
    public String addPerformer(Model model,
                               @RequestParam(name="name") String name,

                               @RequestParam(name="image") MultipartFile multipartFile
    )
    {
        uploadImage.handerUpLoadFile(multipartFile);
        try
        {
            Performer performer = Performer.builder()
                    .name(name)

                    .image(multipartFile.getOriginalFilename())
                    .build();
            performerService.addPerformer(performer);
            return  "redirect:/admin/movie/view-add";

        }
        catch (Exception e)
        {
            return null;
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable(name = "id") Long id, RedirectAttributes ra) {
        try {
            movieService.delete(id);
            ra.addFlashAttribute("successMessage", "Xóa thành công");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Xóa thất bại");
        }
        return "redirect:/admin/movie/view-all/page/1?status=&keyword=";
    }
    @GetMapping("/update/{pageNumber}/{id}")
    public String detailMovie(@PathVariable(name = "id") Long id, Model model, @PathVariable("pageNumber") Integer currentPage) {
        List<Director> movieDirecttor = directorService.findDireactorByMovieId(id);
        System.out.println(movieDirecttor);
        List<MovieType> movieTypes = movieTypeService.findMovieTyprbyMovieId(id);
        List<Language> languageSelector = languageService.findNameByMovieId(id);
        List<Performer> perfprmerSelect = performerService.findPerformerByMovieId(id);
        List<Rated> ratedId = ratedService.findAll();
        List<Director> directorId = directorService.findAll();
        List<Language> languageId = languageService.findAll();
        List<MovieType> movieTypeId = movieTypeService.findAll();
        List<Performer> performerId = performerService.findAll();

        model.addAttribute("ratedId", ratedId);
        model.addAttribute("ratedId", ratedId);
        model.addAttribute("languages", languageId);
        model.addAttribute("movieTypes", movieTypeId);
        model.addAttribute("directors", directorId);
        model.addAttribute("directorsSelect", movieDirecttor);
        model.addAttribute("performerSelect", perfprmerSelect);
        model.addAttribute("performers", performerId);
        model.addAttribute("languageSelect", languageSelector);

        Movie movie = movieService.findById(id);

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        SimpleDateFormat outputFormat = new SimpleDateFormat("M/dd/yyyy");
        Date start = movie.getPremiereDate();
        Date end = movie.getEndDate();
        String startDate = outputFormat.format(start);
        String endDate = outputFormat.format(end);
        model.addAttribute("premiereDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("movie",movie);
        model.addAttribute("id",id);

        return "admin/form-add-movie";
    }


}
