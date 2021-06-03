package org.jeecg.modules.demo.checked.controller;

import io.swagger.annotations.Api;
import org.jeecg.modules.demo.checked.entity.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("test")
public class MyController {
    @PostMapping("/person/list")
    public void hello(@RequestBody Person person){
        String name = person.getName();
        System.out.println(name);
    }
}
