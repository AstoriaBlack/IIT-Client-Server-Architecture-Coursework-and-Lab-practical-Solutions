//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.mapper;

import com.bookstore.exception.OutOfStockException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class OutOfStockExceptionMapper implements ExceptionMapper<OutOfStockException> {
    
    @Override
    public Response toResponse(OutOfStockException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getTitle());
        error.put("message", exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                      .entity(error)
                      .build();
    }
}