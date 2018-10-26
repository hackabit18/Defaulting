var mongoose = require('mongoose');

var medDataSchema = new mongoose.Schema({
    name: {
        type: String
    },
    date: {
        type: Date,
        default: Date.now
    },
    body: {
        type: String
    }
})

var MedData = mongoose.model('MedData', medDataSchema);

module.exports = MedData;