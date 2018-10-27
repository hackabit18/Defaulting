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
  res.json({
    "_id": "5bd460a09c6abc8e8b6984ce",
    "when-ntb-taken": "Contraindicated in pregnant and breastfeeding women, neonates, patients with known hypersensitivity, and liver impairment.",
    "dosage": "Dosing of ALBENZA (albendazole) will vary, depending upon which of the following parasitic infections is being treated.Hydatid cyst disease:\nPO- \nAdults >= 60 kg: 400 mg twice daily with meals for 28 days followed by a 14-day drug-free period. Repeat for 2 more cycles. For inoperable hydatid cysts, up to 5 cycles may be given. \nAdults < 60 kg: 15 mg/kg/day  (not to exceed 800 mg/day) given in two divided doses for 28 days followed by a 14-day drug-free period. Repeat as above. \nChildren >= 6 years: 15 mg/kg/day  given in two divided doses for 28 days, followed by a 14-day drug-free period. Repeat as above.\n\nCysticercosis or neurocysticercosis (larval form of T. solium):\n\nPO- \nAdults >= 60 kg: 400 mg  twice daily for 8 - 30 days. Treatment may be repeated as necessary. \nAdults < 60 kg: 15 mg/kg/day  given in two divided doses for 8 - 30 days. Maximum dosage 800 mg/day. Treatment may be repeated as necessary. \nChildren >= 6 years: 15 mg/kg/day given in two divided doses for for 8 - 30 days. Maximum dosage 800 mg/day. Treatment may be repeated as necessary.\nCapillariasis: \nPO- \nAdults and Children >= 2 years: 200 mg  twice daily for 10 days. \nCutaneous larva migrans: \nPO- \nAdults: 400mg once daily for 3 days. \nChildren: 5 mg/kg/day for 3 days. \nGiardiasis: \nPO- \nAdults: 400 mg once daily for 3 days. \nMicrosporidiosis  including Septata intestinalis infection: \nPO- \nAdults: 400 mg  twice daily to 1600 mg  twice daily has been used. Average dosage 800 mg  twice daily. May take up to 2 months to see effect. \nIntestinal parasites in immigrants: \nPO- \nAdults: 400 mg once daily for 5 days. \nStrongyloidiasis  or taeniasis  (tapeworm infection): \nPO- \nAdults and Children >= 2 years: 400 mg  once daily for 3 days. May repeat course in 3 weeks. \nChildren < 2 years: 200 mg once daily for 3 days. May repeat course in 3 weeks. \nTrichinosis: \nPO- \nAdults: 400 mg twice daily for 15 days. \nTrichostrongyliasis: \nPO- \nAdults: 400 mg  as a single dose. \n",
    "side-effects": "Central Nervous System:\nHeadache, seizures, confusion, raised intracranial pressure, dizziness/vertigo, signs that meningeal sings.\n\nSkin:\nReversible hair loss, infection, hypersensitivity including rash and hives, allergic reactions. \n\nGastrointestinal:\nAbdominal pain, nausea/vomiting. \n\nGenitourinary:\nAcute kidney failure, dark urine. \n\nLiver:\nAbnormal Liver Function Tests, inflammation of liver\n\nBlood:\nDecrease in the number of white blood cells, thrombocytes, hemoglobin. \n\nMiscellaneous:\nFever.\n",
    "drug-name": "X Worm"
})
  // db.MedData.find()
  //   .then(function(medData) {
  //     medData = medData.reverse();
  //     res.json(medData);
  //   })
  //   .catch(function(err) {
  //       res.send(err);
  // })
});

module.exports = router;
