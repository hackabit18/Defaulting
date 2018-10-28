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
	console.log("Request Recieved: ", req.body)
	db.MedData.find()
	.then(function(medData) {
		const badJson = req.body.ocr
		var correctJson = badJson.replace(/(['"])?([a-z0-9A-Z_]+)(['"])?:/g, '"$2": ');
		const ocrArr = JSON.parse(correctJson)
		medData.forEach(o => {
			ocrArr.forEach(scannedOcr => {
			if(
				o["drug-name"].toLowerCase().startsWith(scannedOcr.toLowerCase())  || 
				scannedOcr.toLowerCase().startsWith(o["drug-name"].toLowerCase())
			)
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
