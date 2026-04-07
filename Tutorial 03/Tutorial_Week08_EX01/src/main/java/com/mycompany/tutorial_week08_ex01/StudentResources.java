package com.example;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;
import java.util.concurrent.*;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResources {

    private static final ConcurrentHashMap<String, Student> studentStore = new ConcurrentHashMap<>();

    static {
        addInitialStudents();
    }

    private static void addInitialStudents() {
        Student student1 = new Student(UUID.randomUUID().toString(), "Alice", "Smith");
        Student student2 = new Student(UUID.randomUUID().toString(), "Bob", "Johnson");
        Student student3 = new Student(UUID.randomUUID().toString(), "Charlie", "Brown");
        
        studentStore.put(student1.getId(), student1);
        studentStore.put(student2.getId(), student2);
        studentStore.put(student3.getId(), student3);
    }

    @GET
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentStore.values());
    }

    @GET
    @Path("/{id}")
    public Response getStudentById(@PathParam("id") String id) {
        Student student = studentStore.get(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with ID " + id + " not found")
                    .build();
        }
        return Response.ok(student).build();
    }

    @POST
    public Response createStudent(Student student) {
        if (student.getFirstName() == null || student.getLastName() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("First name and last name are required")
                    .build();
        }
        
        String id = UUID.randomUUID().toString();
        student.setId(id);
        studentStore.put(id, student);
        
        return Response.status(Response.Status.CREATED)
                .entity(student)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateStudent(@PathParam("id") String id, Student updatedStudent) {
        Student existingStudent = studentStore.get(id);
        if (existingStudent == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with ID " + id + " not found")
                    .build();
        }
        
        if (updatedStudent.getFirstName() != null) {
            existingStudent.setFirstName(updatedStudent.getFirstName());
        }
        if (updatedStudent.getLastName() != null) {
            existingStudent.setLastName(updatedStudent.getLastName());
        }
        
        studentStore.put(id, existingStudent);
        return Response.ok(existingStudent).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") String id) {
        Student removedStudent = studentStore.remove(id);
        if (removedStudent == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with ID " + id + " not found")
                    .build();
        }
        return Response.noContent().build();
    }
}