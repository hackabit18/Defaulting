var express = require('express');
var router = express.Router();
var db = require('../models');


/* GET med data. */
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

/* POST med data. */
// router.post('/', function(req, res, next) {
//   db.MedData.create(req.body)
//       .then(function(newMedData) {
//           res.json({newMedData})
//       })
//       .catch(function(err) {
//           res.send(err);  
//       })
// });

module.exports = router;
