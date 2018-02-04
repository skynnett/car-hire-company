/**
 * 
 */
package com.trustpay.carhire.service;


import java.util.Collection;

import com.trustpay.carhire.model.Book;
import com.trustpay.carhire.model.Vehicle;


/**
 * @author Rodrigo Salvo
 *
 */
public interface VehicleService {

    void save( Vehicle vehicle );


    Collection< Vehicle > listAllAvailable();


    Collection< Book > listAllBooked();


    Book book( Book book );


    Collection< String > findVehicles( String text );
}
