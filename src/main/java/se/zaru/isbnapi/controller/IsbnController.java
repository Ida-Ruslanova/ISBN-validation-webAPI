package se.zaru.isbnapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.zaru.isbnapi.model.ISBN;
import se.zaru.isbnapi.service.IsbnService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/isbn")
public class IsbnController {

    private final IsbnService isbnService;

    public IsbnController(IsbnService isbnService) {
        this.isbnService = isbnService;
    }

    @GetMapping("/validateisbnnumber/{isbn}")
    public ResponseEntity< ? > getResultOfValidation(@PathVariable(value = "isbn") String isbn) {
        return isbnService.getResultOfValidation(new ISBN(isbn));
    }
}
