const jsonParser = require('../util/jsonParser')

module.exports = {
    findAllTypes() {
        let types = []
        let mapping = jsonParser.read_mapping_json()
        mapping.map((part) => {
            if (!types.includes(part.type)) {
                types.push(part.type)
            }
        })
        return types
    },

    findAllParts() {
        return jsonParser.read_mapping_json()
    },
}
