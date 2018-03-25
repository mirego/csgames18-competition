const userGateway = require('../gateways/users')

module.exports = {

    getAllUsers(req, res) {
        userGateway.findAllUsers(req, function (e, users) {
            res.json(users)
        })
    },
    getUserById(req, res) {
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
