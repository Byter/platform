package com.visionarysoftwaresolutions.restaurant.nutrition

import grails.test.mixin.*
import org.junit.*

@TestFor(Serving)
class ServingTests {
    
    void testInvalidBlankServingName(){
        def toTest= new Serving(name:'', size:10)
        mockForConstraintsTests(Serving, [toTest])
        assert !toTest.validate()
        assert "blank" == toTest.errors['name']
    }

    void testInvalidNegativeServingSize(){
        def toTest= new Serving(name: 'fake', size:-1)
        mockForConstraintsTests(Serving, [toTest])
        assert !toTest.validate()
        assert "min" == toTest.errors['size']
    }
}
