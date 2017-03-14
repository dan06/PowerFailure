/**
 *  Power Failure
 *
 *  Copyright 2014 Scottin Pollock
 *
 */
 
definition(
    name: "Power Failure",
    namespace: "dpvorster",
    author: "Daniel Vorster",
    description: "Turn off lights when the power is restored",
    category: "My Apps",
    iconUrl: "http://solutionsetcetera.com/stuff/STIcons/powerFailure.png",
    iconX2Url: "http://solutionsetcetera.com/stuff/STIcons/powerFailure@2x.png"
)


preferences {
	section("When power is restrored...") {
		input "offSwitches", "capability.switch", title: "Turn these off", required: true, multiple: true
	}
    section("But only if..."){
    	input "monitorSwitch", "capability.switch", title: "This switch is turned on", required: true, multiple: false
    }
}

def installed() {
	init()
}

def updated() {
	unsubscribe()
	init()
}

def init() {
	subscribe(offSwitches, "switch.on", switchedOn)
}

def switchedOn(evt) {
	if ( offSwitches ) {
        if (monitorSwitch.state == 
    	offSwitches.off()
    }
    
    if ( onSwitches ) {
    	log.debug "restoring Hues"
        def ss = getSunriseAndSunset()
        def now = new Date()
		def dark = ss.sunset
        if ( dark.before(now) ) {
    		onSwitches.on()
        }    
    }
    
    if ( onAllwaysSwitches ) {
    	log.debug "Always turn on"
    	onAllwaysSwitches.on()
	}
}
