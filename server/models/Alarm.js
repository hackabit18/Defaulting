var mongoose = require('mongoose');

var medDataSchema = new mongoose.Schema({
    'drug-name': {
        type: String
    },
    'drug-data': {
        type: Schema.Types.ObjectId,
        ref: 'drug-name'
    },
    'times': {
        type: [{
            time: {type: String},
            isOn: {type: Boolean}
        }]
    },
    'days': {
        type: Number
    }
})

var MedData = mongoose.model('MedData', medDataSchema);

module.exports = MedData;