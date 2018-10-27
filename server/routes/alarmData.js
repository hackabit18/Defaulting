var express = require('express');
var router = express.Router();
var db = require('../models');


/* GET med data. */
router.get('/', function(req, res, next) {
  db.Alarm.find()
    .then(function(alarm) {
      alarm = alarm.reverse();
      res.json(alarm);
    })
    .catch(function(err) {
        res.send(err);
  })
});

/* POST med data. */
router.post('/createAlarm', function(req, res, next) {
  db.Alarm.create(req.body)
      .then(function(newAlarm) {
          res.json({newAlarm})
      })
      .catch(function(err) {
          res.send(err);
      })
});

module.exports = router;
