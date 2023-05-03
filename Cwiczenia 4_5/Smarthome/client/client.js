const Ice = require('ice').Ice
const Smarthome = require('./generated/smarthome').Smarthome
const prompt = require('prompt-sync')()
const devices = require('./commons').devices
const servers = require('./commons').config


const getDeviceList = async (communicator) => {
    const proxy = communicator.stringToProxy('deviceManager/Donald : ' + servers[1])
    const deviceManager = await Smarthome.DeviceManagerIPrx.checkedCast(proxy)
    const deviceList = await deviceManager.getDeviceList()
    deviceList.forEach((device) => {
        devices[device.name.toString()] = {type: device.type, server: device.server}
    })
    console.log('----- Devices -----')
    console.log(devices)
}


const main = async () => {
    const communicator = Ice.initialize()
    await getDeviceList(communicator)

    while (true) {
        console.log('Enter device name or "list" to list devices or "exit" to exit')
        deviceName = prompt('Device: ')

        if (deviceName === 'exit') {
            break
        }

        if (deviceName === 'list') {
            console.log(devices)
            continue
        }

        if (!devices[deviceName]) {
            console.log(`${deviceName} is unreachable`)
            continue
        }

        try {
            switch (devices[deviceName].type) {
                case 'Bulbulator':
                    await require('./handlers').bulbulatorHandler(deviceName, communicator)
                    break
                case 'CoffeeMaker':
                    await require('./handlers').coffeeMakerHandler(deviceName, communicator)
                    break
                case 'MusicPlayer':
                    await require('./handlers').musicPlayerHandler(deviceName, communicator)
                    break
                case 'Przyczlapnik':
                    await require('./handlers').przyczlapnikHandler(deviceName, communicator)
                    break
                case 'Radio':
                    await require('./handlers').radioHandler(deviceName, communicator)
                    break
                case 'TeaMaker':
                    await require('./handlers').teaMakerHandler(deviceName, communicator)
                    break
                default:
                    throw new Error(`Unsupported type: ${devices[deviceName].type}`)
            }
        } catch (e) {
            console.log(e)
        }
    }

    communicator.destroy()
}

main()
