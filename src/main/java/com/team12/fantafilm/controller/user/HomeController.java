package com.team12.fantafilm.controller.user;

import com.team12.fantafilm.model.Movie;
import com.team12.fantafilm.repository.UserRepository;
import com.team12.fantafilm.service.impl.DetailMovieService;
import com.team12.fantafilm.service.impl.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
@Log4j2
public class HomeController {

    private final DetailMovieService detailMovieService;

    private final UserService userService;

    private final UserRepository userRepository;

    public HomeController(DetailMovieService detailMovieService, UserService userService, UserRepository userRepository) {
        this.detailMovieService = detailMovieService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String home()
    {
        return "user/index";
    }

    @GetMapping("contact")
    public  String contact(){return  "user/contact";}

    @GetMapping("about")
    public  String about(){return "user/about";}

    @GetMapping("blog")
    public  String blog()
    {
        return "user/blog";
    }
//    @GetMapping("signup")
//    public String signup(){
//        return "user/signup";
//    }

    @GetMapping("all")
    public String moviegrid(){
        return ("user/all");
    }

    @GetMapping("order")
    public  String order()
    {
        return "redirect:/order/";
    }

    @GetMapping("detail-movie/{id}")
    public String detailMovie(@PathVariable("id") Long id, Model model){
        Movie movie = detailMovieService.getDetailMovie(id);
        model.addAttribute("movie", movie);
        return "user/detailMovie";
    }

    @GetMapping("information")
    public ModelAndView getInformation(Authentication authentication){
        var modelAndView = new ModelAndView("information");
        var username = authentication.getName();
        var userEntityOpt = userService.findUserByUserName(username);
        var userEntity = userEntityOpt.orElseThrow();

        modelAndView.addObject("user", userEntity);
        return modelAndView;
    }

    @PostMapping("information")
    public String updateInformation(@RequestParam String userName,
                                    @RequestParam String fullName,
                                    @RequestParam String email,
                                    @RequestParam String phoneNumber,
                                    Model model,
                                    Authentication authentication) throws Exception{
        var oldUsername = authentication.getName();
        var userEntity = userRepository.findUserEntityByUserName(oldUsername).orElseThrow();

        userEntity.setUserName(userName);
        userEntity.setFullName(fullName);
        userEntity.setEmail(email);
        userEntity.setPhoneNumber(phoneNumber);

        try{
            userRepository.save(userEntity);
            model.addAttribute("error", "Can not update!");
        }catch(Exception e){
            throw new Exception(e);
        }

        return "redirect:/information";
    }
}
