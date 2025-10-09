package dev.am.catalog_service.catalog.web.exception;

import dev.am.catalog_service.config.SpringAppProperties;
import java.net.URI;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final URI NOT_FOUND_URI = URI.create("https://api.bookstore.com/errors/not-found");
    private static final URI ISE_URI = URI.create("https://api.bookstore.com/errors/internal-server-error");

    private final SpringAppProperties springAppProperties;

    GlobalExceptionHandler(SpringAppProperties springAppProperties) {
        this.springAppProperties = springAppProperties;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFoundException(ProductNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Product Not Found");
        problemDetail.setType(NOT_FOUND_URI);
        problemDetail.setProperty("service", springAppProperties.name());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnhandledException(Exception ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setType(ISE_URI);
        problemDetail.setProperty("service", springAppProperties.name());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
