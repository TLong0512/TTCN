package com.team12.fantafilm.service;

import java.util.List;

import com.team12.fantafilm.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMovieService {
    List<Movie> findAll();

    Movie save(Movie movie);

    Movie update(Long id, Movie Movie);

    void delete(Long id);

    Movie findById(Long id);

    void exportExcel();

    Movie readExcel();

    List<Movie> showPhimDangChieu();

    List<Movie> showPhimSapChieu();

    List<Movie> showPhishowPhimSapChieuAndDangChieumSapChieu();

    List<Movie> getMovie(String cinemaId, String movieId);

    Page<Movie> getAll(Integer pageNumber);

    Pageable pageMovie(Integer pageNumber);

    Movie findByName(String name);
    void checkStatusToday();
    Page<Movie> filterMovies(Integer pageNumber, String directors, String languages, String movieTypes,  String performers, String status, String keyword);
}
