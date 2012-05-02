package com.visionarysoftwaresolutions.location

import grails.test.mixin.*
import org.junit.*


@TestFor(ContactInformation)
class ContactInformationTests {
    ContactInformation toTest
    
    void testValidContactInformation() {
        Map moo = ["Twitter":'@swVisionary']
        toTest = new ContactInformation(phone:'4807207565',
            email:'ntv1534@gmail.com',
            virtualSpots:moo
        )
        mockForConstraintsTests(ContactInformation, [toTest])
        assert toTest.validate()
    }

    void testValidNullPhoneNumber() {
        Map moo = ["Twitter":'@swVisionary']
        toTest = new ContactInformation(
            email:'ntv1534@gmail.com',
            virtualSpots:moo
        )
        mockForConstraintsTests(ContactInformation, [toTest])
        assert toTest.validate()
    }

    void testValidNullEmail() {
        Map moo = ["Twitter":'@swVisionary']
        toTest = new ContactInformation(phone:'4807207565',
            virtualSpots:moo
        )
        mockForConstraintsTests(ContactInformation, [toTest])
        assert toTest.validate()
    }

    void testValidNullVirtualSpots() {
        toTest = new ContactInformation(phone:'4807207565',
            email:'ntv1534@gmail.com')
        mockForConstraintsTests(ContactInformation, [toTest])
        assert toTest.validate()
    }

    void testInvalidEmail(){
        Map moo = ["Twitter":'@swVisionary']
        toTest = new ContactInformation(phone:'4807207565',
            email:'fuck',
            virtualSpots:moo
        )
        mockForConstraintsTests(ContactInformation, [toTest])
        assert !toTest.validate()
        assert "email" == toTest.errors['email']
    }
}
