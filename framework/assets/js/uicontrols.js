function UIControls() {
	this.menuItems = [];
	this.tabBarTag = 0;
	this.tabBarCallbacks = {};
}

UIControls.prototype.createMenu = function(menuItems) {
	
	UIControlsHook.createMenu( JSON.stringify(this.menuItems) );
	
}

UIControls.prototype.addItem = function(title, icon, win) {
	
	var tag = this.tabBarTag++;
	
	this.menuItems.push({
		id: tag,
		title: title,
		icon: icon
	});
	
	if (typeof(win) == 'function') {
		this.tabBarCallbacks[tag] = win;
	}
}

/**
 * Function called when a tab bar item has been selected.
 * @param {Number} tag the tag number for the item that has been selected
 */
UIControls.prototype.tabBarItemSelected = function(tag) {
	if (typeof(this.tabBarCallbacks[tag]) == 'function')
		this.tabBarCallbacks[tag]();
};

PhoneGap.addConstructor(function() {
    if (typeof navigator.uicontrols == "undefined") navigator.uicontrols = new UIControls();
});