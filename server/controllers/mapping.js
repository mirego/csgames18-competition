const mappingGateway = require('../gateways/mapping')
const util = require('../util/util')

module.exports = {

    getAllTypes(req, res) {
        const possibleOptionsResponse = util.respondToOptionsMethod(req, res)
        if (possibleOptionsResponse) return possibleOptionsResponse

        let allTypes = mappingGateway.findAllTypes()
        return res.json(allTypes)
    }
}
