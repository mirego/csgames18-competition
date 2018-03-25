const fs = require('fs')
const path = require('path')

module.exports = {
    read_mapping_json() {
        return JSON.parse(fs.readFileSync(path.resolve(__dirname, '../data/newMapping.json'), 'utf8'))
    },

    write_mapping_json(mapping_json) {
        fs.writeFileSync(path.resolve(__dirname, '../data/newMapping.json'), JSON.stringify(mapping_json), 'utf8')
    }
}
