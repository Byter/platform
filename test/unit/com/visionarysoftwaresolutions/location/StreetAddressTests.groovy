package com.visionarysoftwaresolutions.location

import grails.test.mixin.*
import org.junit.*

@TestFor(StreetAddress)
class StreetAddressTests {
    StreetAddress toTest
    
    void testValidStreetAddress() {
        toTest = new StreetAddress(address:"3116 S. Mill Ave",
        city:"Tempe",
        region:'Arizona',
        postalCode:"85281",
        country:"USA")
        mockForConstraintsTests(StreetAddress, [toTest])
        assert toTest.validate()
    }
}
