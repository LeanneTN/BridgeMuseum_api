package com.example.bridgemuseum_api.controller;

import com.example.bridgemuseum_api.common.CommonResponse;
import com.example.bridgemuseum_api.domain.Poem;
import com.example.bridgemuseum_api.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

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

    @GetMapping("/poems/name/{name}")
    public CommonResponse<List<Poem>> getPoemsByName(@PathVariable("name") String name){
        return poemService.getPoemsByName(name);
    }

    @GetMapping("/poems/poet/{poet}")
    public CommonResponse<List<Poem>> getPoemsByPoet(@PathVariable("poet") String poet){
        return poemService.getPoemsByPoet(poet);
    }

    @GetMapping("/poems/dynasty/{dynasty}")
    public CommonResponse<List<Poem>> getPoemsByDynasty(@PathVariable("dynasty") String dynasty){
        return poemService.getPoemsByDynasty(dynasty);
    }

    @PostMapping("/")
    public CommonResponse<Poem> modifyPoem(@RequestBody @NotBlank Poem poem){
        return poemService.modifyPoem(poem);
    }

    @DeleteMapping("/id/{id}")
    public CommonResponse<Poem> deletePoemById(@PathVariable("id") Integer id){
        return poemService.deletePoemById(id);
    }

    @DeleteMapping("/")
    public CommonResponse<Poem> deletePoemByPoem(@RequestBody @NotBlank Poem poem){
        return poemService.deletePoemByPoem(poem);
    }
}
