const mappingGateway = require('../gateways/mapping')

module.exports = {

    getAllTypes(req, res) {
        let allTypes = mappingGateway.findAllTypes()
        return res.json(allTypes)
    }
}
