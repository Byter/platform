package com.visionarysoftwaresolutions.restaurant.menu

import grails.test.mixin.*
import org.junit.*

@TestFor(MenuItemController)
@Mock([MenuItem])
class MenuItemControllerTests {
    
    void testShowingAMenuItem() {
        def item = FakeFactory.createMenuItem()
        item.save()
        this.controller.params.id = "1"
        def model = this.controller.show()
        assert "Krazy Karla's Chicken" == model["menuItemInstance"]?.name
        
    }
    
    void testRetrievingMenuItemPicture(){
        def item = FakeFactory.createMenuItem()
        item.image = [127, 20, 21, 46, 72, 42] as byte[]
        item.save()
        this.controller.params.id = "1"
        this.controller.picture()
        assert item.image == this.controller.response.contentAsByteArray
    }
    
    void testRetrievingMenuItemBarCode(){
        def item = FakeFactory.createMenuItem()
        item.barCode = [127, 20, 21, 46, 72, 42] as byte[]
        item.save()
        this.controller.params.id = "1"
        this.controller.barCode()
        assert item.barCode == this.controller.response.contentAsByteArray
    }
}
