var express = require('express');
var path = require('path');

var indexRouter = require('./routes/index');
var medDataRouter = require('./routes/medData');
var alarmDataRouter = require('./routes/alarmData');

var app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.use('/', indexRouter);
app.use('/medData', medDataRouter);
app.use('/alarmData', alarmDataRouter);

module.exports = app;
