const mappingGateway = require('../gateways/mapping')
const util = require('../util/util')

module.exports = {

    getDistanceBetweenPoints(req, res) {
        const possibleOptionsResponse = util.respondToOptionsMethod(req, res)
        if (possibleOptionsResponse) return possibleOptionsResponse

        let currentPoint = req.body
        if (!currentPoint || !currentPoint.lat || !currentPoint.lon) {
            return res.status(422).send({"message":"You must supply the 'lat' and 'lon' fields for your point."})
        }

        let allParts = mappingGateway.findAllParts()
        let newParts = []
        for (part of allParts) {
            part.distance = Math.sqrt(part.lat*part.lat + part.lon*part.lon)
            newParts.push(part)
        }

        return res.json(newParts)
    }
}
