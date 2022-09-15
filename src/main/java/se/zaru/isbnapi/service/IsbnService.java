package se.zaru.isbnapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.zaru.isbnapi.model.ISBN;
import se.zaru.isbnapi.model.Validator;

@Service
@Slf4j
public class IsbnService extends Validator {

    public IsbnService() {
    }

    @Override
    public boolean isValid(String isbn) {
        if (isbn == null || isbn.matches("[0-9]") || (isbn.length() != 10 && isbn.length() != 13)) {
            return false;
        }
        if (isbn.length() == 13) {
            return isValidISBNNumber13(isbn);
        } else if (isbn.length() == 10) {
            return isValidISBNNumber10(isbn);
        } else {
            return false;
        }
    }

    public boolean isValidISBNNumber13(String isbn) {
        try {
            int sum = 0;
            for (int i = 0; i < 12; i++) {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                sum += (i % 2 == 0) ? digit * 1 : digit * 3;
            }
            int checksum = 1 - (sum % 10);
            return checksum == 1;
        } catch (NumberFormatException exception) {
            log.error("ISBN: " + isbn + " contains a letter" + exception.getMessage());
            return false;
        }
    }

    public boolean isValidISBNNumber10(String isbn) {
        try {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                int digit = isbn.charAt(i) - '0';
                if (0 > digit || 9 < digit)
                    return false;
                sum += (digit * (10 - i));
            }
            char last = isbn.charAt(9);
            if (last != 'X' && (last < '0' || last > '9'))
                return false;
            sum += ((last == 'X') ? 10 : (last - '0'));
            return (sum % 11 == 0);
        } catch (NumberFormatException exception) {
            log.error("ISBN: " + isbn + " contains a letter" + exception.getMessage());
            return false;
        }
    }

    public ResponseEntity< String > getResultOfValidation(ISBN isbnToValidate) {
        return isValid(isbnToValidate.getNumber().replaceAll("-", "")) ? new ResponseEntity<>("Is valid", HttpStatus.OK) : new ResponseEntity<>("Is not valid", HttpStatus.OK);
    }
}
