const mappingGateway = require('../gateways/mapping')

module.exports = {

    getDistanceBetweenPoints(req, res) {
        let currentPoint = req.body
        console.log(currentPoint)
        if (!currentPoint || !currentPoint.lat || !currentPoint.lon) {
            return res.status(422).send({"message":"You must supply the 'lat' and 'lon' fields for your point."})
        }

        let allParts = mappingGateway.findAllParts()
        let newParts = []
        for (part of allParts) {
            if (part.lat && part.lon) {
                part.distance = Math.sqrt(part.lat*part.lat + part.lon*part.lon)
                newParts.push(part)
            }
        }

        return res.json(newParts)
    }
}
