package at.htl.cardealerManagement.rest;

import at.htl.cardealerManagement.business.CarExemplarRepository;
import at.htl.cardealerManagement.model.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("/carExemplars")
public class CarExemplarEndpoint {

    @Inject
    CarExemplarRepository carExemplarRepository;

    @POST
    @Path("/insertCarExemplar")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response insertCarExemplar(CarExemplar carExemplar) {
        try {
            carExemplar = carExemplarRepository.save(carExemplar);
            return Response.ok().entity(carExemplar).build();
        }catch(Exception e){
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/getCarExemplar/{id}")
    public Response getCar(@PathParam("id") long id) {
        return Response.ok(carExemplarRepository.find("id", id)).build();
    }


    @GET
    @Path("/getCarExemplars")
    public Response getCarExemplars(){
        return Response.ok(carExemplarRepository.listAll()).build();
    }

    @PUT
    @Path("/updateCarExemplar/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCar(@PathParam("id") long id, CarExemplar updatedCarExemplar) {
        try {
            CarExemplar current = carExemplarRepository.findById(id);
            current.setCarType(updatedCarExemplar.getCarType());
            current.setColor(updatedCarExemplar.getColor());
            current.setHorsepower(updatedCarExemplar.getHorsepower());
            current.setMileage(updatedCarExemplar.getMileage());
            updatedCarExemplar = carExemplarRepository.update(current);
            return Response.ok().entity(updatedCarExemplar).build();
        }catch(Exception e){
            return Response.serverError().build();
        }
    }

    @DELETE
    @Transactional
    @Path("/deleteCarExemplar/{id}")
    public Response deleteCar(@PathParam("id") long id) {
        try {
            CarExemplar carExemplar = carExemplarRepository.findById(id);
            carExemplarRepository.delete(carExemplar);
            return Response.ok().entity(carExemplar).build();
        }catch(Exception e){
            return Response.serverError().build();
        }
    }
}
