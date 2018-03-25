const express = require('express')
const session = require('express-session')
const nedb = require('nedb')
const bodyParser = require('body-parser')

// Configure application server
let app = express()
app.set('port', process.env.PORT || 3002)
app.use(session({
    // This secret is in plain-text just because
    // we're in a competition environment.
    // We realize that doing this in real life is
    // a major no-no.
    secret: process.env.SESSION_SECRET || 'JBOkrvMQMZvbIIvem0k6RwxxS3W9y3HluUv7UUqFT4JW8arQ4qvR3gfUmFdI',
    saveUninitialized: false
}))

// Initialize nedb, a simple JSON-file-based database
let db = {}
db.users = new nedb({
    filename: 'data/users.db',
    autoload: true
})
// Forward the database to the router
app.use(function (req, res, next) {
    req.db = db
    next()
})

// Configure HTTP body encodings
app.use(bodyParser.json({
    limit: '50mb'
}))
app.use(bodyParser.urlencoded({
    extended: false,
    limit: '50mb'
}))

// Setup routes and handlers for 400/500 status codes
const routes = require('./routes')(app)
app.use(function (req, res) {
    res.status(404).send('Route not found')
})
app.use(function (err, req, res) {
    res.status(err.status || 500).json({
        message: err.message,
        error: err
    })
})

// Run the server
const server = app.listen(app.get('port'), function () {
    console.log('Express server listening on localhost:' + server.address().port)
})
module.exports = app

