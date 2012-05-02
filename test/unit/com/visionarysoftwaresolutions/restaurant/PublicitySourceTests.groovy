package com.visionarysoftwaresolutions.restaurant

import grails.test.mixin.*
import org.junit.*

@TestFor(PublicitySource)
class PublicitySourceTests  {
    PublicitySource toTest

    void testValidPublicitySource() {
        toTest= new PublicitySource(name:"Twitter", url:"http://www.twitter.com")
        mockForConstraintsTests(PublicitySource, [toTest])
        assert toTest.validate()

    }

    void testBlankName() {
        toTest= new PublicitySource(name:"", url:"http://www.twitter.com")
        mockForConstraintsTests(PublicitySource, [toTest])
        assert !toTest.validate()
        assert "blank" == toTest.errors["name"]
    }

    void testNonUniqueName() {
        toTest= new PublicitySource(name:"Twitter", url:"http://www.twitter.com")
        PublicitySource test2 = new PublicitySource(name:"Twitter", url:"http://www.hackme.com")
        mockForConstraintsTests(PublicitySource, [toTest, test2])
        assert !toTest.validate()
        assert "unique" == toTest.errors["name"]
    }

    void testNonBadUrl() {
        toTest= new PublicitySource(name:"Twitter", url:"fuck me")
        mockForConstraintsTests(PublicitySource, [toTest])
        assert !toTest.validate()
        assert "url" == toTest.errors["url"]
    }
}
