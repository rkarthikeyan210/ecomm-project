package com.ecommproject.productservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    //GET /hello
    @GetMapping("")
    public String sayHello()
    {
        return "Hello";
    }

    //GET /hello/{name}
    @GetMapping("/{name}")
    public String helloByName(@PathVariable("name") String name)
    {
        return "Hello " + name + "!";
    }

    //GET /hello/by-name?[q={xyz}]
    @GetMapping("/by-name")
    public String helloQueryParam(@RequestParam(name = "q", defaultValue = "there", required = false) String name)
    {
        return "Hello " + name + "!!";
    }
}
