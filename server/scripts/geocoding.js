const fs = require('fs')
const path = require('path')

const geocoderConstructor = require('node-geocoder')
const mapping = require('../data/mapping.json')
let newMapping = []

const options = {
    provider: 'google',
    httpAdapter: 'https',

    // This secret is in plain-text just because
    // we're in a competition environment.
    // We realize that doing this in real life is
    // a major no-no.
    apiKey: 'AIzaSyALy9Rgf2e3imXaT8y6lhC1eJKySMxMYDY',
    formatter: null // 'gpx', 'string', ...
}

const geocoder = geocoderConstructor(options)

mapping.map((part) => {
    if ( part.address ) {
        geocoder.geocode(part.address, function(err, res) {
            if (res && res.length > 0) {
                part.lat = res[0].latitude
                part.lon = res[0].longitude
                newMapping.push(part)
            }
        })
    } else {
        newMapping.push(part)
    }
})

// No time to debug async issues!!
setTimeout(() => {
    console.log(newMapping)
    fs.writeFileSync(path.resolve(__dirname, '../data/newMapping.json'), JSON.stringify(newMapping), 'utf8')
}, 1000)

