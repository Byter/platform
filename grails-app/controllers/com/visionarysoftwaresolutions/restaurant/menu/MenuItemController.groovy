package com.visionarysoftwaresolutions.restaurant.menu

class MenuItemController {

    def index = { }
    
    def show = {
        def menuItem = MenuItem.get(params.id)
        [ menuItemInstance : menuItem ]
    }
    
    def picture = {
        def menuItem = MenuItem.get(params.id)
        response.outputStream << menuItem.image
    }
    
    def barCode = {
        def menuItem = MenuItem.get(params.id)
        response.outputStream << menuItem.barCode
    }
}
