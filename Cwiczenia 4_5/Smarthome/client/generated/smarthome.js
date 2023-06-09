//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.9
//
// <auto-generated>
//
// Generated from file `smarthome.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

/* eslint-disable */
/* jshint ignore: start */

(function(module, require, exports)
{
    const Ice = require("ice").Ice;
    const _ModuleRegistry = Ice._ModuleRegistry;
    const Slice = Ice.Slice;

    let Smarthome = _ModuleRegistry.module("Smarthome");

    Smarthome.SmarthomeException = class extends Ice.UserException
    {
        constructor(message = "", _cause = "")
        {
            super(_cause);
            this.message = message;
        }

        static get _parent()
        {
            return Ice.UserException;
        }

        static get _id()
        {
            return "::Smarthome::SmarthomeException";
        }

        _mostDerivedType()
        {
            return Smarthome.SmarthomeException;
        }

        _writeMemberImpl(ostr)
        {
            ostr.writeString(this.message);
        }

        _readMemberImpl(istr)
        {
            this.message = istr.readString();
        }
    };

    const iceC_Smarthome_DeviceI_ids = [
        "::Ice::Object",
        "::Smarthome::DeviceI"
    ];

    Smarthome.DeviceI = class extends Ice.Object
    {
    };

    Smarthome.DeviceIPrx = class extends Ice.ObjectPrx
    {
    };

    Slice.defineOperations(Smarthome.DeviceI, Smarthome.DeviceIPrx, iceC_Smarthome_DeviceI_ids, 1,
    {
        "getState": [, 2, 2, , [1], , , , , ],
        "turnOn": [, , , , [1], , ,
        [
            Smarthome.SmarthomeException
        ], , ],
        "turnOff": [, , , , [1], , ,
        [
            Smarthome.SmarthomeException
        ], , ]
    });

    Smarthome.InvalidVolumeException = class extends Smarthome.SmarthomeException
    {
        constructor(message, _cause = "")
        {
            super(message, _cause);
        }

        static get _parent()
        {
            return Smarthome.SmarthomeException;
        }

        static get _id()
        {
            return "::Smarthome::InvalidVolumeException";
        }

        _mostDerivedType()
        {
            return Smarthome.InvalidVolumeException;
        }
    };

    const iceC_Smarthome_SpeakerI_ids = [
        "::Ice::Object",
        "::Smarthome::DeviceI",
        "::Smarthome::SpeakerI"
    ];

    Smarthome.SpeakerI = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                Smarthome.DeviceI
            ];
        }
    };

    Smarthome.SpeakerIPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                Smarthome.DeviceIPrx];
        }
    };

    Slice.defineOperations(Smarthome.SpeakerI, Smarthome.SpeakerIPrx, iceC_Smarthome_SpeakerI_ids, 2,
    {
        "play": [, 2, 2, , [1], , , , , ],
        "stop": [, 2, 2, , [1], , , , , ],
        "getVolume": [, 2, 2, , [3], , , , , ],
        "setVolume": [, 2, 2, , [1], [[3]], ,
        [
            Smarthome.InvalidVolumeException
        ], , ]
    });

    Smarthome.RadioStation = Slice.defineEnum([
        ['RadioMaryja', 0], ['RadioLosSantos', 1], ['NightFM', 2], ['WestCoastClassics', 3], ['TheBlueArk', 4],
        ['EnclaveRadio', 5]]);

    Smarthome.InvalidRadioStationException = class extends Smarthome.SmarthomeException
    {
        constructor(message, _cause = "")
        {
            super(message, _cause);
        }

        static get _parent()
        {
            return Smarthome.SmarthomeException;
        }

        static get _id()
        {
            return "::Smarthome::InvalidRadioStationException";
        }

        _mostDerivedType()
        {
            return Smarthome.InvalidRadioStationException;
        }
    };

    Slice.defineSequence(Smarthome, "radioStationsHelper", "Smarthome.RadioStation._helper", false);

    const iceC_Smarthome_RadioI_ids = [
        "::Ice::Object",
        "::Smarthome::DeviceI",
        "::Smarthome::RadioI",
        "::Smarthome::SpeakerI"
    ];

    Smarthome.RadioI = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                Smarthome.SpeakerI
            ];
        }
    };

    Smarthome.RadioIPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                Smarthome.SpeakerIPrx];
        }
    };

    Slice.defineOperations(Smarthome.RadioI, Smarthome.RadioIPrx, iceC_Smarthome_RadioI_ids, 2,
    {
        "getStation": [, 2, 2, , [Smarthome.RadioStation._helper], , , , , ],
        "setStation": [, 2, 2, , [1], [[Smarthome.RadioStation._helper]], ,
        [
            Smarthome.InvalidRadioStationException
        ], , ],
        "getRadioStations": [, 2, 2, , ["Smarthome.radioStationsHelper"], , , , , ]
    });

    Smarthome.Song = class
    {
        constructor(artist = "", title = "")
        {
            this.artist = artist;
            this.title = title;
        }

        _write(ostr)
        {
            ostr.writeString(this.artist);
            ostr.writeString(this.title);
        }

        _read(istr)
        {
            this.artist = istr.readString();
            this.title = istr.readString();
        }

        static get minWireSize()
        {
            return  2;
        }
    };

    Slice.defineStruct(Smarthome.Song, true, true);

    Smarthome.InvalidSongException = class extends Smarthome.SmarthomeException
    {
        constructor(message, _cause = "")
        {
            super(message, _cause);
        }

        static get _parent()
        {
            return Smarthome.SmarthomeException;
        }

        static get _id()
        {
            return "::Smarthome::InvalidSongException";
        }

        _mostDerivedType()
        {
            return Smarthome.InvalidSongException;
        }
    };

    const iceC_Smarthome_MusicPlayerI_ids = [
        "::Ice::Object",
        "::Smarthome::DeviceI",
        "::Smarthome::MusicPlayerI",
        "::Smarthome::SpeakerI"
    ];

    Smarthome.MusicPlayerI = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                Smarthome.SpeakerI
            ];
        }
    };

    Smarthome.MusicPlayerIPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                Smarthome.SpeakerIPrx];
        }
    };

    Slice.defineOperations(Smarthome.MusicPlayerI, Smarthome.MusicPlayerIPrx, iceC_Smarthome_MusicPlayerI_ids, 2,
    {
        "getSong": [, 2, 2, , [Smarthome.Song], , , , , ],
        "setSong": [, 2, 2, , [1], [[Smarthome.Song]], ,
        [
            Smarthome.InvalidSongException
        ], , ]
    });

    const iceC_Smarthome_DummyDeviceI_ids = [
        "::Ice::Object",
        "::Smarthome::DeviceI",
        "::Smarthome::DummyDeviceI"
    ];

    Smarthome.DummyDeviceI = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                Smarthome.DeviceI
            ];
        }
    };

    Smarthome.DummyDeviceIPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                Smarthome.DeviceIPrx];
        }
    };

    Slice.defineOperations(Smarthome.DummyDeviceI, Smarthome.DummyDeviceIPrx, iceC_Smarthome_DummyDeviceI_ids, 2,
    {
        "tellMeSomething": [, , , , [7], , , , , ],
        "whatIsThis": [, , , , [7], [[7]], , , , ]
    });

    const iceC_Smarthome_BulbulatorI_ids = [
        "::Ice::Object",
        "::Smarthome::BulbulatorI",
        "::Smarthome::DeviceI",
        "::Smarthome::DummyDeviceI"
    ];

    Smarthome.BulbulatorI = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                Smarthome.DummyDeviceI
            ];
        }
    };

    Smarthome.BulbulatorIPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                Smarthome.DummyDeviceIPrx];
        }
    };

    Slice.defineOperations(Smarthome.BulbulatorI, Smarthome.BulbulatorIPrx, iceC_Smarthome_BulbulatorI_ids, 1,
    {
        "getBulbul": [, , , , [7], , , , , ]
    });

    const iceC_Smarthome_PrzyczlapnikI_ids = [
        "::Ice::Object",
        "::Smarthome::DeviceI",
        "::Smarthome::DummyDeviceI",
        "::Smarthome::PrzyczlapnikI"
    ];

    Smarthome.PrzyczlapnikI = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                Smarthome.DummyDeviceI
            ];
        }
    };

    Smarthome.PrzyczlapnikIPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                Smarthome.DummyDeviceIPrx];
        }
    };

    Slice.defineOperations(Smarthome.PrzyczlapnikI, Smarthome.PrzyczlapnikIPrx, iceC_Smarthome_PrzyczlapnikI_ids, 3,
    {
        "getCzlapCzlap": [, , , , [7], , , , , ]
    });

    Smarthome.WaterOverflowException = class extends Smarthome.SmarthomeException
    {
        constructor(message, _cause = "")
        {
            super(message, _cause);
        }

        static get _parent()
        {
            return Smarthome.SmarthomeException;
        }

        static get _id()
        {
            return "::Smarthome::WaterOverflowException";
        }

        _mostDerivedType()
        {
            return Smarthome.WaterOverflowException;
        }
    };

    Smarthome.WaterUnderflowException = class extends Smarthome.SmarthomeException
    {
        constructor(message, _cause = "")
        {
            super(message, _cause);
        }

        static get _parent()
        {
            return Smarthome.SmarthomeException;
        }

        static get _id()
        {
            return "::Smarthome::WaterUnderflowException";
        }

        _mostDerivedType()
        {
            return Smarthome.WaterUnderflowException;
        }
    };

    const iceC_Smarthome_BeverageMakerI_ids = [
        "::Ice::Object",
        "::Smarthome::BeverageMakerI",
        "::Smarthome::DeviceI"
    ];

    Smarthome.BeverageMakerI = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                Smarthome.DeviceI
            ];
        }
    };

    Smarthome.BeverageMakerIPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                Smarthome.DeviceIPrx];
        }
    };

    Slice.defineOperations(Smarthome.BeverageMakerI, Smarthome.BeverageMakerIPrx, iceC_Smarthome_BeverageMakerI_ids, 1,
    {
        "getWaterLevel": [, 2, 2, , [3], , , , , ],
        "addWater": [, , , , [1], [[3]], ,
        [
            Smarthome.WaterOverflowException
        ], , ]
    });

    Smarthome.CoffeeType = Slice.defineEnum([
        ['Espresso', 0], ['Cappuccino', 1], ['Latte', 2], ['Americano', 3]]);

    Smarthome.Coffee = class
    {
        constructor(type = Smarthome.CoffeeType.Espresso, strength = 0)
        {
            this.type = type;
            this.strength = strength;
        }

        _write(ostr)
        {
            Smarthome.CoffeeType._write(ostr, this.type);
            ostr.writeInt(this.strength);
        }

        _read(istr)
        {
            this.type = Smarthome.CoffeeType._read(istr);
            this.strength = istr.readInt();
        }

        static get minWireSize()
        {
            return  5;
        }
    };

    Slice.defineStruct(Smarthome.Coffee, true, true);

    Smarthome.InvalidCoffeeException = class extends Smarthome.SmarthomeException
    {
        constructor(message, _cause = "")
        {
            super(message, _cause);
        }

        static get _parent()
        {
            return Smarthome.SmarthomeException;
        }

        static get _id()
        {
            return "::Smarthome::InvalidCoffeeException";
        }

        _mostDerivedType()
        {
            return Smarthome.InvalidCoffeeException;
        }
    };

    Slice.defineSequence(Smarthome, "coffeeTypesHelper", "Smarthome.CoffeeType._helper", false);

    const iceC_Smarthome_CoffeeMakerI_ids = [
        "::Ice::Object",
        "::Smarthome::BeverageMakerI",
        "::Smarthome::CoffeeMakerI",
        "::Smarthome::DeviceI"
    ];

    Smarthome.CoffeeMakerI = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                Smarthome.BeverageMakerI
            ];
        }
    };

    Smarthome.CoffeeMakerIPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                Smarthome.BeverageMakerIPrx];
        }
    };

    Slice.defineOperations(Smarthome.CoffeeMakerI, Smarthome.CoffeeMakerIPrx, iceC_Smarthome_CoffeeMakerI_ids, 2,
    {
        "getCurrentCoffee": [, 2, 2, , [Smarthome.Coffee], , , , , ],
        "setCoffee": [, 2, 2, , [1], [[Smarthome.Coffee]], ,
        [
            Smarthome.InvalidCoffeeException
        ], , ],
        "makeCoffee": [, 2, 2, , [Smarthome.Coffee], , ,
        [
            Smarthome.WaterUnderflowException
        ], , ],
        "getCoffeeTypes": [, 2, 2, , ["Smarthome.coffeeTypesHelper"], , , , , ]
    });

    Smarthome.TeaType = Slice.defineEnum([
        ['Black', 0], ['Green', 1], ['White', 2], ['Fruit', 3]]);

    Smarthome.Tea = class
    {
        constructor(type = Smarthome.TeaType.Black, strength = 0)
        {
            this.type = type;
            this.strength = strength;
        }

        _write(ostr)
        {
            Smarthome.TeaType._write(ostr, this.type);
            ostr.writeInt(this.strength);
        }

        _read(istr)
        {
            this.type = Smarthome.TeaType._read(istr);
            this.strength = istr.readInt();
        }

        static get minWireSize()
        {
            return  5;
        }
    };

    Slice.defineStruct(Smarthome.Tea, true, true);

    Smarthome.InvalidTeaException = class extends Smarthome.SmarthomeException
    {
        constructor(message, _cause = "")
        {
            super(message, _cause);
        }

        static get _parent()
        {
            return Smarthome.SmarthomeException;
        }

        static get _id()
        {
            return "::Smarthome::InvalidTeaException";
        }

        _mostDerivedType()
        {
            return Smarthome.InvalidTeaException;
        }
    };

    Slice.defineSequence(Smarthome, "teaTypesHelper", "Smarthome.TeaType._helper", false);

    const iceC_Smarthome_TeaMakerI_ids = [
        "::Ice::Object",
        "::Smarthome::BeverageMakerI",
        "::Smarthome::DeviceI",
        "::Smarthome::TeaMakerI"
    ];

    Smarthome.TeaMakerI = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                Smarthome.BeverageMakerI
            ];
        }
    };

    Smarthome.TeaMakerIPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                Smarthome.BeverageMakerIPrx];
        }
    };

    Slice.defineOperations(Smarthome.TeaMakerI, Smarthome.TeaMakerIPrx, iceC_Smarthome_TeaMakerI_ids, 3,
    {
        "getCurrentTea": [, 2, 2, , [Smarthome.Tea], , , , , ],
        "setTea": [, 2, 2, , [1], [[Smarthome.Tea]], ,
        [
            Smarthome.InvalidTeaException
        ], , ],
        "makeTea": [, 2, 2, , [Smarthome.Tea], , ,
        [
            Smarthome.WaterUnderflowException
        ], , ],
        "getTeaTypes": [, 2, 2, , ["Smarthome.teaTypesHelper"], , , , , ]
    });

    Smarthome.DeviceInfo = class
    {
        constructor(name = "", type = "", server = 0)
        {
            this.name = name;
            this.type = type;
            this.server = server;
        }

        _write(ostr)
        {
            ostr.writeString(this.name);
            ostr.writeString(this.type);
            ostr.writeInt(this.server);
        }

        _read(istr)
        {
            this.name = istr.readString();
            this.type = istr.readString();
            this.server = istr.readInt();
        }

        static get minWireSize()
        {
            return  6;
        }
    };

    Slice.defineStruct(Smarthome.DeviceInfo, true, true);

    Slice.defineSequence(Smarthome, "deviceListHelper", "Smarthome.DeviceInfo", false);

    const iceC_Smarthome_DeviceManagerI_ids = [
        "::Ice::Object",
        "::Smarthome::DeviceManagerI"
    ];

    Smarthome.DeviceManagerI = class extends Ice.Object
    {
    };

    Smarthome.DeviceManagerIPrx = class extends Ice.ObjectPrx
    {
    };

    Slice.defineOperations(Smarthome.DeviceManagerI, Smarthome.DeviceManagerIPrx, iceC_Smarthome_DeviceManagerI_ids, 1,
    {
        "getDeviceList": [, 2, 2, , ["Smarthome.deviceListHelper"], , , , , ]
    });
    exports.Smarthome = Smarthome;
}
(typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? module : undefined,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? require :
 (typeof WorkerGlobalScope !== "undefined" && self instanceof WorkerGlobalScope) ? self.Ice._require : window.Ice._require,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? exports :
 (typeof WorkerGlobalScope !== "undefined" && self instanceof WorkerGlobalScope) ? self : window));
