const bcrypt = require('bcrypt')
const util = require('../util/util')

const userGateway = require('../gateways/users')
const SALT_ROUNDS = 10

function _ensureNecessaryUserProperties(user, res) {
    if (!user || !user.username || !user.password) {
        return res.status(422).send({"message":"You must supply the 'username' and 'password fields'"})
    }
}

module.exports = {
    register(req, res) {
        const possibleOptionsResponse = util.respondToOptionsMethod(req, res)
        if (possibleOptionsResponse) return possibleOptionsResponse

        let user = req.body
        const possibleEarlyReturn = _ensureNecessaryUserProperties(user, res)
        if (possibleEarlyReturn) return possibleEarlyReturn

        userGateway.findUserByUsername(user.username, req, function(err, found_user) {
            if (found_user) {
                return res.status(422).send({'message':'That user already exists!'})
            }
            if (err) {
                return res.status(400).send({'message':'Unable to query for that user'})
            }

            let supplied_password = user.password.slice(0)
            bcrypt.hash(supplied_password, SALT_ROUNDS, function(err, hash) {
                user.password = hash
                user.createdTimestamp = new Date()
                user.lastUpdatedTimestamp = new Date()
                userGateway.createUser(user, req, function(err, result) {
                    if (err) {
                        return res.status(400).send({'message':'Unable to insert that user'})
                    }
                    delete result.password
                    req.session.username = user.username
                    res.send(result)
                })
            })
        })
    },

    login(req, res) {
        const possibleOptionsResponse = util.respondToOptionsMethod(req, res)
        if (possibleOptionsResponse) return possibleOptionsResponse

        var user = req.body
        const possibleEarlyReturn = _ensureNecessaryUserProperties(user)
        if (possibleEarlyReturn) return possibleEarlyReturn

        userGateway.findUserByUsername(user.username, req, function(err, found_user) {
            if (!found_user)
                return res.status(400).send({'message':'That user does not exist'})
            if (err)
                return res.status(400).send({'message':'Unable to query for that user'})

            bcrypt.compare(user.password, found_user.password, function(err, hash_result) {
                if (!hash_result || err) {
                    return res.status(400).send({'message':'Password does not match'})
                }
                delete found_user.password
                req.session.username = user.username
                res.send(found_user)
            })
        })
    },

    logout(req, res) {
        const possibleOptionsResponse = util.respondToOptionsMethod(req, res)
        if (possibleOptionsResponse) return possibleOptionsResponse

        req.session.username = null
        return res.status(200).send({'message':'Successfully logged out.'})
    }

}
