package com.ecommproject.productservice.controller;

import com.ecommproject.productservice.dto.Hello;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    //GET /hello
    @GetMapping("")
    public Hello sayHello()
    {
        return new Hello("Hello");
    }

    //GET /hello/{name}
    @GetMapping("/{name}")
    public Hello helloByName(@PathVariable("name") String name)
    {
        return new Hello(String.format("Hello %s", name));
    }

    //GET /hello/?name={name}
    @GetMapping("/")
    public Hello helloQueryParam(@RequestParam(name = "name", defaultValue = "there", required = false) String name)
    {
        return new Hello(String.format("Hello %s, how are you?", name));
    }
}
