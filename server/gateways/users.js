module.exports = {
    findUserByUsername(username, req, cb) {
        req.db.users.findOne({
            'username': username
        }, {}, cb)
    },

    findUserByIdWithoutPassword(id, req, cb) {
        req.db.users.findOne({
            '_id': id
        }, {
            password: 0
        }, cb)
    },

    findAllUsers(req, cb) {
        req.db.users.find({}, {password: 0}, cb)
    },

    createUser(user, req, cb) {
        req.db.users.insert(user, cb)
    }
}
