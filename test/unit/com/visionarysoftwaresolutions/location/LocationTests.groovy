package com.visionarysoftwaresolutions.location

import grails.test.mixin.*
import org.junit.*

@TestFor(Location)
@Mock(StreetAddress)

class LocationTests {
    StreetAddress address = new StreetAddress(address:"3116 S. Mill Ave",
        city:"Tempe",
        region:'Arizona',
        postalCode:"85281",
        country:"USA"
    )
    ContactInformation contact = new ContactInformation(email:"visionary.software.solutions@gmail.com")
    Location toTest

    void testValidLocation() {
        toTest = new Location(latitude:85, 
                              longitude:80,
                              address:address,
                              contact:contact
                          )
        mockForConstraintsTests(Location, [toTest])
        assert toTest.validate()
    }
    
    void testInvalidLocationNullAddress() {
        toTest = new Location(latitude:85, 
                              longitude:80,
                              contact:contact
                          )
        mockForConstraintsTests(Location, [toTest])
        assert !toTest.validate()
        assert "nullable" == toTest.errors["address"]
    }

    void testInvalidLocationNullContact() {
        toTest = new Location(latitude:85,
                              longitude:80,
                              address:address
                          )
        mockForConstraintsTests(Location, [toTest])
        assert !toTest.validate()
        assert "nullable" == toTest.errors["contact"]
    }

    void testInvalidLowLatitude() {
        toTest = new Location(latitude:-92,
                              longitude:80,
                              address:address,
                              contact:contact
                          )
        mockForConstraintsTests(Location, [toTest])
        assert  !toTest.validate()
        assert  "range" == toTest.errors["latitude"]
    }

    void testInvalidHighLatitude() {
        toTest = new Location(latitude:92,
                              longitude:80,
                              address:address,
                              contact:contact
                          )
        mockForConstraintsTests(Location, [toTest])
        assert  !toTest.validate()
        assert  "range" == toTest.errors["latitude"]
    }

    void testInvalidLowLongitude() {
        toTest = new Location(latitude:-92,
                              longitude:-200,
                              address:address,
                              contact:contact
                          )
        mockForConstraintsTests(Location, [toTest])
        assert !toTest.validate()
        assert "range" == toTest.errors["longitude"]
    }

    void testInvalidHighLongitude() {
        toTest = new Location(latitude:92,
                              longitude:200,
                              address:address,
                              contact:contact
                          )
        mockForConstraintsTests(Location, [toTest])
        assert !toTest.validate()
        assert "range" == toTest.errors["longitude"]
    }

}