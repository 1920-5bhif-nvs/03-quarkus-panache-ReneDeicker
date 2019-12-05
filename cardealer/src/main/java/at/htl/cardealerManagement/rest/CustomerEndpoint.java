package at.htl.cardealerManagement.rest;

import at.htl.cardealerManagement.business.CustomerRepository;
import at.htl.cardealerManagement.model.Customer;

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
import java.util.stream.Collectors;

@Path("/customers")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class CustomerEndpoint {

    @Inject
    CustomerRepository customerRepository;

    @POST
    @Path("/insertCustomer")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response insertCustomer(Customer customer) {
        try {
            customer = customerRepository.save(customer);
            return Response.ok().entity(customer).build();
        }catch(Exception e){
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/getCustomer/{id}")
    public Response getCustomer(@PathParam("id") long id) {
        return Response.ok(customerRepository.find("id", id)).build();
    }

    @GET
    @Path("/getCustomers")
    public Response getCustomers(){
        return Response.ok(customerRepository.listAll()).build();
    }

    @PUT
    @Path("/updateCustomer/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") long id, Customer updatedCustomer) {
        try {
            Customer current = customerRepository.findById(id);
            current.setIBAN(updatedCustomer.getIBAN());
            current.setBirthDate(updatedCustomer.getBirthDate());
            current.setCity(updatedCustomer.getCity());
            current.setFirstName(updatedCustomer.getFirstName());
            current.setLastName(updatedCustomer.getLastName());
            current.setPhoneNumber(updatedCustomer.getPhoneNumber());
            current.setStreet(updatedCustomer.getStreet());
            current.setZipCode(updatedCustomer.getZipCode());
            updatedCustomer = customerRepository.update(current);
            return Response.ok().entity(updatedCustomer).build();
        }catch(Exception e){
            return Response.serverError().build();
        }
    }

    @DELETE
    @Transactional
    @Path("/deleteCustomer/{id}")
    public Response deleteCustomer(@PathParam("id") long id) {
        try {
            Customer customer = customerRepository.findById(id);
            customerRepository.delete(customer);
            return Response.ok().entity(customer).build();
        }catch(Exception e){
            return Response.serverError().build();
        }
    }
}
