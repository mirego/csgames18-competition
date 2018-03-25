const userGateway = require('../gateways/users')
const util = require('../util/util')

module.exports = {

    getAllUsers(req, res) {
        const possibleOptionsResponse = util.respondToOptionsMethod(req, res)
        if (possibleOptionsResponse) return possibleOptionsResponse

        userGateway.findAllUsers(req, function (e, users) {
            res.json(users)
        })
    },
    getUserById(req, res) {
        const possibleOptionsResponse = util.respondToOptionsMethod(req, res)
        if (possibleOptionsResponse) return possibleOptionsResponse

        let userId = req.params.userId
        userGateway.findUserByIdWithoutPassword(userId, req, function (e, user) {
            if (user) {
                res.json(user)
            } else {
                res.status(404).send('User not found')
            }
        })
    }

}
