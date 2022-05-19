package com.doubletex.app.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
@JsonIgnoreProperties({"suppressed", "cause", "stackTrace", "localizedMessage", "message"})
public class DbtBadRequest extends  RuntimeException{
    private int httpCode = HttpStatus.BAD_REQUEST.value();
    private Map<String, Set<String>> fields = new HashMap<>();
    private LocalDateTime time = LocalDateTime.now();

    private static ThreadLocal<DbtBadRequest> local = ThreadLocal.withInitial(DbtBadRequest::new);

    public void addValidation(String fieldName, String validationMessage) {
        Set<String> validationMessages;
        if (fields.containsKey(fieldName)) {
            validationMessages = fields.get(fieldName);
        } else {
            validationMessages = new TreeSet<>();
        }
        validationMessages.add(validationMessage);
        fields.put(fieldName, validationMessages);
    }

    public void throwIfNecessary() throws DbtBadRequest {
        if (!fields.isEmpty()) throw this;
    }

    public static DbtBadRequest current() {
        return local.get();
    }
}
