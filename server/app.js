var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var medDataRouter = require('./routes/medData');
var alarmDataRouter = require('./routes/alarmData');

var app = express();

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
// app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/medData', medDataRouter);
app.use('/alarmData', alarmDataRouter);

module.exports = app;
