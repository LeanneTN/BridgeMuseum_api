package com.example.bridgemuseum_api.controller;

import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Poem;
import com.example.bridgemuseum_api.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/poem")
public class PoemController {
    @Autowired
    private PoemService poemService;

    @GetMapping("/id/{id}")
    public CommonResponse<Poem> getPoemById(@PathVariable("id") Integer id){
        return poemService.getPoemById(id);
    }

    @PutMapping("/")
    public CommonResponse<Poem> addPoem(@RequestBody @NotBlank Poem poem){
        return poemService.addPoem(poem);
    }
}
