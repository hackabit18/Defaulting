var express = require('express');
var router = express.Router();
var db = require('../models');


/* GET home page. */
router.get('/', function(req, res, next) {
  db.MedData.find()
    .then(function(medData) {
      medData = medData.reverse();
      res.json(medData);
    })
    .catch(function(err) {
        res.send(err);
  })
});

module.exports = router;
