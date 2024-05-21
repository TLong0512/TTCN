package com.team12.fantafilm.controller.user;

import com.team12.fantafilm.model.Phim;
import com.team12.fantafilm.model.User;
import com.team12.fantafilm.repository.PhimRepository;
import com.team12.fantafilm.service.impl.PhimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/all")
public class PhimController {
    @Autowired
    private PhimService phimService;

    @GetMapping
    public String findByName(@RequestParam(value = "name", required = false) String name, Model model) {
        List<Phim> list;
        try {
            if (name == null || name.isEmpty()) {
                list = this.phimService.getAll();
                name = ""; // Set giá trị mặc định cho `searchTerm` nếu `name` là null
            } else {
                list = this.phimService.findByName(name);
            }
            model.addAttribute("list", list);
            model.addAttribute("searchTerm", name);
            return "user/all";
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            return "error/500"; // Trả về trang lỗi tùy chỉnh nếu có
        }
    }



}
