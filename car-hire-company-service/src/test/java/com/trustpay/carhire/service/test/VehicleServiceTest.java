/**
 * 
 */
package com.trustpay.carhire.service.test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.trustpay.carhire.model.Book;
import com.trustpay.carhire.model.Customer;
import com.trustpay.carhire.model.Vehicle;
import com.trustpay.carhire.model.VehicleType;
import com.trustpay.carhire.service.VehicleService;


/**
 * @author Rodrigo Salvo
 *
 */
@RunWith( SpringRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
public class VehicleServiceTest {

    @Autowired
    private VehicleService vehicleService;


    @Test
    public void save() {

        saveVehicle();

    }


    @Test
    public void listAllAvailable() {

        saveVehicle();

        final Collection< Vehicle > vehicles = vehicleService.listAllAvailable();

        assertFalse( vehicles.isEmpty() );

    }


    @Test
    public void listAllBooked() {

        bookVehicle();

        final Collection< Book > vehicles = vehicleService.listAllBooked();

        assertFalse( vehicles.isEmpty() );

    }


    @Test
    public void book() {

        final Book booked = bookVehicle();

        assertNotNull( booked );

        assertNotNull( booked.getBookedWhen() );

    }


    private Vehicle saveVehicle() {

        final Vehicle vehicle = createVehicle();

        vehicleService.save( vehicle );

        return vehicle;
    }


    private Vehicle createVehicle() {

        return new Vehicle( UUID.randomUUID().toString(), VehicleType.CAR );
    }


    private Book bookVehicle() {

        final Vehicle vehicle = saveVehicle();

        final Customer customer = mock( Customer.class );
        when( customer.getEmail() ).thenReturn( UUID.randomUUID().toString() );

        return vehicleService.book( new Book( vehicle, customer ) );
    }

}
