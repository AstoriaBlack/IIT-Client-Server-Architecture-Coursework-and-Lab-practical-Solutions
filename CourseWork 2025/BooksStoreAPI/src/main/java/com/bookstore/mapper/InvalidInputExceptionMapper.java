//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.mapper;

import com.bookstore.exception.InvalidInputException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class InvalidInputExceptionMapper implements ExceptionMapper<InvalidInputException> {
    
    @Override
    public Response toResponse(InvalidInputException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getTitle());
        error.put("message", exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                      .entity(error)
                      .build();
    }
}