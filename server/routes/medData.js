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
router.post('/', function(req, res, next) {
	db.MedData.find()
	.then(function(medData) {
		const ocrArr = JSON.parse(req.body.ocr)
		medData.forEach(o => {
			ocrArr.forEach(scannedOcr => {
			if(o["drug-name"].toLowerCase().startsWith(scannedOcr.toLowerCase()))
				return res.json(o);
			})
		})
		return res.json({null: null})
	})
	.catch(function(err) {
		res.send(err);
	})
});


module.exports = router;
