package com.visionarysoftwaresolutions.restaurant

import grails.test.mixin.*
import org.junit.*

import com.visionarysoftwaresolutions.location.*

@TestFor(Restaurant)
class RestaurantTests  {
    Restaurant toTest
    StreetAddress address = new StreetAddress(address:"3116 S. Mill Ave",
        city:"Tempe",
        region:'Arizona',
        postalCode:"85281",
        country:"USA"
    )
    ContactInformation contact = new ContactInformation(email:"visionary.software.solutions@gmail.com")
    Location helper = new Location(address:address, contact:contact, latitude:80, longitude:80)

    void testValidRestaurant() {
        toTest= new Restaurant(name:'QuickWok', schedule:"M-F 9:00 am - 5:00 pm", location:helper)
        mockForConstraintsTests(Restaurant, [toTest])
        assert toTest.validate()
    }

    void testInvalidBlankNameRestaurant(){
        toTest= new Restaurant(name:'', schedule:"M-F 9:00 am - 5:00 pm",location:helper)
        mockForConstraintsTests(Restaurant, [toTest])
        assert !toTest.validate()
        assert "blank" == toTest.errors['name']
    }
    void testInvalidConflictingRestaurants(){
        toTest= new Restaurant(name:'QuickWok', schedule:"M-F 9:00 am - 5:00 pm",location:helper)
        Restaurant test2 = new Restaurant(name:"QuickWok", schedule:"M-F 9:00 am - 5:00 pm",location:helper)
        mockForConstraintsTests(Restaurant, [toTest, test2])
        assert !toTest.validate()
        assert "unique" == toTest.errors['name']
        assert !test2.validate()
        assert "unique" == test2.errors['name']
    }

    void testInvalidRestaurantNoSchedule(){
        toTest= new Restaurant(name:'QuickWok',location:helper)
        mockForConstraintsTests(Restaurant, [toTest])
        assert !toTest.validate()
        assert "nullable" == toTest.errors["schedule"]
    }

    void testInvalidRestaurantNoLocation(){
        toTest= new Restaurant(name:'QuickWok')
        mockForConstraintsTests(Restaurant, [toTest])
        assert !toTest.validate()
        assert "nullable" == toTest.errors["location"]
    }
}
