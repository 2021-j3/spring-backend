package com.ecommerce.j3.controller.api;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"13. Suggestion"})
@Slf4j
@RestController
@RequestMapping("/api/suggestions")
@AllArgsConstructor
public class SuggestionApiController {
}
