package com.visionarysoftwaresolutions.location

class StreetAddress {
    String address
    String city
    String region
    String postalCode
    String country

    static constraints = {
        address()
        city()
        region()
        postalCode()
        country()
    }
}
