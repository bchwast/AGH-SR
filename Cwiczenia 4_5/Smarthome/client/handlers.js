const {Smarthome} = require("./generated/smarthome");
const stubs = require("./commons").stubs;
const devices = require("./commons").devices;
const servers = require("./commons").config;
const prompt = require("prompt-sync")();


const getStub = async (name, communicator) => {
    if (stubs[name]) {
        return stubs[name]
    }

    const {type, server} = devices[name]
    const proxy = communicator.stringToProxy(`${type}/${name} : ${servers[server]}`)
    let device

    switch (type) {
        case 'Bulbulator':
            device = await Smarthome.BulbulatorIPrx.checkedCast(proxy)
            break
        case 'CoffeeMaker':
            device = await Smarthome.CoffeeMakerIPrx.checkedCast(proxy)
            break
        case 'MusicPlayer':
            device = await Smarthome.MusicPlayerIPrx.checkedCast(proxy)
            break
        case 'Przyczlapnik':
            device = await Smarthome.PrzyczlapnikIPrx.checkedCast(proxy)
            break
        case 'Radio':
            device = await Smarthome.RadioIPrx.checkedCast(proxy)
            break
        case 'TeaMaker':
            device = await Smarthome.TeaMakerIPrx.checkedCast(proxy)
            break
        default:
            throw new Error(`Unsupported type: ${type}`)
    }

    stubs[name] = device
    return device
}

const handleCommonDeviceCommands = async (command, stub) => {
    switch (command) {
        case 'getState':
            console.log(await stub.getState())
            return true
        case 'turnOn':
            console.log(await stub.turnOn())
            return true
        case 'turnOff':
            console.log(await stub.turnOff())
            return true
    }
    return false
}

const handleDummyDeviceCommands = async (command, stub) => {
    switch (command) {
        case 'tellMeSomething':
            console.log(await stub.tellMeSomething())
            return true
        case 'whatIsThis':
            const something = prompt('Something: ')
            console.log(await stub.whatIsThis(something))
            return true

    }
    return false
}

const handleBeverageMakerCommands = async (command, stub) => {
    switch (command) {
        case 'getWaterLevel':
            console.log(await stub.getWaterLevel())
            return true
        case 'addWater':
            const amount = prompt('Amount: ')
            console.log(await stub.addWater(amount))
            return true
    }
    return false
}

const handleSpeakerCommands = async (command, stub) => {
    switch (command) {
        case 'play':
            console.log(await stub.play())
            return true
        case 'stop':
            console.log(await stub.stop())
            return true
        case 'getVolume':
            console.log(await stub.getVolume())
            return true
        case 'setVolume':
            const volume = prompt('Volume: ')
            console.log(await stub.setVolume(volume))
            return true
    }
}

const bulbulatorHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator)
    const command = prompt('Commands: getState, turnOn, turnOff, tellMeSomething, whatIsThis, getBulBul: ')

    if (await handleCommonDeviceCommands(command, stub)) return
    if (await handleDummyDeviceCommands(command, stub)) return

    switch (command) {
        case 'getBulBul':
            console.log(await stub.getBulbul())
            break
        default:
            console.log('Unknown command')
    }
}

const coffeeMakerHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator)
    const command = prompt('Commands: getState, turnOn, turnOff, getWaterLevel, addWater, getCurrentCoffee, setCoffee, makeCoffee, getCoffeeTypes): ')

    if (await handleCommonDeviceCommands(command, stub)) return
    if (await handleBeverageMakerCommands(command, stub)) return

    switch (command) {
        case 'getCurrentCoffee':
            console.log(await stub.getCurrentCoffee())
            break
        case 'setCoffee':
            const type = Smarthome.CoffeeType[prompt('Type: ')]
            if (!Smarthome.CoffeeType[type]) {
                console.log('Unknown coffee type')
                return
            }
            const strength = parseInt(prompt('Strength: '))
            console.log(await stub.setCoffee(new Smarthome.Coffee(type, strength)))
            break
        case 'makeCoffee':
            console.log(await stub.makeCoffee())
            break
        case 'getCoffeeTypes':
            console.log(await stub.getCoffeeTypes())
            break
        default:
            console.log('Unknown command')
    }
}

const musicPlayerHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator)
    const command = prompt('Commands: getState, turnOn, turnOff, play, stop, getVolume, setVolume, getSong, setSong: ')

    if (await handleCommonDeviceCommands(command, stub)) return
    if (await handleSpeakerCommands(command, stub)) return

    switch (command) {
        case 'getSong':
            console.log(await stub.getSong())
            break
        case 'setSong':
            const artist = prompt('Artist: ')
            const title = prompt('Title: ')
            console.log(await stub.setSong(new Smarthome.Song(artist, title)))
            break
        default:
            console.log('Unknown command')
    }
}

const przyczlapnikHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator)
    const command = prompt('Commands: getState, turnOn, turnOff, tellMeSomething, whatIsThis, getCzlapCzlap: ')

    if (await handleCommonDeviceCommands(command, stub)) return
    if (await handleDummyDeviceCommands(command, stub)) return

    switch (command) {
        case 'getCzlapCzlap':
            console.log(await stub.getCzlapCzlap())
            break
        default:
            console.log('Unknown command')
    }
}

const radioHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator)
    const command = prompt('Commands: getState, turnOn, turnOff, play, stop, getVolume, setVolume, getStation, setStation, getRadioStations: ')

    if (await handleCommonDeviceCommands(command, stub)) return
    if (await handleSpeakerCommands(command, stub)) return

    switch (command) {
        case 'getStation':
            console.log(await stub.getStation())
            break
        case 'setStation':
            const station = prompt('Station: ')
            if (!Smarthome.RadioStation[station]) {
                console.log('Unknown station')
                break
            }
            console.log(await stub.setStation(Smarthome.RadioStation[station]))
            break
        case 'getRadioStations':
            console.log(await stub.getRadioStations())
            break
        default:
            console.log('Unknown command')
    }
}

const teaMakerHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator)
    const command = prompt('Commands: getState, turnOn, turnOff, getWaterLevel, addWater, getCurrenTea, setTea, makeTea, getTeaTypes: ')

    if (await handleCommonDeviceCommands(command, stub)) return
    if (await handleBeverageMakerCommands(command, stub)) return

    switch (command) {
        case 'getCurrentTea':
            console.log(await stub.getCurrentTea())
            break
        case 'setTea':
            const type = Smarthome.TeaType[prompt('Type: ')]
            if (!Smarthome.TeaType[type]) {
                console.log('Unknown type')
                break
            }
            const strength = parseInt(prompt('Strength: '))
            console.log(await stub.setTea(new Smarthome.Tea(type, strength)))
            break
        case 'makeTea':
            console.log(await stub.makeTea())
            break
        case 'getTeaTypes':
            console.log(await stub.getTeaTypes())
            break
        default:
            console.log('Unknown command')
    }
}

exports.bulbulatorHandler = bulbulatorHandler
exports.coffeeMakerHandler = coffeeMakerHandler
exports.musicPlayerHandler = musicPlayerHandler
exports.przyczlapnikHandler = przyczlapnikHandler
exports.radioHandler = radioHandler
exports.teaMakerHandler = teaMakerHandler
