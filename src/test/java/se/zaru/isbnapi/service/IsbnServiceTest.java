package se.zaru.isbnapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.zaru.isbnapi.model.ISBN;

import static org.junit.jupiter.api.Assertions.*;

class IsbnServiceTest {

    private static final String VALID_ISBN_13 = "9783161484100";
    private static final String INVALID_ISBN_13 = "ab83161484100";
    private static final String VALID_ISBN_10 = "9185057819";
    private static final String INVALID_ISBN_10 = "ab85057819";
    private static final String INVALID_ISBN_11 = "91850578192";
    private static final String INVALID_ISBN_14 = "97831614841006";
    private static final String VALID_ISBN_13_WITH_DASH = "978-3161-4841-00";
    private static final String INVALID_ISBN_13_WITH_DASH = "13-978-3161-4841-00";
    ISBN validIsbn13WithDash;
    ISBN invalidIsbn13WithDash;
    IsbnService isbnService;

    @BeforeEach
    void setUp() {
        validIsbn13WithDash = new ISBN();
        invalidIsbn13WithDash = new ISBN();
        isbnService = new IsbnService();
        validIsbn13WithDash.setNumber(VALID_ISBN_13_WITH_DASH);
        invalidIsbn13WithDash.setNumber(INVALID_ISBN_13_WITH_DASH);
    }


    @Test
    void is_Valid_ISBN_13_Should_Return_True() {
        assertTrue(isbnService.isValid(VALID_ISBN_13));
    }

    @Test
    void is_Not_Valid_ISBN_13_Should_Return_False() {
        assertFalse(isbnService.isValid(INVALID_ISBN_13));
    }

    @Test
    void is_Valid_ISBN_13_With_Dash_Should_Return_True() {
        ResponseEntity< String > resultOfValidationActual = isbnService.getResultOfValidation(validIsbn13WithDash);
        ResponseEntity< String > resultOfValidationExpected = new ResponseEntity<>("Is valid", HttpStatus.OK);
        assertEquals(resultOfValidationExpected, resultOfValidationActual);
    }

    @Test
    void is_Not_Valid_ISBN_13_With_Dash_Should_Return_False() {
        ResponseEntity< String > resultOfValidationExpected = new ResponseEntity<>("Is not valid", HttpStatus.OK);
        ResponseEntity< String > resultOfValidationActual = isbnService.getResultOfValidation(invalidIsbn13WithDash);
        assertEquals(resultOfValidationExpected, resultOfValidationActual);
    }

    @Test
    void is_Not_Valid_ISBN_11_Should_Return_False() {
        assertFalse(isbnService.isValid(INVALID_ISBN_11));
    }

    @Test
    void is_Not_Valid_ISBN_14_Should_Return_False() {
        assertFalse(isbnService.isValid(INVALID_ISBN_14));
    }

    @Test
    void is_Valid_ISBN_10_Should_Return_True() {
        assertTrue(isbnService.isValid(VALID_ISBN_10));
    }

    @Test
    void is_Not_Valid_ISBN_10_Should_Return_False() {
        assertFalse(isbnService.isValid(INVALID_ISBN_10));
    }

    @Test
    void is_Not_Valid_ISBN_13_Should_Return_False_The_Value_Is_Null() {
        assertFalse(isbnService.isValid(null));
    }
}
