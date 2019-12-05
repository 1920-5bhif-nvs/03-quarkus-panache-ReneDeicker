package at.htl.cardealerManagement.rest;

import at.htl.cardealerManagement.business.EmployeeRepository;
import at.htl.cardealerManagement.model.Customer;
import at.htl.cardealerManagement.model.Employee;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/employees")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeEndpoint {
    @Inject
    EmployeeRepository employeeRepository;

    @POST
    @Path("/insertEmployee")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response insertEmployee(Employee employee) {
        try {
            employee = employeeRepository.save(employee);
            return Response.ok().entity(employee).build();
        }catch(Exception e){
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/getEmployee/{id}")
    public Response getEmployee(@PathParam("id") long id) {
        return Response.ok(employeeRepository.find("id", id)).build();
    }

    @GET
    @Path("/getEmployees")
    public Response getEmployees(){
        return Response.ok(employeeRepository.listAll()).build();
    }

    @PUT
    @Path("/updateEmployee/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("id") long id, Employee updatedEmployee) {
        try {
            Employee current = employeeRepository.findById(id);
            current.setSalary(updatedEmployee.getSalary());
            current.setsocialSecurityNumber(updatedEmployee.getsocialSecurityNumber());
            current.setBirthDate(updatedEmployee.getBirthDate());
            current.setCity(updatedEmployee.getCity());
            current.setFirstName(updatedEmployee.getFirstName());
            current.setLastName(updatedEmployee.getLastName());
            current.setPhoneNumber(updatedEmployee.getPhoneNumber());
            current.setStreet(updatedEmployee.getStreet());
            current.setZipCode(updatedEmployee.getZipCode());

            updatedEmployee = employeeRepository.update(current);
            return Response.ok().entity(updatedEmployee).build();
        }catch(Exception e){
            return Response.serverError().build();
        }
    }

    @DELETE
    @Transactional
    @Path("/deleteEmployee/{id}")
    public Response deleteEmployee(@PathParam("id") long id) {
        try {
            Employee employee = employeeRepository.findById(id);
            employeeRepository.delete(employee);
            return Response.ok().entity(employee).build();
        }catch(Exception e){
            return Response.serverError().build();
        }
    }
}
