const users = require('./controllers/users')
const mapping = require('./controllers/mapping')
const location = require('./controllers/location')

const BASE_URL = '/mirego/api/v1'

module.exports = (app) => {
    //////////
    // User //
    //////////
    app.get(BASE_URL + '/user', users.getAllUsers)

    app.get(BASE_URL + '/user/:userId', users.getUserById)
    // app.put(BASE_URL + '/user/:userId', users.modifyUserById)
    // app.delete(BASE_URL + '/user/:userId', users.deleteUserById)

    /////////////
    // Mapping //
    /////////////
    app.get(BASE_URL + '/mapping/types', mapping.getAllTypes)

    //////////////
    // Location //
    //////////////
    app.post(BASE_URL + '/distance', location.getDistanceBetweenPoints)
}

