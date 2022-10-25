package com.example.shopapi;

import com.example.shopcore.ReviewCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @GetMapping("/code-title/delete")
    public String getCode() {
        return ReviewCode.DELETE.getTitle();
    }

}
