package com.ecommerce.j3.controller.api;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"10. Tag"})
@Slf4j
@RestController
@RequestMapping("/api/tags")
@AllArgsConstructor
public class TagApiController{
}
